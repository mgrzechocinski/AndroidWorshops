package net.grzechocinski.android.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


/**
 * @author mgrzechocinski@gmail.com
 * @since 13/10/2011
 */
public class StopWatchActivity extends Activity implements StopWatchListener {

	private StopWatch stopWatch;

	private TextView counter;

	private static final String PATTERN = "HH:mm:ss";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.count);

		Button startButton = (Button) findViewById(R.id.button_start);
		Button stopButton = (Button) findViewById(R.id.button_stop);

		stopWatch = new StopWatch(this);

		startButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				showToast(R.string.button_start);
				stopWatch.start();
			}
		});

		stopButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				showToast(R.string.button_stop);
				stopWatch.stop();
			}
		});

		counter = (TextView) findViewById(R.id.counter);

	}

	private void showToast(int msgResId) {
		Toast.makeText(StopWatchActivity.this, msgResId, Toast.LENGTH_SHORT).show();
	}

	public void onNewValue(long millis) {
		SimpleDateFormat elapsedTimeFormatter = new SimpleDateFormat(PATTERN);
		elapsedTimeFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		counter.setText(elapsedTimeFormatter.format(new Date(millis)));
	}
}