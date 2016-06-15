package net.archeryc.glidedemo.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import net.archeryc.glidedemo.GlidePhotoView.BigPhotoActivity;
import net.archeryc.glidedemo.GlidePhotoView.Common;
import net.archeryc.glidedemo.ImageLoader.ImageLoader;
import net.archeryc.glidedemo.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String normalUrl = "http://ww1.sinaimg.cn/large/7a8aed7bjw1f2zwrqkmwoj20f00lg0v7.jpg";
    private static final String gifUrl = "http://ww2.sinaimg.cn/large/85cccab3tw1esjq9r0pcpg20d3086qtr.jpg";

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        ImageLoader.getInstance().loadImage(this,"http://ww2.sinaimg.cn/large/610dc034jw1f454lcdekoj20dw0kumzj.jpg",imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBigImage();
            }
        });
    }


    public void showNormalImage(View view) {
        ImageLoader.getInstance().loadCircleImage(this,normalUrl,imageView);
    }

    public void showResourceImage(View view) {
        ImageLoader.getInstance().loadImage(this,R.mipmap.ic_launcher,imageView);
    }

    public void showGif(View view) {
        ImageLoader.getInstance().loadGif(this,gifUrl,imageView);
    }


    public void showBigImage(View view){
        Intent intent = new Intent(this, BigPhotoActivity.class);
        ArrayList<String> urls = new ArrayList<>();
        urls.add("http://ww2.sinaimg.cn/large/610dc034jw1f454lcdekoj20dw0kumzj.jpg");
        urls.add("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo_top_ca79a146.png");
        urls.add("http://ww3.sinaimg.cn/mw690/8345c393jw1f32xv7zd4gj20go24yaqv.jpg");
        urls.add("http://ww2.sinaimg.cn/large/7a8aed7bjw1f3c7zc3y3rj20rt15odmp.jpg");

        intent.putStringArrayListExtra(Common.BigPhotoActivity.URLS, urls);
        intent.putExtra(Common.BigPhotoActivity.CURRENT_POSITION, 0);
        startActivity(intent);
        overridePendingTransition(0,0);

    }
    public void showBigImage(){
        Intent intent = new Intent(this, BigPhotoActivity.class);
        ArrayList<String> urls = new ArrayList<>();
        urls.add("http://ww2.sinaimg.cn/large/610dc034jw1f454lcdekoj20dw0kumzj.jpg");
        urls.add("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo_top_ca79a146.png");
        urls.add("http://ww3.sinaimg.cn/mw690/8345c393jw1f32xv7zd4gj20go24yaqv.jpg");
        urls.add("http://ww2.sinaimg.cn/large/7a8aed7bjw1f3c7zc3y3rj20rt15odmp.jpg");

        intent.putStringArrayListExtra(Common.BigPhotoActivity.URLS, urls);
        intent.putExtra(Common.BigPhotoActivity.CURRENT_POSITION, 0);
        startActivity(intent);
        overridePendingTransition(R.anim.photo_activity_in,0);

    }
}
