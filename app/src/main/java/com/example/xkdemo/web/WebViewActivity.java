package com.example.xkdemo.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.xkdemo.R;
import com.example.xkdemo.pin.PinnedEntry;
import com.example.xkdemo.pin.PinnedHeaderItemDecoration;
import com.example.xkdemo.pin.PinnedHeaderRecyclerView;
import com.example.xkdemo.pin.PinnedListAdapter;

import java.util.ArrayList;
import java.util.List;

public class WebViewActivity extends AppCompatActivity {
    private String url = "https://www.toutiao.com/i6649495680224068103/";
    private WebView webView;

    private NestedScrollView scrollView;
    private PinnedHeaderRecyclerView recyclerView;
    private List<PinnedEntry> data = new ArrayList<>();
    private PinnedListAdapter adapter;
    private TextView tvTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = findViewById(R.id.web);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                return super.shouldOverrideUrlLoading(view, url);
                if (url.startsWith("http")) return false;
                return true;
            }
        });


        webView.loadUrl(url);
        configData();

    }


    private void configData() {
        tvTitle = findViewById(R.id.tv_title);
        scrollView = findViewById(R.id.scroll);
//        scrollView.setNestedScrollingEnabled(true);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setNestedScrollingEnabled(false);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter = new PinnedListAdapter(data));
        recyclerView.addItemDecoration(new PinnedHeaderItemDecoration());

        data.clear();
        for (int i = 0; i < 10; i++) {

            data.add(new PinnedEntry("2018-1-" + i));
            int randomItem = (int) (Math.random() * 10 + 1);
            for (int j = 0; j < randomItem; j++) {
                data.add(new PinnedEntry("1" + j + ":00", "这是内容" + j));
            }
        }

        adapter.notifyDataSetChanged();


        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.e("1234", "nestscroll  " + scrollY + "  " + oldScrollY);
                if (scrollY > 300) {
//                    if (tvTitle.getAlpha() == 0) {
                        tvTitle.setVisibility(View.VISIBLE);
//                        tvTitle.animate().alpha(1).setDuration(50).start();
//                    }
                } else {
//                    if(tvTitle.getAlpha()==1){
//                        tvTitle.animate().alpha(0).setDuration(50).start();
//                    }
                    tvTitle.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

}
