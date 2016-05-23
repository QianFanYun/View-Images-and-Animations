package net.archeryc.glidedemo.GlidePhotoView;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import net.archeryc.glidedemo.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 长按图片后可下载图片的Dialog
 * Created by 24706 on 2016/4/20.
 */
public class LongClickDialog extends Dialog {
    private static final int SAVE_SUCCESS = 110;
    private TextView textView;
    private Context mContext;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SAVE_SUCCESS:
                    Toast.makeText(mContext, "保存成功", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    //图片下载在sd卡中的位置
    private static final String dir = Environment.getExternalStorageDirectory() + "/demo/";

    private Bitmap mBitmap;

    public LongClickDialog(Context context) {
        super(context);
        mContext = context;
        init();
    }

    private void init() {
        setContentView(R.layout.long_click_dialog);
        textView = (TextView) findViewById(R.id.tv_save);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        saveToLocal();
                    }
                }).start();
            }
        });
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }


    public void setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
    }


    /**
     * 将图片保存到本地
     */
    private void saveToLocal() {
        dismiss();

        File dirFile = new File(dir);
        //设置文件名
        File file = new File(dir + System.currentTimeMillis() + ".png");

        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream fs = new FileOutputStream(file);
            mBitmap.compress(Bitmap.CompressFormat.PNG, 90, fs);
            fs.flush();//不要缓冲，立刻保存到文件
            fs.close();
            handler.sendEmptyMessage(SAVE_SUCCESS);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
