package com.wanli.com.multitypedemo.multi.prividers;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.wanli.com.multitypedemo.R;
import com.wanli.com.multitypedemo.bean.FooterLoadMoreBean;

import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by wanli on 2016/10/27.
 */

public class FooterLoadMorePrivider extends ItemViewProvider<FooterLoadMoreBean, FooterLoadMorePrivider.FooterHolder> {


    @NonNull
    @Override
    protected FooterHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new FooterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_load_more, parent, false));
    }


    @Override
    protected void onBindViewHolder(@NonNull FooterHolder holder, @NonNull FooterLoadMoreBean footerLoadMoreBean) {

    }

    public static class FooterHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public FooterHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar_load_more);
        }
    }

}
