<?php
include_once 'UserDetails.php';
class LoginDao {
	public function isValidUser($username, $password) {
		$q = mysql_query ( "SELECT * FROM user_registrations WHERE (username='$username' or email='$username') and password='$password' " );
		if (mysql_num_rows ( $q ) == 0) {
			return false;
		} else {
			$row = mysql_fetch_array ( $q );
			return $row;
		}
	}
	public function getUserProfileByFBId($facebookid) {
		$q = mysql_query ( "SELECT * FROM user_registrations WHERE facebookid = '$facebookid' and regtype=1" );
		if (mysql_num_rows ( $q ) == 0) {
			return false;
		} else {
			$row = mysql_fetch_array ( $q );
			$userDetailsDao = new UserDetails ();
			$data = $userDetailsDao->getUserDetails ( $row ['id'] );
			return $data;
		}
	}
	public function getUserProfileByGmailId($email) {
		
		$q = mysql_query ( "SELECT * FROM user_registrations WHERE email = '$email' and regtype=2" );
		if (mysql_num_rows ( $q ) == 0) {
			return false;
		} else {
			$row = mysql_fetch_array ( $q );
			$userDetailsDao = new UserDetails ();
			$data = $userDetailsDao->getUserDetails ( $row ['id'] );
			return $data;
		}
	}
}