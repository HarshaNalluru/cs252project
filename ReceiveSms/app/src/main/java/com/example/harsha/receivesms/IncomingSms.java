package com.example.harsha.receivesms;

/**
 * Created by harsha on 22/10/16.
 */

import android.os.AsyncTask;
import android.os.Bundle;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;


import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class IncomingSms extends BroadcastReceiver {
    public String senderNum,message;
    // Get the object of SmsManager
    final SmsManager sms = SmsManager.getDefault();

    public void onReceive(Context context, Intent intent) {

        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();

        try {

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    senderNum = phoneNumber;
                    message = currentMessage.getDisplayMessageBody();

                    Log.i("SmsReceiver", "senderNum: "+ senderNum + "; message: " + message);


                    // Show Alert
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context,
                            "senderNum: "+ senderNum + ", message: " + message, duration);
                    toast.show();
                   // sendmessage(message);
                   // new postdata().execute();

                } // end for loop
            } //
            // bundle is null


        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" + e);

        }
    }

    private void sendmessage(String message) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://www.interiit.com/php")
                .build();
        DataService dataService =restAdapter.create(DataService.class);

        Callback callback = new Callback() {

            @Override
            public void success(Object o, Response response) {
                APIResponse ticketids = (APIResponse)o;
//                try {
//                    JSONArray jsonArray =new JSONArray(ticketids.getData());
//                    Type type = new TypeToken<List<StandingsDTO>>(){}.getType();
//                    standingsDTOList = GsonFactory.getISOFormatInstance().fromJson(jsonArray.toString(), type);
//                    adapter = new StandingsAdapter(Points.this,standingsDTOList);
//                    recyclerView.setAdapter(adapter);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                //standingsDTOList= new ArrayList<>();
            }
        };
           // dataService.senddata("abhajjalkalak", callback);
    }
//    class postdata extends AsyncTask<Void, Void, Void> {
//
//        @Override
//        protected Void doInBackground(Void... params) {
//            HttpClient httpClient = new DefaultHttpClient();
//            HttpPost httpPost = new HttpPost(
//                    "http://172.27.30.50:3000/message");
//            List<NameValuePair> nameValuePair = new ArrayList<>(2);
//            nameValuePair.add(new BasicNameValuePair("content",message ));
//            nameValuePair.add(new BasicNameValuePair("from_number", senderNum));
//            Log.e("message",message);
//            try {
//                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
//            } catch (UnsupportedEncodingException e) {
//                // writing error to Log
//                e.printStackTrace();
//            }
//            // Making HTTP Request
//            try {
//                HttpResponse response = httpClient.execute(httpPost);
//                // writing response to log
//                Log.d("Http Response:", response.toString());
//            } catch (ClientProtocolException e) {
//                // writing exception to log
//                e.printStackTrace();
//            } catch (IOException e) {
//                // writing exception to log
//                e.printStackTrace();
//
//            }
//            return null;
//        }
//    }
}

