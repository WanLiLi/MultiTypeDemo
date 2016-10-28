package com.wanli.com.multitypedemo.api.presenter;

/**
 * Created by wanli on 2016/10/25.
 */

public interface IBookListPresenter {
    void loadBooks(String q, String tag, int start, int count, String fields,boolean isRefresh);

    void cancelLoading();
}
