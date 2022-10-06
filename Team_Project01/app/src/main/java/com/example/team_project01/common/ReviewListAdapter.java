package com.example.team_project01.common;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.team_project01.R;
import com.example.team_project01.conn.CommonConn;
import com.example.team_project01.myinfo.ModifyActivity;

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
        TextView review_text,review_store,  review_modify,review_delete ;
        ImageView review_imgae;

        public ViewHolder(@NonNull View v) {
            super(v);

            review_imgae = v.findViewById(R.id.review_imgae);
            review_text = v.findViewById(R.id.review_text);
            review_store = v.findViewById(R.id.review_store);
            review_modify = v.findViewById(R.id.review_modify);
            review_delete = v.findViewById(R.id.review_delete);


        }

        public void bind(@NonNull ViewHolder h, int i){
            h.review_store.setText(list.get(i).getStore_name());
            h.review_text.setText(list.get(i).getContent());


            review_modify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });


            review_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogInterface.OnClickListener confirm = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Log.d("TAG", "onClick: 네");
                            CommonConn conn = new CommonConn(context, "andReviewDelete");
                            conn.addParams("email", CommonVal.loginInfo.getEmail());
                            conn.excuteConn(new CommonConn.ConnCallback() {
                                @Override
                                public void onResult(boolean isResult, String data) {
                                    Log.d("삭제", "onResult: 삭제완 " );
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
