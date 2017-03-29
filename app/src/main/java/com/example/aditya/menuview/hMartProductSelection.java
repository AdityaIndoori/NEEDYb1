package com.example.aditya.menuview;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

public class hMartProductSelection extends AppCompatActivity {
    private int clickedItemIndex;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h_mart_product_selection);
        clickedItemIndex = getIntent().getIntExtra("clickedItemIndex",0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(clickedItemIndex,true);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(mViewPager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_h_mart_product_selection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements H_MartPSRecyclerViewAdapter.onH_MartProductClickListener {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private String[] fruitsAndVegetablesArray = new String[]{
                "Onion-Medium"
                ,"Chilly Green Big"
                ,"Capsicum"
                ,"Tomatoes"
                };
        private String[] groceriesAndStaples = new String[]{
                "Tomato sauce",
                "Mustard",
                "Barbecue sauce",
                "Red-wine vinegar",
                };
        private String[] breadDairyAndEggs = new String[]{
                "Whole Wheat Bread"
                ,"Toned Milk"
                ,"Milk Bread"
                ,"Eggs"};
        private String[] beverages = new String[]{
                "Fanta",
                "Grape Wine",
                "Aqua-Panna",
                "Shirley Temple"};
        private String[][] categoryNamesArray = new String[][]{fruitsAndVegetablesArray,
                groceriesAndStaples,
                breadDairyAndEggs,
                beverages};
        private String[] OnionMediumQuantity = {"1","2","3","4"};
        private String[] ChillyGreenBigQuantity = {"2","4","6","8"};
        private String[] Capsicum = {"1","3","5","7"};
        private String[] Tomatoes = {"1","2","3","4"};
        private String[][] fruitsAndVegetablesArrayQantity = new String[][]{
                OnionMediumQuantity,
                ChillyGreenBigQuantity,
                Capsicum,
                Tomatoes};
        private String[][] groceriesAndStaplesQuantity = new String[][]{
                OnionMediumQuantity,
                ChillyGreenBigQuantity,
                Capsicum,
                Tomatoes};
        private String[][] breadDairyAndEggsQuantity = new String[][]{
                OnionMediumQuantity,
                ChillyGreenBigQuantity,
                Capsicum,
                Tomatoes};
        private String[][] beveragesQuantity = new String[][]{
                OnionMediumQuantity,
                ChillyGreenBigQuantity,
                Capsicum,
                Tomatoes};
        private String[][][] spinnerQuantitiesArray = new String[][][]{fruitsAndVegetablesArrayQantity
                ,groceriesAndStaplesQuantity
                ,breadDairyAndEggsQuantity
                ,beveragesQuantity};
        private int[] fruitsAndVegetablesArrayPrice = {5,10,10,5};
        private int[] groceriesAndStaplesPrice = {10,5,5,10};
        private int[] breadDairyAndEggsPrice = {5,10,5,10};
        private int[] beveragesPrice = {10,5,10,5};
        private int[][] pricePerProduct = new int[][]{fruitsAndVegetablesArrayPrice
                ,groceriesAndStaplesPrice
                ,breadDairyAndEggsPrice
                ,beveragesPrice};
        private RecyclerView recyclerViewH_MartPS;
        private H_MartPSRecyclerViewAdapter hMartPSRecyclerViewAdapter;
        private LinearLayoutManager linearLayoutManager;
        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_h_mart_product_selection, container, false);
            int categoryPosition = getArguments().getInt(ARG_SECTION_NUMBER);
            /*TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));*/
            recyclerViewH_MartPS = (RecyclerView) rootView.findViewById(R.id.hMartPSRecyclerView);
            linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
            recyclerViewH_MartPS.setLayoutManager(linearLayoutManager);
            hMartPSRecyclerViewAdapter = new H_MartPSRecyclerViewAdapter(categoryNamesArray[categoryPosition].length,
                    categoryNamesArray[categoryPosition],
                    spinnerQuantitiesArray[categoryPosition],
                    pricePerProduct[categoryPosition],
                    this,
                    getContext());
            recyclerViewH_MartPS.setAdapter(hMartPSRecyclerViewAdapter);
            return rootView;
        }
        @Override
        public void onH_MartProductClicked(int clickedItemIndex, String data) {
            MainActivity.setToast("Position: " + clickedItemIndex + " Name: " + data,getContext());
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return H_MartMainActivity.hMartRecyclerViewImageIDs.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return H_MartMainActivity.hMartRecyclerViewTextArray[position];
        }
    }
}
