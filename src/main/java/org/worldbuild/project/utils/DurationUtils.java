package org.worldbuild.project.utils;

import lombok.extern.log4j.Log4j2;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Log4j2
public final class DurationUtils {

	public static int calculateDuration(Date startDate, Date endDate, TimeUnit timeUnit) {
		long duration = endDate.getTime() - startDate.getTime();
		switch (timeUnit) {
			case MILLISECONDS: {
				return Math.toIntExact(TimeUnit.MILLISECONDS.toMillis(duration));
			}
			case SECONDS: {
				return Math.toIntExact(TimeUnit.MILLISECONDS.toSeconds(duration));
			}
			case MINUTES: {
				return Math.toIntExact(TimeUnit.MILLISECONDS.toMinutes(duration));
			}
			case HOURS: {
				return Math.toIntExact(TimeUnit.MILLISECONDS.toHours(duration));
			}
			case DAYS: {
				return Math.toIntExact(TimeUnit.MILLISECONDS.toDays(duration));
			}
			default: {
				return 0;
			}
		}
	}

	public static int calculateDurationAbs(Date startDate, Date endDate, TimeUnit timeUnit){
		return Math.abs(calculateDuration(startDate,endDate,timeUnit));
	}

}
