package net.grzechocinski.android.stopwatch.service;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.grzechocinski.android.stopwatch.StopWatch;
import net.grzechocinski.android.stopwatch.StopWatchListener;
import net.grzechocinski.android.stopwatch.provider.ResultsMetadata;
import net.grzechocinski.android.stopwatch.util.Const;


/**
 * @author mateusz.grzechocinski@gmail.com
 * @since 13/10/2011
 */
public class StopwatchService extends Service implements StopWatchListener {

	protected final String LOG_TAG = Const.getLogTag(this);

	private StopWatch stopWatch;

	private List<StopWatchListener> listeners = new ArrayList<StopWatchListener>();

	public void registerListener(StopWatchListener listenerToRegister) {
		listeners.add(listenerToRegister);
	}

	public void unregisterListener(StopWatchListener listenerToUnregister) {
		listeners.remove(listenerToUnregister);
	}

	public void onNewValue(long millis) {
		for (StopWatchListener listener : listeners) {
			listener.onNewValue(millis);
		}
	}

	public class LocalBinder extends Binder {

		public StopwatchService getService() {
			return StopwatchService.this;
		}
	}

	private final IBinder localServiceBinder = new LocalBinder();

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(LOG_TAG, "onCreate()");
		stopWatch = new StopWatch(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(LOG_TAG, "onDestroy()");
	}

	public void start() {
		stopWatch.start();
	}

	public void stop() {
		long currentValue = stopWatch.stop();
		ContentValues contentValues = new ContentValues();
		contentValues.put(ResultsMetadata.ResultsTableMetaData.COLUMN_RESULT, currentValue);
		contentValues.put(ResultsMetadata.ResultsTableMetaData.COLUMN_CREATED_DATE, new Date().getTime());
		getContentResolver().insert(ResultsMetadata.ResultsTableMetaData.CONTENT_URI, contentValues);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return localServiceBinder;
	}
}
