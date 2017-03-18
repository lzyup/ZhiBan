package com.felix.zhiban.bean.zhihunews;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class RootEntity implements Parcelable {

    private ArrayList<StroriesEntity>stories;

    public ArrayList<StroriesEntity> getStories() {
        return stories;
    }

    public void setStories(ArrayList<StroriesEntity> stories) {
        this.stories = stories;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.stories);
    }

    public RootEntity() {
    }

    protected RootEntity(Parcel in) {
        this.stories = in.createTypedArrayList(StroriesEntity.CREATOR);
    }

    public static final Parcelable.Creator<RootEntity> CREATOR = new Parcelable.Creator<RootEntity>() {
        @Override
        public RootEntity createFromParcel(Parcel source) {
            return new RootEntity(source);
        }

        @Override
        public RootEntity[] newArray(int size) {
            return new RootEntity[size];
        }
    };
}
