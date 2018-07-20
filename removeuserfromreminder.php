<?php
header ( "Access-Control-Allow-Origin: *" );
header ( "Content-Type: application/json; charset=UTF-8" );
include_once 'db_connect.php';
$db = new DB_CONNECT ();

include_once 'dao/GroupsDao.php';

$reminderid = $_POST ['reminderid'];
$userid = $_POST ['userid'];

$groupsDao = new GroupsDao ();
$jsonarray = array ();

$query = $groupsDao->removeUserfromReminder ( $userid, $reminderid );

echo json_encode ( $jsonarray );
