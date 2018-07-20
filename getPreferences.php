<?php
header ( "Access-Control-Allow-Origin: *" );
header ( "Content-Type: application/json; charset=UTF-8" );
include_once 'db_connect.php';
$db = new DB_CONNECT ();

include_once 'dao/EnterPreferencesDao.php';

$userid = $_POST ['userid'];

$enterPreferencesDao = new EnterPreferencesDao ();
$jsonarray = array ();

$query = $enterPreferencesDao->getPreferences ( $userid );

while ( $row = mysql_fetch_array ( $query ) ) {
	
	$data ['id'] = $row ['id'];
	$data ['preference'] = $row ['preference'];
	$data ['userid'] = $row ['userid'];
	
	array_push ( $jsonarray, $data );
}

echo json_encode ( $jsonarray );
