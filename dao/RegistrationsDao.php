<?php
class RegistrationsDao {
	public function addUser($regtype, $gmailid, $facebookid, $name, $email) {
		$query = mysql_query ( "insert into user_registrations(`regtype`, `gmailid`, `facebookid`,`name`, `email` ) values( '$regtype', '$gmailid','$facebookid','$name','$email')" );
		
		return mysql_affected_rows ();
	}
}