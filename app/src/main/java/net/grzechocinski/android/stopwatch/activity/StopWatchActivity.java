package net.grzechocinski.android.stopwatch.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import net.grzechocinski.android.stopwatch.R;
import net.grzechocinski.android.stopwatch.StopWatchListener;
import net.grzechocinski.android.stopwatch.service.StopwatchService;
import net.grzechocinski.android.stopwatch.util.Color;
import net.grzechocinski.android.stopwatch.util.Formatter;


/**
 * @author mgrzechocinski@gmail.com
 * @since 13/10/2011
 */
public class StopWatchActivity extends Activity implements StopWatchListener, ServiceConnection {

	private TextView counter;

	private StopwatchService stopwatchService;

	private Button startButton;

	private Button stopButton;

	private Button crashButton;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.count);

		startButton = (Button) findViewById(R.id.button_start);
		stopButton = (Button) findViewById(R.id.button_stop);

		crashButton = (Button) findViewById(R.id.button_crash);

		counter = (TextView) findViewById(R.id.counter);


		crashButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				throw new RuntimeException("My custom message");
			}
		});

	}

	private int loadColorFromPreferences() {
		SharedPreferences prefManager = PreferenceManager.getDefaultSharedPreferences(this);
		String settingsStopwatchColor = prefManager.getString("settingsStopwatchColor", "WHITE");
		return getResources().getColor(Color.valueOf(settingsStopwatchColor).asResId());
	}

	@Override
	protected void onResume() {
		super.onResume();
		Intent service = new Intent(this, StopwatchService.class);
		startService(service);
		bindService(service, this, 0); //no flags required in this case
		counter.setTextColor(loadColorFromPreferences());
	}

	@Override
	protected void onPause() {
		super.onPause();
		unbindService(this);
	}

	@Override
	public void onBackPressed() {
		Intent service = new Intent(this, StopwatchService.class);
		stopService(service);
		super.onBackPressed();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_results:
				startActivity(new Intent(this, ResultsListActivity.class));
				break;
			case R.id.menu_preferences:
				startActivity(new Intent(this, StopwatchPreferenceActivity.class));
				break;
		}
		return true;
	}


	public void onNewValue(long millis) {
		counter.setText(Formatter.formatElapsed(millis));
	}

	public void onServiceConnected(ComponentName componentName, IBinder service) {
		stopwatchService = ((StopwatchService.LocalBinder) service).getService();
		this.stopwatchService.registerListener(this);
		bindButtons();
	}

	private void bindButtons() {
		startButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				stopwatchService.start();
			}
		});

		stopButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				stopwatchService.stop();
			}
		});
	}

	public void onServiceDisconnected(ComponentName componentName) {
		stopwatchService.unregisterListener(this);
	}
}