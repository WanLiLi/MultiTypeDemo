package com.wanli.com.multitypedemo.api.view.impl;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.wanli.com.multitypedemo.R;
import com.wanli.com.multitypedemo.api.BaseActivity;
import com.wanli.com.multitypedemo.api.presenter.IBookListPresenter;
import com.wanli.com.multitypedemo.api.presenter.impl.BookDetailPreImpl;
import com.wanli.com.multitypedemo.api.presenter.impl.BookListPreImpl;
import com.wanli.com.multitypedemo.api.view.IBookListView;
import com.wanli.com.multitypedemo.bean.BookInfoResponse;
import com.wanli.com.multitypedemo.bean.BookListResponse;
import com.wanli.com.multitypedemo.multi.prividers.BookListsProvider;

import me.drakeet.multitype.MultiTypeAdapter;


/**
 * Created by wanli on 2016/10/25.
 * IBookDetailsPresenter iBookDetailsPresenter = new BookDetailsPreImpl(this);
 */
public class BookListActivity extends BaseActivity implements IBookListView, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView rv;
    private SwipeRefreshLayout swiperefresh;

    private BookListPreImpl iBookDetailsPresenter;


    private String tag = "推荐";
    private static final String fields = "id,title,subtitle,origin_title,rating,author,translator,publisher,pubdate,summary,images,pages,price,binding,isbn13,series";
    private static int count = 10;


    private MultiTypeAdapter adapter;

//普通写法
//    private List<BookInfoResponse> bookInfoResponses;
//    private BookListAdapter bookListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        this.swiperefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        this.rv = (RecyclerView) findViewById(R.id.rv_details);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void initEvent() {
        swiperefresh.setOnRefreshListener(this);
        iBookDetailsPresenter = new BookListPreImpl(this);

        //普通写法
//        bookInfoResponses = new ArrayList<>();
//        bookListAdapter = new BookListAdapter(this, bookInfoResponses);
//        rv.setAdapter(bookListAdapter);

        onRefresh();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        Log.e("showMessage", msg);
    }

    @Override
    public void showProgress() {
        swiperefresh.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swiperefresh.setRefreshing(false);
    }

    @Override
    public void showData(Object result) {
        BookListResponse response = (BookListResponse) result;
        adapter = new MultiTypeAdapter(response.getBooks());
        adapter.register(BookInfoResponse.class, new BookListsProvider());
        //assertAllRegistered(adapter, lists);
        rv.setAdapter(adapter);

//普通写法
//        bookInfoResponses.clear();
//        bookInfoResponses.addAll(response.getBooks());
//        bookListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        iBookDetailsPresenter.loadBooks(null, tag, 0, count, fields);
    }
}
