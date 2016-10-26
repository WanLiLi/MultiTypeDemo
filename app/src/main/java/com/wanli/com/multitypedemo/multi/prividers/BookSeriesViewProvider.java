package com.wanli.com.multitypedemo.multi.prividers;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;


import com.wanli.com.multitypedemo.R;
import com.wanli.com.multitypedemo.adapter.BookSeriesItemAdapter;
import com.wanli.com.multitypedemo.bean.BookInfoResponse;
import com.wanli.com.multitypedemo.bean.BookSeriesListResponse;

import java.util.List;

import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by wanli on 2016/10/14.
 */

public class BookSeriesViewProvider extends ItemViewProvider<BookSeriesListResponse, BookSeriesViewProvider.BookSeriesHolder> {

    @NonNull
    @Override
    protected BookSeriesHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycview, parent, false);
        return new BookSeriesViewProvider.BookSeriesHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull BookSeriesHolder holder, @NonNull BookSeriesListResponse BookSeriesListResponse) {
        holder.setData(BookSeriesListResponse.getBooks());
    }

    class BookSeriesHolder extends RecyclerView.ViewHolder {
        private RecyclerView recyclerView;
        private BookSeriesItemAdapter adapter;

        public BookSeriesHolder(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recycview);
            recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
            adapter = new BookSeriesItemAdapter();
            recyclerView.setAdapter(adapter);
        }

        public void setData(List<BookInfoResponse> bookInfos) {
            adapter.setData(bookInfos);
            adapter.notifyDataSetChanged();
        }
    }
}
