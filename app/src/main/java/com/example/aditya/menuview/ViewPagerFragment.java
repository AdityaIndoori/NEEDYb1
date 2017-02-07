package com.example.aditya.menuview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import static com.example.aditya.menuview.MainActivity.toast;

/**
 * Created by aditya on 07-02-2017.
 */

public class ViewPagerFragment extends Fragment {
    private int mPageNumber;
    public static final String ARG_PAGE = "page";
    int ImgResId[] = {R.drawable.advertising,R.drawable.advertising2,R.drawable.advertising3,R.drawable.advertising4};

    //Constructs a new fragment for the given page number.
    public static ViewPagerFragment create(int mPageNumber){
        ViewPagerFragment fragment = new ViewPagerFragment();
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
        ViewGroup viewGroup = (ViewGroup)inflater.inflate(R.layout.fragment_screen_view_pager,container,false);
        ImageView imageView =(ImageView)viewGroup.findViewById(R.id.ImageViewInFragment);
        imageView.setImageResource(ImgResId[mPageNumber]);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int PgNo = mPageNumber+1;
                if (toast != null)
                    toast.cancel();
                toast=Toast.makeText(getContext(),"The Advertisement number is: " + PgNo,Toast.LENGTH_LONG);
                toast.show();
            }
        });
        return  viewGroup;
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return mPageNumber;
    }
}
