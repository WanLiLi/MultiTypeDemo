package com.wanli.com.multitypedemo.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.wanli.com.multitypedemo.R;
import com.wanli.com.multitypedemo.bean.BookInfoResponse;

import java.util.List;

/**
 * Created by wanli on 2016/10/26.
 */

public class BookSeriesItemAdapter extends RecyclerView.Adapter<BookSeriesItemAdapter.BookSeriesHolder> {
    private List<BookInfoResponse> mBookInfoResponse;

    @Override
    public BookSeriesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookSeriesHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_series_ceil, parent, false));
    }

    public void setData(List<BookInfoResponse> bookInfos) {
        this.mBookInfoResponse = bookInfos;
    }

    @Override
    public void onBindViewHolder(final BookSeriesHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(mBookInfoResponse.get(position).getImages().getLarge())
                .into(holder.iv_book_img);
        holder.tv_title.setText(mBookInfoResponse.get(position).getTitle());
        holder.ratingBar_hots.setRating(Float.valueOf(mBookInfoResponse.get(position).getRating().getAverage()) / 2);
        holder.tv_hots_num.setText(mBookInfoResponse.get(position).getRating().getAverage());
    }

    @Override
    public int getItemCount() {
        return mBookInfoResponse.size();
    }

    static class BookSeriesHolder extends RecyclerView.ViewHolder {
        private ImageView iv_book_img;
        private TextView tv_title;
        private AppCompatRatingBar ratingBar_hots;
        private TextView tv_hots_num;

        public BookSeriesHolder(View itemView) {
            super(itemView);
            iv_book_img = (ImageView) itemView.findViewById(R.id.iv_book_img);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            ratingBar_hots = (AppCompatRatingBar) itemView.findViewById(R.id.ratingBar_hots);
            tv_hots_num = (TextView) itemView.findViewById(R.id.tv_hots_num);
        }
    }
}
