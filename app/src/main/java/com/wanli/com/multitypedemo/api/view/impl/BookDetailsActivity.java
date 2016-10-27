package com.wanli.com.multitypedemo.api.view.impl;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wanli.com.multitypedemo.R;
import com.wanli.com.multitypedemo.api.BaseActivity;
import com.wanli.com.multitypedemo.api.IBaseView;
import com.wanli.com.multitypedemo.api.presenter.impl.BookDetailPreImpl;
import com.wanli.com.multitypedemo.bean.BookInfoResponse;
import com.wanli.com.multitypedemo.bean.BookListResponse;
import com.wanli.com.multitypedemo.bean.BookReviewResponse;
import com.wanli.com.multitypedemo.bean.BookReviewsListResponse;
import com.wanli.com.multitypedemo.bean.BookSeriesListResponse;
import com.wanli.com.multitypedemo.multi.prividers.BookInfoBasicsfProvider;
import com.wanli.com.multitypedemo.multi.prividers.BookInfoBriefProvider;
import com.wanli.com.multitypedemo.multi.prividers.BookReviewsListResponseItemViewProvider;
import com.wanli.com.multitypedemo.multi.prividers.BookSeriesViewProvider;

import java.util.ArrayList;

import me.drakeet.multitype.Item;
import me.drakeet.multitype.MultiTypeAdapter;

public class BookDetailsActivity extends BaseActivity implements IBaseView {

    private BookInfoResponse mBookInfoResponse;
    private RecyclerView rydetails;
    private MultiTypeAdapter multiTypeAdapter;
    ArrayList<Item> list = new ArrayList<>();


    private BookDetailPreImpl iBookListPresenter;
    private static final String COMMENT_FIELDS = "id,rating,author,title,updated,comments,summary,votes,useless";
    private static final String SERIES_FIELDS = "id,title,subtitle,origin_title,rating,author,translator,publisher,pubdate,summary,images,pages,price,binding,isbn13,series";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_book_details);
        this.rydetails = (RecyclerView) findViewById(R.id.ry_details);
        rydetails.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void initEvent() {
        iBookListPresenter = new BookDetailPreImpl(this);
        mBookInfoResponse = (BookInfoResponse) getIntent().getSerializableExtra(BookInfoResponse.serialVersionName);
        setTitle(mBookInfoResponse.getTitle());

        /**基础信息*/
        list.add(mBookInfoResponse);
        multiTypeAdapter = new MultiTypeAdapter(list);
        multiTypeAdapter.register(BookInfoResponse.class, new BookInfoBasicsfProvider());
        rydetails.setAdapter(multiTypeAdapter);

        /**简介*/
        BookInfoResponse.BookInfoResponseBrief brief = new BookInfoResponse.BookInfoResponseBrief(mBookInfoResponse.getSummary());
        list.add(brief);
        multiTypeAdapter.register(BookInfoResponse.BookInfoResponseBrief.class, new BookInfoBriefProvider());
        multiTypeAdapter.notifyDataSetChanged();


        loadReviews(mBookInfoResponse.getId(), 0, 5, COMMENT_FIELDS);
    }

    public void loadReviews(String bookId, int start, int count, String fields) {
        iBookListPresenter.loadReviews(bookId, start, count, fields);
    }

    @Override
    public void showData(Object result) {
        /**评论*/
        if (result instanceof BookReviewsListResponse) {
            if (!((BookReviewsListResponse) result).getReviews().isEmpty()) {
                list.addAll(((BookReviewsListResponse) result).getReviews());
                multiTypeAdapter.register(BookReviewResponse.class, new BookReviewsListResponseItemViewProvider());
                multiTypeAdapter.notifyDataSetChanged();
            }
            if (mBookInfoResponse.getSeries() != null) {
                iBookListPresenter.loadSeries(mBookInfoResponse.getSeries().getId(), 0, 6, SERIES_FIELDS);
            }
        } else if (result instanceof BookSeriesListResponse) {
            /**获取推荐丛书*/
            list.add(((BookSeriesListResponse) result));
            multiTypeAdapter.register(BookSeriesListResponse.class, new BookSeriesViewProvider());
            multiTypeAdapter.notifyDataSetChanged();
        }
    }
}
