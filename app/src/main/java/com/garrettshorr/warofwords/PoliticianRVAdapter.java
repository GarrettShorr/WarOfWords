package com.garrettshorr.warofwords;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by g on 2/13/2016.
 */
public class PoliticianRVAdapter extends
        RecyclerView.Adapter<PoliticianRVAdapter.PoliticianViewHolder> {

    List<Politicians> politicians;

    PoliticianRVAdapter(List<Politicians> politicians){
        this.politicians = politicians;
    }

    @Override
    public PoliticianViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from
                (parent.getContext()).inflate(R.layout.card_view_item_politician,
                parent, false);
        PoliticianViewHolder pvh = new PoliticianViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PoliticianViewHolder holder, int position) {
        holder.politicianName.setText(politicians.get(position).getName());
        holder.politicianPhoto.setImageResource(politicians.get(position).getPhotoId());
        Word w = politicians.get(position).getWord();
        String wordStatText = String.format("Count: %d\nPercentage %.6f",
                w.getCount(),w.getPercentage());
        holder.wordStats.setText(wordStatText);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return politicians.size();
    }

    public class PoliticianViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView politicianName;
        ImageView politicianPhoto;
        TextView wordStats;

        PoliticianViewHolder (View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.politician_card_view);
            politicianName = (TextView) itemView.findViewById(R.id.politician_name);
            politicianPhoto = (ImageView) itemView.findViewById(R.id.politician_photo);
            wordStats = (TextView) itemView.findViewById(R.id.politician_word);

        }
    }

}
