package com.wanli.com.multitypedemo.api.view;

import com.wanli.com.multitypedemo.api.IBaseView;

/**
 * Author   :hymanme
 * Email    :hymanme@163.com
 * Create at 2016/1/10
 * Description:
 */
public interface IBookListView extends IBaseView {
    void showMessage(String msg);

    void showProgress(boolean isRefresh);

    void hideProgress();
}