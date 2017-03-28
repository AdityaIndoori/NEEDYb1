package com.example.aditya.menuview;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by aditya on 22-03-2017.
 */

public class EmergencyRecyclerViewAdapter extends RecyclerView.Adapter<EmergencyRecyclerViewAdapter.ViewHolderClass>{

    final private ListItemClickListener mOnClickListener;
    private String[] templatesArray;
    private int NumberOfItems;

    public EmergencyRecyclerViewAdapter(ListItemClickListener mOnClickListener, String[] templatesArray, int numberOfItems) {
        this.mOnClickListener = mOnClickListener;
        this.templatesArray = templatesArray;
        NumberOfItems = numberOfItems;
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex, int viewCode, String data);
    }
    @Override
    public ViewHolderClass onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolderClass viewHolder;
        View view;
        LayoutInflater layoutInflater;
        Context context;

        context = parent.getContext();
        layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.emergency_recycler_view_item,parent,false);
        viewHolder = new ViewHolderClass(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderClass holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return NumberOfItems;
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder{
        TextView template;

        public ViewHolderClass(View itemView) {
            super(itemView);
            template = (TextView) itemView.findViewById(R.id.textViewEmergencyRecyclerViewItem);
            template.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickedPosition = getAdapterPosition();
                    mOnClickListener.onListItemClick(clickedPosition, 0, template.getText().toString());
                }
            });
        }

        void bind(int Position){
            //Assign the data to those view Instances Here:
            template.setText(templatesArray[Position]);
        }

    }
}
