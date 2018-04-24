<?php
/**
 * Created by PhpStorm.
 * User: hardo91
 * Date: 2018-03-21
 * Time: 9:50 AM
 */

class Patient_model extends CI_Model
{
    public function __construct()
    {
        $query = $this->db->query("select * from patient");
        return $query->result_array();
    }

    public function get($id = 0)
    {
        $this->db->select('name', 'email');
        $this->db->from('patient');
        $this->db->where("iduser = $id");
        return $this->db->get()->result_array();
    }

    public function getAuth($email, $password)
    {
        $this->db->select(array('name', 'email', 'authkey'));
        $this->db->from('patient');
        $this->db->where("email", $email);
        $this->db->where("password", $password);
        $this->db->where("enabled = 1");
        return $this->db->get()->result_array();
    }

    public function get_all()
    {
        $query = $this->db->query("select name, email from patient");
        return $query->result_array();
    }
}
