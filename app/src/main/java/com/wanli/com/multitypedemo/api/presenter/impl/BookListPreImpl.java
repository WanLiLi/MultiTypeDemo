package com.wanli.com.multitypedemo.api.presenter.impl;

import com.wanli.com.multitypedemo.api.ApiCompleteListener;
import com.wanli.com.multitypedemo.api.IBaseView;
import com.wanli.com.multitypedemo.api.model.IBookListModel;
import com.wanli.com.multitypedemo.api.model.impl.BookListModelImpl;
import com.wanli.com.multitypedemo.api.presenter.IBookListPresenter;
import com.wanli.com.multitypedemo.api.view.IBookListView;
import com.wanli.com.multitypedemo.bean.BaseResponse;

/**
 * Created by wanli on 2016/10/25.
 * <p>
 * this.iBookDetailsView = iBookDetailsView;
 * iBookDetailsModel = new BookDetailsModelImpl();
 */

public class BookListPreImpl implements IBookListPresenter, ApiCompleteListener {
    private IBookListView iBookDetailsView;
    private IBookListModel iBookDetailsModel;

    public BookListPreImpl(IBaseView iBookDetailsView) {
        this.iBookDetailsView = (IBookListView)iBookDetailsView;
        iBookDetailsModel = new BookListModelImpl();
    }

    @Override
    public void loadBooks(String q, String tag, int start, int count, String fields) {
        iBookDetailsView.showProgress();
        iBookDetailsModel.loadBookList(q, tag, start, count, fields, this);
    }

    @Override
    public void cancelLoading() {
        iBookDetailsView.hideProgress();
    }

    @Override
    public void onComplected(Object result) {
        iBookDetailsView.hideProgress();
        iBookDetailsView.showData(result);
    }

    @Override
    public void onFailed(BaseResponse msg) {
        iBookDetailsView.showMessage(msg.getMsg());
    }
}
