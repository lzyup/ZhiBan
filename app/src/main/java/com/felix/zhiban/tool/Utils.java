package com.felix.zhiban.tool;


import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.List;

public class Utils {
    /**
     * 获取屏幕宽度px
     * @param context
     * @return 分辨率
     */
     public static int getScreenWidthPixels(Context context){
        DisplayMetrics dm=new DisplayMetrics();
        ((WindowManager)context.getSystemService(context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 获取屏幕宽度dp
     * @param context
     * @return
     */
    public static int getScreenWIdthDp(Context context){
        WindowManager wm=(WindowManager)context.getSystemService(context.WINDOW_SERVICE);

        int width=wm.getDefaultDisplay().getWidth();
        return width;
    }

    /**
     * 根据手机分辨率从dp的单位转化成px
     * @param context
     * @param dpValue
     * @return
     */
    public static int dp2px(Context context,int dpValue){
        final float scale=context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale+0.5f);
    }

    public static int px2dp(Context context,int pxValue){
        final float scale=context.getResources().getDisplayMetrics().density;
        return (int)(pxValue/scale+0.5f);
    }

/**
 * toolbar滑动渐变
 */
    public void initSlideShapeTheme(String imgUrl, ImageView toolbg){

    }


    /**
     * 格式化导演 主演名字
     */
    public static String formatName(List<String> cast){
        if(cast!=null&&cast.size()>0){
            StringBuilder stringBuilder=new StringBuilder();
            for(int i=0;i<cast.size();i++){
                if(i<cast.size()-1){
                    stringBuilder.append(cast.get(i)).append("/");
                }else{
                    stringBuilder.append(cast.get(i));
                }
            }
            return stringBuilder.toString();
        }else{

            return "佚名";
        }
    }


}
