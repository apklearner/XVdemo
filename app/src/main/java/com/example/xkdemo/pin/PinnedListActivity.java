package com.example.xkdemo.pin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.example.xkdemo.R;

import java.util.ArrayList;
import java.util.List;

public class PinnedListActivity extends AppCompatActivity {
    private PinnedHeaderRecyclerView recyclerView;
    private List<PinnedEntry> data = new ArrayList<>();
    private PinnedListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinned);


        recyclerView = findViewById(R.id.recyclerview);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter = new PinnedListAdapter(data));
        recyclerView.addItemDecoration(new PinnedHeaderItemDecoration());

        configData();
    }

    private void configData() {

        data.clear();
        for (int i = 0; i < 10; i++) {

            data.add(new PinnedEntry("2018-1-" + i));
            int randomItem = (int) (Math.random() * 10 + 1);
            for (int j = 0; j < randomItem; j++) {
                data.add(new PinnedEntry("1" + j + ":00", "这是内容" + j));
            }
        }

        adapter.notifyDataSetChanged();


    }

}
