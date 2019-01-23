package com.example.xkdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.xkdemo.channel.ChannelActivity;
import com.example.xkdemo.item.NewsListActivity;
import com.example.xkdemo.pin.PinnedListActivity;
import com.example.xkdemo.tab.TabActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnChannel, btnPin, btnTab, btnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRes();
    }

    private void initRes() {
        btnChannel = findViewById(R.id.btn_channel);
        btnPin = findViewById(R.id.btn_pinned);
        btnTab = findViewById(R.id.btn_tab);
        btnList = findViewById(R.id.btn_list);

        btnChannel.setOnClickListener(this);
        btnPin.setOnClickListener(this);
        btnTab.setOnClickListener(this);
        btnList.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_channel:
                startActivity(new Intent(this, ChannelActivity.class));
                break;
            case R.id.btn_pinned:
                startActivity(new Intent(this, PinnedListActivity.class));
                break;
            case R.id.btn_tab:
                startActivity(new Intent(this, TabActivity.class));
                break;
            case R.id.btn_list:
                startActivity(new Intent(this, NewsListActivity.class));
                break;
        }
    }
}
