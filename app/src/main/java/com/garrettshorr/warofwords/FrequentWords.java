package com.garrettshorr.warofwords;

/**
 * Created by g on 2/14/2016.
 */
public class FrequentWords {
    private String mTfidf;
    private int mCount;
    private String mNgram;

    public FrequentWords(String mTfidf, int mCount, String mNgram) {
        this.mTfidf = mTfidf;
        this.mCount = mCount;
        this.mNgram = mNgram;
    }

    public String getmTfidf() {
        return mTfidf;
    }

    public void setmTfidf(String mTfidf) {
        this.mTfidf = mTfidf;
    }

    public int getmCount() {
        return mCount;
    }

    public void setmCount(int mCount) {
        this.mCount = mCount;
    }

    public String getmNgram() {
        return mNgram;
    }

    public void setmNgram(String mNgram) {
        this.mNgram = mNgram;
    }
}
