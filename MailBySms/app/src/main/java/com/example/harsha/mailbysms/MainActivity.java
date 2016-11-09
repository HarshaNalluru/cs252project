package com.example.harsha.mailbysms;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.harsha.mailbysms.R;
import com.yarolegovich.lovelydialog.LovelyTextInputDialog;

public class MainActivity extends AppCompatActivity {
    private EditText to,subject,message,number;
    private Button send;
    private WebView mWebview ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        to= (EditText)findViewById(R.id.to);
        subject=(EditText)findViewById(R.id.subject);
        message = (EditText)findViewById(R.id.message);
        //number = (EditText)findViewById(R.id.number);
        send= (Button)findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNo = "7755047917";
                String sms = to.getText().toString()+":"+subject.getText().toString()+":"+message.getText().toString();

                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, sms, null, null);
                    Toast.makeText(getApplicationContext(), "SMS Sent!",
                            Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again later!",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
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
                                    mWebview  = new WebView(MainActivity.this);

                                    mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript

                                    final Activity activity = MainActivity.this;

                                    mWebview.setWebViewClient(new WebViewClient() {
                                        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                                            Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    String url = "http://"+text+"/cs252project/register.php";
                                    mWebview .loadUrl(url);
                                    setContentView(mWebview );
                                }
                            })
                            .show();


                }
            });

        }

    }
}
