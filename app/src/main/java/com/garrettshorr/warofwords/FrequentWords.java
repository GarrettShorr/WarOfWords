package com.garrettshorr.warofwords;

/**
 * Created by g on 2/14/2016.
 */
public class FrequentWords {
    private String tfidf;
    private int count;
    private String ngram;

    public FrequentWords(String mTfidf, int mCount, String mNgram) {
        this.tfidf = mTfidf;
        this.count = mCount;
        this.ngram = mNgram;
    }

    public String getTfidf() {
        return tfidf;
    }

    public void setTfidf(String tfidf) {
        this.tfidf = tfidf;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNgram() {
        return ngram;
    }

    public void setNgram(String ngram) {
        this.ngram = ngram;
    }

    public String toString() {
        return "Count: " + count + "       " + ngram;
    }

}
