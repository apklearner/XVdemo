package com.example.xkdemo.tab;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.example.xkdemo.R;
import com.example.xkdemo.pin.PinnedEntry;
import com.example.xkdemo.pin.PinnedHeaderItemDecoration;
import com.example.xkdemo.pin.PinnedHeaderRecyclerView;
import com.example.xkdemo.pin.PinnedListAdapter;
import com.example.xkdemo.pull.PtrLayout;
import com.example.xkdemo.pull.lib.PtrDefaultHandler;
import com.example.xkdemo.pull.lib.PtrFrameLayout;
import com.example.xkdemo.pull.lib.PtrHandler;
import com.example.xkdemo.pull.lib.indicator.PtrIndicator;

import java.util.ArrayList;
import java.util.List;

public class TabFragment extends Fragment {

    private PtrLayout ptr_layout;
    private TextView tvTitle;
    private PinnedHeaderRecyclerView recyclerView;
    private int second = 0;
    private int height;
    private Scroller scroller;
    private Handler handler = new Handler();
    private final int CLOSE_DURATION = 800;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.layout_fra_tab, null);
        ptr_layout = v.findViewById(R.id.ptr_layout);
        tvTitle = v.findViewById(R.id.tv_msg);
        recyclerView = v.findViewById(R.id.pined_recycler);
//        height = tvTitle.getMeasuredHeight();
        height = (int) (getResources().getDisplayMetrics().density * 30);
        scroller = new Scroller(getActivity());
        configData();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        tvContent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (scroller.isFinished()) {
//                    PtrIndicator.setPosSecond(height);
//                    ptr_layout.refreshComplete();
//                    scroller.startScroll(0, -height, 0, height, 500);
//                    handler.post(taskRunner);
//                }
//            }
//        });

//        ptr_layout.autoRefresh(true,300);
        ptr_layout.postDelayed(new Runnable() {
            @Override
            public void run() {
                ptr_layout.autoRefresh();
            }
        }, 300);

        ptr_layout.setPtrHandler(new PtrHandler() {

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        PtrIndicator.setPosSecond(height);
                        ptr_layout.refreshComplete();
                        scroller.startScroll(0, -height, 0, height, CLOSE_DURATION);
                        handler.post(taskRunner);
                    }
                }, 2500);
            }
        });

        ptr_layout.setDurationToCloseHeader(CLOSE_DURATION);

    }

    private Runnable taskRunner = new Runnable() {
        @Override
        public void run() {
            if (scroller.computeScrollOffset()) {
                int curY = scroller.getCurrY();

                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) tvTitle.getLayoutParams();
                params.topMargin = curY;
                tvTitle.setLayoutParams(params);


                handler.post(this);
            } else if (PtrIndicator.getPosStart() != 0) {
                PtrIndicator.setPosSecond(0);
                ptr_layout.refreshComplete();
                scroller.startScroll(0, 0, 0, -height, CLOSE_DURATION);
                handler.post(taskRunner);
            } else {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) tvTitle.getLayoutParams();
                if (params.topMargin == 0) {
                    scroller.startScroll(0, 0, 0, -height, CLOSE_DURATION);
                    handler.post(taskRunner);
                }
            }
        }
    };


    private List<PinnedEntry> data = new ArrayList<>();
    private PinnedListAdapter adapter;

    private void configData() {

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
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


    }

}
