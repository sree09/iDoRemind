<?php
header ( "Access-Control-Allow-Origin: *" );
header ( "Content-Type: application/json; charset=UTF-8" );
include_once 'db_connect.php';
$db = new DB_CONNECT ();

include_once 'dao/ReminderDao.php';
include_once 'dao/GroupsDao.php';

$reminderid = $_POST ['reminderid'];
$ignoredby = $_POST ['ignoredby'];

$groupsDao = new GroupsDao ();

$reminderDao = new ReminderDao ();

$jsonarray = array ();

$query = $groupsDao->getGroupMembers ( $ignoredby );

while ( $row = mysql_fetch_array ( $query ) ) {
	
	$data ['id'] = $row ['id'];
	$data ['name'] = $row ['name'];
	
	$groupsDao->addSingleUsertoReminder( $data ['id'], $reminderid );
}

echo json_encode ( $jsonarray );