package com.garrettshorr.warofwords;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by g on 2/14/2016.
 */
public class FrequentWordsFragment extends Fragment {
    private ImageButton mHillary;
    private ImageButton mBernie;
    private String mUrl;


    private static final String API_URL = "http://capitolwords.org/api/1/phrases.json?entity_type=" +
            "legislator&n=5&page=0&sort=count%20desc&apikey=26b803c45ba14dfb94a63bad60bdb21a" +
            "&entity_value=";
    public static final String CLINTON_ID = "C001041";
    public static final String SANDERS_ID = "S000033";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_frequent_words, container, false);
        mHillary = (ImageButton) rootView.findViewById(R.id.hillary);
        mBernie = (ImageButton) rootView.findViewById(R.id.bernie);

        mHillary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchCommonWords(CLINTON_ID);
            }
        });
        mBernie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchCommonWords(SANDERS_ID);
            }
        });

        return rootView;
    }

    private void searchCommonWords(String text) {
        mUrl = API_URL + text;
        WordFetcher fetcher = new WordFetcher();
        fetcher.execute();
    }

    //private inner class for AsyncTask to access the external API
    private class WordFetcher extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            fetchWords(mUrl);
            return null;
        }

        private void fetchWords(String urlStr) {
            URL url = null;
            try {
                url = new URL(urlStr);
                Reader reader = new InputStreamReader(url.openStream());
                Scanner s = new Scanner(reader);
                String json = "";
                while (s.hasNextLine()) {
                    json += s.nextLine();
                }

//                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = new JSONArray(json);
                Log.e("MAIN:", jsonArray.toString());
//                Gson gson = new GsonBuilder().create();
//                List<Word> words = Arrays.asList(gson.fromJson(internalObject.toString(), Word[].class));
//                handleWord(words, word);
            } catch (MalformedURLException e) {
                e.printStackTrace();
//                handleWordFailure();
            } catch (IOException e) {
                e.printStackTrace();
//                handleWordFailure2();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}
