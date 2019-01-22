package com.example.xkdemo.pull;

import android.content.Context;
import android.util.AttributeSet;

import com.example.xkdemo.pull.lib.PtrFrameLayout;


public class PtrLayout extends PtrFrameLayout {

    private PtrHeadLayout headLayout;

    public PtrLayout(Context context) {
        this(context, null);
    }

    public PtrLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PtrLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        headLayout = new PtrHeadLayout(getContext());
        setHeaderView(headLayout);
        addPtrUIHandler(headLayout);
    }

    public void onRefreshComplete() {
        refreshComplete();
    }

}
