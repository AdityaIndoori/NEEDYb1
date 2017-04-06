package com.example.aditya.menuview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * Created by aditya on 07-02-2017.
 */

public class MainActivityAdvertisingViewPagerFragment extends Fragment {
    public static final String ARG_PAGE = "page";
    int ImgResId[] = {R.drawable.ads1, R.drawable.ads2, R.drawable.ads3, R.drawable.ads4};
    private int mPageNumber;

    //Constructs a new fragment for the given page number.
    public static MainActivityAdvertisingViewPagerFragment create(int mPageNumber) {
        MainActivityAdvertisingViewPagerFragment fragment = new MainActivityAdvertisingViewPagerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, mPageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.main_activity_fragment_screen_view_pager, container, false);
        ImageView imageView = (ImageView) viewGroup.findViewById(R.id.ImageViewInFragment);
        imageView.setImageResource(ImgResId[mPageNumber]);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.setToast("The Advertisement number is: " + mPageNumber, getContext());
            }
        });
        return viewGroup;
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return mPageNumber;
    }
}
