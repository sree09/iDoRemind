<?php
header ( "Access-Control-Allow-Origin: *" );
header ( "Content-Type: application/json; charset=UTF-8" );
include_once 'db_connect.php';
$db = new DB_CONNECT ();

include_once 'dao/ReminderDao.php';

$reminderid = $_POST ['reminderid'];

$jsonarray = array ();
$reminderDao = new ReminderDao ();

$reminderDao->deleteReminder($reminderid);