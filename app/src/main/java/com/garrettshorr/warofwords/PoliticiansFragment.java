package com.garrettshorr.warofwords;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by g on 2/13/2016.
 */
public class PoliticiansFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_word_count, container, false);
        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.politician_recycler_view);
        //rv.setHasFixedSize(true); //only if I know the size is fixed

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        Politicians p = new Politicians();
        PoliticianRVAdapter adapter = new PoliticianRVAdapter(p.getPoliticians());


        return rootView;
    }
}
