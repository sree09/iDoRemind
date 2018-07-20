<?php
header ( "Access-Control-Allow-Origin: *" );
header ( "Content-Type: application/json; charset=UTF-8" );
include_once 'db_connect.php';
$db = new DB_CONNECT ();

include_once 'dao/GroupsDao.php';

$userid = $_POST ['userid'];
$otheruserid = $_POST ['otheruserid'];

$jsonarray = array ();

$groupsDao = new GroupsDao ();
$groupsDao->addGroup($userid, $otheruserid);


echo json_encode ( $jsonarray );