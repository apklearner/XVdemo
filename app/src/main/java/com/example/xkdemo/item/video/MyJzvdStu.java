package com.example.xkdemo.item.video;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import cn.jzvd.JzvdStd;

public class MyJzvdStu extends JzvdStd {
    public MyJzvdStu(Context context) {
        super(context);
    }

    public MyJzvdStu(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == cn.jzvd.R.id.start) {
            if (listener != null)
                listener.onStart();
        }
        super.onClick(v);
    }


    private OnVideoListener listener;

    public void setVideoListener(OnVideoListener listener) {
        this.listener = listener;
    }
}
