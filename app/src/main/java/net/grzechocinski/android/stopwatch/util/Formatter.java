package net.grzechocinski.android.stopwatch.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author mateusz.grzechocinski@gmail.com
 * @since 14/10/2011
 */
public class Formatter {

	private static final String TIME_PATTERN = "HH:mm:ss";

	public static final String DATE_TIME_PATTERN = "dd-MM-yyyy " + TIME_PATTERN;

	public static String formatElapsed(long millis) {
		SimpleDateFormat elapsedTimeFormatter = new SimpleDateFormat(TIME_PATTERN);
		elapsedTimeFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		return elapsedTimeFormatter.format(new Date(millis));
	}

	public static String formatDateTime(Long createdDate) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_PATTERN);
		return simpleDateFormat.format(new Date(createdDate));
	}
}
