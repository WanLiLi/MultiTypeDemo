package com.wanli.com.multitypedemo.api.http.services;

import com.wanli.com.multitypedemo.bean.BookSeriesListResponse;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wanli on 2016/10/26.
 */

public interface IBookSeriesService {
    @GET("book/series/{seriesId}/books")
    Observable<Response<BookSeriesListResponse>> getBookSeries(@Path("seriesId") String seriesId, @Query("start") int start, @Query("count") int count, @Query("fields") String fields);
}
