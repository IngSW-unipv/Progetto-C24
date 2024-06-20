package it.unipv.ingsfw.gasCorpCinema.utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeUtils {

	public static boolean isWithinValidTimeRange(String timeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            LocalTime time = LocalTime.parse(timeStr, formatter);
            LocalTime startTime = LocalTime.of(15, 0); // 15:00
            LocalTime endTime = LocalTime.of(23, 0);   // 23:00
            return !time.isBefore(startTime) && !time.isAfter(endTime);
        } catch (DateTimeParseException e) {
            return false;
        }
    }
	
	public static boolean isValidTimeFormat(String str) {
        return str.matches("\\d{1,2}:\\d{2}");
    }
	
}
