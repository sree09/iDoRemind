<?php
$data ['id'] = $row ['id'];
$data ['userid'] = $row ['userid'];
$data ['latitude'] = $row ['latitude'];
$data ['longitude'] = $row ['longitude'];
$data ['title'] = $row ['title'];
$data ['fromtime'] = $row ['fromtime'];
$data ['totime'] = $row ['totime'];
$data ['repeat'] = $row ['repeat'];
$data ['repeatinterval'] = $row ['repeatinterval'];
$data ['status'] = $row ['status'];
$data ['remindedtime'] = $row ['remindedtime'];

$userdetails = $userDetailsDao->getUserDetails ( $row ['userid'] );

$data ['remindercreatedby'] = $userdetails ['name'];

$data ['reminderinfo'] = $row ['reminderinfo'];
$data ['assignedto'] = $reminderDao->getAssignedto ( $row ['id'] );

$data ['startdate'] = $row ['startdate'];
$data ['enddate'] = $row ['enddate'];

$data ['shouldremind'] = 1;

$time = time ();
if ($data ['status'] == 1 && $time > $row ['remindedtime'] + 60 * 60) {
	
	$data ['status'] = 0;
}


