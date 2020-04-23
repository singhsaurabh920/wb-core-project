package org.worldbuild.project.utils;

import lombok.extern.log4j.Log4j2;
import org.worldbuild.project.constant.DateTime;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

@Log4j2
public class DateToString {

    public static String UTCDateToUserDateStringV1(String userTimeZone, Date utcDate) {
        if (utcDate != null) {
            ZoneId utcTzId = ZoneId.of("UTC");
            ZoneId userTzId = ZoneId.of(userTimeZone);
            Instant instant = utcDate.toInstant();
            ZonedDateTime zonedDateTimeUTC = instant.atZone(utcTzId);
            DateTimeFormatter df1 = DateTimeFormatter.ofPattern(DateTime.DATE_FORMATE_STRING_DDMMYY);
            log.info(df1.format(zonedDateTimeUTC.toLocalDate()));
            ZonedDateTime zonedDateTimeUser = zonedDateTimeUTC.withZoneSameInstant(userTzId);
            DateTimeFormatter df2 = DateTimeFormatter.ofPattern(DateTime.DATE_FORMATE_STRING_DDMMYY);
            log.info(df2.format(zonedDateTimeUser.toLocalDate()));
            return df2.format(zonedDateTimeUser.toLocalDate());
        }
        return "--";
    }

    public static String UTCDateToUserDateString(String userTimeZone, Date utcDate) {
        if (utcDate != null) {
            SimpleDateFormat DATE_FORMATE = new SimpleDateFormat(DateTime.DATE_FORMATE_STRING_DDMMYY);
            DATE_FORMATE.setTimeZone(TimeZone.getTimeZone(userTimeZone));
            return DATE_FORMATE.format(utcDate);
        }
        return "--";
    }

    public static String UTCDateToUserDateTimeStringHHmm(String userTimeZone, Date utcDate) {
        if (utcDate != null) {
            SimpleDateFormat DATETIME_FORMATE_HHMM = new SimpleDateFormat(DateTime.DATETIME_FORMATE_STRING_HHMM);
            DATETIME_FORMATE_HHMM.setTimeZone(TimeZone.getTimeZone(userTimeZone));
            return DATETIME_FORMATE_HHMM.format(utcDate);
        }
        return "--";
    }

    public static String UTCDateToUserDateTimeStringHHmmss(String userTimeZone, Date utcDate) {
        if (utcDate != null) {
            SimpleDateFormat DATETIME_FORMATE_HHMMSS = new SimpleDateFormat(DateTime.DATETIME_FORMATE_STRING_HHMMSS);
            DATETIME_FORMATE_HHMMSS.setTimeZone(TimeZone.getTimeZone(userTimeZone));
            return DATETIME_FORMATE_HHMMSS.format(utcDate);
        }
        return "--";
    }
}
