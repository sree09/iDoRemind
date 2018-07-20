<?php
class SuggestionsDao {
	public function getSuggestions($userid) {
		$query = mysql_query ( "SELECT count(location_type) as count ,location_type FROM reminders where userid =$userid GROUP BY location_type HAVING count >= 1;" ) or die ( mysql_error () );
		return $query;
	}
}