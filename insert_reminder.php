<?php
header ( "Access-Control-Allow-Origin: *" );
header ( "Content-Type: application/json; charset=UTF-8" );
include_once 'db_connect.php';
include_once 'dao/ReminderDao.php';

$userid = $_POST ['userid'];
$latitude = $_POST ['latitude'];
$longitude = $_POST ['longitude'];
$title = $_POST ['title'];
$fromtime = $_POST ['fromtime'];
$totime = $_POST ['totime'];
$repeat = $_POST ['repeat'];
$repeatinterval = $_POST ['repeatinterval'];
$reminderinfo = $_POST ['reminderinfo'];

$location_type = $_POST ['location_type'];


$fromdate = $_POST ['fromdate'];
$todate = $_POST ['todate'];

$jsonarray = array ();
$db = new DB_CONNECT ();


$reminderDao = new ReminderDao ();
$reminderDao->addReminder($userid, $latitude, $longitude, $title, $fromtime, $totime, $repeat, $repeatinterval,$reminderinfo,$location_type,$fromdate,$todate);

