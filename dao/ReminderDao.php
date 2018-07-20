<?php
class ReminderDao {
	public function addReminder($userid, $latitude, $longitude, $title, $fromtime, $totime, $repeat, $repeatinterval, $reminderinfo, $location_type,$fromdate,$todate) {
		$query = mysql_query ( "insert into reminders(`userid`, `latitude`, `longitude`,`title`, `fromtime`, `totime`,`repeat`,`repeatinterval`,`reminderinfo`,`location_type`,`startdate`,`enddate` ) values( '$userid', '$latitude','$longitude','$title', '$fromtime','$totime', '$repeat','$repeatinterval','$reminderinfo','$location_type','$fromdate','$todate')" );
		
		$reminderid = mysql_insert_id ();
		
		$query = mysql_query ( "insert into alertreminders(`userid`, `reminderid`, `status` ) values( '$userid', '$reminderid','0')" );
		
		return mysql_affected_rows ();
	}
	public function loadReminders($userid) {
		$q = mysql_query ( "SELECT reminders.id,reminders.userid,reminders.latitude,reminders.longitude,reminders.title,reminders.fromtime,reminders.totime,reminders.repeat,reminders.repeatinterval,reminders.reminderinfo,alertreminders.status,alertreminders.remindedtime,user_registrations.name as remindercreatedby,reminders.startdate,reminders.enddate FROM reminders,alertreminders,user_registrations  WHERE reminders.id = alertreminders.reminderid and alertreminders.userid=user_registrations.id and  alertreminders.userid =$userid order by reminders.id desc" );
		
		return $q;
	}
	public function deleteReminder($reminderid) {
		$q = mysql_query ( "delete from reminders where id = $reminderid" );
		
		return $q;
	}
	public function updateReminderStatus($userid, $reminderid) {
		$time = time();
		$q = mysql_query ( "update alertreminders set status=1,remindedtime='$time' where userid=$userid and reminderid =$reminderid " );
		
		return $q;
	}
	
	public function getAssignedto($reminderid) {
		$q = mysql_query ( "SELECT GROUP_CONCAT(user_registrations.name) as users  FROM alertreminders,user_registrations  WHERE alertreminders.userid=user_registrations.id and  alertreminders.reminderid =$reminderid" );
		$row = mysql_fetch_array($q);
		
		return $row['users'];
	}
	
}