<?php
header ( "Access-Control-Allow-Origin: *" );
header ( "Content-Type: application/json; charset=UTF-8" );
include_once 'db_connect.php';
include_once 'dao/ReminderDao.php';

$userid = $_POST ['userid'];
$reminderid = $_POST ['reminderid'];

$db = new DB_CONNECT ();

$reminderDao = new ReminderDao ();

$reminderDao->updateReminderStatus($userid, $reminderid);
