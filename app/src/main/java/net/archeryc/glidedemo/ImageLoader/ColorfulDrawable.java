package net.archeryc.glidedemo.ImageLoader;

import android.graphics.drawable.ColorDrawable;

import java.util.Random;

/**
 * Created by 24706 on 2016/4/19.
 * 颜色随机的占位图
 */
public class ColorfulDrawable extends ColorDrawable {

    private int[] colors={0xffff9900,0xffff9999,0xffff99ff,0xffff3366,0xffcccccc,0xffcc99ff,0xff66ff66,0xff6633cc};

    public ColorfulDrawable() {
        Random random = new Random();
        setColor(colors[random.nextInt(8)]);
    }
}
