package com.wanli.com.multitypedemo.api.model.impl;

import com.google.gson.Gson;
import com.wanli.com.multitypedemo.api.ApiCompleteListener;
import com.wanli.com.multitypedemo.api.http.ServiceFactory;
import com.wanli.com.multitypedemo.api.http.services.IBookListService;
import com.wanli.com.multitypedemo.api.model.IBookListModel;
import com.wanli.com.multitypedemo.bean.BaseResponse;
import com.wanli.com.multitypedemo.bean.BookListResponse;

import java.net.UnknownHostException;

import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wanli on 2016/10/25.
 */

public class BookListModelImpl implements IBookListModel {
    @Override
    public void loadBookList(String q, String tag, int start, int count, String fields, final ApiCompleteListener listener) {
        IBookListService service = ServiceFactory.createService(IBookListService.class);
        service.getBookList(q, tag, start, count, fields)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<BookListResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof UnknownHostException) {
                            listener.onFailed(null);
                            return;
                        }
                        listener.onFailed(new BaseResponse(404, e.getMessage()));
                    }

                    @Override
                    public void onNext(Response<BookListResponse> bookListResponseResponse) {
                        if (bookListResponseResponse.isSuccessful()) {
                            String str = new Gson().toJson(bookListResponseResponse.body());
                            listener.onComplected(bookListResponseResponse.body());
                        } else {
                            listener.onFailed(new BaseResponse(bookListResponseResponse.code(), bookListResponseResponse.message()));
                        }
                    }
                });
    }

    @Override
    public void cancelLoading() {

    }
}
