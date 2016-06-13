package net.archeryc.glidedemo.ImageLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;


import net.archeryc.glidedemo.R;

import java.io.File;

/**
 * Created by 24706 on 2016/4/19.
 * 单例模式 实现了ImageLoader接口
 */
public class GlideImageLoader extends ImageLoader{

    //设置加载错误时的图片
    private static final int ERROR_IMAGE = R.drawable.load_failure;

    public  static GlideImageLoader glideImageLoader;

    private GlideImageLoader(){

    }

    public static GlideImageLoader getInstance(){
        if (glideImageLoader==null){
            synchronized (GlideImageLoader.class){
                if (glideImageLoader == null) {
                    glideImageLoader=new GlideImageLoader();
                }
            }
        }
        return glideImageLoader;
    }
    /**
     * 加载普通网络图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    @Override
    public void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .placeholder(new ColorfulDrawable())
                .error(ERROR_IMAGE)
                .centerCrop()
                //设置填充满imageview，可能有部分被裁剪掉，还有一种方式是fitCenter，将图片完整显示
                .into(imageView);
    }

    @Override
    public void loadImage(Context context, String url, ImageView imageView, int width, int height) {
        Glide.with(context)
                .load(url)
                .override(width,height)
                .placeholder(new ColorfulDrawable())
                .error(ERROR_IMAGE)
                .centerCrop()
                //设置填充满imageview，可能有部分被裁剪掉，还有一种方式是fitCenter，将图片完整显示
                .into(imageView);
    }

    /**
     * 如果需要设置请求优先级使用这个，不设置默认是Priority.NORMAL
     * @param context
     * @param url
     * @param imageView
     * @param priority
     */
    @Override
    public  void loadImage(Context context, String url, ImageView imageView,Priority priority) {
        Glide.with(context)
                .load(url)
                .priority(priority)
                .placeholder(new ColorfulDrawable())
                .error(ERROR_IMAGE)
                .centerCrop()
                //设置填充满imageview，可能有部分被裁剪掉，还有一种方式是fitCenter，将图片完整显示
                .into(imageView);
    }

    /**
     * 加载网络图片,圆
     *
     * @param context
     * @param url
     * @param imageView
     */
    @Override
    public  void loadCircleImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .placeholder(new ColorfulDrawable())
                .error(ERROR_IMAGE)
                .centerCrop()
                .transform(new GlideCircleTransform(context))
                //设置填充满imageview，可能有部分被裁剪掉，还有一种方式是fitCenter，将图片完整显示
                .into(imageView);
    }

    /**
     * 加载网络图片,添加圆角
     *
     * @param context
     * @param url
     * @param imageView
     */
    @Override
    public  void loadRoundImage(Context context, String url, ImageView imageView,int dp) {
        Glide.with(context)
                .load(url)
                .placeholder(new ColorfulDrawable())
                .error(ERROR_IMAGE)
                .centerCrop()
                .transform(new GlideRoundTransform(context,dp))
                //设置填充满imageview，可能有部分被裁剪掉，还有一种方式是fitCenter，将图片完整显示
                .into(imageView);
    }


    /**
     * 监控加载过程
     * @param context
     * @param url
     * @param loadingImageListener
     */
    @Override
    public void loadImage(Context context, String url, LoadingImageListener loadingImageListener) {
        MySimpleTarget mySimpleTarget=new MySimpleTarget(loadingImageListener);
        Glide.with(context)
                .load(url)
                .asBitmap()
                .placeholder(new ColorfulDrawable())
                .error(ERROR_IMAGE)
                .centerCrop()//centerCrop设置填充满imageview，可能有部分被裁剪掉，还有一种方式是fitCenter，将图片完整显示
                .into(mySimpleTarget);
    }




    /**
     * 从资源文件中加载图片
     *
     * @param context
     * @param sourceId
     * @param imageView
     */
    @Override
    public  void loadImage(Context context, int sourceId, ImageView imageView) {
        Glide.with(context)
                .load(sourceId)
                .placeholder(new ColorfulDrawable())
                .error(ERROR_IMAGE)
                .centerCrop()
                .into(imageView);
    }

    /**
     * 从文件中加载图片
     *
     * @param context
     * @param file
     * @param imageView
     */
    @Override
    public  void loadImage(Context context, File file, ImageView imageView) {
        Glide.with(context)
                .load(file)
                .placeholder(new ColorfulDrawable())
                .error(ERROR_IMAGE)
                .centerCrop()
                .into(imageView);

    }

    /**
     * 从Uri中加载图片
     *
     * @param context
     * @param uri
     * @param imageView
     */
    @Override
    public  void loadImage(Context context, Uri uri, ImageView imageView) {
        Glide.with(context)
                .load(uri)
                .placeholder(new ColorfulDrawable())
                .error(ERROR_IMAGE)
                .centerCrop()
                .into(imageView);
    }


    /**
     * 从网络中加载Gif
     *
     * @param context
     * @param url
     * @param imageView
     */
    @Override
    public  void loadGif(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .asGif()
                .placeholder(new ColorfulDrawable())
                .error(ERROR_IMAGE)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }


    /**
     * 从资源文件中加载Gif
     *
     * @param context
     * @param sourceId
     * @param imageView
     */
    @Override
    public  void loadGif(Context context, int sourceId, ImageView imageView) {
        Glide.with(context)
                .load(sourceId)
                .asGif()
                .placeholder(new ColorfulDrawable())
                .error(ERROR_IMAGE)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }

    /**
     * 从文件中加载Gif
     *
     * @param context
     * @param file
     * @param imageView
     */
    @Override
    public  void loadGif(Context context, File file, ImageView imageView) {
        Glide.with(context)
                .load(file)
                .asGif()
                .placeholder(new ColorfulDrawable())
                .error(ERROR_IMAGE)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .into(imageView);

    }

    /**
     * 从Uri中加载Gif
     *
     * @param context
     * @param uri
     * @param imageView
     */
    @Override
    public void loadGif(Context context, Uri uri, ImageView imageView) {
        Glide.with(context)
                .load(uri)
                .asGif()
                .placeholder(new ColorfulDrawable())
                .error(ERROR_IMAGE)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }

    public class MySimpleTarget extends SimpleTarget<Bitmap>{

        private LoadingImageListener imageListener;

        public MySimpleTarget(LoadingImageListener imageListener) {
            this.imageListener = imageListener;
        }

        public MySimpleTarget(int width, int height, LoadingImageListener imageListener) {
            super(width, height);
            this.imageListener = imageListener;
        }


        @Override
        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
            if (null!=imageListener){
                imageListener.onLoadSuccess(resource);
            }
        }

        @Override
        public void onLoadFailed(Exception e, Drawable errorDrawable) {
            super.onLoadFailed(e, errorDrawable);
            imageListener.onLoadFailure();
        }

        @Override
        public void onLoadStarted(Drawable placeholder) {
            super.onLoadStarted(placeholder);
            imageListener.onLoadStart();
        }
    }
}
