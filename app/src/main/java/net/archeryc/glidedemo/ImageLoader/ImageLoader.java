package net.archeryc.glidedemo.ImageLoader;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Priority;

import java.io.File;

/**
 * Created by 24706 on 2016/5/12.
 * 加载图片的接口
 */
public abstract class ImageLoader {


    /**
     * 加载普通网络图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public abstract void loadImage(Context context, String url, ImageView imageView);

    /**
     * 加载指定大小的图片
     * @param context
     * @param url
     * @param imageView
     * @param width
     * @param height
     */
    public abstract void loadImage(Context context, String url, ImageView imageView, int width, int height);

    /**
     * 如果需要设置请求优先级使用这个，不设置默认是Priority.NORMAL
     *
     * @param context
     * @param url
     * @param imageView
     * @param priority
     */
    public abstract void loadImage(Context context, String url, ImageView imageView, Priority priority);

    /**
     * 加载网络图片,圆
     *
     * @param context
     * @param url
     * @param imageView
     */
    public abstract void loadCircleImage(Context context, String url, ImageView imageView);

    /**
     * 加载网络图片,添加圆角
     *
     * @param context
     * @param url
     * @param imageView
     */
    public abstract void loadRoundImage(Context context, String url, ImageView imageView, int dp);

    /**
     * 加载图片时传入监听器
     *
     * @param context
     * @param url
     * @param loadingImageListener
     */
    public abstract void loadImage(Context context, String url, LoadingImageListener loadingImageListener);

    /**
     * 从资源文件中加载图片
     *
     * @param context
     * @param sourceId
     * @param imageView
     */
    public abstract void loadImage(Context context, int sourceId, ImageView imageView);

    /**
     * 从文件中加载图片
     *
     * @param context
     * @param file
     * @param imageView
     */
    public abstract void loadImage(Context context, File file, ImageView imageView);

    /**
     * 从Uri中加载图片
     *
     * @param context
     * @param uri
     * @param imageView
     */
    public abstract void loadImage(Context context, Uri uri, ImageView imageView);

    /**
     * 从网络中加载Gif
     *
     * @param context
     * @param url
     * @param imageView
     */
    public abstract void loadGif(Context context, String url, ImageView imageView);

    /**
     * 从资源文件中加载Gif
     *
     * @param context
     * @param sourceId
     * @param imageView
     */
    public abstract void loadGif(Context context, int sourceId, ImageView imageView);

    /**
     * 从文件中加载Gif
     *
     * @param context
     * @param file
     * @param imageView
     */
    public abstract void loadGif(Context context, File file, ImageView imageView);

    /**
     * 从Uri中加载Gif
     *
     * @param context
     * @param uri
     * @param imageView
     */
    public abstract void loadGif(Context context, Uri uri, ImageView imageView);
}