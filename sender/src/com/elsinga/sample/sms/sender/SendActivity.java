package com.elsinga.sample.sms.sender;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendActivity extends Activity
{
  private static final int   SENT     = 1;

  private static final short SMS_PORT = 8998;

  private EditText           _phoneNumber;
  private EditText           _message;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sender);

    Button button = (Button) findViewById(R.id.send);
    button.setOnClickListener(new OnClickListener()
    {

      @Override
      public void onClick(View v)
      {
        sendSMS();
      }
    });

    _phoneNumber = (EditText) findViewById(R.id.number);
    _message = (EditText) findViewById(R.id.message);
  }

  private void sendSMS()
  {

    PendingIntent sent = this.createPendingResult(SENT, new Intent(), PendingIntent.FLAG_UPDATE_CURRENT);
    String messageText = _message.getText().toString();

    SmsManager smsManager = SmsManager.getDefault();
    smsManager.sendDataMessage(_phoneNumber.getText().toString(), null, SMS_PORT, messageText.getBytes(), sent, null);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data)
  {
    String toast = null;
    switch (requestCode)
    {
      case SENT :
        switch (resultCode)
        {
          case RESULT_OK :
            toast = "OK";
            break;
          case SmsManager.RESULT_ERROR_GENERIC_FAILURE :
            toast = "Generic Failure";
            break;
          case SmsManager.RESULT_ERROR_RADIO_OFF :
            toast = "Radio Off";
            break;
          case SmsManager.RESULT_ERROR_NULL_PDU :
            toast = "Null Pdu";
            break;
        }
        break;
    }

    if (toast != null)
    {
      Toast.makeText(this, toast, Toast.LENGTH_LONG).show();
    }

  }
}
