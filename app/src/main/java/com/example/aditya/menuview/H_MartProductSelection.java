package com.example.aditya.menuview;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
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

import java.util.ArrayList;

public class H_MartProductSelection extends AppCompatActivity {
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
    public static FloatingActionButton floatingActionButtonHMartCart;

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
        setTitle("H Mart");
        floatingActionButtonHMartCart = (FloatingActionButton) findViewById(R.id.floatingActionButtonHMartCart);
        floatingActionButtonHMartCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),H_MartCartActivity.class));
            }
        });
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
        private RecyclerView recyclerViewH_MartPS;
        private H_MartPSRecyclerViewAdapter hMartPSRecyclerViewAdapter;
        private LinearLayoutManager linearLayoutManager;
        private DatabaseSqlClass.NeedyProductDbHelper databaseHelper;
        int numberOfItems;
        ArrayList<String> namesArray;
        ArrayList<Integer> imagesArray;
        ArrayList<String[]> spinnerArray;
        ArrayList<Integer[]> spinnerArrayData;
        ArrayList<Integer> pricePerProduct;
        private String[] tableNames = new String[]{
                DatabaseSqlClass.FruitsVegetablesTable.TABLE_NAME_FRUITS_VEGETABLES,
                DatabaseSqlClass.GroceriesAndStaples.TABLE_NAME_GROCERIES_STAPLES,
                DatabaseSqlClass.BreadDairyAndEggs.TABLE_NAME_BREAD_DAIRY_EGGS,
                DatabaseSqlClass.Beverages.TABLE_NAME_BEVERAGES
        };
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
            databaseHelper = new DatabaseSqlClass.NeedyProductDbHelper(this.getActivity().getApplicationContext());
            View rootView = inflater.inflate(R.layout.fragment_h_mart_product_selection, container, false);

            int categoryPosition = getArguments().getInt(ARG_SECTION_NUMBER);
            /*TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));*/
            recyclerViewH_MartPS = (RecyclerView) rootView.findViewById(R.id.hMartPSRecyclerView);
            linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
            recyclerViewH_MartPS.setLayoutManager(linearLayoutManager);

            /*numberOfItems = 4;
            namesArray = new ArrayList<>(Arrays.asList("Onions","Rice","Tuborg","Pizza Base"));
            imagesArray= new ArrayList<>(Arrays.asList(R.drawable.onion,R.drawable.rice,R.drawable.tuborg,R.drawable.pizzabase));
            spinnerArray = new ArrayList<>(Arrays.asList(new String[]{"1KG","2KG","3KG","4KG"},new String[]{"10 KiloGrams","20 Kilograms","30 Kilograms","40 Kilos"},new String[]{"1 Bottle","3 Bottles"," 5 Bottles","6 Bottles"},new String[]{"1 base","3 Bases","5 Bases","10 Bases"}));
            spinnerArrayData = new ArrayList<>(Arrays.asList(new Integer[]{1,2,3,4},new Integer[]{10,20,30,40},new Integer[]{1,3,5,6},new Integer[]{1,3,5,10}));
            pricePerProduct  = new ArrayList<>(Arrays.asList(10,30,70,40));
*/
            numberOfItems = databaseHelper.numberOfItems(tableNames[categoryPosition]);
            namesArray = databaseHelper.getItemNamesFromTable(tableNames[categoryPosition]);
            imagesArray = databaseHelper.getImageIDFromTable(tableNames[categoryPosition]);
            spinnerArray = databaseHelper.getQuantityStringArray(tableNames[categoryPosition]);
            spinnerArrayData = databaseHelper.getQuantiyIntArray(tableNames[categoryPosition]);
            pricePerProduct = databaseHelper.getPriceFromTable(tableNames[categoryPosition]);
            hMartPSRecyclerViewAdapter = new H_MartPSRecyclerViewAdapter(numberOfItems,namesArray,imagesArray,spinnerArray,spinnerArrayData,pricePerProduct,this,getContext());
            recyclerViewH_MartPS.setAdapter(hMartPSRecyclerViewAdapter);
            return rootView;
        }
        @Override
        public void onH_MartProductClicked(int clickedItemIndex, String data) {
            MainActivity.setToast("Added to cart",getContext());
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
