package com.example.harsha.receivesms;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SharedPreferences sharedPreferences = getSharedPreferences("ipaddress", Context.MODE_PRIVATE);
        sharedPreferences.getString("ip","");
         editText = (EditText)findViewById(R.id.ipaddress);
         submit = (Button)findViewById(R.id.button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("ip", editText.getText().toString());
                editor.commit();
               // String ip = sharedPreferences.getString("ip", "");
                //sendmessage(ip, "apkr09@gmail.com:subject3:body4:9657782929");
            }
        });



    }
    private void sendmessage(String ip,String message) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ip)
                .build();
        DataService dataService =restAdapter.create(DataService.class);

        Callback callback = new Callback() {

            @Override
            public void success(Object o, Response response) {
                APIResponse ticketids = (APIResponse)o;
                ticketids.getResult();
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.v("failure","fail");
            }
        };
        dataService.getEvents(message, callback);
    }
}
