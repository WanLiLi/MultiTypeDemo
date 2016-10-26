package com.wanli.com.multitypedemo.multi.prividers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.wanli.com.multitypedemo.R;
import com.wanli.com.multitypedemo.api.view.impl.BookDetailsActivity;
import com.wanli.com.multitypedemo.bean.BookInfoResponse;

import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by wanli on 2016/10/25.
 */
public class BookListsProvider
        extends ItemViewProvider<BookInfoResponse, BookListsProvider.BookListHolder> {

    @NonNull
    @Override
    protected BookListHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_list, parent, false);
        return new BookListHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final BookListHolder holder, @NonNull final BookInfoResponse bookInfo) {
        Glide.with(holder.itemView.getContext())
                .load(bookInfo.getImages().getLarge())
                .into(holder.iv_book_img);
        holder.tv_book_title.setText(bookInfo.getTitle());
        holder.ratingBar_hots.setRating(Float.valueOf(bookInfo.getRating().getAverage()) / 2);
        holder.tv_hots_num.setText(bookInfo.getRating().getAverage());
        holder.tv_book_info.setText(bookInfo.getInfoString());
        holder.tv_book_description.setText("\u3000" + bookInfo.getSummary());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putSerializable(BookInfoResponse.serialVersionName, bookInfo);
                Bitmap bitmap;
                GlideBitmapDrawable imageDrawable = (GlideBitmapDrawable) holder.iv_book_img.getDrawable();
                if (imageDrawable != null) {
                    bitmap = imageDrawable.getBitmap();
                    b.putParcelable("book_img", bitmap);
                }
                Intent intent = new Intent(holder.itemView.getContext(), BookDetailsActivity.class);
                intent.putExtras(b);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    if (BaseActivity.activity == null) {
//                        UIUtils.startActivity(intent);
//                        return;
//                    }
////                    ActivityOptionsCompat options = ActivityOptionsCompat.
////                            makeSceneTransitionAnimation(BaseActivity.activity, holder.iv_book_img, "book_img");
////                    BaseActivity.activity.startActivity(intent, options.toBundle());
//                } else {
//                    UIUtils.startActivity(intent);
                //}
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    public static class BookListHolder extends RecyclerView.ViewHolder {
        public final ImageView iv_book_img;
        public final TextView tv_book_title;
        public final AppCompatRatingBar ratingBar_hots;
        public final TextView tv_hots_num;
        public final TextView tv_book_info;
        public final TextView tv_book_description;

        BookListHolder(View itemView) {
            super(itemView);
            iv_book_img = (ImageView) itemView.findViewById(R.id.iv_book_img);
            tv_book_title = (TextView) itemView.findViewById(R.id.tv_book_title);
            ratingBar_hots = (AppCompatRatingBar) itemView.findViewById(R.id.ratingBar_hots);
            tv_hots_num = (TextView) itemView.findViewById(R.id.tv_hots_num);
            tv_book_info = (TextView) itemView.findViewById(R.id.tv_book_info);
            tv_book_description = (TextView) itemView.findViewById(R.id.tv_book_description);
        }
    }

}