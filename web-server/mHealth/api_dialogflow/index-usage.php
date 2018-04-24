<?php

require_once __DIR__.'/vendor/autoload.php';

use DialogFlow\Client;
use DialogFlow\Model\Query;
use DialogFlow\Method\QueryApi;

try {
    $q = ($_GET['q']?$_GET['q']:'');

    $client = new Client('0cfff9202b384dbeae89c1f5f6dfb7b6');
    $queryApi = new QueryApi($client);

    $meaning = $queryApi->extractMeaning($q, array(
//        'sessionId' => '1234567890',
        "lang" => 'en',
        "sessionId" => "123213312",
        "timezone" => "America/New_York",
        'lang' => 'en'
	));
    $response = new Query($meaning);
    //var_dump($response);
    echo json_encode($response);
} catch (\Exception $error) {
    echo $error->getMessage();
}
