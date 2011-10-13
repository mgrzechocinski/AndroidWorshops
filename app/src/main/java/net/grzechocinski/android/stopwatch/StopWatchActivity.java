package net.grzechocinski.android.stopwatch;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import net.grzechocinski.android.stopwatch.service.StopwatchService;


/**
 * @author mgrzechocinski@gmail.com
 * @since 13/10/2011
 */
public class StopWatchActivity extends Activity implements StopWatchListener, ServiceConnection {

	private TextView counter;

	private static final String PATTERN = "HH:mm:ss";

	private StopwatchService stopwatchService;

	private Button startButton;

	private Button stopButton;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.count);

		startButton = (Button) findViewById(R.id.button_start);
		stopButton = (Button) findViewById(R.id.button_stop);

		counter = (TextView) findViewById(R.id.counter);
	}

	@Override
	protected void onResume() {
		super.onResume();
		Intent service = new Intent(this, StopwatchService.class);
		startService(service);
		bindService(service, this, 0); //no flags required in this case
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

	public void onNewValue(long millis) {
		SimpleDateFormat elapsedTimeFormatter = new SimpleDateFormat(PATTERN);
		elapsedTimeFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		counter.setText(elapsedTimeFormatter.format(new Date(millis)));
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