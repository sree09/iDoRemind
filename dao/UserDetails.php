<?php


class UserDetails
{
    public function getUserDetails ($userid)
    {
        
        $q = mysql_query(
                "SELECT * FROM user_registrations WHERE id=$userid");
        if (mysql_num_rows($q) == 0) {
            return false;
        } else {
            $row = mysql_fetch_array($q);
            return $row;
        }
    }
    
}