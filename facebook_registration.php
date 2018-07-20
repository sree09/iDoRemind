<?php
header ( "Access-Control-Allow-Origin: *" );
header ( "Content-Type: application/json; charset=UTF-8" );

$facebookid = $_POST ['facebookid'];
$name = $_POST ['name'];
$email = $_POST ['email'];

include_once 'db_connect.php';
include_once 'dao/LoginDao.php';
include_once 'dao/RegistrationsDao.php';

$jsonarray = array ();
$db = new DB_CONNECT ();
$loginDao = new LoginDao ();

$registrationsDao = new RegistrationsDao ();

$result = $loginDao->getUserProfileByFBId ( $facebookid );
if ($result == false) {
	
	$affected_rows = $registrationsDao->addUser ( 1, '', $facebookid, $name, $email );
	
	if ($affected_rows == 1) {
		
		$row_array ["success"] = "1";
		$row_array ["userid"] = mysql_insert_id ();
		$row_array ["email"] = $email;
		$row_array ["name"] = $name;
	} else {
		
		$row_array ["success"] = "0";
		$row_array ["failurereason"] = "Registration failed. Try again";
	}
} else {
	$row_array ["success"] = "1";
	$row_array ["userid"] = $result ['id'];
	$row_array ["email"] = $email;
	$row_array ["name"] = $name;
}

array_push ( $jsonarray, $row_array );
echo json_encode ( $jsonarray );