package net.grzechocinski.android.stopwatch;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;
import roboguice.application.RoboApplication;

/**
 * @author mateusz.grzechocinski@gmail.com
 * @since 14/10/2011
 */
@ReportsCrashes(formKey = "dDViQlJ4UmxpUzJ0Q3pRc3R3TEFKcnc6MQ")
public class StopwatchApp extends RoboApplication {

	@Override
	public void onCreate() {
		ACRA.init(this);
		super.onCreate();
	}
}
