package com.example.aditya.menuview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

public class H_MartMainActivity extends AppCompatActivity implements H_MartRecyclerViewAdapter.ListItemClickListenerForH_Mart {
    private RecyclerView RecyclerViewH_MartMain;
    private LinearLayoutManager linearLayoutManager;
    private H_MartRecyclerViewAdapter hMartRecyclerViewAdapter;
    //Download the list of categories from server, there must be two arrays, one for name of the category and the other for images
    public static String[] hMartRecyclerViewTextArray = {"Fruits & Vegetables","Groceries & Staples","Bread, Dairy & Eggs", "Beverages"};
    public static int[] hMartRecyclerViewImageIDs ={R.drawable.fruits_vegetables,R.drawable.groceries,R.drawable.bread,R.drawable.beverages};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h__mart_main);
        setTitle("H Mart");

        RecyclerViewH_MartMain = (RecyclerView) findViewById(R.id.RecyclerViewH_MartLowerDetails);
        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        RecyclerViewH_MartMain.setLayoutManager(linearLayoutManager);
        hMartRecyclerViewAdapter = new H_MartRecyclerViewAdapter(this,hMartRecyclerViewTextArray,hMartRecyclerViewImageIDs,hMartRecyclerViewImageIDs.length);
        RecyclerViewH_MartMain.setAdapter(hMartRecyclerViewAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_h_mart_action_bar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.action_search_main_hmart)
            MainActivity.setToast("H Mart Search", getApplicationContext());
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnListItemClickListenerForH_Mart(int clickedItemIndex, String data) {
        Intent hMartIntent = new Intent(getApplicationContext(),H_MartProductSelection.class);
        hMartIntent.putExtra("clickedItemIndex",clickedItemIndex);
        startActivity(hMartIntent);
    }
}
