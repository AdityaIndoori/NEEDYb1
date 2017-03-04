package com.example.aditya.menuview;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GridRecyclerViewAdapter.ListItemClickListener, recyclerViewAdapter.ListItemClickListener {

    String[] GridSubHeading = {"Emergency","H-Mart","Wheels On Rent","C-BayBee"};
    int[] GridIconImage = {R.drawable.emergency,R.drawable.hmart,R.drawable.wheelsonrent,R.drawable.cbaybee};
    private GridRecyclerViewAdapter GridRecyclerViewAdapter;
    private recyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerViewG;
    private GridLayoutManager gridLayoutManager;
    public static Toast toast;
    private static final int NUM_PAGES = 4;
    ViewPager viewPager;
    private PagerAdapter mPagerAdapter;
    int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

/*        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //ViewPager
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), NUM_PAGES);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        viewPager.setAdapter(mPagerAdapter);
        indicator.setViewPager(viewPager);
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    boolean touchedViewPager;
                    @Override
                    public void run() {
                        viewPager.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View view, MotionEvent motionEvent) {
                                if(motionEvent.getAction()==MotionEvent.ACTION_MOVE)
                                    touchedViewPager=true;
                                else touchedViewPager = false;
                                return false;
                            }
                        });
                        if (!touchedViewPager){
                            currentPage = viewPager.getCurrentItem();
                            if (currentPage >= NUM_PAGES-1){
                                currentPage = -1;
                            }
                            int nextPage = currentPage + 1;
                            viewPager.setCurrentItem(nextPage,true);
                        }
                    }
                });
            }
        },0,7000);
        //Grid View
        /*
        recyclerViewG = (RecyclerView)findViewById(R.id.HorizontalRecyclerView);
        gridLayoutManager = new GridLayoutManager(this,1, LinearLayoutManager.HORIZONTAL,false);//2 = number of columns
        recyclerViewG.setLayoutManager(gridLayoutManager);
        GridRecyclerViewAdapter = new GridRecyclerViewAdapter(GridIconImage,GridSubHeading,this);
        recyclerViewG.setAdapter(GridRecyclerViewAdapter);*/

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_admin) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        /*if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onListItemClick(int clickedItemIndex, int viewCode) {
        if (toast != null)
            toast.cancel();
        if (viewCode == 0 || viewCode == 1 || viewCode == 2)
        {
            toast=Toast.makeText(getApplicationContext(),"The Grid number clicked is: " + clickedItemIndex,Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
