package com.garrettshorr.warofwords;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by g on 2/13/2016.
 */
public class PoliticiansFragment extends Fragment {

    private ImageButton mShareButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_politicians, container, false);
        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.politician_recycler_view);
        //rv.setHasFixedSize(true); //only if I know the size is fixed

        TextView header = (TextView) rootView.findViewById(R.id.battle_header);
        header.setText(header.getText() + " \"" +
                getActivity().getIntent().getStringExtra("battleWord") + "\"");

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        Politicians p = new Politicians();
        Word word1 = (Word)getActivity().getIntent().getParcelableExtra("word1");
        Word word2 = (Word)getActivity().getIntent().getParcelableExtra("word2");
        if(Math.abs(word1.getPercentage() - word2.getPercentage()) < 1E-13) {
            p.getPoliticians().get(0).setColor(Color.BLUE);
            p.getPoliticians().get(1).setColor(Color.BLUE);
        }
        else if(word1.getPercentage() > word2.getPercentage()) {
            p.getPoliticians().get(0).setColor(Color.GREEN);
            p.getPoliticians().get(1).setColor(Color.RED);
        }
        else {
            p.getPoliticians().get(0).setColor(Color.RED);
            p.getPoliticians().get(1).setColor(Color.GREEN);
        }



        p.getPoliticians().get(0).setWord(word1);
        p.getPoliticians().get(1).setWord(word2);
        PoliticianRVAdapter adapter = new PoliticianRVAdapter(p.getPoliticians());
        rv.setAdapter(adapter);

        //share button
        mShareButton = (ImageButton) rootView.findViewById(R.id.share_button);


        return rootView;
    }
}
