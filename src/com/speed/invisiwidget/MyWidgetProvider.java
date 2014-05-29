package com.speed.invisiwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

public class MyWidgetProvider extends AppWidgetProvider {

	public static String ACTION_WIDGET_OPEN_APP = "OPEN_APP";

	public static int time = 60;

	private RemoteViews remoteViews;

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {


		remoteViews = new RemoteViews(context.getPackageName(),
				R.layout.widget_layout);

		Intent active = new Intent(context, MyWidgetProvider.class);
		active.setAction(ACTION_WIDGET_OPEN_APP);
		PendingIntent actionPendingIntent = PendingIntent.getBroadcast(context,
				0, active, 0);
		remoteViews.setOnClickPendingIntent(R.id.button, actionPendingIntent);

		appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
	}

	public static void updateAppWidget(Context context,
			AppWidgetManager appWidgetManager, int appWidgetId,
			String packageName) {

		Log.d("iw", "updateappwidget()");

		RemoteViews updateViews = new RemoteViews(context.getPackageName(),
				R.layout.widget_layout);

		try {

			Intent launchIntent = context.getPackageManager()
					.getLaunchIntentForPackage(packageName);
			PendingIntent actionPendingIntent = PendingIntent.getActivity(
					context, 0, launchIntent, 0);
			updateViews.setOnClickPendingIntent(R.id.button,
					actionPendingIntent);

			appWidgetManager.updateAppWidget(appWidgetId, updateViews);
		} catch (Exception e) {
			Toast.makeText(context, "Can't create widget for this app, sorry!",
					Toast.LENGTH_LONG).show();
			updateViews.setTextViewText(R.id.button, "");

		} 
	}

}