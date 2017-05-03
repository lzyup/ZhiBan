package com.felix.zhiban.tool.ImageUtils;


import android.content.Context;
import android.widget.ImageView;

public interface IImageLoaderManager {
    void displayForImageView(Context context, String uri, ImageView imageView);

    void displayGaussian(Context context,String Url,ImageView imageView);

    void displaycrossFade(Context context, String uri, ImageView imageView);
}
