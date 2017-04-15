package com.example.aditya.menuview;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class WOR_CarDetailsActivity extends AppCompatActivity {
    private ImageView IVicon;
    private TextView TVbrand,TVmodel,TVmileage,TVseating,TVbasePrice,TVtax,TVrent,TVmiscPrice,TVtotalPrice;
    private Button BTconfirmOrder;
    private Spinner rent;
    private String[] brandStr,modelStr,mileageStr,seatingStr;
    private String [][] spinnerArray;
    private int[][] spinnerData;
    private int[] imageID,basePriceInt,taxInt,miscInt;
    private int totalInt, carPosition,spinnerPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wor__car_details);
        setTitle("Details");

        carPosition = getIntent().getIntExtra("position", 0);
        IVicon = (ImageView) findViewById(R.id.iv_wor_car_details);
        TVbrand = (TextView) findViewById(R.id.tv_wor_car_details_brand);
        TVmodel = (TextView) findViewById(R.id.tv_wor_car_details_model);
        TVmileage = (TextView) findViewById(R.id.tv_wor_car_details_mileage);
        TVseating = (TextView) findViewById(R.id.tv_wor_car_details_seating);
        TVbasePrice = (TextView) findViewById(R.id.tv_wor_car_details_base);
        TVtax = (TextView) findViewById(R.id.tv_wor_car_details_tax);
        TVrent = (TextView) findViewById(R.id.tv_wor_car_details_rent);
        TVmiscPrice = (TextView) findViewById(R.id.tv_wor_car_details_misc);
        TVtotalPrice = (TextView) findViewById(R.id.tv_wor_car_details_total);
        rent = (Spinner) findViewById(R.id.spinner_wor_car_details_rent);
        BTconfirmOrder = (Button) findViewById(R.id.bt_wor_car_details);

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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, spinnerArray[carPosition]);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        rent.setAdapter(adapter);
        IVicon.setImageResource(imageID[carPosition]);
        TVbrand.setText(brandStr[carPosition]);
        TVmodel.setText(modelStr[carPosition]);
        TVmileage.setText(mileageStr[carPosition]);
        TVseating.setText(seatingStr[carPosition]);
        TVbasePrice.setText(indianCurrency(basePriceInt[carPosition]));
        TVtax.setText(indianCurrency(taxInt[carPosition]));
        TVmiscPrice.setText(indianCurrency(miscInt[carPosition]));
        TVtotalPrice.setText(indianCurrency(totalInt));

        rent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TVrent.setText(indianCurrency(spinnerData[carPosition][position]));
                totalInt = basePriceInt[carPosition] + taxInt[carPosition] + miscInt[carPosition] + spinnerData[carPosition][position];
                TVtotalPrice.setText(indianCurrency(totalInt));
                spinnerPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        BTconfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),WOR_CartActivity.class);
                intent.putExtra("CarPosition",carPosition);
                intent.putExtra("SpinnerPosition",spinnerPosition);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_wor__main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

            switch (item.getItemId()) {
                case android.R.id.home:
                    NavUtils.navigateUpFromSameTask(this);
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    public static String indianCurrency(int amount){
        return new DecimalFormat("##,##,##0").format(amount);
    }
}
