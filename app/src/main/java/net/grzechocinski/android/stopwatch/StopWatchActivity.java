package net.grzechocinski.android.stopwatch;

import android.app.Activity;
import android.os.Bundle;


/**
 * @author mgrzechocinski@gmail.com
 * @since 13/10/2011
 */
public class StopWatchActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.count);
	}
}