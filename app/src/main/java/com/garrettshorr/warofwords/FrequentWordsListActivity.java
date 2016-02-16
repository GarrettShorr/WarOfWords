package com.garrettshorr.warofwords;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by g on 2/14/2016.
 */
public class FrequentWordsListActivity extends AppCompatActivity {
    ListView listView;
    TextView mCandidate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frequent_words_list);


        listView = (ListView) findViewById(R.id.freq_words_list);
        String[] values = getIntent().getStringArrayExtra("strings");
        String candidate = getIntent().getStringExtra("candidate");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.freq_words_list_item,values);


        listView.setAdapter(adapter);

        mCandidate = (TextView) findViewById(R.id.candidate);
        mCandidate.setText("Most Frequent Words\nSaid by " + candidate);


    }
}
