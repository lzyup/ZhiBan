package com.felix.zhiban.bean.zhihunews;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class StroriesEntity implements Parcelable {

    private int id;

    private String title;

    private List<String>images;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeStringList(this.images);
    }

    public StroriesEntity() {
    }

    protected StroriesEntity(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.images = in.createStringArrayList();
    }

    public static final Parcelable.Creator<StroriesEntity> CREATOR = new Parcelable.Creator<StroriesEntity>() {
        @Override
        public StroriesEntity createFromParcel(Parcel source) {
            return new StroriesEntity(source);
        }

        @Override
        public StroriesEntity[] newArray(int size) {
            return new StroriesEntity[size];
        }
    };
}
