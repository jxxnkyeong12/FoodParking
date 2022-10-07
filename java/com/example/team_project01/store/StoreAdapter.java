package com.example.team_project01.store;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.team_project01.R;
import com.example.team_project01.common.CommonVal;
import com.example.team_project01.common.ReviewDetailActivity;
import com.example.team_project01.myinfo.LikeAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder>{
    LayoutInflater inflater;
    ArrayList<StoreInfoVO> list;
    Context context;
    StoreActivity storeActivity;


    public StoreAdapter(LayoutInflater inflater, ArrayList<StoreInfoVO> list, Context context, StoreActivity storeActivity) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
        this.storeActivity = storeActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_store_review, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.bind(holder,position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RatingBar store_strating;
        TextView stor_flot, store_rev_date,store_con, store_cnt, store_total;
        LinearLayout item_store_review;
        ImageView store_review_imgv;
        float max, min;


        public ViewHolder(@NonNull View v) {
            super(v);
            store_strating = v.findViewById(R.id.store_strating);

            stor_flot = v.findViewById(R.id.star_tv);
            store_rev_date = v.findViewById(R.id.store_rev_date);
            store_con = v.findViewById(R.id.store_con);
            store_cnt = v.findViewById(R.id.store_cnt);
            store_total = v.findViewById(R.id.store_total);
            item_store_review = v.findViewById(R.id.item_store_review);
            store_review_imgv = v.findViewById(R.id.store_review_imgv);




        }

        public void bind(@NonNull ViewHolder h, int i){
           float total =list.get(i).getStar_rating();

            h.stor_flot.setText(list.get(i).getStar_rating()+"");
            h.store_strating.setRating(total);
            h.store_rev_date.setText(list.get(i).getWritedate());
            h.store_con.setText(list.get(i).getReview_content());

            if(h.store_review_imgv != null){
                h.store_review_imgv.setVisibility(View.VISIBLE);
                Glide.with(context).load(list.get(i).getStore_image()).into(h.store_review_imgv);
            }

  /*          h.item_store_review.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //리뷰 클릭시, 리뷰 상세화면 jk 2022/10/02
                            Log.d("리뷰상세", "onClick: 가지니" + list.get(i).getId());
                           *//* CommonVal.reviewdetail.setId(list.get(i).getId());*//*
                            Intent intent = new Intent(v.getContext(), ReviewDetailActivity.class);
                            intent.putExtra("store_adater", 1);
                            intent.putExtra("id",list.get(i).getId());
                            intent.putExtra("review_content", list.get(i).getReview_content());
                            intent.putExtra("taste", list.get(i).getTaste());
                            intent.putExtra("mood", list.get(i).getMood());
                            intent.putExtra("kind", list.get(i).getKind());
                            intent.putExtra("clean", list.get(i).getClean());
                            intent.putExtra("image", list.get(i).getStore_image());

                            //이미지를 아직 안함 jk
                            context.startActivity(intent);

                }
            });*/
        }

    }
}
