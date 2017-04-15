package com.example.aditya.menuview;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
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

import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import static com.example.aditya.menuview.R.id.container;

public class WOR_MainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_wor__main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Wheels On Rent");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_wor__main, menu);
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
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        private ImageView ivCarMain, ivCarBack, ivCarFront, ivCarSide,ivCarMain2;
        private RelativeLayout relativeLayoutMain;
        private int[]
                imageIdMain={R.drawable.yaris_main,R.drawable.cruz_main},
                imageIdFront={R.drawable.yaris_front,R.drawable.cruz_front},
                imageIdSide={R.drawable.yaris_side,R.drawable.cruz_side},
                imageIdBack={R.drawable.yaris_back,R.drawable.cruz_back};

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

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
            final int pageNumber = getArguments().getInt(ARG_SECTION_NUMBER);
            View rootView = inflater.inflate(R.layout.fragment_wor__main, container, false);
            ivCarFront = (ImageView) rootView.findViewById(R.id.iv_carFront);
            ivCarMain = (ImageView) rootView.findViewById(R.id.iv_carMain);
            ivCarSide = (ImageView) rootView.findViewById(R.id.iv_carSide);
            ivCarBack = (ImageView) rootView.findViewById(R.id.iv_carTop);
            ivCarMain2 = (ImageView) rootView.findViewById(R.id.iv_carMain2);
            relativeLayoutMain = (RelativeLayout) rootView.findViewById(R.id.rl_wor_main);

            ivCarMain.setImageResource(imageIdMain[pageNumber]);
            ivCarFront.setImageResource(imageIdFront[pageNumber]);
            ivCarSide.setImageResource(imageIdSide[pageNumber]);
            ivCarBack.setImageResource(imageIdBack[pageNumber]);
            ivCarMain2.setImageResource(imageIdMain[pageNumber]);

            ivCarMain2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ivCarMain.setImageResource(imageIdMain[pageNumber]);
                }
            });

            ivCarBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ivCarMain.setImageResource(imageIdBack[pageNumber]);
                }
            });

            ivCarSide.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ivCarMain.setImageResource(imageIdSide[pageNumber]);
                }
            });

            ivCarFront.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ivCarMain.setImageResource(imageIdFront[pageNumber]);
                }
            });

            relativeLayoutMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getContext()
                            .getApplicationContext(),WOR_CarDetailsActivity.class)
                            .putExtra("position",pageNumber));
                }
            });
            return rootView;
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
            return 2;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Toyota Yaris iA";
                case 1:
                    return "Chevrolet Cruze";

            }
            return null;
        }
    }

}
