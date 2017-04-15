package com.example.aditya.menuview;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.aditya.menuview.WOR_CarDetailsActivity.indianCurrency;

public class WOR_CartActivity extends AppCompatActivity {
    private ImageView IVicon;
    private TextView TVbrand,TVmodel,TVmileage,TVseating,TVbasePrice,TVtax,TVrent,TVmiscPrice,TVtotalPrice,rent;
    private Button BTcheckOut;
    private String[] brandStr,modelStr,mileageStr,seatingStr;
    private String [][] spinnerArray;
    private int[][] spinnerData;
    private int[] imageID,basePriceInt,taxInt,miscInt;
    private int totalInt, carPosition,spinnerPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wor__cart);
        setTitle("Cart");

        carPosition = getIntent().getIntExtra("CarPosition", 0);
        spinnerPosition = getIntent().getIntExtra("SpinnerPosition", 0);

        IVicon = (ImageView) findViewById(R.id.iv_wor_car_cart);
        TVbrand = (TextView) findViewById(R.id.tv_wor_car_cart_brand);
        TVmodel = (TextView) findViewById(R.id.tv_wor_car_cart_model);
        TVmileage = (TextView) findViewById(R.id.tv_wor_car_cart_mileage);
        TVseating = (TextView) findViewById(R.id.tv_wor_car_cart_seating);
        TVbasePrice = (TextView) findViewById(R.id.tv_wor_car_cart_base);
        TVtax = (TextView) findViewById(R.id.tv_wor_car_cart_tax);
        TVrent = (TextView) findViewById(R.id.tv_wor_car_cart_rent_price);
        TVmiscPrice = (TextView) findViewById(R.id.tv_wor_car_cart_misc);
        TVtotalPrice = (TextView) findViewById(R.id.tv_wor_car_cart_total);
        rent = (TextView) findViewById(R.id.tv_wor_car_cart_rent_details);
        BTcheckOut = (Button) findViewById(R.id.bt_wor_car_cart);

        brandStr = new String[]{"Toyota", "Chevrolet"};
        modelStr = new String[]{"Yaris iA 2017", "Cruze 2017"};
        mileageStr = new String[]{"30 kmpl", "25 kmpl"};
        seatingStr = new String[]{"5 Seater", "5 Seater"};
        spinnerArray = new String[][]{{"1 Day : Rs. 1,250 /-", "2 Days : Rs. 2,200 /-", "4 Days : Rs. 4,000/-"},{"1 Day : Rs. 1,500 /-", "2 Days : Rs. 2,450/-", "4 Days : Rs. 4,300/-"}};

        imageID = new int[]{R.drawable.yaris_main, R.drawable.cruz_main};
        basePriceInt = new int[]{3000, 3200};
        taxInt = new int[]{2500, 2000};
        miscInt = new int[]{1000, 1200};
        spinnerData = new int[][]{{1250, 2200, 4000},{1500,2450,4300}};

        IVicon.setImageResource(imageID[carPosition]);
        TVbrand.setText(brandStr[carPosition]);
        TVmodel.setText(modelStr[carPosition]);
        TVmileage.setText(mileageStr[carPosition]);
        TVseating.setText(seatingStr[carPosition]);
        TVbasePrice.setText(indianCurrency(basePriceInt[carPosition]));
        TVtax.setText(indianCurrency(taxInt[carPosition]));
        TVmiscPrice.setText(indianCurrency(miscInt[carPosition]));
        TVtotalPrice.setText(indianCurrency(totalInt));
        rent.setText(spinnerArray[carPosition][spinnerPosition]);

        TVrent.setText(indianCurrency(spinnerData[carPosition][spinnerPosition]));
        totalInt = basePriceInt[carPosition] + taxInt[carPosition] + miscInt[carPosition] + spinnerData[carPosition][spinnerPosition];
        TVtotalPrice.setText(indianCurrency(totalInt));

        BTcheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),WOR_PaymentOptions.class).putExtra("bill",totalInt).putExtra("items",1));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
