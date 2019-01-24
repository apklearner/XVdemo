package com.example.xkdemo.item;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xkdemo.R;
import com.example.xkdemo.item.video.MyJzvdStu;
import com.example.xkdemo.item.video.OnVideoListener;
import com.example.xkdemo.utils.GlideUtils;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_SINGLE = 0;
    private static final int TYPE_SECOND = 1;
    private static final int TYPE_THIRD = 2;
    private static final int TYPE_VIDEO = 3;
    private static final int MAX_COUNT = 30;

    private Context context;

    public NewsAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return null;
        switch (viewType) {
            case TYPE_SINGLE:
                return new ViewHolderSingle(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_recy_1, null));
            case TYPE_SECOND:
                return new ViewHolderSecond(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_recy_2, null));
            case TYPE_THIRD:
                return new ViewHolderThird(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_recy_3, null));
            case TYPE_VIDEO:
                return new ViewHolderVideo(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_video, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_SINGLE:
                ViewHolderSingle single = (ViewHolderSingle) holder;

                break;
            case TYPE_SECOND:
                ViewHolderSecond second = (ViewHolderSecond) holder;
                GlideUtils.loadImage(context, second.image, "http://pic26.nipic.com/20121227/10193203_131357536000_2.jpg");
                break;
            case TYPE_THIRD:
                ViewHolderThird third = (ViewHolderThird) holder;
                GlideUtils.loadImage(context, third.image1, "http://img.bimg.126.net/photo/ZZ5EGyuUCp9hBPk6_s4Ehg==/5727171351132208489.jpg");
                GlideUtils.loadImage(context, third.image2, "http://www.pptok.com/wp-content/uploads/2012/08/xunguang-4.jpg");
                GlideUtils.loadImage(context, third.image3, "http://www.pptbz.com/pptpic/UploadFiles_6909/201306/2013062320262198.jpg");

                break;
            case TYPE_VIDEO:
                ViewHolderVideo video = (ViewHolderVideo) holder;
//                Jzvd.releaseAllVideos();
//                video.jzvdStd.
//                Jzvd.
                video.tvTime.setVisibility(View.VISIBLE);
                video.jzvdStd.setUp("http://vjs.zencdn.net/v/oceans.mp4", "标题", Jzvd.SCREEN_WINDOW_LIST);
                video.jzvdStd.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                video.jzvdStd.setVideoListener(new OnVideoListener() {
                    @Override
                    public void onStart() {
                        video.tvTime.setVisibility(View.GONE);
                    }

                    @Override
                    public void onStateNormal() {
                        video.tvTime.setVisibility(View.VISIBLE);
                    }
                });
//                video.jzvdStd.thumbImageView.setImageResource(R.mipmap.ic_channel_edit);
                GlideUtils.loadImage(video.jzvdStd.getContext(), video.jzvdStd.thumbImageView, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1548820713&di=41d62480193c03d16e184e566299984d&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.gzlco.com%2Fimggzl%2F657%2F6e3a001796264e4db462a39301ccc9e74237.jpeg");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return MAX_COUNT;
    }


    @Override
    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
        switch (position % 4) {
            case 0:
                return TYPE_SINGLE;
            case 1:
                return TYPE_SECOND;
            case 2:
                return TYPE_THIRD;
            default:
                return TYPE_VIDEO;
        }
    }


    private class ViewHolderSingle extends RecyclerView.ViewHolder {

        public ViewHolderSingle(View itemView) {
            super(itemView);
        }
    }

    private class ViewHolderSecond extends RecyclerView.ViewHolder {

        public ImageView image;

        public ViewHolderSecond(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
        }
    }

    private class ViewHolderThird extends RecyclerView.ViewHolder {

        public ImageView image1;
        public ImageView image2;
        public ImageView image3;

        public ViewHolderThird(View itemView) {
            super(itemView);
            image1 = itemView.findViewById(R.id.image_1);
            image2 = itemView.findViewById(R.id.image_2);
            image3 = itemView.findViewById(R.id.image_3);
        }
    }

    private class ViewHolderVideo extends RecyclerView.ViewHolder {

        public MyJzvdStu jzvdStd;
        public TextView tvTime;

        public ViewHolderVideo(View itemView) {
            super(itemView);
            jzvdStd = itemView.findViewById(R.id.videoplayer);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }
}
