package com.example.aditya.menuview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by aditya on 07-02-2017.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    final int NUM_PAGES;


    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex, int viewCode);
    }

    public ViewPagerAdapter(FragmentManager fm, int num_pages) {
        super(fm);
        NUM_PAGES = num_pages;
    }

    @Override
    public Fragment getItem(int position) {
        return ViewPagerFragment.create(position);
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

}