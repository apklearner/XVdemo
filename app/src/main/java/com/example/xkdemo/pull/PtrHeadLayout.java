package com.example.xkdemo.pull;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.example.xkdemo.R;
import com.example.xkdemo.pull.lib.PtrFrameLayout;
import com.example.xkdemo.pull.lib.PtrUIHandler;
import com.example.xkdemo.pull.lib.indicator.PtrIndicator;

public class PtrHeadLayout extends RelativeLayout implements PtrUIHandler {
    public PtrHeadLayout(Context context) {
        this(context, null);
    }

    public PtrHeadLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PtrHeadLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(R.layout.layout_pull_head, this, true);


    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {

    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

    }
}
