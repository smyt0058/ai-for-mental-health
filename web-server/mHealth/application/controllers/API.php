<?php
defined('BASEPATH') OR exit('No direct script access allowed');


require(BASEPATH . 'libraries/REST_Controller.php');
require_once  BASEPATH.'../api_dialogflow/vendor/autoload.php';
use DialogFlow\Client;
use DialogFlow\Model\Query;
use DialogFlow\Method\QueryApi;


class api extends CI_REST_Controller
{

	private  $default = array(
		"status" => 404,
		"errormessage" => "Use proper api URL",
		"errorType" => "URL"
	);

	function __construct()
	{
		parent::__construct();
		$this->load->model('Patient_model');

	}

	function index_get(){
        $this->response($this->default, 200);
	}
	function index_post(){
        $this->response($this->default, 200);
	}
	function index_put(){
        $this->response($this->default, 200);
	}
	function index_delete(){
        $this->response($this->default, 200);
	}

	function auth_get()
	{
        if ((!$this->get('email')) && (!$this->get('password'))) {
            $this->response($this->default, 200);
        }
        $patient = $this->Patient_model->getAuth($this->get('email'), $this->get('password'));
		if ($patient) {
            $patient = (object)array_merge((array)$patient[0], array('status' => '200', 'errorType' => 'success'));
			$this->response($patient, 200); // 200 being the HTTP response code
		} else {
            $this->response($this->default, 200);
		}
	}

    function auth_post()
    {
        if ((!$this->post('email')) && (!$this->post('password'))) {
            $this->response($this->default, 200);
        }
        $patient = $this->Patient_model->getAuth($this->post('email'), $this->post('password'));
        if ($patient) {
            $patient = (object)array_merge((array)$patient[0], array('status' => '200', 'errorType' => 'success'));
            $this->response($patient, 200); // 200 being the HTTP response code
        } else {
            $this->response($this->default, 200);
        }
    }

	function chat_get(){
        if ((!$this->get('authkey')) && (!$this->get('query'))) {
            $this->response($this->default, 200);
        }else{
            try {

                $client = new Client(DIALOGFLOW_HARSH );
                $queryApi = new QueryApi($client);
                $meaning = $queryApi->extractMeaning($this->get('query'), array(
                    "lang" => 'en',
                    "sessionId" => "123213312",
                    "timezone" => "America/New_York",
                    'lang' => 'en'
                ));
                $response = new Query($meaning);
                $timestamp = $response->getTimestamp();
                $statuscode = $response->getStatus()->getCode();
                $errorMessage = $response->getStatus()->getErrorType();
                $speech = $response->getResult()->getFulfillment()->getSpeech();
                $new_resp = array(
                    "access_token" => $this->get('authkey'),
                    "timestamp" => $timestamp,
                    "statuscode" => $statuscode,
                    "errorMessage" => $errorMessage,
                    "message" => $speech
                );
                $this->response($new_resp,200);
            } catch (\Exception $error) {
                $this->response($error->getMessage(),200);
//                echo $error->getMessage();
            }
        }
    }


    function chat_post(){
        if ((!$this->post('authkey')) && (!$this->post('query'))) {
            $this->response($this->default, 200);
        }else{
            try {

                $client = new Client(DIALOGFLOW_HARSH );
                $queryApi = new QueryApi($client);
                $meaning = $queryApi->extractMeaning($this->post('query'), array(
                    "lang" => 'en',
                    "sessionId" => "123213312",
                    "timezone" => "America/New_York",
                    'lang' => 'en'
                ));
                $response = new Query($meaning);
                $timestamp = $response->getTimestamp();
                $statuscode = $response->getStatus()->getCode();
                $errorMessage = $response->getStatus()->getErrorType();
                $speech = $response->getResult()->getFulfillment()->getSpeech();
                $new_resp = array(
                    "access_token" => $this->post('authkey'),
                    "timestamp" => $timestamp,
                    "statuscode" => $statuscode,
                    "errorMessage" => $errorMessage,
                    "message" => $speech
                );
                $this->response($new_resp,200);
            } catch (\Exception $error) {
                $this->response($error->getMessage(),200);
//                echo $error->getMessage();
            }
        }
    }

//	function patient_get()
//	{
//
//		if (!$this->get('id')) {
//			$this->response(NULL, 401);
//		}
//		$patient = $this->Patient_model->get($this->get('id'));
//		if ($patient) {
//			$this->response($patient, 200); // 200 being the HTTP response code
//		} else {
//			$this->response(NULL, 404);
//		}
//	}

//	function patient_post()
//	{
//
//		$result = $this->Patient_model->update($this->post('id'), array(
//			'name' => $this->post('name'),
//			'email' => $this->post('email')
//		));
//		if ($result === FALSE) {
//			$this->response(array('status' => 'failed'));
//		} else {
//			$this->response(array('status' => 'success'));
//		}
//
//	}

//	function patients_get()
//	{
//		$patients = $this->Patient_model->get_all();
//		if ($patients) {
//			$this->response($patients, 200);
//		} else {
//			$this->response(NULL, 404);
//		}
//	}

}
