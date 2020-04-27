package org.worldbuild.core.utils;

import lombok.extern.log4j.Log4j2;
import org.worldbuild.core.constant.Timezone;
import org.worldbuild.core.constant.DateTime;

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

    public static Date userDateDDMMYYStringToUTCDate(String userTimeZone, String userDateString) {
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

    public static Date userDateTimeHHStringToUTCDate(String userTimeZone, String userDateString) {
        ZoneId userTzId = ZoneId.of(userTimeZone);
        ZoneId utcTzId = ZoneId.of("UTC");
        Date utcDate = null;
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern(DateTime.DATETIME_FORMATE_STRING_HH);
            LocalDateTime startLocalDate = LocalDateTime.parse(userDateString, df);
            ZonedDateTime zonedStartDateTime = startLocalDate.atZone(userTzId);
            ZonedDateTime utcTzFrom = zonedStartDateTime.withZoneSameInstant(utcTzId);
            utcDate = Date.from(utcTzFrom.toInstant());
        } catch (Exception e) {
            log.error("Exception- ",e);
        }
        return utcDate;
    }

    public static Date userDateTimeHHMMStringToUTCDate(String userTimeZone, String userDateString) {
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

    public static Date userDateTimeHHMMSSStringToUTCDate(String userTimeZone, String userDateString) {
        ZoneId userTzId = ZoneId.of(userTimeZone);
        ZoneId utcTzId = ZoneId.of("UTC");
        Date utcDate = null;
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern(DateTime.DATETIME_FORMATE_STRING_HHMMSS);
            LocalDateTime startLocalDate = LocalDateTime.parse(userDateString, df);
            ZonedDateTime zonedStartDateTime = startLocalDate.atZone(userTzId);
            ZonedDateTime utcTzFrom = zonedStartDateTime.withZoneSameInstant(utcTzId);
            utcDate = Date.from(utcTzFrom.toInstant());
        } catch (Exception e) {
            log.error("Exception- ",e);
        }
        return utcDate;
    }

    public static void test() {
        log.info("################################################################");
        Date date = dateFromMonthAndYear("JAN 2020");
        log.info("UTC_TZ dateFromMonthAndYear - " + date);
        Date date11 = userDateDDMMYYStringToUTCDate(Timezone.UTC_TZ,"26/04/2020");
        log.info("UTC_TZ userDateDDMMYYStringToUTCDate - " + date11);
        Date date12 = userDateDDMMYYStringToUTCDate(Timezone.IST_TZ,"26/04/2020");
        log.info("IST_TZ userDateDDMMYYStringToUTCDate - " + date12);
        Date date21 = userDateTimeHHStringToUTCDate(Timezone.UTC_TZ,"26/04/2020 19");
        log.info("UTC_TZ userDateTimeHHStringToUTCDate - " + date21);
        Date date22 = userDateTimeHHStringToUTCDate(Timezone.IST_TZ,"26/04/2020 19");
        log.info("IST_TZ userDateTimeHHStringToUTCDate - " + date22);
        Date date31 = userDateTimeHHMMStringToUTCDate(Timezone.UTC_TZ,"26/04/2020 19:30");
        log.info("UTC_TZ userDateTimeHHMMStringToUTCDate - " + date31);
        Date date32 = userDateTimeHHMMStringToUTCDate(Timezone.IST_TZ,"26/04/2020 19:30");
        log.info("IST_TZ userDateTimeHHMMStringToUTCDate - " + date32);
        Date date41 = userDateTimeHHMMSSStringToUTCDate(Timezone.UTC_TZ,"26/04/2020 19:30:20");
        log.info("UTC_TZ userDateTimeHHMMSSStringToUTCDate - " + date41);
        Date date42 = userDateTimeHHMMSSStringToUTCDate(Timezone.IST_TZ,"26/04/2020 19:30:20");
        log.info("IST_TZ userDateTimeHHMMSSStringToUTCDate - " + date42);
        log.info("################################################################");
    }
}
