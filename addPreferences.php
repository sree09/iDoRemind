<?php
header ( "Access-Control-Allow-Origin: *" );
header ( "Content-Type: application/json; charset=UTF-8" );
include_once 'db_connect.php';
$db = new DB_CONNECT ();

include_once 'dao/EnterPreferencesDao.php';

$userid = $_POST ['userid'];
$preferences = $_POST ['preferences'];

$enterPreferencesDao = new EnterPreferencesDao ();
$jsonarray = array ();

$query = $enterPreferencesDao->addPreferences ( $userid, $preferences );

echo json_encode ( $jsonarray );

