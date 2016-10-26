package com.wanli.com.multitypedemo.api.http.services;
import com.wanli.com.multitypedemo.bean.BookListResponse;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public interface IBookListService {
    @GET("book/search")
    Observable<Response<BookListResponse>> getBookList(@Query("q") String q, @Query("tag") String tag, @Query("start") int start, @Query("count") int count, @Query("fields") String fields);
}
