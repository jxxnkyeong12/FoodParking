package com.example.team_project01.common;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
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
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.team_project01.R;
import com.example.team_project01.conn.CommonAskTask;
import com.example.team_project01.myinfo.MyinfoFragment;

import java.util.ArrayList;

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.ViewHolder>{

    LayoutInflater inflater;
    ArrayList<ReviewVO> list;
    Context context;
    Activity activity;

    public ReviewListAdapter(LayoutInflater inflater, ArrayList<ReviewVO> list, Context context, Activity activity) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_reviewlist, parent, false);
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
        TextView review_text,review_store, review_modify,review_delete, review_total ;
        ImageView review_imgae;
        RatingBar rev_listrating;
        LinearLayout review_liner;

        public ViewHolder(@NonNull View v) {
            super(v);

            review_imgae = v.findViewById(R.id.review_imgae);
            review_text = v.findViewById(R.id.review_text);
            review_store = v.findViewById(R.id.review_store);
            review_modify = v.findViewById(R.id.review_modify);
            review_delete = v.findViewById(R.id.review_delete);
            rev_listrating = v.findViewById(R.id.rev_listrating);
            review_total = v.findViewById(R.id.review_total);
            review_liner = v.findViewById(R.id.review_liner);





        }

        public void bind(@NonNull ViewHolder h, int i){


                Log.d("리뷰있어", "bind: ");
                h.review_store.setText(list.get(i).getStore_name());
                h.review_text.setText(list.get(i).getReview_content());
                h.review_total.setText(list.get(i).getStar_rating()+"");
                if(list.get(i).getReview_image() != null){
                    review_liner.setVisibility(View.VISIBLE);
                    Glide.with(context).load(list.get(i).getReview_image()).into(review_imgae);
                }

                float rating = list.get(i).getStar_rating();
                h.rev_listrating.setRating(rating);


            review_modify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                     Intent intent = new Intent(context, ReviewDetailActivity.class);

                     intent.putExtra("star_code", list.get(i).getStar_code());

                     context.startActivity(intent);
                }
            });


            review_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogInterface.OnClickListener confirm = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Log.d("TAG", "onClick: 네");
                            CommonAskTask askTask = new CommonAskTask(context, "andReviewDelete");
                            askTask.addParams("email", CommonVal.loginInfo.getEmail());
                            askTask.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
                                @Override
                                public void onResult(String data, boolean isResult) {

                                }

                            });
                        }
                    };


                    DialogInterface.OnClickListener cancle = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Log.d("TAG", "onClick: 아니요");
                        }
                    };

                    new AlertDialog.Builder(activity)
                            .setTitle("정말 삭제하시겠습니까?")
                            .setPositiveButton("네", confirm)
                            .setNegativeButton("아니요", cancle)
                            .show();
                }
            });

        }

    }
}
