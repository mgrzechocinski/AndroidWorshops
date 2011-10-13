package net.grzechocinski.android.stopwatch.activity;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import net.grzechocinski.android.stopwatch.R;

/**
 * @author mateusz.grzechocinski@gmail.com
 * @since 13/10/2011
 */
public class StopwatchPreferenceActivity extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
	}
}
