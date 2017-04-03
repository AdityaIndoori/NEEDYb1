package com.example.aditya.menuview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class H_MartDeliveryOptions extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout relativeLayoutStdDel,relativeLayoutExpDel;
    private TextView textViewNumberOfItemsSTD,getTextViewNumberOfItemsEXP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Delivery Options");
        setContentView(R.layout.activity_h__mart_delivery_options);
        relativeLayoutStdDel = (RelativeLayout) findViewById(R.id.relativeLayoutStdDelivery);
        relativeLayoutExpDel = (RelativeLayout) findViewById(R.id.relativeLayoutExpDelivery);
        textViewNumberOfItemsSTD = (TextView) findViewById(R.id.standardDeliveryNumberOfItems);
        getTextViewNumberOfItemsEXP = (TextView) findViewById(R.id.numberOfItemsExpDelivery);
        relativeLayoutExpDel.setOnClickListener(this);
        relativeLayoutStdDel.setOnClickListener(this);
        int numberOfItems = getIntent().getIntExtra("items",0);
        textViewNumberOfItemsSTD.setText(numberOfItems + " Items");
        getTextViewNumberOfItemsEXP.setText(numberOfItems + " Items");
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.relativeLayoutStdDelivery)
            startActivity(new Intent(getApplicationContext(),H_MartPaymentOptions.class).putExtra("isExpress",false).putExtra("bill",getIntent().getIntExtra("bill",0)).putExtra("items",getIntent().getIntExtra("items",0)));
        else if (id == R.id.relativeLayoutExpDelivery)
            startActivity(new Intent(getApplicationContext(),H_MartPaymentOptions.class).putExtra("isExpress",true).putExtra("bill",getIntent().getIntExtra("bill",0)).putExtra("items",getIntent().getIntExtra("items",0)));
    }
}
