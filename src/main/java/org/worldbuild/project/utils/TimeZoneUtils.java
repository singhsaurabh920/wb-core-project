package org.worldbuild.project.utils;


import lombok.extern.log4j.Log4j2;

import java.time.*;
import java.util.Date;


@Log4j2
public class TimeZoneUtils {

	/*################################################################################################################*/
	//                                         CURRENT DATE IN UTC
	/*################################################################################################################*/
	public static Date getCurrentDateInUTC() {
		Instant nowUtc = Instant.now();
		ZoneId utcTzId = ZoneId.of("UTC");
		LocalDateTime localDateTime=LocalDateTime.ofInstant(nowUtc, utcTzId);
	    return Date.from(localDateTime.atZone(utcTzId).toInstant());
	}

	/*################################################################################################################*/
	//                                         CURRENT DATE IN IST
	/*################################################################################################################*/
	public static Date getCurrentDateInIST() {
		Instant nowUtc = Instant.now();
		ZoneId utcTzId = ZoneId.of("UTC");
		ZoneId istTzId = ZoneId.of("Asia/Kolkata");
		LocalDateTime localDateTime=LocalDateTime.ofInstant(nowUtc, istTzId);
	    return Date.from(localDateTime.atZone(utcTzId).toInstant());
	}

	/*################################################################################################################*/
	//                                         CURRENT DATE IN ANY TIMEZONE
	/*################################################################################################################*/
	public static Date getCurrentDateInUserTimeZone(String userTimzezone){
		Instant nowUtc = Instant.now();
		ZoneId utcTzId = ZoneId.of("UTC");
		ZoneId userTzId = ZoneId.of(userTimzezone);
		LocalDateTime localDateTime=LocalDateTime.ofInstant(nowUtc, userTzId);
	    return Date.from(localDateTime.atZone(utcTzId).toInstant());
	}

	/*################################################################################################################*/
	//                        START OF THE DAY CURRENT DATE IN USER TIMEZONE
	/*################################################################################################################*/
	public static Date getSodDateInUserTimeZone(String userTimzezone){
		Instant nowUtc = Instant.now();
		ZoneId utcTzId = ZoneId.of("UTC");
		ZoneId userTzId = ZoneId.of(userTimzezone);
		LocalDateTime localDateTime=LocalDateTime.ofInstant(nowUtc, userTzId).toLocalDate().atStartOfDay();
		ZonedDateTime zonedDateTime=localDateTime.atZone(utcTzId);
	    return Date.from(zonedDateTime.toInstant());
	}

	/*################################################################################################################*/
	//                        END OF THE DAY CURRENT DATE IN USER TIMEZONE
	/*################################################################################################################*/
	public static Date getEodDateInUserTimeZone(String userTimzezone){
		Instant nowUtc = Instant.now();
		ZoneId utcTzId = ZoneId.of("UTC");
		ZoneId userTzId = ZoneId.of(userTimzezone);
		LocalDateTime localDateTime=LocalDateTime.ofInstant(nowUtc, userTzId).toLocalDate().atTime(LocalTime.MAX);
		ZonedDateTime zonedDateTime=localDateTime.atZone(utcTzId);
		return Date.from(zonedDateTime.toInstant());
	}

	/*################################################################################################################*/
	//                        START OF THE DAY BEFORE CURRENT DATE IN USER TIMEZONE
	/*################################################################################################################*/
	public static Date getSodDateBackInUserTimeZone(String userTimzezone, int days){
		Instant nowUtc = Instant.now();
		ZoneId utcTzId = ZoneId.of("UTC");
		ZoneId userTzId = ZoneId.of(userTimzezone);
		LocalDateTime localDateTime=LocalDateTime.ofInstant(nowUtc, userTzId).toLocalDate().atStartOfDay().minusDays(days);
		ZonedDateTime zonedDateTime=localDateTime.atZone(utcTzId);
	    return Date.from(zonedDateTime.toInstant());
	}

	/*################################################################################################################*/
	//                        END OF THE DAY BEFORE CURRENT DATE IN USER TIMEZONE
	/*################################################################################################################*/
	public static Date getEodDateBeforeInUserTimeZone(String userTimzezone, int days){
		Instant nowUtc = Instant.now();
		ZoneId utcTzId = ZoneId.of("UTC");
		ZoneId userTzId = ZoneId.of(userTimzezone);
		LocalDateTime localDateTime=LocalDateTime.ofInstant(nowUtc, userTzId).toLocalDate().atTime(LocalTime.MAX).minusDays(days);
		ZonedDateTime zonedDateTime=localDateTime.atZone(utcTzId);
	    return Date.from(zonedDateTime.toInstant());
	}

	/*################################################################################################################*/
	//                        START OF THE DAY DATE OF CURRENT DATE IN UTC OF ANY TIMEZONE
	/*################################################################################################################*/
	public static Date getUserDateSodDateInUTC(String userTimzezone) {
		Instant nowUtc = Instant.now();
		ZoneId utcTzId = ZoneId.of("UTC");
		ZoneId userTzId = ZoneId.of(userTimzezone);
		LocalDateTime localDateTime=LocalDateTime.ofInstant(nowUtc, userTzId).toLocalDate().atStartOfDay();
		ZonedDateTime zonedDateTime=localDateTime.atZone(userTzId);
	    return Date.from(zonedDateTime.toInstant());
	}

	/*################################################################################################################*/
	//                        END OF THE DAY DATE OF CURRENT DATE IN UTC OF ANY TIMEZONE
	/*################################################################################################################*/
	public static Date getUserDateEodDateInUTC(String userTimzezone) {
		Instant nowUtc = Instant.now();
		ZoneId utcTzId = ZoneId.of("UTC");
		ZoneId userTzId = ZoneId.of(userTimzezone);
		LocalDateTime localDateTime=LocalDateTime.ofInstant(nowUtc, userTzId).toLocalDate().atTime(LocalTime.MAX);
		ZonedDateTime zonedDateTime=localDateTime.atZone(userTzId);
	    return Date.from(zonedDateTime.toInstant());
	}

	/*################################################################################################################*/
	//                        START OF THE DAY BEFORE CURRENT DATE IN UTC OF ANY TIMEZONE
	/*################################################################################################################*/
	public static Date getUserDateSodBeforeDateInUTC(String userTimzezone, int days) {
		Instant nowUtc = Instant.now();
		ZoneId utcTzId = ZoneId.of("UTC");
		ZoneId userTzId = ZoneId.of(userTimzezone);
		LocalDateTime localDateTime=LocalDateTime.ofInstant(nowUtc, userTzId).toLocalDate().atStartOfDay().minusDays(days);
		ZonedDateTime zonedDateTime=localDateTime.atZone(userTzId);
		return Date.from(zonedDateTime.toInstant());
	}

	/*################################################################################################################*/
	//                        END OF THE DAY BEFORE CURRENT DATE IN UTC OF ANY TIMEZONE
	/*################################################################################################################*/
	public static Date getUserDateEodBeforeDateInUTC(String userTimzezone, int days) {
		Instant nowUtc = Instant.now();
		ZoneId utcTzId = ZoneId.of("UTC");
		ZoneId userTzId = ZoneId.of(userTimzezone);
		LocalDateTime localDateTime=LocalDateTime.ofInstant(nowUtc, userTzId).toLocalDate().atTime(LocalTime.MAX).minusDays(days);
		ZonedDateTime zonedDateTime=localDateTime.atZone(userTzId);
	    return Date.from(zonedDateTime.toInstant());
	}

	/*################################################################################################################*/
	//                        START OF THE DAY BEFORE FROM DATE IN UTC OF ANY TIMEZONE
	/*################################################################################################################*/	//start of the day before from date of any timezone time in utc
	public static Date getUserDateSodBeforeDateInUTC(String userTimzezone, int days, Date from) {
		Instant nowUtc = from.toInstant();
		ZoneId utcTzId = ZoneId.of("UTC");
		ZoneId userTzId = ZoneId.of(userTimzezone);
		LocalDateTime localDateTime=LocalDateTime.ofInstant(nowUtc, userTzId).toLocalDate().atStartOfDay().minusDays(days);
		ZonedDateTime zonedDateTime=localDateTime.atZone(userTzId);
		return Date.from(zonedDateTime.toInstant());
	}

	/*################################################################################################################*/
	//                        END OF THE DAY BEFORE FROM DATE IN UTC OF ANY TIMEZONE
	/*################################################################################################################*/
	//end of the day before from date of any timezone time in utc
	public static Date getUserDateEodBeforeDateInUTC(String userTimzezone, int days, Date from) {
		Instant nowUtc = from.toInstant();
		ZoneId utcTzId = ZoneId.of("UTC");
		ZoneId userTzId = ZoneId.of(userTimzezone);
		LocalDateTime localDateTime=LocalDateTime.ofInstant(nowUtc, userTzId).toLocalDate().atTime(LocalTime.MAX).minusDays(days);
		ZonedDateTime zonedDateTime=localDateTime.atZone(userTzId);
		return Date.from(zonedDateTime.toInstant());
	}

	/*################################################################################################################*/
	//                        START OF THE DAY AFTER CURRENT DATE IN UTC OF ANY TIMEZONE
	/*################################################################################################################*/
	//start of the day after current date of any timezone time in utc
	public static Date getUserDateSodAfterDateInUTC(String userTimzezone, int days) {
		Instant nowUtc = Instant.now();
		ZoneId utcTzId = ZoneId.of("UTC");
		ZoneId userTzId = ZoneId.of(userTimzezone);
		LocalDateTime localDateTime=LocalDateTime.ofInstant(nowUtc, userTzId).toLocalDate().atStartOfDay().minusDays(days);
		ZonedDateTime zonedDateTime=localDateTime.atZone(userTzId);
		return Date.from(zonedDateTime.toInstant());
	}

	/*################################################################################################################*/
	//                        END OF THE DAY AFTER CURRENT DATE IN UTC OF ANY TIMEZONE
	/*################################################################################################################*/
	//end of the day after current date of any timezone time in utc
	public static Date getUserDateEodAfterDateInUTC(String userTimzezone, int days) {
		Instant nowUtc = Instant.now();
		ZoneId utcTzId = ZoneId.of("UTC");
		ZoneId userTzId = ZoneId.of(userTimzezone);
		LocalDateTime localDateTime=LocalDateTime.ofInstant(nowUtc, userTzId).toLocalDate().atTime(LocalTime.MAX).minusDays(days);
		ZonedDateTime zonedDateTime=localDateTime.atZone(userTzId);
		return Date.from(zonedDateTime.toInstant());
	}

	/*################################################################################################################*/
	//                        START OF THE DAY AFTER FROM DATE IN UTC OF ANY TIMEZONE
	/*################################################################################################################*/	//start of the day after from date of any timezone time in utc
	public static Date getUserDateSodAfteDateInUTC(String userTimzezone, int days, Date from) {
		Instant nowUtc = from.toInstant();
		ZoneId utcTzId = ZoneId.of("UTC");
		ZoneId userTzId = ZoneId.of(userTimzezone);
		LocalDateTime localDateTime=LocalDateTime.ofInstant(nowUtc, userTzId).toLocalDate().atTime(LocalTime.MIN).plusDays(days);
		ZonedDateTime zonedDateTime=localDateTime.atZone(userTzId);
		return Date.from(zonedDateTime.toInstant());
	}

	/*################################################################################################################*/
	//                        END OF THE DAY AFTER FROM DATE IN UTC OF ANY TIMEZONE
	/*################################################################################################################*/
	public static Date getUserDateEodAfteDateInUTC(String userTimzezone, int days, Date from) {
		Instant nowUtc = from.toInstant();
		ZoneId utcTzId = ZoneId.of("UTC");
		ZoneId userTzId = ZoneId.of(userTimzezone);
		LocalDateTime localDateTime=LocalDateTime.ofInstant(nowUtc, userTzId).toLocalDate().atTime(LocalTime.MAX).plusDays(days);
		ZonedDateTime zonedDateTime=localDateTime.atZone(userTzId);
		return Date.from(zonedDateTime.toInstant());
	}
}
