package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class Broadcastreceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        final Bundle bundle = intent.getExtras();
        try {
            if (bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                for (int i = 0; i < pdusObj.length; i++) {
                    SmsMessage currentMessage = getIncomingMessage(pdusObj[i], bundle);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();
                    Log.i("SmsReceiver", "senderNum: " + senderNum + "; message: " + message);
                    Intent showSmsIntent = new Intent(context, SmsReceiver.class);

                    showSmsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    showSmsIntent.putExtra(SmsReceiver.EXTRA_SMS_NO, phoneNumber);
                    showSmsIntent.putExtra(SmsReceiver.EXTRA_SMS_MESSAGE, message);
                    context.startActivity(showSmsIntent);
                }
            }
        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" + e);
        }
    }



    private SmsMessage getIncomingMessage(Object object, Bundle bundle){

        SmsMessage currentSMS;
        if (Build.VERSION.SDK_INT >= 23){
            String format = bundle.getString("format");
            currentSMS = SmsMessage.createFromPdu((byte []) object, format);
        }else {
            currentSMS = SmsMessage.createFromPdu((byte []) object);
        }

        return currentSMS;
    }
}
