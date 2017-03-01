package com.felix.zhiban.bean.book;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Books implements Serializable{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("isbn10")
    @Expose
    private String isbn10;
    @SerializedName("isbn13")
    @Expose
    private String isbn13;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("origin_title")
    @Expose
    private String originTitle;
    @SerializedName("alt_title")
    @Expose
    private String altTitle;
    @SerializedName("subtitle")
    @Expose
    private String subtitle;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("alt")
    @Expose
    private String alt;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("images")
    @Expose
    private Images images;
    @SerializedName("author")
    @Expose
    private List<String> author = null;
    @SerializedName("translator")
    @Expose
    private List<String> translator = null;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("pubdate")
    @Expose
    private String pubdate;
    @SerializedName("rating")
    @Expose
    private Rating rating;
    @SerializedName("tags")
    @Expose
    private List<Tag> tags = null;
    @SerializedName("binding")
    @Expose
    private String binding;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("series")
    @Expose
    private Series series;
    @SerializedName("pages")
    @Expose
    private String pages;
    @SerializedName("author_intro")
    @Expose
    private String authorIntro;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("catalog")
    @Expose
    private String catalog;
    @SerializedName("ebook_url")
    @Expose
    private String ebookUrl;
    @SerializedName("ebook_price")
    @Expose
    private String ebookPrice;

//    protected Book(Parcel in) {
//        id = in.readString();
//        isbn10 = in.readString();
//        isbn13 = in.readString();
//        title = in.readString();
//        originTitle = in.readString();
//        altTitle = in.readString();
//        subtitle = in.readString();
//        url = in.readString();
//        alt = in.readString();
//        image = in.readString();
//        author = in.createStringArrayList();
//        translator = in.createStringArrayList();
//        publisher = in.readString();
//        pubdate = in.readString();
//        binding = in.readString();
//        price = in.readString();
//        pages = in.readString();
//        authorIntro = in.readString();
//        summary = in.readString();
//        catalog = in.readString();
//        ebookUrl = in.readString();
//        ebookPrice = in.readString();
//    }
//
//    public static final Creator<Book> CREATOR = new Creator<Book>() {
//        @Override
//        public Book createFromParcel(Parcel in) {
//            return new Book(in);
//        }
//
//        @Override
//        public Book[] newArray(int size) {
//            return new Book[size];
//        }
//    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginTitle() {
        return originTitle;
    }

    public void setOriginTitle(String originTitle) {
        this.originTitle = originTitle;
    }

    public String getAltTitle() {
        return altTitle;
    }

    public void setAltTitle(String altTitle) {
        this.altTitle = altTitle;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public List<String> getTranslator() {
        return translator;
    }

    public void setTranslator(List<String> translator) {
        this.translator = translator;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getAuthorIntro() {
        return authorIntro;
    }

    public void setAuthorIntro(String authorIntro) {
        this.authorIntro = authorIntro;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getEbookUrl() {
        return ebookUrl;
    }

    public void setEbookUrl(String ebookUrl) {
        this.ebookUrl = ebookUrl;
    }

    public String getEbookPrice() {
        return ebookPrice;
    }

    public void setEbookPrice(String ebookPrice) {
        this.ebookPrice = ebookPrice;
    }

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel parcel, int i) {
//        parcel.writeString(id);
//        parcel.writeString(isbn10);
//        parcel.writeString(isbn13);
//        parcel.writeString(title);
//        parcel.writeString(originTitle);
//        parcel.writeString(altTitle);
//        parcel.writeString(subtitle);
//        parcel.writeString(url);
//        parcel.writeString(alt);
//        parcel.writeString(image);
//        parcel.writeStringList(author);
//        parcel.writeStringList(translator);
//        parcel.writeString(publisher);
//        parcel.writeString(pubdate);
//        parcel.writeString(binding);
//        parcel.writeString(price);
//        parcel.writeString(pages);
//        parcel.writeString(authorIntro);
//        parcel.writeString(summary);
//        parcel.writeString(catalog);
//        parcel.writeString(ebookUrl);
//        parcel.writeString(ebookPrice);
//    }
}
