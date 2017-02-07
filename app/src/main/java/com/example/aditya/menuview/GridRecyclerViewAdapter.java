package com.example.aditya.menuview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by aditya on 06-02-2017.
 */


public class GridRecyclerViewAdapter extends RecyclerView.Adapter<GridRecyclerViewAdapter.ViewHolderClass> {
    //Create List Data Arrays here:
    private String[] SubHeadingArray;
    private int[] IconImageArray;
    private int NumberOfItems;
    final private ListItemClickListener mOnClickListener;

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex, int viewCode);
    }

    //Add further List Data in the form of arrays here:
    GridRecyclerViewAdapter(int[] IconImageArray, String[] SubHeadingArray, ListItemClickListener listener){
        this.IconImageArray=IconImageArray;
        this.SubHeadingArray=SubHeadingArray;
        NumberOfItems = IconImageArray.length;
        mOnClickListener = listener;
    }

    @Override
    public ViewHolderClass onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolderClass viewHolder;
        View view;
        LayoutInflater layoutInflater;
        Context context;

        context = parent.getContext();
        layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.grid_list_item,parent,false);
        viewHolder = new ViewHolderClass(view);
        return viewHolder;
        //OR
        //You can just implement this one line: return new ViewHolderClass(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_item,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolderClass holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return NumberOfItems;
    }

    //The ViewHolder Class:
    class ViewHolderClass extends RecyclerView.ViewHolder {
        //Instantiate the various view elements here:
        TextView SubHeading;
        ImageView IconImage;
        ViewHolderClass(View view){
            super(view);
            //Initialize those view Instances Here:
            IconImage = (ImageView)view.findViewById(R.id.ListImageView);
            SubHeading = (TextView)view.findViewById(R.id.ListTextView);

            IconImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int clickedPosition = getAdapterPosition();
                    mOnClickListener.onListItemClick(clickedPosition, 0);
                }
            });

            SubHeading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int clickedPosition = getAdapterPosition();
                    mOnClickListener.onListItemClick(clickedPosition, 1);
                }
            });

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int clickedPosition = getAdapterPosition();
                    mOnClickListener.onListItemClick(clickedPosition, 2);
                }
            });
        }

        void bind(int Position){
            //Assign the data to those view Instances Here:
            IconImage.setImageResource(IconImageArray[Position]);
            SubHeading.setText(SubHeadingArray[Position]);
        }


    }
}
