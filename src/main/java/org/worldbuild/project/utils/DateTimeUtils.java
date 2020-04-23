package org.worldbuild.project.utils;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.worldbuild.project.constant.DateTime;
import org.worldbuild.project.modal.DateRange;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;
import java.util.concurrent.TimeUnit;
@Log4j2
public class DateTimeUtils {

	public static DateRange getUTCDateRangeFromUserString(String userTimeZone, String dateRangeString) throws ParseException {
		String drs[] = StringUtils.split(dateRangeString, "-");
		drs[0] = StringUtils.removeEnd(drs[0], " ");
		drs[1] = StringUtils.removeStart(drs[1], " ");
		ZoneId userTzId = ZoneId.of(userTimeZone);
		ZoneId utcTzId = ZoneId.of("UTC");
		DateTimeFormatter df = DateTimeFormatter.ofPattern(DateTime.DATETIME_FORMATE_STRING_HHMM);
		LocalDateTime startLocalDate = LocalDateTime.parse(drs[0], df);
		LocalDateTime endLocalDate = LocalDateTime.parse(drs[1], df);
		ZonedDateTime zonedStartDateTime = startLocalDate.atZone(userTzId);
		ZonedDateTime zonedEndDateTime = endLocalDate.atZone(userTzId);
		ZonedDateTime utcTzFrom = zonedStartDateTime.withZoneSameInstant(utcTzId);
		ZonedDateTime utcTzTo = zonedEndDateTime.withZoneSameInstant(utcTzId);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTime.DATETIME_FORMATE_STRING_HHMM);
		SimpleDateFormat isoFormat = new SimpleDateFormat(DateTime.DATETIME_FORMATE_STRING_HHMM);
		isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date from = isoFormat.parse(formatter.format(utcTzFrom));
		Date to = isoFormat.parse(formatter.format(utcTzTo));
		return new DateRange(from, to);
	}


	public static List<Date> getDateRangeBucketFromDateRangeString(String userTimeZone, String dateRangeString, int interval, TimeUnit timeUnit) throws ParseException {
		DateRange dateRange = getUTCDateRangeFromUserString(userTimeZone, dateRangeString);
		List<Date> dateRangeBucket = new ArrayList<>();
		Date from = dateRange.getFrom();
		Date to = dateRange.getTo();
		while (from.before(to)) {
			dateRangeBucket.add(from);
			from = plusTimeInDate(from, interval, timeUnit);
		}
		dateRangeBucket.add(to);
		return dateRangeBucket;
	}


	public static String UTCDateToUserDateTimeRangeStringHHmm(String userTimeZone, Date dateUTCFrom, int interval, TimeUnit timeUnit) {
		String date = DateToString.UTCDateToUserDateTimeStringHHmm(userTimeZone, dateUTCFrom) + " - ";
		Date dateUTCTo = plusTimeInDate(dateUTCFrom, interval, timeUnit);
		date = date + DateToString.UTCDateToUserDateTimeStringHHmm(userTimeZone, dateUTCTo);
		return date;
	}

	public static Date plusTimeInDate(Date date, int duration, TimeUnit timeUnit) {
		long currentTime = date.getTime();
		switch (timeUnit) {
			case MILLISECONDS: {
				currentTime = currentTime + TimeUnit.MILLISECONDS.toMillis(duration);
				break;
			}
			case SECONDS: {
				currentTime = currentTime + TimeUnit.SECONDS.toMillis(duration);
				break;
			}
			case MINUTES: {
				currentTime = currentTime + TimeUnit.MINUTES.toMillis(duration);
				break;
			}
			case HOURS: {
				currentTime = currentTime + TimeUnit.HOURS.toMillis(duration);
				break;
			}
			case DAYS: {
				currentTime = currentTime + TimeUnit.DAYS.toMillis(duration);
				break;
			}
			default:
				break;
		}
		return new Date(currentTime);
	}

	public static Date minusTimeInDate(Date date, int duration,TimeUnit unit) {
		long currentTime = date.getTime();
		switch (unit) {
			case MILLISECONDS: {
				currentTime = currentTime - TimeUnit.MILLISECONDS.toMillis(duration);
				break;
			}
			case SECONDS: {
				currentTime = currentTime - TimeUnit.SECONDS.toMillis(duration);
				break;
			}
			case MINUTES: {
				currentTime = currentTime - TimeUnit.MINUTES.toMillis(duration);
				break;
			}
			case HOURS: {
				currentTime = currentTime - TimeUnit.HOURS.toMillis(duration);
				break;
			}
			case DAYS: {
				currentTime = currentTime - TimeUnit.DAYS.toMillis(duration);
				break;
			}
			default:
				break;
		}
		return new Date(currentTime);
	}

	public static Date setValueInDate(Date date, long value, TimeUnit timeUnit) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		switch (timeUnit) {
			case MILLISECONDS: {
				calendar.set(Calendar.MILLISECOND, (int) value);
				break;
			}
			case SECONDS: {
				calendar.set(Calendar.SECOND, (int) value);
				break;
			}
			case MINUTES: {
				calendar.set(Calendar.MINUTE, (int) value);
				break;
			}
			case HOURS: {
				calendar.set(Calendar.HOUR, (int) value);
				break;
			}
			case DAYS: {
				calendar.set(Calendar.DAY_OF_YEAR, (int) value);
				break;
			}
			default: {
				break;
			}
		}
		return calendar.getTime();
	}

	public static long currentJobStartEpochTime() {
		Date date=new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int minutes=date.getMinutes();
		if(minutes>=30) {
			date.setMinutes(30);
		}else {
			date.setMinutes(0);
		}
		date.setMinutes(date.getMinutes()-30);
		date=setValueInDate(date,0,TimeUnit.SECONDS);
		date=setValueInDate(date,0,TimeUnit.MILLISECONDS);
		return date.getTime();
	}

	public static long currentJobEndEpochTime() {
		Date date=new Date();
		int hours=date.getHours();
		int minutes=date.getMinutes();
		if(minutes>=30) {
			date.setMinutes(0);
			date.setHours(hours+1);
		}else {
			date.setMinutes(30);
		}
		date.setMinutes(date.getMinutes()-30);
		date=setValueInDate(date,0,TimeUnit.SECONDS);
		date=setValueInDate(date,0,TimeUnit.MILLISECONDS);
		return date.getTime();
	}

}
