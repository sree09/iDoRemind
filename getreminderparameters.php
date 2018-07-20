<?php
include 'dao/UserDetails.php';



while ($row = mysql_fetch_array($query)) {
	
	$data['id'] = $row['id'];
	$userid = $row['userid'];
	
	$userDetailsDao = new UserDetails ();
	$userdetails = $userDetailsDao->getUserDetails ( $userid );
	
	
	$data['remindercreatedby'] = $row['userid'];
	$data['title'] = $row['event_id'];
	$data['longitude'] = $row['event_id'];
	$data['latitude'] = $row['event_id'];
	$data['repeat'] = $row['event_id'];
	$data['repeatinterval'] = $row['event_id'];
	$data['totime'] = $row['event_id'];
	$data['fromtime'] = $row['event_id'];
	
}