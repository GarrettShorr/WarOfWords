package com.garrettshorr.warofwords;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
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
        if (w != null) {
            DecimalFormat df = new DecimalFormat("###,###");
            NumberFormat nf = NumberFormat.getPercentInstance();
            nf.setMinimumFractionDigits(7);
            String wordStatText = String.format("%d uses in %s words spoken.\nPercent: %s",
                    w.getCount(),df.format(w.getTotal()),nf.format(w.getPercentage()));
            holder.wordStats.setText(wordStatText);
        }
        else
            holder.wordStats.setText("PlaceHolder");
        int color = politicians.get(position).getColor();
        holder.politicianPhoto.setBackgroundColor(color);
        holder.mark.setTextSize(50f);
        if(color == Color.GREEN)
            holder.mark.setText("\u2705");
        else if(color == Color.RED)
            holder.mark.setText("\u274C");
        else {
            holder.mark.setTextSize(30f);
            holder.mark.setText("¯\\_(ツ)_/¯");
            holder.mark.setGravity(Gravity.BOTTOM);
        }

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
        TextView mark;

        PoliticianViewHolder (View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.politician_card_view);
            politicianName = (TextView) itemView.findViewById(R.id.politician_name);
            politicianPhoto = (ImageView) itemView.findViewById(R.id.politician_photo);
            wordStats = (TextView) itemView.findViewById(R.id.politician_word);
            mark = (TextView) itemView.findViewById(R.id.politician_mark);

        }
    }

}
