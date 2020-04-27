package org.worldbuild.core.utils;


import lombok.extern.log4j.Log4j2;

import java.time.*;
import java.util.Date;


@Log4j2
public class TimeZoneUtils {

	public static String UTC_TZ = "UTC";
	public static String IST_TZ = "Asia/Kolkata";

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
	public static Date getSodDateBeforeInUserTimeZone(String userTimzezone, int days){
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


	public static void test(){
		log.info("################################################################");
		Date date1=TimeZoneUtils.getCurrentDateInUTC();
		log.info("getCurrentDateInUTC - "+date1);
		Date date2=TimeZoneUtils.getCurrentDateInIST();
		log.info("getCurrentDateInIST - "+date2);
		Date date31=TimeZoneUtils.getCurrentDateInUserTimeZone(UTC_TZ);
		log.info("UTC_TZ getCurrentDateInUserTimeZone - "+date31);
		Date date32=TimeZoneUtils.getCurrentDateInUserTimeZone(IST_TZ);
		log.info("IST_TZ getCurrentDateInUserTimeZone - "+date32);
		Date date41=getSodDateInUserTimeZone(UTC_TZ);
		log.info("UTC_TZ getSodDateInUserTimeZone - "+date41);
		Date date42=getSodDateInUserTimeZone(IST_TZ);
		log.info("IST_TZ getSodDateInUserTimeZone - "+date42);
		Date date51=getEodDateInUserTimeZone(UTC_TZ);
		log.info("UTC_TZ getEodDateInUserTimeZone - "+date51);
		Date date52=getEodDateInUserTimeZone(IST_TZ);
		log.info("IST_TZ getEodDateInUserTimeZone - "+date52);
		Date date61=getSodDateBeforeInUserTimeZone(UTC_TZ,1);
		log.info("UTC_TZ getSodDateBeforeInUserTimeZone - "+date61);
		Date date62=getSodDateBeforeInUserTimeZone(IST_TZ,1);
		log.info("IST_TZ getSodDateBeforeInUserTimeZone - "+date62);
		Date date71=getEodDateBeforeInUserTimeZone(UTC_TZ,1);
		log.info("UTC_TZ getEodDateBeforeInUserTimeZone - "+date71);
		Date date72=getEodDateBeforeInUserTimeZone(IST_TZ,1);
		log.info("IST_TZ getEodDateBeforeInUserTimeZone - "+date72);
		Date date81=getUserDateSodDateInUTC(UTC_TZ);
		log.info("UTC_TZ getUserDateSodDateInUTC - "+date81);
		Date date82=getUserDateSodDateInUTC(IST_TZ);
		log.info("IST_TZ getUserDateSodDateInUTC - "+date82);
		Date date91=getUserDateEodDateInUTC(UTC_TZ);
		log.info("UTC_TZ getUserDateEodDateInUTC - "+date91);
		Date date92=getUserDateEodDateInUTC(IST_TZ);
		log.info("IST_TZ getUserDateEodDateInUTC - "+date92);
		log.info("################################################################");
	}
}
