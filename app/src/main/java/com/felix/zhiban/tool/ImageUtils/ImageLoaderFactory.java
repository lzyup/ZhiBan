package com.felix.zhiban.tool.ImageUtils;


public class ImageLoaderFactory {

    public static IImageLoaderManager getImageLoader(){
        return UniversalImageLoaderManager.getInstance();
    }
}
