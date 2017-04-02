package com.example.aditya.menuview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aditya on 29-03-2017.
 */

public class H_MartPSRecyclerViewAdapter extends RecyclerView.Adapter<H_MartPSRecyclerViewAdapter.ViewHolderClass>{
    private final int NumberOfItems;
    private final ArrayList<String> namesArray;
    private final ArrayList<Integer> imagesArray;
    private final ArrayList<String[]> spinnerArray;
    private final ArrayList<Integer[]> spinnerArrayData;
    private final ArrayList<Integer> pricePerProduct;
    private final onH_MartProductClickListener productClickListener;
    final Context context;
    final String TAG = "ADAPTER";

    public interface onH_MartProductClickListener{
        void onH_MartProductClicked(int clickedItemIndex, String data);
    }
    public H_MartPSRecyclerViewAdapter(int numberOfItems, ArrayList<String> namesArray, ArrayList<Integer> imagesArray, ArrayList<String[]> spinnerArray, ArrayList<Integer[]> spinnerArraydata, ArrayList<Integer> pricePerProduct, onH_MartProductClickListener productClickListener, Context context) {
        NumberOfItems = numberOfItems;
        this.namesArray = namesArray;
        this.imagesArray = imagesArray;
        this.spinnerArray = spinnerArray;
        this.spinnerArrayData = spinnerArraydata;
        this.pricePerProduct = pricePerProduct;
        this.productClickListener = productClickListener;
        this.context = context;
    }


    @Override
    public ViewHolderClass onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.h_mart_ps_recycler_view_item,parent,false);
        return new ViewHolderClass(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderClass holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return NumberOfItems;
    }

    class ViewHolderClass extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productPrice;
        Spinner productQuantity;
        Button addButton;
        public ViewHolderClass(View itemView) {
            super(itemView);
            productImage = (ImageView) itemView.findViewById(R.id.imageViewH_MartPSRecyclerViewItem);
            productName = (TextView) itemView.findViewById(R.id.textViewH_MartProductName);
            productQuantity = (Spinner) itemView.findViewById(R.id.spinnerH_MartPSRecyclerViewItem);
            productPrice = (TextView) itemView.findViewById(R.id.textViewH_MartProductPrice);
            addButton = (Button) itemView.findViewById(R.id.buttonH_MartPSAdd);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int position = getAdapterPosition();
                    productClickListener.onH_MartProductClicked(position,productName.getText().toString());
                }
            });
        }
        public void bind(final int position){
            //Bind Data Here
            productImage.setImageResource(imagesArray.get(position));
            productName.setText(namesArray.get(position));
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, spinnerArray.get(position));
            productQuantity.setAdapter(adapter);

            productQuantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position2, long id) {
                    Integer[] quantities = spinnerArrayData.get(position);
                    int quantity = quantities[parent.getSelectedItemPosition()];
                    int totalPrice = quantity * pricePerProduct.get(position);
                    String str = "₹ " + totalPrice + "/-";
                    productPrice.setText("Price: " + str);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    productPrice.setText("No Selection");
                }
            });
        }
    }
}