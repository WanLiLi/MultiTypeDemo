package com.wanli.com.multitypedemo.multi.prividers;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hymane.expandtextview.ExpandTextView;
import com.wanli.com.multitypedemo.R;
import com.wanli.com.multitypedemo.bean.BookInfoResponse;

import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by wanli on 2016/10/26.
 */

public class BookInfoBriefProvider extends ItemViewProvider<BookInfoResponse.BookInfoResponseBrief,BookInfoBriefProvider.BookBriefHolder> {
    @NonNull
    @Override
    protected BookBriefHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
//      View view = inflater.inflate(R.layout.expand_text_view,parent,false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_brief, parent, false);
        return new BookBriefHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull BookBriefHolder holder, @NonNull BookInfoResponse.BookInfoResponseBrief mBookInfo) {
        if (mBookInfo.getSummary() == null || mBookInfo.getSummary().equals("")) {
            Log.d("", "必須在 BookInfoResponse 类的构造函数中做属性适配");
        } else {
            if (!mBookInfo.getSummary().isEmpty()) {
                holder.etv_brief.setContent(mBookInfo.getSummary());
            } else {
                holder.etv_brief.setContent(holder.itemView.getContext().getString(R.string.no_brief));
            }
        }
    }

    static class BookBriefHolder extends RecyclerView.ViewHolder {
        private ExpandTextView etv_brief;

        public BookBriefHolder(View itemView) {
            super(itemView);
            etv_brief = (ExpandTextView) itemView.findViewById(R.id.etv_brief);
        }
    }
}
