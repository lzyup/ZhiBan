package com.felix.zhiban.tool.ImageUtils;


import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.felix.zhiban.R;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class UniversalImageLoaderManager implements IImageLoaderManager {

    private static final String TAG="ImageLoaderManager";

    private static UniversalImageLoaderManager mImageLoaderManager;

    public static UniversalImageLoaderManager getInstance(){
        if(mImageLoaderManager==null){
            mImageLoaderManager=new UniversalImageLoaderManager();
        }
        return mImageLoaderManager;
    }

    @Override
    public void displayForImageView(Context context, String uri, ImageView imageView) {
        Glide.with(context)
                .load(uri)
                .into(imageView);
    }

    @Override
    public void displayGaussian(Context context, String url, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(url)
                .error(R.drawable.stackblur_default)
                .placeholder(R.drawable.stackblur_default)
                .crossFade(500)
                .bitmapTransform(new BlurTransformation(context,23,4))
                .into(imageView);

    }

    @Override
    public void displaycrossFade(Context context, String uri, ImageView imageView) {
        Glide.with(context)
                .load(uri)
                .crossFade()
                .into(imageView);
    }
}
