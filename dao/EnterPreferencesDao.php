<?php
class EnterPreferencesDao {
	public function addPreferences($userid, $preferences) {
		$query = mysql_query ( "delete from preferences where userid =$userid" );
		
		foreach ( $preferences as $preference ) {
			
			$query = mysql_query ( "insert into preferences(`userid`, `preference` ) values( '$userid', '$preference')" );
		}
	}
	
	public function getPreferences($userid) {
		$query = mysql_query ( "SELECT * FROM preferences where userid = $userid" ) or die ( mysql_error () );
		return $query;
	}
}