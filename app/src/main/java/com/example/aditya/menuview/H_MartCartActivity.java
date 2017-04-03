package com.example.aditya.menuview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.aditya.menuview.R.id.TextViewHMartCartBill;
import static com.example.aditya.menuview.R.id.TextViewHMartCartDiscounts;

public class H_MartCartActivity extends AppCompatActivity implements H_MartCartRecyclerViewAdapter.H_MartCartItemClickeListener {

    private TextView textViewCartBill,textViewCartDiscount;
    private RecyclerView recyclerViewCartItems;
    private LinearLayoutManager linearLayoutManager;
    private H_MartCartRecyclerViewAdapter cartRecyclerViewAdapter;
    private Button confirmButton;
    private final ArrayList<String> namesArray = new ArrayList<String>(Arrays.asList("Onions","Milk","Tuborg","Flour"));
    private final ArrayList<Integer> imagesArray = new ArrayList<Integer>(Arrays.asList(R.drawable.onion,R.drawable.milk,R.drawable.tuborg,R.drawable.flour));
    private final ArrayList<String> quantityArrayString = new ArrayList<>(Arrays.asList("2 KG","4 CARTONS","3 BOTTLES","5 KG"));
    private final ArrayList<Integer> quantityArrayInt = new ArrayList<Integer>(Arrays.asList(2,4,3,5));
    private final ArrayList<Integer> pricePerProduct = new ArrayList<>(Arrays.asList(10,15,60,40));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h__mart_cart);
        textViewCartBill = (TextView) findViewById(R.id.TextViewHMartCartBill);
        textViewCartDiscount = (TextView) findViewById(TextViewHMartCartDiscounts);
        recyclerViewCartItems = (RecyclerView) findViewById(R.id.RecyclerViewHMartCartItems);
        confirmButton = (Button) findViewById(R.id.buttonH_MartCartConfirm);

        linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        cartRecyclerViewAdapter = new H_MartCartRecyclerViewAdapter(4,namesArray,imagesArray,quantityArrayString,quantityArrayInt,pricePerProduct,this,getApplicationContext());
        recyclerViewCartItems.setLayoutManager(linearLayoutManager);
        recyclerViewCartItems.setAdapter(cartRecyclerViewAdapter);
        int totalBill = cartRecyclerViewAdapter.totalBill();
        textViewCartBill.setText(cartRecyclerViewAdapter.getItemCount() + " Items" + " - " + "₹. " + totalBill + "/-");
        setTitle("Review Order");
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),H_MartDeliveryOptions.class).putExtra("items",cartRecyclerViewAdapter.getItemCount()).putExtra("bill",cartRecyclerViewAdapter.totalBill()));
            }
        });
    }

    @Override
    public void onH_MartCartItemClicked(int ViewId, String data) {
        textViewCartBill.setText(cartRecyclerViewAdapter.getItemCount() + " Items" + " - " + "₹. " + cartRecyclerViewAdapter.totalBill() + "/-");
    }
}
