package com.garrettshorr.warofwords;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
 * Created by g on 2/13/2016.
 */
public class WordCountFragment extends Fragment {
    private TextView mResult;
    private EditText mSearch;
    private Button mSearchButton;
    private Word mWord1;
    private Word mWord2;
    private String mUrl1;
    private String mUrl2;

    public static final String CAP_WORDS_URL = "http://capitolwords.org/api/1";
    public static final String CLINTON_ID = "C001041";
    public static final String SANDERS_ID = "S000033";

    private static final String CAP_WORDS_APIKEY = "&apikey=26b803c45ba14dfb94a63bad60bdb21a";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_word_count, container, false);




        mResult = (TextView) rootView.findViewById(R.id.search_result);
        mSearch = (EditText) rootView.findViewById(R.id.search_term);
        mSearchButton = (Button) rootView.findViewById(R.id.search_button);
        mWord1 = new Word(0,0,0);


        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_sunlight_api(mSearch.getText().toString());

            }
        });

        return rootView;


    }

    //dates.json?phrase=united+states&entity_type=state&entity_value=VA&apikey=<YOUR_KEY>


    private void search_sunlight_api(String text) {
        mUrl1 = mUrl2 = CAP_WORDS_URL + "/dates.json?total=true&percentages=true&phrase=" + mSearch.getText().toString().trim() +
                "&bioguide_id=";
        mUrl1 += SANDERS_ID + CAP_WORDS_APIKEY;
        mUrl2 += CLINTON_ID + CAP_WORDS_APIKEY;
        WordFetcher fetcher = new WordFetcher();
        fetcher.execute();

    }
    private void handleWord(List<Word> words, Word word) {
        int count = 0;
        int total = 0;
        for (Word w : words) {
            count += w.getCount();
            total += w.getTotal();
        }
        word.setCount(count);
        word.setTotal(total);
        word.setPercentage((double) count / total);


        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mResult.setText(mWord1.toString());
            }
        });
    }


    private void handleWordFailure() {


        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mResult.setText("Something went wrong.");
            }
        });
    }

    private void handleWordFailure2() {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mResult.setText("Something else went wrong.");
            }
        });
    }

    //private inner class for AsyncTask to access the external API
    private class WordFetcher extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            URL url = null;
            fetchWords(mUrl1, mWord1);
            fetchWords(mUrl2, mWord2);
            return null;
        }
        private void fetchWords(String urlStr, Word word) {
            URL url = null;
            try {
                url = new URL(urlStr);
//                  url = new URL("http://capitolwords.org/api/1/dates.json?phrase=united+states&entity_type=state&entity_value=VA&apikey=26b803c45ba14dfb94a63bad60bdb21a");
                Reader reader = new InputStreamReader(url.openStream());
                Scanner s = new Scanner(reader);
                String json = "";
                while (s.hasNextLine()) {
                    json += s.nextLine();
                }

                JSONObject jsonObject = new JSONObject(json);
                JSONArray internalObject = (JSONArray) jsonObject.get("results");

                Gson gson = new GsonBuilder().create();
                List<Word> words = Arrays.asList(gson.fromJson(internalObject.toString(), Word[].class));
                handleWord(words, word);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                handleWordFailure();
            } catch (IOException e) {
                e.printStackTrace();
                handleWordFailure2();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }
}
