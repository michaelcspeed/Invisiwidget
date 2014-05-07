package com.speed.invisiwidget;

import java.util.List;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

public class MyWidgetProvider extends AppWidgetProvider {

	public static String ACTION_WIDGET_OPEN_APP = "OPEN_APP";

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
				R.layout.widget_layout);

		Intent active = new Intent(context, MyWidgetProvider.class);
		active.setAction(ACTION_WIDGET_OPEN_APP);
		PendingIntent actionPendingIntent = PendingIntent.getBroadcast(context,
				0, active, 0);
		remoteViews.setOnClickPendingIntent(R.id.button, actionPendingIntent);

		appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		
		
		Intent i;
		PackageManager manager = context.getPackageManager();

		// get a list of installed apps.
		List<ApplicationInfo> packages = manager
				.getInstalledApplications(PackageManager.GET_META_DATA);

		for (ApplicationInfo packageInfo : packages) {
			Log.d("InvisiWidget", "Installed package :"
					+ packageInfo.packageName);
			Log.d("InvisiWidget", "Source dir : " + packageInfo.sourceDir);
			Log.d("InvisiWidget",
					"Launch Activity :"
							+ manager.getLaunchIntentForPackage(packageInfo.packageName));
		}

		try {
			i = manager.getLaunchIntentForPackage(packages.get(5).packageName);
			if (i == null)
				throw new PackageManager.NameNotFoundException();
			i.addCategory(Intent.CATEGORY_LAUNCHER);
			context.startActivity(i);
		} catch (PackageManager.NameNotFoundException e) {

		}
		/*	this.context = context;
		manager = context.getPackageManager();

		// get a list of installed apps.
		packages = manager
				.getInstalledApplications(PackageManager.GET_META_DATA);

		AlertDialog.Builder builderSingle = new AlertDialog.Builder(context);
		builderSingle.setIcon(R.drawable.ic_launcher);
		builderSingle.setTitle("Select One Name:-");
		final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
				context, android.R.layout.select_dialog_singlechoice);

		for (ApplicationInfo applicationInfo : packages) {
			arrayAdapter.add(applicationInfo.name);
		}

		builderSingle.setNegativeButton("cancel",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

		builderSingle.setAdapter(arrayAdapter,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						String strName = arrayAdapter.getItem(which);

						try {
							i = manager.getLaunchIntentForPackage(packages
									.get(which).packageName);
							if (i == null)
								throw new PackageManager.NameNotFoundException();
							i.addCategory(Intent.CATEGORY_LAUNCHER);
							MyWidgetProvider.this.context.startActivity(i);
						} catch (PackageManager.NameNotFoundException e) {

						}

					}
				});
		builderSingle.show();

		super.onReceive(context, intent);*/
		

		super.onReceive(context, intent);
	}

}