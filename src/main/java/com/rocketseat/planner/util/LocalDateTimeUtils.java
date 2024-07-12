package com.rocketseat.planner.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeUtils {

    public static LocalDateTime stringToLocalDateTime(String string){
        return LocalDateTime.parse(string, DateTimeFormatter.ISO_DATE_TIME);
    }

}
