<?php

require_once __DIR__.'/vendor/autoload.php';

use DialogFlow\Client;
use DialogFlow\Model\Query;
use DialogFlow\Method\QueryApi;
use GuzzleHttp\HandlerStack;
use React\EventLoop\Factory;
use WyriHaximus\React\GuzzlePsr7\HttpClientAdapter;

$loop = Factory::create();
$reactGuzzle = new \GuzzleHttp\Client(array(
	'base_uri' => Client::API_BASE_URI . Client::DEFAULT_API_ENDPOINT,
	'timeout' => Client::DEFAULT_TIMEOUT,
	'connect_timeout' => Client::DEFAULT_TIMEOUT,
	'handler' => HandlerStack::create(new HttpClientAdapter($loop))
));

$client = new Client('0cfff9202b384dbeae89c1f5f6dfb7b6', new DialogFlow\HttpClient\GuzzleHttpClient($reactGuzzle));
$queryApi = new QueryApi($client);
$_GET['q']?$q = $_GET['q']:$q='';

$queryApi->extractMeaningAsync($q, array(
	"contexts" => array(),
	"query" => $q,
	"lang" => 'en',
	"sessionId" => "userid-".date("Ymd"),
	"timezone" => "America/New_York",
))->then(
	function ($meaning) {
		$response = new Query($meaning);
		$timestamp = $response->getTimestamp();
		$statuscode = $response->getStatus()->getCode();
		$errorMessage = $response->getStatus()->getErrorType();
		$speech = $response->getResult()->getFulfillment()->getSpeech();
		$new_resp = array(
			"access_token" => "TBB5wltKarRtKn5mUVZck9RxHePNN6Jo",
			"timestamp" => $timestamp,
			"statuscode" => $statuscode,
			"errorMessage" => $errorMessage,
			"message" => $speech
		);

		echo json_encode($new_resp);
	},
	function ($error) {
		echo $error;
	}
);

$loop->run();
