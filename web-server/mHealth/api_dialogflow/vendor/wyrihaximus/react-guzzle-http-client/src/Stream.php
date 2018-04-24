<?php

/**
 * This file is part of ReactGuzzleRing.
 *
 ** (c) 2015 Cees-Jan Kiewiet
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
namespace WyriHaximus\React\Guzzle\HttpClient;

use Psr\Http\Message\StreamInterface;

/**
 * Class Stream
 *
 * @package WyriHaximus\React\RingPHP\HttpClient
 */
class Stream implements StreamInterface
{
    const OVERFLOW_LEVEL = 21632; // 2MB

    protected $stream;
    protected $eof = false;
    protected $size = 0;
    protected $buffer = '';
    protected $loop;
    protected $overflowStream;

    public function __construct(array $options)
    {
        $this->loop = $options['loop'];

        /*if (class_exists('React\Filesystem\Filesystem')) {
            $this->setUpFilesystemStream($options);
            return;
        }*/

        $this->setUpNormalStream($options);
    }

    protected function setUpFilesystemStream(array $options)
    {
        $this->overflowStream = new FileCacheStream([
            'loop' => $this->loop,
        ]);

        $options['response']->on(
            'data',
            function ($data) {
                if ($this->size >= static::OVERFLOW_LEVEL && $this->overflowStream->isOpen()) {
                    return $this->overflowStream->write($data);
                }

                if ($this->size >= static::OVERFLOW_LEVEL &&
                    !$this->overflowStream->isOpen() &&
                    !$this->overflowStream->isOpening()
                ) {
                    $this->overflowStream->open();
                }

                $this->buffer .= $data;
                $this->size = strlen($this->buffer);
            }
        );

        $options['request']->on(
            'end',
            function () {
                $this->eof = true;
            }
        );
    }

    protected function setUpNormalStream(array $options)
    {
        $options['response']->on(
            'data',
            function ($data) {
                $this->buffer .= $data;
                $this->size = strlen($this->buffer);
            }
        );

        $options['request']->on(
            'end',
            function () {
                $this->eof = true;
            }
        );
    }

    public function eof()
    {
        return $this->eof && $this->size === 0;
    }

    public function getSize()
    {
        return $this->size;
    }

    public function isReadable()
    {
        return true;
    }

    public function tell()
    {
        return false;
    }

    public function write($string)
    {
        return false;
    }

    public function rewind()
    {
        return false;
    }

    public function isWritable()
    {
        return false;
    }

    public function isSeekable()
    {
        return false;
    }

    public function seek($offset, $whence = SEEK_SET)
    {
        return false;
    }

    public function read($length)
    {
        $this->toTickOrNotToTick();

        if (strlen($this->buffer) <= $length) {
            $buffer = $this->buffer;
            $this->buffer = '';
            $this->size = 0;
            return $buffer;
        }

        $buffer = substr($this->buffer, 0, $length);
        $this->buffer = substr($this->buffer, $length);
        $this->size = strlen($this->buffer);
        return $buffer;
    }

    public function getContents($maxLength = -1)
    {
        $buffer = '';
        while (!$this->eof()) {
            $buffer .= $this->read(1000000);
        }
        return $buffer;
    }

    public function __toString()
    {
        return $this->getContents();
    }

    public function getMetadata($key = null)
    {
        $metadata = [
            'timed_out'     => '',
            'blocked'       => false,
            'eof'           => $this->eof,
            'unread_bytes'  => '',
            'stream_type'   => '',
            'wrapper_type'  => '',
            'wrapper_data'  => '',
            'mode'          => '',
            'seekable'      => false,
            'uri'           => '',
        ];

        if (!$key) {
            return $metadata;
        }

        return isset($metadata[$key]) ? $metadata[$key] : null;
    }

    public function attach($stream)
    {
    }

    public function detach()
    {
    }

    public function close()
    {
    }

    protected function toTickOrNotToTick()
    {
        if ($this->size === 0) {
            $this->loop->tick();
        }
    }

    /**
     * For Guzzle v4 compatibility
     */
    public function flush()
    {
    }
}
