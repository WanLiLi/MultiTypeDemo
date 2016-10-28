package com.wanli.com.multitypedemo.api.view.impl;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wanli.com.multitypedemo.R;
import com.wanli.com.multitypedemo.api.BaseActivity;
import com.wanli.com.multitypedemo.api.presenter.IBookListPresenter;
import com.wanli.com.multitypedemo.api.presenter.impl.BookDetailPreImpl;
import com.wanli.com.multitypedemo.api.presenter.impl.BookListPreImpl;
import com.wanli.com.multitypedemo.api.view.IBookListView;
import com.wanli.com.multitypedemo.bean.BookInfoResponse;
import com.wanli.com.multitypedemo.bean.BookListResponse;
import com.wanli.com.multitypedemo.bean.FooterLoadMoreBean;
import com.wanli.com.multitypedemo.multi.prividers.BookListsProvider;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.Item;
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

    //代表所有的item,包括footer
    private List<Item> items = new ArrayList<>();
    //此处主要获取加载的size，否则不要此属性
    private List<BookInfoResponse> bookInfoResponses = new ArrayList<>();

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


//        TextView textView = (TextView) findViewById(R.id.haha);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                adapter.notifyItemRemoved(0);
//            }
//        });
    }


    private int itemCount;
    private int lastVisibleItem;
    private int page = 1;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void initEvent() {
        swiperefresh.setOnRefreshListener(this);
        iBookDetailsPresenter = new BookListPreImpl(this);
        rv.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem == itemCount - 1) {
                    onLoadMore();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                itemCount = manager.getItemCount();
                lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
            }
        });
        mToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rv.smoothScrollToPosition(0);
            }
        });

        adapter = new MultiTypeAdapter(items);
        rv.setAdapter(adapter);
        adapter.register(BookInfoResponse.class, new BookListsProvider());
        adapter.applyGlobalMultiTypePool(); // <- 使全局的类型加入到局部中来
        //普通写法
//        bookInfoResponses = new ArrayList<>();
//        bookListAdapter = new BookListAdapter(this, bookInfoResponses);
//        rv.setAdapter(bookListAdapter);

        footerLoadMoreBean = new FooterLoadMoreBean();

        onRefresh();
    }

    FooterLoadMoreBean footerLoadMoreBean;

    class onLoadMoreListener extends OnScrollListener {

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
    public void showProgress(boolean isRefresh) {
        if (isRefresh) {
            swiperefresh.setRefreshing(isRefresh);
        } else {

        }
    }

    @Override
    public void hideProgress() {
        swiperefresh.setRefreshing(false);
    }

    @Override
    public void showData(Object result) {
        BookListResponse response = (BookListResponse) result;
        if (response.getStart() == 0) {
            items.clear();
            items.addAll(response.getBooks());
            items.add(footerLoadMoreBean);
            adapter.notifyItemInserted(items.size() - 1);
            if (page >= 1) {
                page = 1;
            }
        } else {
            adapter.notifyItemRemoved(lastVisibleItem);
            items.remove(footerLoadMoreBean);

            int start = items.size();
            items.addAll(response.getBooks());
            page++;

            items.add(footerLoadMoreBean);
            adapter.notifyItemRangeInserted(start + 1, items.size() + 1);
        }
//普通写法
//        bookInfoResponses.clear();
//        bookInfoResponses.addAll(response.getBooks());
//        bookListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        iBookDetailsPresenter.loadBooks(null, tag, 0, count, fields, true);
    }

    public void onLoadMore() {
        iBookDetailsPresenter.loadBooks(null, tag, page * count, count, fields, false);
    }
}
