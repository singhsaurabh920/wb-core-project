package org.worldbuild.project.utils;

import lombok.extern.log4j.Log4j2;
import org.worldbuild.project.enums.DistanceUnit;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

@Log4j2
public final class ConversionUtils {

	public static final MathContext MC = new MathContext(4);
	public static final BigDecimal _1000 = new BigDecimal(1000, MC);
	public static final String START_TIME = "00:00:01";
	public static final String END_TIME = "23:59:59";
	public static final long ONE_MILLI_SECOND = 1;
	public static final long ONE_SECOND = 1000 * ONE_MILLI_SECOND;
	public static final long ONE_MINUTE = 60 * ONE_SECOND;
	public static final long ONE_HOUR = 60 * ONE_MINUTE;
	public static final long ONE_DAY = 24 * ONE_HOUR;

	public static enum DateFormat {
		DDMMYYYY("dd/MM/yyyy"), DDMMYY("dd/MM/yy");
		private String format;

		private DateFormat(String format) {
			this.format = format;
		}

		public String getFormat() {
			return format;
		}
	}

	public static boolean isLatLongZero(double value) {
		double threshold = 0.5;
		return value >= -threshold && value <= threshold;
	}

	public static boolean isValidLatLng(double lat,double lng) {
		if(lat<=90&&lat>=-90&&lng<=180&&lng>=-180) {
			return true;
		}
		return false;
	}

	public static BigDecimal convert(double distance, DistanceUnit from, DistanceUnit to) {
		MathContext mc = new MathContext(4);
		BigDecimal bigDecimal = new BigDecimal(distance);
		switch (from) {
		case METER:
			switch (to) {
			case METER:
				return bigDecimal;
			case KM:
				return bigDecimal.divide(new BigDecimal(1000), mc);
			case MILES:
				return bigDecimal.multiply(new BigDecimal(0.000621), mc);
			}
			break;
		case KM:
			switch (to) {
			case METER:
				return bigDecimal.multiply(new BigDecimal(1000), mc);
			case KM:
				return bigDecimal;
			case MILES:
				return bigDecimal.multiply(new BigDecimal(0.621), mc);
			}
			break;
		case MILES:
			switch (to) {
			case METER:
				return bigDecimal.multiply(new BigDecimal(1609.34), mc);
			case KM:
				return bigDecimal.multiply(new BigDecimal(1.60934), mc);
			case MILES:
				return bigDecimal;
			}
			break;
		}
		return bigDecimal;
	}

	public static BigDecimal convert(long duration, TimeUnit from, TimeUnit to) {
		MathContext mc = new MathContext(4);
		BigDecimal bigDecimal = new BigDecimal(duration);
		switch (from) {
			case MILLISECONDS:
			switch (to) {
				case MILLISECONDS:
					return bigDecimal;
				case SECONDS:
					return bigDecimal.divide(new BigDecimal(1000), mc);
				case MINUTES:
					return bigDecimal.divide(new BigDecimal(60 * 1000), mc);
				case HOURS:
					return bigDecimal.divide(new BigDecimal(60 * 60 * 1000), mc);
			}
			break;
			case SECONDS:
			switch (to) {
				case MILLISECONDS:
					return bigDecimal.multiply(new BigDecimal(1000), mc);
				case SECONDS:
					return bigDecimal;
				case MINUTES:
					return bigDecimal.divide(new BigDecimal(60), mc);
				case HOURS:
					return bigDecimal.divide(new BigDecimal(60 * 60), mc);
			}
			break;
			case MINUTES:
			switch (to) {
				case MILLISECONDS:
					return bigDecimal.multiply(new BigDecimal(60 * 1000), mc);
				case SECONDS:
					return bigDecimal.multiply(new BigDecimal(1000), mc);
				case MINUTES:
					return bigDecimal;
				case HOURS:
					return bigDecimal.divide(new BigDecimal(60), mc);
			}
			break;
			case HOURS:
			switch (to) {
				case MILLISECONDS:
					return bigDecimal.multiply(new BigDecimal(60 * 60 * 1000), mc);
				case SECONDS:
					return bigDecimal.multiply(new BigDecimal(60 * 60), mc);
				case MINUTES:
					return bigDecimal.multiply(new BigDecimal(60), mc);
				case HOURS:
					return bigDecimal;
			}
			break;
		}
		return bigDecimal;
	}

	public static BigDecimal convertMeterToKiloMeter(double meters) {
		return new BigDecimal(meters, MC).divide(_1000, MC);
	}

	public static String convertMeterToKilometer(double distance) {
		return new BigDecimal(distance, MC).divide(_1000, MC).toPlainString() + "(Km)";
	}

	public static String convertFormattedTimeInMillisToHHmmss(long durationInMillis) {
		StringBuilder sb = new StringBuilder();
		int hour = 0;
		int min = 0;
		int sec = 0;
		while (durationInMillis > 1000) {
			if (durationInMillis >= ONE_HOUR) {
				hour = (int) (durationInMillis / ONE_HOUR);
				durationInMillis = (long) (durationInMillis % ONE_HOUR);
			} else if (durationInMillis >= ONE_MINUTE) {
				min = (int) (durationInMillis / ONE_MINUTE);
				durationInMillis = (long) (durationInMillis % ONE_MINUTE);
			} else if (durationInMillis >= ONE_SECOND) {
				sec = (int) (durationInMillis / ONE_SECOND);
				durationInMillis = (long) (durationInMillis % ONE_SECOND);
			}
		}
		sb.append(String.format("%02d", hour)).append(":");
		sb.append(String.format("%02d", min)).append(":");
		sb.append(String.format("%02d", sec));
		return sb.toString();
	}

	public static Long convertMillisToMinutes(long durationInMillis) {

		return durationInMillis / (1000 * 60);
	}

	public static long convertSecondsToMinutes(long durationInSeconds) {
		return durationInSeconds / 60;
	}

	public static double convertSecondsToHours(double durationInSeconds) {

		return durationInSeconds / 3600;
	}

	public static long convertMillisToSeconds(long durationInMillis) {
		return durationInMillis / 1 * 1000;
	}

	public static long convertMillisToHours(long durationInMillis) {
		return durationInMillis / convertMillisToMinutes(durationInMillis) * 60;
	}

	public static String decimalFormat(double decimal) {
		DecimalFormat formatter = new DecimalFormat("#0.00");
		return formatter.format(decimal);
	}
	
	public static String roundDownLoc(double[] loc) {
		if (loc == null || loc.length <= 1) {
			return "--";
		}
		DecimalFormat df = new DecimalFormat("#.######");
		df.setRoundingMode(RoundingMode.CEILING);
		return df.format(loc[1]) + ", " + df.format(loc[0]);
	}

	public static double roundDown6(double d) {
		return (long) (d * 1e6) / 1e6;
	}

	public static double roundDown4(double d) {
		return (long) (d * 1e4) / 1e4;
	}

	public static double roundDown2(double d) {
		return (d * 1e2) / 1e2;
	}

	public static long roundDown(double d) {
		return Math.round(d);
	}
	
	public static String convertSecondsToHHMMSS(long s) {
		long m = s / 60;
		s = s % 60;
		long h = m / 60;
		m = m % 60;
		return String.format("%2d:%02d:%02d", h, m, s);
	}
	
	public static String convertSecondsToDDHHMM(long s) {
	    long seconds=s%60;
		long minutes=s/60;
	    long hours= minutes/60;
	    minutes=minutes%60;
	    long days=hours/24;
	    hours=hours%24;
	    if(days==0 && hours==0 && minutes==0 && seconds==0){
		     return "--";   
		}
	    if(days==0 && hours==0 && minutes==0){
	        return String.format(seconds+"S");
	    }
	    if(days==0 && hours==0 && seconds==0){
	        return String.format(minutes+"M");
	    }
	    if(days==0 && minutes==0 && seconds==0){
	        return String.format(hours+"H");
	    }
	    if(days==0 && hours==0){
	        return String.format(minutes+"M"+" "+seconds+"S");
	    }
	    if(days==0 && minutes==0){
	        return String.format(hours+"H"+" "+seconds+"S");
	    }
	    if(days==0 && seconds==0){
	        return String.format(hours+"H"+" "+minutes+"M");
	    }
	    if(days==0){
	        return String.format(hours+"H"+" "+minutes+"M"+" "+seconds+"S");
	    }
	    if(hours==0 && minutes==0){
		     return String.format(days+"D");   
		}
	    if(hours==0){
	     return String.format(days+"D"+" "+minutes+"M"+" "+seconds+"S");   
	    }
	    if(minutes==0){
	        return String.format(days+"D"+" "+hours+"H"+" "+seconds+"S");
	    }
	    if(seconds==0){
	        return String.format(days+"D"+" "+hours+"H"+" "+minutes+"M");
	    }
	    return String.format(days+"D"+" "+hours+"H"+" "+minutes+"M"+" "+seconds+"S");
	}

	public static String convertSecondToHMm(long seconds) {
	    Date d = new Date(seconds * 1000L);
	    SimpleDateFormat df = new SimpleDateFormat("HH:mm"); // HH for 0-23
	    df.setTimeZone(TimeZone.getTimeZone("GMT"));
	    String time = df.format(d);
	    return time;
	}
	
	public static String convertMinutesToHMm(long m) {
		long h = m / 60;
		m = m % 60;
		return String.format("%2d:%02d", h, m);
	}

	public static String convertSecondToHHmmss(long seconds) {
	    Date d = new Date(seconds * 1000L);
	    SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss"); // HH for 0-23
	    df.setTimeZone(TimeZone.getTimeZone("GMT"));
	    String time = df.format(d);
	    return time;
	}
	
	public static int generateRandomInt(int upperRange){
	    Random random = new Random();
	    return random.nextInt(upperRange);
	}
	
	public static int generateRandomIntIntRange(int min, int max) {
	    Random r = new Random();
	    return r.nextInt((max - min) + 1) + min;
	}
	
	public static String displayTimeZone(String userTimzezone) {
		TimeZone tz=TimeZone.getTimeZone(userTimzezone);
		long hours = java.util.concurrent.TimeUnit.MILLISECONDS.toHours(tz.getRawOffset());
		long minutes = java.util.concurrent.TimeUnit.MILLISECONDS.toMinutes(tz.getRawOffset()) - java.util.concurrent.TimeUnit.HOURS.toMinutes(hours);
		minutes = Math.abs(minutes);
		String result = "";
		if (hours > 0) {
			result = String.format("(GMT+%d:%02d) %s", hours, minutes, tz.getID());
		} else {
			result = String.format("(GMT%d:%02d) %s", hours, minutes, tz.getID());
		}
		return result;
	}

	public static double limitPrecision(String dblAsString, int maxDigitsAfterDecimal) {
	    int multiplier = (int) Math.pow(10, maxDigitsAfterDecimal);
	    double truncated = (double) ((long) ((Double.parseDouble(dblAsString)) * multiplier)) / multiplier;
	    return truncated;
	}

	public static String calculateSinceExact(Date since){
		if(since==null) {
			return "";
		}
		long sinceDate=since.getTime();
		String time="";
		long todayDate=new Date().getTime();
		long different=todayDate-sinceDate;
		if(different>=0){
			long secondsInMilli = 1000;
			long minutesInMilli = secondsInMilli * 60;
			long hoursInMilli = minutesInMilli * 60;
			long daysInMilli = hoursInMilli * 24;
			long days = (long) Math.floor(different / daysInMilli);
			different = different % daysInMilli;
			long hours = (long) Math.floor(different / hoursInMilli);
			different = different % hoursInMilli;
			long minutes = (long) Math.floor(different / minutesInMilli);
			if(days==0 && hours==0 && minutes==0){
				time="Just Now";
			}else if(days>0){
				time=padLeftZeros(Long.toString(days), 2)+"D "+padLeftZeros(Long.toString(hours),2)+"H "+padLeftZeros(Long.toString(minutes),2)+"M";
			}else if(hours>0){
				time=padLeftZeros(Long.toString(hours),2)+"H "+padLeftZeros(Long.toString(minutes),2)+"M";
			}else if(minutes>0){
				time=padLeftZeros(Long.toString(hours),2)+"M";
			}
		}else if(different>-180000){
			time="Just Now";
		}else{
			time = "Just Now";
		}
		return time;
	}

	public static String padLeftZeros(String inputString, int length) {
		if (inputString.length() >= length) {
			return inputString;
		}
		StringBuilder sb = new StringBuilder();
		while (sb.length() < length - inputString.length()) {
			sb.append('0');
		}
		sb.append(inputString);
		return sb.toString();
	}

	public static String getMonthInUserTimeZone(Date date, String userTimzezone) {
		ZoneId utcTzId = ZoneId.of("UTC");
		ZoneId userTzId = ZoneId.of(userTimzezone);
		Instant instant = date.toInstant();
		LocalDate localDate = instant.atZone(userTzId).toLocalDate();
		return localDate.getMonth().name().toLowerCase();
	}

	public static Date getLastDateOfMonth(Date startDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	public static long convertHHmmIntoSeconds(String hhmm) {
		java.text.DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		Date reference = null;
		Date date = null;
		try {
			reference = dateFormat.parse("00:00");
			date = dateFormat.parse(hhmm);
		} catch (Exception e) {
			log.error("Exception- ", e);
		}
		long seconds = (date.getTime() - reference.getTime()) / 1000L;
		return seconds;
	}

	public static long convertUserHHmmIntoUTCMinutes(String timezone,String hhmm) {
		long timezoneOffset=TimeZone.getTimeZone(timezone).getOffset(new Date().getTime())/(60*1000);
		long minutes=(convertHHmmIntoSeconds(hhmm)/60)-timezoneOffset;
		while(minutes<0) {
			minutes=1440+minutes;
		}
		return minutes;
	}

	public static String convertUTCMinutesToUserHMm(String timezone, long utcHHmm) {
		long timezoneOffset=TimeZone.getTimeZone(timezone).getOffset(new Date().getTime())/(60*1000);
		long minutes=utcHHmm+timezoneOffset;
		while(minutes>=1440) {
			minutes=minutes-1440;
		}
		return ConversionUtils.convertMinutesToHMm(minutes);
	}

}
