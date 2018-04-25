<?php

/**
 * This file is part of ReactGuzzleRing.
 *
 ** (c) 2014 Cees-Jan Kiewiet
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
namespace WyriHaximus\React\Guzzle\HttpClient;

use Clue\React\Buzz\Browser;
use Clue\React\Buzz\Io\Sender;
use Clue\React\HttpProxy\ProxyConnector as HttpProxyClient;
use Clue\React\Socks\Client as SocksProxyClient;
use Psr\Http\Message\RequestInterface;
use React\Dns\Resolver\Resolver;
use React\EventLoop\LoopInterface;
use React\HttpClient\Client as HttpClient;
use React\Promise\Deferred;
use React\Socket\Connector;
use React\Socket\TimeoutConnector;
use React\Stream\ReadableStreamInterface;
use React\Stream\WritableResourceStream;
use ReflectionObject;

/**
 * Class RequestFactory
 *
 * @package WyriHaximus\React\Guzzle\HttpClient
 */
class RequestFactory
{
    /**
     *
     * @param RequestInterface $request
     * @param array $options
     * @param $resolver Resolver
     * @param HttpClient $httpClient
     * @param LoopInterface $loop
     * @return \React\Promise\Promise
     */
    public function create(
        RequestInterface $request,
        array $options,
        Resolver $resolver,
        HttpClient $httpClient,
        LoopInterface $loop
    ) {
        $options = $this->convertOptions($options);

        if (isset($options['delay'])) {
            $promise = \WyriHaximus\React\timedPromise($loop, $options['delay']);
        }
        if (!isset($promise)) {
            $promise = \WyriHaximus\React\futurePromise($loop);
        }
        
        return $promise->then(function () use (
            $request,
            $options,
            $httpClient,
            $loop
        ) {
            $sender = $this->createSender($options, $httpClient, $loop);
            return (new Browser($loop, $sender))
                ->withOptions($options)
                ->send($request)->then(function ($response) use ($loop, $options) {
                    if (!isset($options['sink'])) {
                        return \React\Promise\resolve($response);
                    }

                    return \React\Promise\resolve($this->sink($loop, $response, $options['sink']));
                });
        });
    }

    protected function sink($loop, $response, $target)
    {
        $deferred = new Deferred();
        $writeStream = fopen($target, 'w');
        $saveToStream = new WritableResourceStream($writeStream, $loop);

        $saveToStream->on(
            'end',
            function () use ($deferred, $response) {
                $deferred->resolve($response);
            }
        );

        $body = $response->getBody();
        if ($body instanceof ReadableStreamInterface) {
            $body->pipe($saveToStream);
        } else {
            $saveToStream->end($body->getContents());
        }

        return $deferred->promise();
    }

    /**
     * @param array $options
     * @param HttpClient $httpClient
     * @param LoopInterface $loop
     * @return Sender
     */
    protected function createSender(array $options, HttpClient $httpClient, LoopInterface $loop)
    {
        $connector = $this->getProperty($httpClient, 'connector');

        if (isset($options['proxy'])) {
            switch (parse_url($options['proxy'], PHP_URL_SCHEME)) {
                case 'http':
                    $connector = new Connector(
                        $loop,
                        [
                            'tcp' => new HttpProxyClient(
                                $options['proxy'],
                                $connector
                            ),
                        ]
                    );
                    break;
                case 'socks':
                case 'socks4':
                case 'socks4a':
                case 'socks5':
                    $connector = new Connector(
                        $loop,
                        [
                            'tcp' => new SocksProxyClient(
                                $options['proxy'],
                                $connector
                            ),
                        ]
                    );
                    break;
            }
        }

        if (isset($options['connect_timeout'])) {
            $connector = new TimeoutConnector($connector, $options['connect_timeout'], $loop);
        }

        return $connector;
    }

    /**
     * @param array $options
     * @return array
     */
    protected function convertOptions(array $options)
    {
        // provides backwards compatibility for Guzzle 3-5.
        if (isset($options['client'])) {
            $options = array_merge($options, $options['client']);
            unset($options['client']);
        }

        // provides for backwards compatibility for Guzzle 3-5
        if (isset($options['save_to'])) {
            $options['sink'] = $options['save_to'];
            unset($options['save_to']);
        }

        if (isset($options['allow_redirects'])) {
            $this->convertRedirectOption($options);
        }

        return $options;
    }

    protected function convertRedirectOption(&$options)
    {
        $option = $options['allow_redirects'];
        unset($options['allow_redirects']);

        if (is_bool($option)) {
            $options['followRedirects'] = $option;
            return;
        }

        if (is_array($option)) {
            if (isset($option['max'])) {
                $options['maxRedirects'] = $option['max'];
            }
            $options['followRedirects'] = true;
            return;
        }
    }

    /**
     * @param object $object
     * @param string $desiredProperty
     * @return mixed
     */
    protected function getProperty($object, $desiredProperty)
    {
        $reflection = new ReflectionObject($object);
        $property = $reflection->getProperty($desiredProperty);
        $property->setAccessible(true);
        return $property->getValue($object);
    }
}
