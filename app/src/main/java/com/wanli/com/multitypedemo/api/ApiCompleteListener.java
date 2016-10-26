package com.wanli.com.multitypedemo.api;


import com.wanli.com.multitypedemo.bean.BaseResponse;

/**
 * 网络请求回调接口
 *
 *
 */
public interface ApiCompleteListener {
    void onComplected(Object result);

    void onFailed(BaseResponse msg);
}