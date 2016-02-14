package com.garrettshorr.warofwords;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by g on 2/13/2016.
 */
public class BattleActivity extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.recycler_container, new PoliticiansFragment(), "PoliticiansFragment")
                .commit();

//        Intent i = getIntent();

    }
}
