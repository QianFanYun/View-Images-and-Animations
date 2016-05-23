package net.archeryc.glidedemo.GlidePhotoView;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import net.archeryc.glidedemo.ImageLoader.GlideImageLoader;
import net.archeryc.glidedemo.ImageLoader.LoadingImageListener;
import net.archeryc.glidedemo.photoview.PhotoView;


/**
 * Created by 24706 on 2016/5/13.
 */
public class GlidePhotoView extends PhotoView {
    private String mUrl;
    private Context mContext;
    private LongClickDialog longClickDialog;
    private static final int MAX_HEIGHT_WIDTH = 4096;
    private static final int LONG_DURATION = 1000;
    private Paint paint;
    private float currentValue = 0f;
    private boolean isLoading = true;


    public GlidePhotoView(Context context, String url) {
        super(context);
        this.mContext = context;
        this.mUrl = url;
        initImageView();
    }


    public GlidePhotoView(Context context, AttributeSet attr, String url) {
        super(context, attr);
        this.mContext = context;
        this.mUrl = url;
        initImageView();
    }

    public GlidePhotoView(Context context, AttributeSet attr, int defStyle, String url) {
        super(context, attr, defStyle);
        this.mContext = context;
        this.mUrl = url;
        initImageView();
    }

    private void initImageView() {
        paint = new Paint();
        paint.setColor(Color.WHITE);
        setBackgroundColor(Color.BLACK);
        longClickDialog = new LongClickDialog(mContext);
        GlideImageLoader.getInstance().loadImage(mContext, mUrl, new LoadingImageListener() {
            @Override
            public void onLoadStart() {
                startAnim();
                invalidate();
            }

            @Override
            public void onLoadFailure() {

            }

            @Override
            public void onLoadSuccess(Bitmap bitmap) {
                stopAnim();
                if ((float) bitmap.getHeight() / (float) bitmap.getWidth() >= 3.0f) {//如果高度和宽度的比例大于3，说明是长图，全屏显示，MediuScale设置为屏幕大小，设置当前缩放为MediuScale
                    float mutiple = (float) PhotoViewHelper.screenWidth(mContext) / ((float) bitmap.getWidth() * PhotoViewHelper.screenHeight(mContext) / bitmap.getHeight());
                    getAttacher().setMaximumScale(mutiple * 2);
                    getAttacher().setMediumScale(mutiple);
                    getAttacher().update();
                    getAttacher().setZoomTransitionDuration(0);
                    getAttacher().setScale(getAttacher().getMediumScale(), 0, 0, true);
                }
                if ((float) bitmap.getWidth() / (float) bitmap.getHeight() >= 3.0f) {
                    float mutiple = (float) PhotoViewHelper.screenHeight(mContext) / ((float) bitmap.getHeight() * PhotoViewHelper.screenWidth(mContext) / bitmap.getWidth());
                    getAttacher().setMaximumScale(mutiple * 2);
                    getAttacher().setMediumScale(mutiple);
                    getAttacher().update();
                    getAttacher().setZoomTransitionDuration(0);
                    getAttacher().setScale(getAttacher().getMediumScale(), 0, 0, true);
                }

                if (bitmap.getWidth() < bitmap.getHeight() && bitmap.getHeight() > MAX_HEIGHT_WIDTH) {//对过大的图片进行裁剪
                    setImageBitmap(Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() * MAX_HEIGHT_WIDTH / bitmap.getHeight(), MAX_HEIGHT_WIDTH, true));
                } else if (bitmap.getWidth() > bitmap.getHeight() && bitmap.getWidth() > MAX_HEIGHT_WIDTH) {
                    setImageBitmap(Bitmap.createScaledBitmap(bitmap, MAX_HEIGHT_WIDTH, bitmap.getHeight() * MAX_HEIGHT_WIDTH / bitmap.getWidth(), true));
                } else {
                    setImageBitmap(bitmap);
                }
                longClickDialog.setBitmap(bitmap);

            }
        });
        setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                longClickDialog.show();
                return false;
            }
        });
    }

    public void startAnim(){
        isLoading=true;
    }

    private void stopAnim(){
        isLoading=false;
    }


    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        ObjectAnimator anim = null;
        if (isLoading) {
            if (currentValue == 0) {//如果开始的时候值为0，表明动画还没开始，先开启一个加载动画
                anim = ObjectAnimator.ofFloat(this, "circle", 40f, 20f, 40f)
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
            } else {//家栋动画已经开始，使用currentValue画圆
                canvas.drawCircle(0.5f * (float) PhotoViewHelper.screenWidth(mContext), 0.5f * (float) PhotoViewHelper.screenHeight(mContext), currentValue, paint);
            }
        }else{
            //停止加载动画后什么都绘制
//            anim.cancel();
        }
    }
}