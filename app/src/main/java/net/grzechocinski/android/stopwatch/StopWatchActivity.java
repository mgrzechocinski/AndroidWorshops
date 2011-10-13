package net.grzechocinski.android.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


/**
 * @author mgrzechocinski@gmail.com
 * @since 13/10/2011
 */
public class StopWatchActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.count);

		Button startButton = (Button) findViewById(R.id.button_start);
		Button stopButton = (Button) findViewById(R.id.button_stop);
		Button resetButton = (Button) findViewById(R.id.button_reset);

		startButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				showToast(R.string.button_start);
			}
		});

		stopButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				showToast(R.string.button_stop);
			}
		});

		resetButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				showToast(R.string.button_reset);
			}
		});
	}

	private void showToast(int msgResId){
		Toast.makeText(StopWatchActivity.this, msgResId,Toast.LENGTH_SHORT).show();
	}
}