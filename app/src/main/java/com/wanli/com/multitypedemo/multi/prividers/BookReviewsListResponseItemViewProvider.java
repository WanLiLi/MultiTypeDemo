package com.wanli.com.multitypedemo.multi.prividers;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.wanli.com.multitypedemo.R;
import com.wanli.com.multitypedemo.bean.BookReviewResponse;


import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by wanli on 2016/10/13.
 */

public class BookReviewsListResponseItemViewProvider extends ItemViewProvider<BookReviewResponse, BookReviewsListResponseItemViewProvider.BookCommentHolder> {

    @NonNull
    @Override
    protected BookCommentHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_comment, parent, false);
        return new BookReviewsListResponseItemViewProvider.BookCommentHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final BookCommentHolder holder, @NonNull BookReviewResponse reviewResponse) {
        if (reviewResponse==null || TextUtils.isEmpty(reviewResponse.getComments())) {
            holder.itemView.setVisibility(View.GONE);
        } else {
            holder.tv_user_name.setText(reviewResponse.getAuthor().getName());
            Glide.with(holder.itemView.getContext())
                    .load(reviewResponse.getAuthor().getAvatar())
                    .asBitmap()
                    .centerCrop()
                    .into(new BitmapImageViewTarget(holder.iv_avatar) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(holder.itemView.getContext().getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            holder.iv_avatar.setImageDrawable(circularBitmapDrawable);
                        }
                    });

            if (reviewResponse.getRating() != null) {
                holder.ratingBar_hots.setRating(Float.valueOf(reviewResponse.getRating().getValue()));
            }
            holder.tv_comment_content.setText(reviewResponse.getSummary());
            holder.tv_favorite_num.setText(reviewResponse.getVotes() + "");
            holder.tv_update_time.setText(reviewResponse.getUpdated().split(" ")[0]);
        }
//        else if (position == HEADER_COUNT) {   //进入评论区域第一条
//            holder.tv_comment_title.setVisibility(View.VISIBLE);
//        } else if (position == reviews.size() + 1) {  //最后一个  5+1
//            holder.tv_more_comment.setVisibility(View.VISIBLE);
//            holder.tv_more_comment.setText(UIUtils.getContext().getString(R.string.more_brief) + mReviewsListResponse.getTotal() + "条");
//            holder.tv_more_comment.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(UIUtils.getContext(), BookReviewsActivity.class);
//                    intent.putExtra("bookId", mBookInfo.getId());
//                    intent.putExtra("bookName", mBookInfo.getTitle());
//                    UIUtils.startActivity(intent);
//                }
//            });
//        }
    }

    static class BookCommentHolder extends RecyclerView.ViewHolder {
        private TextView tv_comment_title;
        private ImageView iv_avatar;
        private TextView tv_user_name;
        private AppCompatRatingBar ratingBar_hots;
        private TextView tv_comment_content;
        private ImageView iv_favorite;
        private TextView tv_favorite_num;
        private TextView tv_update_time;
        private TextView tv_more_comment;

        public BookCommentHolder(View itemView) {
            super(itemView);
            tv_comment_title = (TextView) itemView.findViewById(R.id.tv_comment_title);
            iv_avatar = (ImageView) itemView.findViewById(R.id.iv_avatar);
            tv_user_name = (TextView) itemView.findViewById(R.id.tv_user_name);
            ratingBar_hots = (AppCompatRatingBar) itemView.findViewById(R.id.ratingBar_hots);
            tv_comment_content = (TextView) itemView.findViewById(R.id.tv_comment_content);
            iv_favorite = (ImageView) itemView.findViewById(R.id.iv_favorite);
            tv_favorite_num = (TextView) itemView.findViewById(R.id.tv_favorite_num);
            tv_update_time = (TextView) itemView.findViewById(R.id.tv_update_time);
            tv_more_comment = (TextView) itemView.findViewById(R.id.tv_more_comment);
        }
    }
}
