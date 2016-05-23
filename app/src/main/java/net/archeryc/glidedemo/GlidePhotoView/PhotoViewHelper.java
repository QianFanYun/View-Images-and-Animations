package net.archeryc.glidedemo.GlidePhotoView;

import android.content.Context;

/**
 * Created by 24706 on 2016/5/5.
 */
public class PhotoViewHelper {
    /**
     * @param context
     * @return int
     * @description: 获取屏幕宽
     */
    public static int screenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * @param context
     * @return int
     * @description: 获取屏幕高
     */
    public static int screenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
}
