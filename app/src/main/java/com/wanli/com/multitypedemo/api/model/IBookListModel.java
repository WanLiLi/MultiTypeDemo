package com.wanli.com.multitypedemo.api.model;

import com.wanli.com.multitypedemo.api.ApiCompleteListener;

/**
 * Created by wanli on 2016/10/25.
 */

public interface IBookListModel {
    /**
     * 获取图书接口
     */
    void loadBookList(String q, String tag, int start, int count, String fields, ApiCompleteListener listener);

    /**
     * 取消加载数据
     */
    void cancelLoading();
}
