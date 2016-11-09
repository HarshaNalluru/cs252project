package com.example.harsha.receivesms;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.yarolegovich.lovelydialog.LovelyTextInputDialog;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SharedPreferences sharedPreferences = getSharedPreferences("ipaddress", Context.MODE_PRIVATE);
        sharedPreferences.getString("ip", "");
        FloatingActionButton floatingActionButton =(FloatingActionButton)findViewById(R.id.fab);
        if (floatingActionButton != null) {
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new LovelyTextInputDialog(MainActivity.this, R.style.EditTextTintTheme)
                            .setTopColorRes(R.color.darkDeepOrange)
                            .setTitle("Add the ip address")
                            .setMessage("Ex:192.168.0.112")
                            .setIcon(R.drawable.ic_account_check_white_24dp)

                            .setConfirmButton(android.R.string.ok, new LovelyTextInputDialog.OnTextInputConfirmListener() {
                                @Override
                                public void onTextInputConfirmed(String text) {
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("ip", text);
                                    editor.commit();
                                }
                            })
                            .show();
                }
            });

        }


    }

}
