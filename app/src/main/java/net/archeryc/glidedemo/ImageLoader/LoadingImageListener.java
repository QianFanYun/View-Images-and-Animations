package net.archeryc.glidedemo.ImageLoader;

import android.graphics.Bitmap;

/**
 * Created by 24706 on 2016/5/12.
 * 监听图片加载过程
 */
public interface LoadingImageListener {
    public void onLoadStart();

    public void onLoadFailure();

    public void onLoadSuccess(Bitmap bitmap);
}
