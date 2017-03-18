package com.felix.zhiban.bean.zhihunews;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class StroyDetailEntity implements Parcelable {
    private String body;

    private String image_source;

    private String title;

    private String image;

    private String share_url;

    private String ga_prefix;

    private int type;

    private int id;

    private List<String> js;

    private List<String>images;

    private List<String>css;

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }

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

    public List<String> getJs() {
        return js;
    }

    public void setJs(List<String> js) {
        this.js = js;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public StroyDetailEntity() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.body);
        dest.writeString(this.image_source);
        dest.writeString(this.title);
        dest.writeString(this.image);
        dest.writeString(this.share_url);
        dest.writeString(this.ga_prefix);
        dest.writeInt(this.type);
        dest.writeInt(this.id);
        dest.writeStringList(this.js);
        dest.writeStringList(this.images);
        dest.writeStringList(this.css);
    }

    protected StroyDetailEntity(Parcel in) {
        this.body = in.readString();
        this.image_source = in.readString();
        this.title = in.readString();
        this.image = in.readString();
        this.share_url = in.readString();
        this.ga_prefix = in.readString();
        this.type = in.readInt();
        this.id = in.readInt();
        this.js = in.createStringArrayList();
        this.images = in.createStringArrayList();
        this.css = in.createStringArrayList();
    }

    public static final Creator<StroyDetailEntity> CREATOR = new Creator<StroyDetailEntity>() {
        @Override
        public StroyDetailEntity createFromParcel(Parcel source) {
            return new StroyDetailEntity(source);
        }

        @Override
        public StroyDetailEntity[] newArray(int size) {
            return new StroyDetailEntity[size];
        }
    };
}
