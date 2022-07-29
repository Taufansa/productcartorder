package com.svc.order.util;

import lombok.extern.log4j.Log4j2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@Log4j2
public class GeneralUtil {

    public static String toCamelCase(String param) {
        String m = param;
        String g = m.substring(0, 1);

        return m.replaceFirst(g, g.toLowerCase(Locale.ROOT));
    }

    public static String getCurrentTimestamp(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
        df.setTimeZone(TimeZone.getTimeZone("GMT+7")); //set to indonesian time zone
        return df.format(new Date());
    }

    public static OffsetDateTime convertStringToDate(String timestamp){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        return OffsetDateTime.parse(timestamp, formatter);
    }

}
