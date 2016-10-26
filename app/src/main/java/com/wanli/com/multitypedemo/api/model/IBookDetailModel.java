package com.wanli.com.multitypedemo.api.model;

import com.wanli.com.multitypedemo.api.ApiCompleteListener;

/**
 * Created by wanli on 2016/10/26.
 */

public interface IBookDetailModel {
    /**
     * 获取图书评论
     */
    void loadReviewsList(String bookId, int start, int count, String fields, ApiCompleteListener listener);
    /**
     * 获取推荐图书
     */
    void loadSeriesList(String bookId, int start, int count, String fields, ApiCompleteListener listener);
}
