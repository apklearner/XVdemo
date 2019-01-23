package com.example.xkdemo.item;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.xkdemo.R;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

public class NewsListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
//        JzvdStd.setVideoImageDisplayType(JzvdStd.SCREEN_WINDOW_FULLSCREEN);//设置容器内播放器高

        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new NewsAdapter(this));
    }



}
