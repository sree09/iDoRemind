<?php
header ( "Access-Control-Allow-Origin: *" );
header ( "Content-Type: application/json; charset=UTF-8" );
include_once 'db_connect.php';
$db = new DB_CONNECT ();

include_once 'dao/GroupsDao.php';

$userid = $_POST ['userid'];

$groupsDao = new GroupsDao ();
$jsonarray = array ();

$query = $groupsDao->getGroupMembers ( $userid );


while ( $row = mysql_fetch_array ( $query ) ) {
	

	$data['id'] = $row['id'];
	$data['name'] = $row['name'];
	
	array_push ( $jsonarray, $data );
}
echo json_encode ( $jsonarray );