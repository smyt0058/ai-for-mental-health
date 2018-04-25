<?php
defined('BASEPATH') OR exit('No direct script access allowed');


class Doctor extends CI_Controller
{

	public function __construct()
	{
		parent::__construct();
		$this->load->model('Doctor_model');
		$this->load->helper('url_helper');
	}

	public function index()
	{
		$data = array(
			'title' => 'Doctor - Login'
		);

		$this->load->view('template/header',$data);
		$this->load->view('doctor/login',$data);
		$this->load->view('template/footer',$data);
	}

}
