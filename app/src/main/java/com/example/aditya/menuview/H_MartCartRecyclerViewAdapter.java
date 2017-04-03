package com.example.aditya.menuview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aditya on 02-04-2017.
 */

public class H_MartCartRecyclerViewAdapter extends RecyclerView.Adapter<H_MartCartRecyclerViewAdapter.CartViewHolderClass>{
    private final int NumberOfItems;
    private final ArrayList<String> namesArray;
    private final ArrayList<Integer> imagesArray;
    private final ArrayList<String> quantityArrayString;
    private final ArrayList<Integer> quantityArrayInt;
    private final ArrayList<Integer> pricePerProduct;
    private final H_MartCartItemClickeListener h_martCartItemClickeListener;
    final Context context;

    public H_MartCartRecyclerViewAdapter(int numberOfItems, ArrayList<String> namesArray, ArrayList<Integer> imagesArray, ArrayList<String> quantityArrayString, ArrayList<Integer> quantityArrayInt, ArrayList<Integer> pricePerProduct, H_MartCartItemClickeListener h_martCartItemClickeListener, Context context) {
        NumberOfItems = numberOfItems;
        this.namesArray = namesArray;
        this.imagesArray = imagesArray;
        this.quantityArrayString = quantityArrayString;
        this.quantityArrayInt = quantityArrayInt;
        this.pricePerProduct = pricePerProduct;
        this.h_martCartItemClickeListener = h_martCartItemClickeListener;
        this.context = context;
    }

    public interface H_MartCartItemClickeListener {
        void onH_MartCartItemClicked(int ViewId, String data);
    }
    @Override
    public CartViewHolderClass onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.h_mart_cart_recycler_view_item,parent,false);
        return new CartViewHolderClass(view);
    }

    @Override
    public void onBindViewHolder(CartViewHolderClass holder, int position) {
        holder.bind(holder.getAdapterPosition());
    }

    @Override
    public int getItemCount() {
        return namesArray.size();
    }

    public void deleteItem(int position){
        namesArray.remove(position);
        imagesArray.remove(position);
        quantityArrayString.remove(position);
        quantityArrayInt.remove(position);
        pricePerProduct.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,namesArray.size());
    }

    public int totalBill(){
        int totalBill = 0;
        for (int i = 0;i<getItemCount();i++){
            totalBill+=pricePerProduct.get(i)*quantityArrayInt.get(i);
        }
        return totalBill;
    }

    class CartViewHolderClass extends RecyclerView.ViewHolder{
        private ImageView productImage;
        private TextView productName, productPrice, productQuantity;
        private Button deleteButton;

        public CartViewHolderClass(final View itemView) {
            super(itemView);
            productImage = (ImageView) itemView.findViewById(R.id.imageViewH_MartCartRecyclerViewItem);
            productName = (TextView) itemView.findViewById(R.id.textViewH_MartCartProductName);
            productPrice = (TextView) itemView.findViewById(R.id.textViewH_MartCartProductPrice);
            productQuantity = (TextView) itemView.findViewById(R.id.textViewH_MartCartQuantity);
            deleteButton = (Button) itemView.findViewById(R.id.buttonH_MartCartDelete);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteItem(getAdapterPosition());
                    h_martCartItemClickeListener.onH_MartCartItemClicked(deleteButton.getId(),deleteButton.getText().toString());
                }
            });
        }

        public void bind(int bindPosition){
            productImage.setImageResource(imagesArray.get(bindPosition));
            productName.setText(namesArray.get(bindPosition));
            int totalPrice = quantityArrayInt.get(bindPosition) * pricePerProduct.get(bindPosition);
            productPrice.setText("Price: " + totalPrice);
            productQuantity.setText(quantityArrayString.get(bindPosition));
        }
    }
}
