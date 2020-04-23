package org.worldbuild.project.utils;

import lombok.extern.log4j.Log4j2;
import org.worldbuild.project.constant.DateTime;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Log4j2
public class StringToDate {

    public static Date dateFromMonthAndYear(String date) {
        Date startDate=null;
        try {
            startDate = new SimpleDateFormat(DateTime.DATE_FORMATE_STRING_MMYY).parse(date);
        } catch (Exception e) {
            log.error("Exception- ",e);
        }
        return startDate;
    }

    public static Date userDateStringToDate(String userTimeZone, String userDateString) {
        log.info(userDateString);
        ZoneId userTzId = ZoneId.of(userTimeZone);
        ZoneId utcTzId = ZoneId.of("UTC");
        Date utcDate = null;
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern(DateTime.DATE_FORMATE_STRING_DDMMYY);
            LocalDateTime startLocalDate = LocalDateTime.from(LocalDate.parse(userDateString, df).atStartOfDay(userTzId));
            ZonedDateTime zonedStartDateTime = startLocalDate.atZone(userTzId);
            ZonedDateTime utcTzFrom = zonedStartDateTime.withZoneSameInstant(utcTzId);
            utcDate = Date.from(utcTzFrom.toInstant());
        } catch (Exception e) {
            log.error("Exception- ",e);
        }
        return utcDate;
    }

    public static Date userDateTimeStringToUTCDate(String userTimeZone, String userDateString) {
        ZoneId userTzId = ZoneId.of(userTimeZone);
        ZoneId utcTzId = ZoneId.of("UTC");
        Date utcDate = null;
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern(DateTime.DATETIME_FORMATE_STRING_HHMM);
            LocalDateTime startLocalDate = LocalDateTime.parse(userDateString, df);
            ZonedDateTime zonedStartDateTime = startLocalDate.atZone(userTzId);
            ZonedDateTime utcTzFrom = zonedStartDateTime.withZoneSameInstant(utcTzId);
            utcDate = Date.from(utcTzFrom.toInstant());
        } catch (Exception e) {
            log.error("Exception- ",e);
        }
        return utcDate;
    }
}
