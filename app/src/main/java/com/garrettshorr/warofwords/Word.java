package com.garrettshorr.warofwords;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gshorr on 2/12/16.
 */
public class Word implements Parcelable {
    private int count;
    private double percentage;
    private int total;
    private int raw_count;
    private String day;

    public static final int CLINTON_TOTAL = 79931772;
    public static final int SANDERS_TOTAL = 135150394;


    public Word(int count, double percentage, int total) {
        this.count = count;
        this.percentage = percentage;
        this.total = total;
    }

    public Word(Parcel p)
    {
        count = p.readInt();
        percentage = p.readDouble();
        day = p.readString();
        total = p.readInt();
        raw_count = p.readInt();
    }

    public void calculatePercentage() {
        percentage = (double) count / total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getRaw_count() {
        return raw_count;
    }

    public void setRaw_count(int raw_count) {
        this.raw_count = raw_count;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String toString() {
        return "count: " + count + "\npercentage: " + percentage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(count);
        dest.writeDouble(percentage);
        dest.writeString(day);
        dest.writeInt(total);
        dest.writeInt(raw_count);
    }

    public static final Parcelable.Creator<Word> CREATOR = new Parcelable.Creator<Word>() {
        public Word createFromParcel(Parcel in) {
            return new Word(in);
        }

        public Word[] newArray(int size) {
            return new Word[size];
        }
    };
}
