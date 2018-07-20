<?php
class GroupsDao {
	public function addGroup($userid, $otheruserid) {
		$query = mysql_query ( "insert into usergroups(`userid`, `otheruserid`) values($userid, $otheruserid)" );
	}
	public function getGroupMembers($userid) {
		$query = mysql_query ( "SELECT name,U.id FROM user_registrations U,usergroups G WHERE U.id = G.otheruserid and G.userid= $userid" ) or die ( mysql_error () );
		return $query;
	}
	public function deleteGroup($userid, $otheruserid) {
		$query = mysql_query ( "delete from usergroups where userid=$userid and otheruserid=$otheruserid" );
	}
	public function addUsertoReminder($userids, $reminderid) {
		// mysql_query ( "DELETE FROM alertreminders where reminderid = $reminderid" );
		foreach ( $userids as $userid ) {
			
			$query = mysql_query ( "insert into alertreminders(`userid`, `reminderid`, `status` ) values( '$userid', '$reminderid','0')" );
		}
	}
	public function addSingleUsertoReminder($userid, $reminderid) {
		// mysql_query ( "DELETE FROM alertreminders where reminderid = $reminderid" );
		$query = mysql_query ( "insert into alertreminders(`userid`, `reminderid`, `status` ) values( '$userid', '$reminderid','0')" );
	}
	public function removeUserfromReminder($userid, $reminderid) {
		$query = mysql_query ( "delete from alertreminders where userid=$userid and reminderid=$reminderid" );
	}
}