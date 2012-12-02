package com.elsinga.sample.sms.receiver;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class ReceiveActivity extends Activity
{
  private static final int   HELLO_ID = 123;

  public static final String MESSAGE  = "message";
  public static final String FROM     = "from";

  @SuppressWarnings("deprecation")
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    Intent intent = getIntent();

    String message = intent.getStringExtra(MESSAGE);
    String from = intent.getStringExtra(FROM);

    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

    Notification notification = new Notification(R.drawable.ic_launcher, message, System.currentTimeMillis());

    Intent handlerIntent = new Intent(this, MainActivity.class);
    PendingIntent contentIntent = createPendingResult(0, handlerIntent, PendingIntent.FLAG_ONE_SHOT);

    notification.setLatestEventInfo(this, "Data sms from: " + from, message, contentIntent);

    notificationManager.notify(HELLO_ID, notification);

    finish();

  }
}
