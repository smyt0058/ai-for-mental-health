<?php
/**
 * Created by PhpStorm.
 * User: hardo91
 * Date: 2018-03-21
 * Time: 10:01 AM
 */

class Doctor_model extends CI_Model
{

//	public function __construct()
//	{
//		$this->load->database();
//	}


	public function read()
	{

		$query = $this->db->query("select * from doctor");
		return $query->result_array();

	}


	public function insert($data)
	{

		$this->name = $data['name'];

		return $this->db->insert('doctor', $this) ? 'Data is inserted successfully' : "Error has occured";

	}


	public function update($id, $data)
	{


		$this->name = $data['name'];

		return $this->db->update('doctor', $this, array('iddoctor' => $id)) ? "Data is updated successfully" : "Error has occurred";

	}


	public function delete($id)
	{

		return $this->db->query("delete from `doctor` where iddoctor = $id") ? "Data is deleted successfully" : "Error has occurred";

	}

}
