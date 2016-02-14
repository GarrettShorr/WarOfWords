package com.garrettshorr.warofwords;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("War of Words");
        setSupportActionBar(toolbar);


        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment;
        FragmentTransaction ft = fm.beginTransaction();
        if(fm.findFragmentByTag("WordCountFragment") == null) {
            fragment = new WordCountFragment();
            ft.add(R.id.fragment_container, fragment, "WordCountFragment");
        }
        else {
            fragment = fm.findFragmentByTag("WordCountFragment");
            ft.replace(R.id.fragment_container, fragment, "WordCountFragment");
        }

        ft.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = null;
        FragmentTransaction ft = fm.beginTransaction();
        switch(id) {
            case R.id.nav_word_battle:
                fragment = fm.findFragmentByTag("WordCountFragment");
                if (fragment == null || !fragment.isVisible()) {
                    fragment = new WordCountFragment();
                    ft.replace(R.id.fragment_container, fragment, "WordCountFragment");
                    ft.commit();
                }
                break;
            case R.id.nav_frequent_words:
                fragment = fm.findFragmentByTag("FrequentWordsFragment");
                if (fragment == null || !fragment.isVisible()) {
                    fragment = new FrequentWordsFragment();
                    ft.replace(R.id.fragment_container, fragment, "FrequentWordsFragment");
                    ft.commit();
                }
                break;
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
