package com.wanli.com.multitypedemo.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wanli.com.multitypedemo.R;
import com.wanli.com.multitypedemo.bean.BookInfoResponse;

import java.util.List;

/**
 * Created by wanli on 2016/10/25.
 */

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookListHolder> {

    private Context mContext;
    private  List<BookInfoResponse> bookInfoResponses;

    public BookListAdapter(Context context, List<BookInfoResponse> bookInfoResponses) {
        this.mContext = context;
        this.bookInfoResponses = bookInfoResponses;
    }

    public void  setData(List<BookInfoResponse> bookInfoResponses){
        this.bookInfoResponses = bookInfoResponses;
    }

    @Override
    public BookListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_list, parent, false);
        return new BookListHolder(view);
    }

    @Override
    public void onBindViewHolder(BookListHolder holder, int position) {
        final BookInfoResponse bookInfo = bookInfoResponses.get(position);
        Glide.with(mContext)
                .load(bookInfo.getImages().getLarge())
                .into(((BookListHolder) holder).iv_book_img);
        ((BookListHolder) holder).tv_book_title.setText(bookInfo.getTitle());
        ((BookListHolder) holder).ratingBar_hots.setRating(Float.valueOf(bookInfo.getRating().getAverage()) / 2);
        ((BookListHolder) holder).tv_hots_num.setText(bookInfo.getRating().getAverage());
        ((BookListHolder) holder).tv_book_info.setText(bookInfo.getInfoString());
        ((BookListHolder) holder).tv_book_description.setText("\u3000" + bookInfo.getSummary());
    }

    @Override
    public int getItemCount() {
        return bookInfoResponses.size();
    }

    public static class BookListHolder extends RecyclerView.ViewHolder {
        public final ImageView iv_book_img;
        public final TextView tv_book_title;
        public final AppCompatRatingBar ratingBar_hots;
        public final TextView tv_hots_num;
        public final TextView tv_book_info;
        public final TextView tv_book_description;

        BookListHolder(View itemView) {
            super(itemView);
            iv_book_img = (ImageView) itemView.findViewById(R.id.iv_book_img);
            tv_book_title = (TextView) itemView.findViewById(R.id.tv_book_title);
            ratingBar_hots = (AppCompatRatingBar) itemView.findViewById(R.id.ratingBar_hots);
            tv_hots_num = (TextView) itemView.findViewById(R.id.tv_hots_num);
            tv_book_info = (TextView) itemView.findViewById(R.id.tv_book_info);
            tv_book_description = (TextView) itemView.findViewById(R.id.tv_book_description);
        }
    }
}
