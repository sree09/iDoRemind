<?php
header ( "Access-Control-Allow-Origin: *" );
header ( "Content-Type: application/json; charset=UTF-8" );
include_once 'db_connect.php';
$db = new DB_CONNECT ();

include_once 'dao/GroupsDao.php';

$userids = $_POST ['userids'];
$reminderid = $_POST ['reminderid'];

$groupsDao = new GroupsDao ();
$jsonarray = array ();

$query = $groupsDao->addUsertoReminder ( $userids, $reminderid );

echo json_encode ( $jsonarray );