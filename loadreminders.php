<?php
header ( "Access-Control-Allow-Origin: *" );
header ( "Content-Type: application/json; charset=UTF-8" );
include_once 'db_connect.php';
include_once 'dao/ReminderDao.php';
include 'dao/UserDetails.php';

$userDetailsDao = new UserDetails ();

$userid = $_POST ['userid'];

$db = new DB_CONNECT ();

$reminderDao = new ReminderDao ();

$query = $reminderDao->loadReminders ( $userid );

$jsonarray = array ();

while ( $row = mysql_fetch_array ( $query ) ) {
	
	include 'reminder_template.php';
	
	array_push ( $jsonarray, $data );
}
echo json_encode ( $jsonarray );