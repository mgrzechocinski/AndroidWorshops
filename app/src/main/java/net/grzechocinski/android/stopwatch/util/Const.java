package net.grzechocinski.android.stopwatch.util;

/**
 * @author mateusz.grzechocinski@gmail.com
 * @since 13/10/2011
 */
public class Const {

	public static final String STOP_WATCH_LOG = "StopWatchLOG_";

	public static String getLogTag(Object o) {
		return STOP_WATCH_LOG + o.getClass().getSimpleName();
	}
}
