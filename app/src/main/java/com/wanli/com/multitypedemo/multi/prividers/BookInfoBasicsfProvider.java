package com.wanli.com.multitypedemo.multi.prividers;

import android.animation.ObjectAnimator;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wanli.com.multitypedemo.R;
import com.wanli.com.multitypedemo.bean.BookInfoResponse;

import java.util.Random;

import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by wanli on 2016/10/26.
 */

public class BookInfoBasicsfProvider extends ItemViewProvider<BookInfoResponse,BookInfoBasicsfProvider.BookInfoHolder> {
    boolean flag;
    //模拟加载时间
    private static final int PROGRESS_DELAY_MIN_TIME = 500;
    private static final int PROGRESS_DELAY_SIZE_TIME = 1000;

    @NonNull
    @Override
    protected BookInfoHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_info, parent, false);
        return new BookInfoHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final BookInfoHolder holder, @NonNull BookInfoResponse mBookInfo) {
        holder.ratingBar_hots.setRating(Float.valueOf(mBookInfo.getRating().getAverage()) / 2);

        holder.tv_hots_num.setText(mBookInfo.getRating().getAverage());
        holder.tv_comment_num.setText(mBookInfo.getRating().getNumRaters() +holder.itemView.getContext().getString(R.string.comment_num));
        holder.tv_book_info.setText(mBookInfo.getInfoString());
        holder.rl_more_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    ObjectAnimator.ofFloat(holder.iv_more_info, "rotation", 90, 0).start();
                    holder.progressBar.setVisibility(View.GONE);
                    holder.ll_publish_info.setVisibility(View.GONE);
                    flag = false;
                } else {
                    ObjectAnimator.ofFloat(holder.iv_more_info, "rotation", 0, 90).start();
                    holder.progressBar.setVisibility(View.VISIBLE);
                    new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            super.handleMessage(msg);
                            if (flag) {
                                holder.ll_publish_info.setVisibility(View.VISIBLE);
                                holder.progressBar.setVisibility(View.GONE);
                            }
                        }
                    }.sendEmptyMessageDelayed(0, getDelayTime());
                    flag = true;
                }
            }
        });
        if (mBookInfo.getAuthor().length > 0) {
            holder.tv_author.setText("作者:" + mBookInfo.getAuthor()[0]);
        }
        holder.tv_publisher.setText("出版社:" + mBookInfo.getPublisher());
        if (mBookInfo.getSubtitle().isEmpty()) {
            holder.tv_subtitle.setVisibility(View.GONE);
        }
        holder.tv_subtitle.setText("副标题:" + mBookInfo.getSubtitle());
        if (mBookInfo.getOrigin_title().isEmpty()) {
            holder.tv_origin_title.setVisibility(View.GONE);
        }
        holder.tv_origin_title.setText("原作名:" + mBookInfo.getOrigin_title());
        if (mBookInfo.getTranslator().length > 0) {
            holder.tv_translator.setText("译者:" + mBookInfo.getTranslator()[0]);
        } else {
            holder.tv_translator.setVisibility(View.GONE);
        }
        holder.tv_publish_date.setText("出版年:" + mBookInfo.getPubdate());
        holder.tv_pages.setText("页数:" + mBookInfo.getPages());
        holder.tv_price.setText("定价:" + mBookInfo.getPrice());
        holder.tv_binding.setText("装帧:" + mBookInfo.getBinding());
        holder.tv_isbn.setText("isbn:" + mBookInfo.getIsbn13());
    }

    static class BookInfoHolder extends RecyclerView.ViewHolder {
        private AppCompatRatingBar ratingBar_hots;
        private TextView tv_hots_num;
        private TextView tv_comment_num;
        private TextView tv_book_info;
        private ImageView iv_more_info;
        private ProgressBar progressBar;
        private RelativeLayout rl_more_info;
        private LinearLayout ll_publish_info;

        private TextView tv_author;
        private TextView tv_publisher;
        private TextView tv_subtitle;
        private TextView tv_origin_title;
        private TextView tv_translator;
        private TextView tv_publish_date;
        private TextView tv_pages;
        private TextView tv_price;
        private TextView tv_binding;
        private TextView tv_isbn;


        public BookInfoHolder(View itemView) {
            super(itemView);
            ratingBar_hots = (AppCompatRatingBar) itemView.findViewById(R.id.ratingBar_hots);
            tv_hots_num = (TextView) itemView.findViewById(R.id.tv_hots_num);
            tv_comment_num = (TextView) itemView.findViewById(R.id.tv_comment_num);
            tv_book_info = (TextView) itemView.findViewById(R.id.tv_book_info);
            iv_more_info = (ImageView) itemView.findViewById(R.id.iv_more_info);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
            rl_more_info = (RelativeLayout) itemView.findViewById(R.id.rl_more_info);
            ll_publish_info = (LinearLayout) itemView.findViewById(R.id.ll_publish_info);

            tv_author = (TextView) itemView.findViewById(R.id.tv_author);
            tv_publisher = (TextView) itemView.findViewById(R.id.tv_publisher);
            tv_subtitle = (TextView) itemView.findViewById(R.id.tv_subtitle);
            tv_origin_title = (TextView) itemView.findViewById(R.id.tv_origin_title);
            tv_translator = (TextView) itemView.findViewById(R.id.tv_translator);
            tv_publish_date = (TextView) itemView.findViewById(R.id.tv_publish_date);
            tv_pages = (TextView) itemView.findViewById(R.id.tv_pages);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            tv_binding = (TextView) itemView.findViewById(R.id.tv_binding);
            tv_isbn = (TextView) itemView.findViewById(R.id.tv_isbn);
        }
    }

    private int getDelayTime() {
        return new Random().nextInt(PROGRESS_DELAY_SIZE_TIME) + PROGRESS_DELAY_MIN_TIME;
    }
}
