package net.archeryc.glidedemo.GlidePhotoView;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import net.archeryc.glidedemo.ImageLoader.GlideImageLoader;
import net.archeryc.glidedemo.ImageLoader.LoadingImageListener;
import net.archeryc.glidedemo.photoview.PhotoView;
import net.archeryc.glidedemo.photoview.PhotoViewAttacher;


/**
 * Created by 24706 on 2016/5/13.
 * 通过Glide加载图片并且设置到PhotoView中
 */
public class GlidePhotoView extends PhotoView {
    private String mUrl;
    private Context mContext;
    private LongClickDialog longClickDialog;
    private static final int MAX_HEIGHT_WIDTH = 4096;
    private Paint paint;
    private float currentValue = 0f;
    private boolean isLoading = true;


    public GlidePhotoView(Context context, String url) {
        this(context, null, url);
    }


    public GlidePhotoView(Context context, AttributeSet attr, String url) {
        this(context, null, 0, url);
    }

    public GlidePhotoView(Context context, AttributeSet attr, int defStyle, String url) {
        super(context, attr, defStyle);
        this.mContext = context;
        this.mUrl = url;
        initView();
        initImageView();
    }

    private void initView() {
        paint = new Paint();
        paint.setColor(Color.WHITE);
        setBackgroundColor(Color.BLACK);
        longClickDialog = new LongClickDialog(mContext);
        setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                longClickDialog.show();
                return false;
            }
        });

        //根据Url加载图片
        initImageView();

    }


    private void initImageView() {
        GlideImageLoader.getInstance().loadImage(mContext, mUrl, new LoadingImageListener() {
            @Override
            public void onLoadStart() {
                startAnim();
            }

            @Override
            public void onLoadFailure() {

            }

            @Override
            public void onLoadSuccess(Bitmap bitmap) {
                //对过大的图片进行裁剪,长或宽超过4096，图片会无法显示。
                if (bitmap.getWidth() < bitmap.getHeight() && bitmap.getHeight() > MAX_HEIGHT_WIDTH) {
                    setImageBitmap(Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() * MAX_HEIGHT_WIDTH / bitmap.getHeight(), MAX_HEIGHT_WIDTH, true));
                } else if (bitmap.getWidth() > bitmap.getHeight() && bitmap.getWidth() > MAX_HEIGHT_WIDTH) {
                    setImageBitmap(Bitmap.createScaledBitmap(bitmap, MAX_HEIGHT_WIDTH, bitmap.getHeight() * MAX_HEIGHT_WIDTH / bitmap.getWidth(), true));
                } else {
                    setImageBitmap(bitmap);
                }

                stopAnim();
                longClickDialog.setBitmap(bitmap);

            }
        });

    }

    public void startAnim() {
        isLoading = true;
        invalidate();
    }

    private void stopAnim() {
        isLoading = false;
    }


    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (isLoading) {
            paint.setColor(Color.BLACK);
            canvas.drawRect(0, 0, PhotoViewHelper.screenWidth(mContext), PhotoViewHelper.screenHeight(mContext), paint);
            if (currentValue == 0) {//如果开始的时候值为0，表明动画还没开始，先开启一个加载动画
                startCircleAnim();
            } else {//动画已经开始，使用currentValue画圆
                paint.setColor(Color.WHITE);
                canvas.drawCircle(0.5f * (float) PhotoViewHelper.screenWidth(mContext), 0.5f * (float) PhotoViewHelper.screenHeight(mContext), currentValue, paint);
            }
        } else {
            //停止加载动画后什么都绘制
//            anim.cancel();
        }
    }

    private void startCircleAnim() {
        ObjectAnimator anim = ObjectAnimator.ofFloat(this, "circle", 40f, 20f, 40f)
                .setDuration(1500);
        anim.setRepeatCount(-1);
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
    }

}
