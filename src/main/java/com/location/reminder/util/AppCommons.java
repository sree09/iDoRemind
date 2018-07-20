package com.location.reminder.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AppCommons {

    public static int currenttimeinminutes() {

        String timeStamp = new SimpleDateFormat("hh:mm a").format(new Date());

        Calendar cal = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        Date date = null;
        try {
            date = sdf.parse(timeStamp);
            System.out.println(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        cal.setTime(date);

        int mins = cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE);

        return mins;
    }


    public static boolean timeinbetween(int starttime, int endtime) {

        boolean answer = false;
        int currenttime = currenttimeinminutes();



        if ((currenttime >= starttime && currenttime <= endtime)  ) {
            return true;
        } else {

            if(starttime > endtime){

                if(currenttime> starttime || currenttime>=0 && currenttime <endtime){
                    return true;
                }

            }

        }
        return false;

    }

    public static boolean dateinbetween(long startdate, long enddate) {

        boolean answer = false;
        long currenttime = System.currentTimeMillis() / 1000L;
        if (currenttime >= startdate && currenttime <= enddate) {
            return true;
        } else {
            return false
                    ;
        }
    }
}
