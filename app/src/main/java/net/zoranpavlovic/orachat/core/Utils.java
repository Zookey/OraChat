package net.zoranpavlovic.orachat.core;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by osx on 01/08/2017.
 */

public class Utils {

    public static String getDate(String inputDate){
        Date date = formatDateAsIso8601(inputDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String time = getTime((calendar.getTimeInMillis()));
        return time;
    }

    private static Date formatDateAsIso8601(final String inputDateAsString) {
        final DateFormat iso8601DateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+SSSS", Locale.ENGLISH);
        Date inputDate = null;
        try {
            inputDate = iso8601DateFormatter.parse(inputDateAsString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return inputDate;
    }

    private static String getTime(long miliseconds){
        int totalTimeInSeconds = (int) (miliseconds / 1000) / 60;
        long days = totalTimeInSeconds / 86400;
        long hours = totalTimeInSeconds / 3600;
        long hoursLeftover = totalTimeInSeconds % 3600;
        long minutes = hoursLeftover / 60;
        long seconds = totalTimeInSeconds - hours * 3600;
        seconds = seconds - minutes * 60;
        if(totalTimeInSeconds >= 86400){
            return days+" days ago";
        }
        else if(totalTimeInSeconds >= 3600 && totalTimeInSeconds < 86400){
            return hours+"hours ago";
        } else if(totalTimeInSeconds < 3600 && totalTimeInSeconds >= 60){
            return minutes+"minutes ago";
        } else{
            return seconds+"seconds ago";
        }
    }
}
