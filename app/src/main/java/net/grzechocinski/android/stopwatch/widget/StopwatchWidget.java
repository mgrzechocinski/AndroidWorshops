package net.grzechocinski.android.stopwatch.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import net.grzechocinski.android.stopwatch.R;
import net.grzechocinski.android.stopwatch.service.StopwatchService;
import net.grzechocinski.android.stopwatch.util.Const;

/**
 * @author mgrzechocinski@gmail.com
 * @since 13/10/2011
 */
public class StopwatchWidget extends AppWidgetProvider {

	protected final String LOG_TAG = Const.getLogTag(this);

    public static String ACTION_WIDGET_SET_START = "net.grzechocinski.android.stopwatch.ACTION_SET_START";
    public static String ACTION_WIDGET_SET_STOP = "net.grzechocinski.android.stopwatch.ACTION_SET_STOP";

    private static final Map<String, Integer> actionWidgetImgResources = new HashMap<String, Integer>();

	public static final String ACTION_STOPWATCH_TOGGLE_START_STOP = "net.grzechocinski.android.stopwatch.ACTION_TOGGLE_START_STOP";

	static {
        actionWidgetImgResources.put(ACTION_WIDGET_SET_START, R.drawable.btn_widget_off);
        actionWidgetImgResources.put(ACTION_WIDGET_SET_STOP, R.drawable.btn_widget_on);
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.d(LOG_TAG, "StopwatchButtonWidget - onEnabled");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.d(LOG_TAG, "StopwatchButtonWidget - onReceive intent" + intent);
        Integer icoResId = actionWidgetImgResources.get(intent.getAction());
        if (icoResId != null) {
            updateWidget(context, icoResId);
        }
    }

    public void updateWidget(Context context, int icoResId) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName thisWidget = new ComponentName(context, StopwatchWidget.class);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
		for (int appWidgetId : appWidgetIds) {
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
			views.setImageViewResource(R.id.widget_image, icoResId);
			appWidgetManager.updateAppWidget(appWidgetId, views);
		}
    }


    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;
        final ComponentName serviceName = new ComponentName(context, StopwatchService.class);

        Log.d(LOG_TAG, "StopwatchButtonWidget - onUpdate appWidgetIds:" + Arrays.toString(appWidgetIds));
        for (int i = 0; i < N; i++) {
            int appWidgetId = appWidgetIds[i];

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);

            Intent intent = new Intent(ACTION_STOPWATCH_TOGGLE_START_STOP);
            intent.setComponent(serviceName);
            PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            views.setOnClickPendingIntent(R.id.widget_image, pendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.d(LOG_TAG, "StopwatchButtonWidget - onDeleted: " + Arrays.toString(appWidgetIds));
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.d(LOG_TAG, "StopwatchButtonWidget - onDisabled");
    }
}
