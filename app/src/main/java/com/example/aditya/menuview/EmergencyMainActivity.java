package com.example.aditya.menuview;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.R.id.message;

public class EmergencyMainActivity extends AppCompatActivity implements EmergencyRecyclerViewAdapter.ListItemClickListener, View.OnClickListener {

    private static final String ACCOUNT_SID = "AC3c5a1a692625d57fbe5acd1397db8d21";
    private static final String AUTH_TOKEN = "e17199154f5a88cc8a6cd908060133e2";
    private static final String TAG = "EmergencyActivity";
    private TextView TextViewEmergencyMain;
    private RecyclerView RecyclerViewEmergencyMain;
    private Button ButtonEmergencyMainSend;
    private LinearLayoutManager linearLayoutManager;
    private EmergencyRecyclerViewAdapter emergencyRecyclerViewAdapter;
    private String[] templates = {"Template 1","Template 2","Template 3","Template 4"};
    private String data="";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_main);
        TextViewEmergencyMain = (TextView) findViewById(R.id.TextViewEmergencyMain);
        RecyclerViewEmergencyMain = (RecyclerView) findViewById(R.id.RecyclerViewEmergencyMain);
        ButtonEmergencyMainSend = (Button) findViewById(R.id.ButtonEmergencyMainSend);
        linearLayoutManager = new LinearLayoutManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.hide();
        //Recycler View Part
        RecyclerViewEmergencyMain.setLayoutManager(linearLayoutManager);
        emergencyRecyclerViewAdapter = new EmergencyRecyclerViewAdapter(this,templates,templates.length);
        RecyclerViewEmergencyMain.setAdapter(emergencyRecyclerViewAdapter);

        //Button CLick:
        ButtonEmergencyMainSend.setOnClickListener(this);

    }

    @Override
    public void onListItemClick(int clickedItemIndex, int viewCode, String data) {
        if (viewCode == 0){
            this.data = data;//Data is the string in the textViewH_MartRecyclerViewItem
            TextViewEmergencyMain.setText(data);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == ButtonEmergencyMainSend){
            sendSms("Sending Emergency Message",data);
        }
    }

    //Used the below method to simulate a sms sent action
    private void showProgressBar(String title,String message){
        final int interval = 2000; // 1 Second
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.show();
        Handler handler = new Handler();
        Runnable runnable = new Runnable(){
            public void run() {
                progressDialog.dismiss();
                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));

            }
        };
        handler.postAtTime(runnable, System.currentTimeMillis()+interval);
        handler.postDelayed(runnable, interval);
    }

    //The actual action of sending sms, using OKHttp!
    private void sendSms(String title, String message){
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.show();
        OkHttpClient client = new OkHttpClient();
        String url = "https://api.twilio.com/2010-04-01/Accounts/"+ACCOUNT_SID+"/Messages";
        String base64EncodedCredentials = "Basic " + Base64.encodeToString((ACCOUNT_SID + ":" + AUTH_TOKEN).getBytes(), Base64.NO_WRAP);
        String fromPhoneNumber = "+12565105876";
        String toPhoneNumber = "+919553344488";
        RequestBody body = new FormBody.Builder()
                .add("From", fromPhoneNumber)
                .add("To", toPhoneNumber)
                .add("Body", message)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Authorization", base64EncodedCredentials)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        MainActivity.setToast("Failed to send Sms!", getApplicationContext());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String responseStr = response.body().string();
                    Log.v(TAG,responseStr);
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();
                            MainActivity.setToast("Sent Sms!", getApplicationContext());
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            finish();
                        }
                    });
                }
            }
        });
    }
}
