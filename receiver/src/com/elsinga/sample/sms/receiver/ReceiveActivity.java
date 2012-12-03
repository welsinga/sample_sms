package com.elsinga.sample.sms.receiver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ReceiveActivity extends Activity
{
  public static final String MESSAGE = "message";

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_received);

    Intent intent = getIntent();

    String message = intent.getStringExtra(MESSAGE);

    if (message != null)
    {
      TextView messageTextView = (TextView) findViewById(R.id.textMessage);
      messageTextView.setText(message);
    }
  }
}
