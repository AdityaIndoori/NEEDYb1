package com.example.aditya.menuview;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EmergencyMainActivity extends AppCompatActivity implements EmergencyRecyclerViewAdapter.ListItemClickListener, View.OnClickListener {

    private TextView TextViewEmergencyMain;
    private RecyclerView RecyclerViewEmergencyMain;
    private Button ButtonEmergencyMainSend;
    private LinearLayoutManager linearLayoutManager;
    private EmergencyRecyclerViewAdapter emergencyRecyclerViewAdapter;
    private String[] templates = {"Template 1","Template 2","Template 3","Template 4"};
    private String data;
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
            showProgressBar("Sending Emergency Message",data);
        }
    }

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
}
