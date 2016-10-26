package com.wanli.com.multitypedemo.api.presenter.impl;

import com.wanli.com.multitypedemo.api.ApiCompleteListener;
import com.wanli.com.multitypedemo.api.IBaseView;
import com.wanli.com.multitypedemo.api.model.IBookDetailModel;
import com.wanli.com.multitypedemo.api.model.impl.BookDetailModelImpl;
import com.wanli.com.multitypedemo.api.presenter.IBookDetailPresenter;
import com.wanli.com.multitypedemo.bean.BaseResponse;

/**
 * Created by wanli on 2016/10/26.
 */

public class BookDetailPreImpl implements IBookDetailPresenter,ApiCompleteListener{
    private IBaseView iBaseView;
    private IBookDetailModel iBookDetailModel;

    public BookDetailPreImpl(IBaseView ibookDetalView) {
        this.iBaseView = ibookDetalView;
        iBookDetailModel = new BookDetailModelImpl();
    }

    @Override
    public void loadReviews(String bookId, int start, int count, String fields) {
        iBookDetailModel.loadReviewsList(bookId,start,count,fields,this);
    }

    @Override
    public void loadSeries(String seriesId, int start, int count, String fields) {
        iBookDetailModel.loadSeriesList(seriesId, start, count, fields, this);
    }

    @Override
    public void onComplected(Object result) {
        iBaseView.showData(result);
    }

    @Override
    public void onFailed(BaseResponse msg) {

    }
}
