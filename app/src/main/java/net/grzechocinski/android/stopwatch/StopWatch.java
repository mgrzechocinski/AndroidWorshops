package net.grzechocinski.android.stopwatch;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import java.util.Date;
import net.grzechocinski.android.stopwatch.provider.ResultsMetadata;
import net.grzechocinski.android.stopwatch.util.Formatter;
import net.grzechocinski.android.stopwatch.widget.StopwatchWidget;

/**
 * @author mateusz.grzechocinski@gmail.com
 * @since 13/10/2011
 */
public class StopWatch {

	public static final int HEART_BEAT = 0;

	private long startTimestamp;

	private Context ctx;

	private StopWatchListener listener;

	private boolean started;

	public StopWatch(Context ctx, StopWatchListener listener) {
		this.ctx = ctx;
		this.listener = listener;
	}

	private long currentValue;

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			long nowTimeStamp = new Date().getTime();
			currentValue = nowTimeStamp - startTimestamp;
			listener.onNewValue(currentValue);
			handler.sendEmptyMessageDelayed(HEART_BEAT, 1000);
		}
	};

	public void start() {
		startTimestamp = new Date().getTime();
		handler.sendEmptyMessage(HEART_BEAT);
		started = true;
	}

	public void stop() {
		handler.removeMessages(HEART_BEAT);
		started = false;
		ContentValues contentValues = new ContentValues();
		contentValues.put(ResultsMetadata.ResultsTableMetaData.COLUMN_RESULT, currentValue);
		contentValues.put(ResultsMetadata.ResultsTableMetaData.COLUMN_CREATED_DATE, new Date().getTime());
		ctx.getContentResolver().insert(ResultsMetadata.ResultsTableMetaData.CONTENT_URI, contentValues);
	}

	public boolean isStarted() {
		return started;
	}

	public void toggleStartStop(Context ctx) {
		if (isStarted()) {
			stop();
			Toast.makeText(ctx, Formatter.formatElapsed(currentValue), Toast.LENGTH_SHORT).show();
			ctx.sendBroadcast(new Intent(StopwatchWidget.ACTION_WIDGET_SET_START));
		} else {
			start();
			ctx.sendBroadcast(new Intent(StopwatchWidget.ACTION_WIDGET_SET_STOP));
		}
	}
}
