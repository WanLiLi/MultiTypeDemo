package com.wanli.com.multitypedemo.api.http.services;


import com.wanli.com.multitypedemo.bean.BookReviewsListResponse;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


public interface IBookReviewsService {
    /**
     * 获取图书评论
     *
     * @param bookId 图书id
     * @param start  起始index
     * @param count  请求条数
     * @param fields 过滤字段(多个字段用","分隔)
     */
    @GET("book/{bookId}/reviews")
    Observable<Response<BookReviewsListResponse>> getBookReviews(@Path("bookId") String bookId, @Query("start") int start, @Query("count") int count, @Query("fields") String fields);

}
