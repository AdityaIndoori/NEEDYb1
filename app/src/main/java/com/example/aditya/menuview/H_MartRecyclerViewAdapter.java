package com.example.aditya.menuview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by aditya on 28-03-2017.
 */

public class H_MartRecyclerViewAdapter extends RecyclerView.Adapter<H_MartRecyclerViewAdapter.ViewHolderClass>{
    final private ListItemClickListenerForH_Mart mOnClickListener;
    final private String[] stringsArray;
    final private int[] imageViewID;
    final private int numberOfItems;

    public H_MartRecyclerViewAdapter(ListItemClickListenerForH_Mart mOnClickListener, String[] stringsArray, int[] imageViewID, int numberOfItems) {
        this.mOnClickListener = mOnClickListener;
        this.stringsArray = stringsArray;
        this.imageViewID = imageViewID;
        this.numberOfItems = numberOfItems;
    }

    @Override
    public ViewHolderClass onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolderClass viewHolderClass;
        View view;
        LayoutInflater layoutInflater;
        Context context;

        context = parent.getContext();
        layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.h_mart_recycler_view_item,parent,false);
        viewHolderClass = new ViewHolderClass(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(ViewHolderClass holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return numberOfItems;
    }


    public interface ListItemClickListenerForH_Mart{
        public void OnListItemClickListenerForH_Mart(int clickedItemIndex, String data);
    }
    public class ViewHolderClass extends RecyclerView.ViewHolder {
        ImageView imageViewH_MartRecyclerViewItem;
        TextView textViewH_MartRecyclerViewItem;
        RelativeLayout relativeLayoutH_MartRecyclerViewItem;
        public ViewHolderClass(View itemView) {
            super(itemView);
            imageViewH_MartRecyclerViewItem = (ImageView) itemView.findViewById(R.id.imageViewH_MartRecyclerView);
            textViewH_MartRecyclerViewItem = (TextView) itemView.findViewById(R.id.textViewH_MartRecyclerView);
            relativeLayoutH_MartRecyclerViewItem = (RelativeLayout) itemView.findViewById(R.id.relativeLayoutH_MartRecyclerViewItem);
            relativeLayoutH_MartRecyclerViewItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    mOnClickListener.OnListItemClickListenerForH_Mart(position, textViewH_MartRecyclerViewItem.getText().toString());
                }
            });
        }
        void bind(int position){
            textViewH_MartRecyclerViewItem.setText(stringsArray[position]);
            imageViewH_MartRecyclerViewItem.setImageResource(imageViewID[position]);
        }
    }
}
