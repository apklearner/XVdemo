package com.example.xkdemo.pin;


import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xkdemo.R;

import java.util.ArrayList;
import java.util.List;

public class PinnedListAdapter extends PinnedHeaderAdapter<RecyclerView.ViewHolder> {

    private List<PinnedEntry> datas = new ArrayList<>();

    private static final int TYPE_PINNED_TITLE = 0;
    private static final int TYPE_PINNED_CONTENT = 1;


    public PinnedListAdapter(List<PinnedEntry> datas) {
        this.datas = datas;
    }

    @Override
    public boolean isPinnedPosition(int position) {
        return getItemViewType(position) == TYPE_PINNED_TITLE;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        if (getItemViewType(position) == TYPE_PINNED_TITLE) {
            return new TitleHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_pinned_title, parent, false));
        } else {
            return new ContentHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_pinned_content, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof TitleHolder) {
            TitleHolder holder = (TitleHolder) viewHolder;
            holder.title.setText(datas.get(position).content);
        } else {
            ContentHolder holder = (ContentHolder) viewHolder;
            holder.time.setText(datas.get(position).time);
            holder.content.setText(datas.get(position).content);
            holder.vTop.setVisibility(View.VISIBLE);
            holder.vBot.setVisibility(View.VISIBLE);
//            if (position != 1) {
//                holder.vTop.setVisibility(View.GONE);
//            }


        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    @Override
    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
        return datas.get(position).isPinned ? TYPE_PINNED_TITLE : TYPE_PINNED_CONTENT;
    }

    private class TitleHolder extends RecyclerView.ViewHolder {

        public TextView title;

        public TitleHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_pinned_title);
        }
    }

    private class ContentHolder extends RecyclerView.ViewHolder {
        public TextView time;
        public TextView content;
        public View vTop;
        public View vBot;

        public ContentHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.tv_pinned_time);
            content = itemView.findViewById(R.id.tv_pinned_content);
            vTop = itemView.findViewById(R.id.view_top);
            vBot = itemView.findViewById(R.id.view_bottom);

        }
    }


}


