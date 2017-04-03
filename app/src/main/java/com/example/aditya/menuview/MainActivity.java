package com.example.aditya.menuview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private static final int NUM_PAGES = 4;
    private ViewPager viewPager;
    private int currentPage;
    private LinearLayout linearLayoutEmergency;
    private RelativeLayout relativeLayoutH_Mart, relativeLayoutWheelsOnRent, relativeLayoutC_BayBee;
    public static Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //ViewPager
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        PagerAdapter mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), NUM_PAGES);
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
                            currentPage = viewPager.getCurrentItem();
                            if (currentPage >= NUM_PAGES-1){
                                currentPage = -1;
                            }
                            int nextPage = currentPage + 1;
                            viewPager.setCurrentItem(nextPage,true);
                    }
                });
            }
        },0,7000);

        //UI Elements:
        linearLayoutEmergency = (LinearLayout) findViewById(R.id.linearLayoutEmergency);
        relativeLayoutH_Mart = (RelativeLayout) findViewById(R.id.relativeLayoutH_Mart);
        relativeLayoutWheelsOnRent = (RelativeLayout) findViewById(R.id.relativeLayoutWheelsOnRent);
        relativeLayoutC_BayBee = (RelativeLayout) findViewById(R.id.relativeLayoutC_BayBee);

        //OnCLick of UI ELements:
        linearLayoutEmergency.setOnClickListener(this);
        relativeLayoutC_BayBee.setOnClickListener(this);
        relativeLayoutWheelsOnRent.setOnClickListener(this);
        relativeLayoutH_Mart.setOnClickListener(this);

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
        getMenuInflater().inflate(R.menu.activity_main_action_bar, menu);
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
            setToast("Clicked Admin Menu",getApplicationContext());
        }

        if (id == R.id.action_cart){
            startActivity(new Intent(getApplicationContext(),H_MartCartActivity.class));

        }

        if (id == R.id.action_wallet){
            setToast("Clicked Wallet Menu",getApplicationContext());

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_myOrders) {
            setToast("Clicked Orders Menu",getApplicationContext());

        } else if (id == R.id.nav_wishList) {
            setToast("Clicked WishList Menu",getApplicationContext());

        } else if (id == R.id.nav_invite) {
            setToast("Clicked Invite Menu",getApplicationContext());

        } else if (id == R.id.nav_settings) {
            setToast("Clicked Settings Menu",getApplicationContext());

        } else if (id == R.id.nav_rateUs) {
            setToast("Clicked rateUs Menu",getApplicationContext());

        } else if (id == R.id.nav_helpFeedback) {
            setToast("Clicked Help & Feedback Menu",getApplicationContext());

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static void setToast(String toastMessage, Context context){
        if (toast != null)
        toast.cancel();
        toast=Toast.makeText(context,toastMessage,Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onClick(View v) {
        if (v == linearLayoutEmergency)
            clickedlinearLayoutEmergency();
        else if (v == relativeLayoutH_Mart)
            clickedrelativeLayoutH_Mart();
        else if (v == relativeLayoutWheelsOnRent)
            clickedrelativeLayoutWheelsOnRent();
        else if (v == relativeLayoutC_BayBee)
            clickedrelativeLayoutC_BayBee();
    }

    private void clickedrelativeLayoutC_BayBee() {
        setToast("C-Baybee",getApplicationContext());
    }

    private void clickedrelativeLayoutWheelsOnRent() {
        startActivity(new Intent(getApplicationContext(),WOR_MainActivity.class));
    }

    private void clickedrelativeLayoutH_Mart() {
        //setToast("Clicked H Mart", getApplicationContext());
        startActivity(new Intent(this,H_MartMainActivity.class));
    }

    private void clickedlinearLayoutEmergency() {
        startActivity(new Intent(getApplicationContext(),EmergencyLoginActivity.class));
    }
}
