package com.garrettshorr.warofwords;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by g on 2/13/2016.
 */
public class Politicians {
    private String name;
    private int photoId;
    private Word word;

    public Politicians(String name, int photoId, Word word) {
        this.name = name;
        this.photoId = photoId;
        this.word = word;
    }
    public Politicians() {
        initializeData();
    }

    public void setWord(Word w) {
        word = w;
    }

    public String getName() {
        return name;
    }

    public int getPhotoId() {
        return photoId;
    }

    public Word getWord() {
        return word;
    }

    public List<Politicians> getPoliticians() {
        return politicians;
    }

    private List<Politicians> politicians;

    private void initializeData() {
        politicians = new ArrayList<>();
        politicians.add(new Politicians("Bernie Sanders", R.drawable.sanders, null));
        politicians.add(new Politicians("Hillary Clinton", R.drawable.clinton, null));
    }

}
