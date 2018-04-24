<?php

require_once __DIR__.'/vendor/autoload.php';

use DialogFlow\Client;

try {
	$_GET['q']?$q = $_GET['q']:$q='';
	$client = new Client('f94dd988f38a4f1582eb835c1186bf46');

	$query = $client->get('query', array(
		"contexts" => array(
			"welcome"
		),
		"query" => $q,
		"lang" => 'en',
		"sessionId" => "123213312",
		"timezone" => "America/New_York"
	));
	echo "q = $q <br>".PHP_EOL;
	echo $query->getBody();
	echo "<br>";
	echo "<br>";
	$response = json_decode((string) $query->getBody(), true);
	var_dump($response);
} catch (\Exception $error) {
	echo $error->getMessage();
}
