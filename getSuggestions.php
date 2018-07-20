<?php
header ( "Access-Control-Allow-Origin: *" );
header ( "Content-Type: application/json; charset=UTF-8" );
include_once 'db_connect.php';
$db = new DB_CONNECT ();

include_once 'dao/SuggestionsDao.php';

$userid = $_POST ['userid'];

$suggestionsDao = new SuggestionsDao ();
$jsonarray = array ();

$query = $suggestionsDao->getSuggestions ( $userid );

while ( $row = mysql_fetch_array ( $query ) ) {
	
	if($row ['location_type']!=null && trim($row ['location_type'])!="") {
	
	$data ['preference'] = $row ['location_type'];
	
	array_push ( $jsonarray, $data );
	}
}

echo json_encode ( $jsonarray );
