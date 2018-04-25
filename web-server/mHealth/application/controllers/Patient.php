<?php
defined('BASEPATH') OR exit('No direct script access allowed');


class Patient extends CI_Controller
{

	public function __construct()
	{
		parent::__construct();
		$this->load->model('Patient_model');
		$this->load->helper('url_helper');
	}


	public function index()
	{


		$data = array(
			'title' => 'Patient - Login'
		);


		$this->load->view('template/header',$data);
		$this->load->view('patient/login',$data);
		$this->load->view('template/footer',$data);

	}

//	public function view($slug = NULL){
//		$this->load->view('template/header');
////		$this->load->view('patient/login');
//		$this->load->view('template/footer');
//	}
}
