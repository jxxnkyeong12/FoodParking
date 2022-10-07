package com.example.team_project01.common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.team_project01.R;
import com.example.team_project01.conn.CommonAskTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class ReviewListActivity extends AppCompatActivity {

    RecyclerView recv_reviewlist;
    ArrayList<ReviewVO> list;
    LinearLayout review_noreview;
    ImageView review_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_list);
        recv_reviewlist = findViewById(R.id.recv_reviewlist);
        review_noreview = findViewById(R.id.review_noreview);
        review_back = findViewById(R.id.review_back);
        reviewlist();





        
        int i = 0;
        Intent intent = getIntent();
        i = intent.getIntExtra("update", -1);
        //ReviewDetail에서 수정하게 되면 넘어오는 메소드-> update되어서 list 출력 jk
        if (i == 1) {
            reviewlist();
        }

        review_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }


    // 내가쓴 리뷰 리스트 jk
    public void reviewlist() {
        CommonAskTask askTask = new CommonAskTask(ReviewListActivity.this, "andReviewList");
        askTask.addParams("id", CommonVal.loginInfo.getId());
        askTask.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
            @Override
            public void onResult(String data, boolean isResult) {
                if (isResult) {
                    list = new Gson().fromJson(data, new TypeToken<ArrayList<ReviewVO>>() {
                    }.getType());
                    if(list.get(0).getStar_code() == 0){
                      review_noreview.setVisibility(View.VISIBLE);
                      recv_reviewlist.setVisibility(View.GONE);
                    }else {
                        Log.d("review", "onResult: " + list.size());
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getLayoutInflater().getContext(), RecyclerView.VERTICAL, false);
                        ReviewListAdapter adapter = new ReviewListAdapter(getLayoutInflater(), list, ReviewListActivity.this, ReviewListActivity.this);
                        recv_reviewlist.setLayoutManager(layoutManager);
                        recv_reviewlist.setAdapter(adapter);
                        recv_reviewlist.setVisibility(View.VISIBLE);
                        review_noreview.setVisibility(View.GONE);
                    }



                }


            }

        });
    }

   /* public void updateReviewList() {
        CommonAskTask askTask = new CommonAskTask(ReviewListActivity.this, "andReviewNew");
        askTask.addParams("email", CommonVal.loginInfo.getEmail());
        askTask.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
            @Override
            public void onResult(String data, boolean isResult) {
                if (isResult) {
                    list = new Gson().fromJson(data, new TypeToken<ArrayList<ReviewVO>>() {
                    }.getType());
                    Log.d("review", "onResult: " + list.size());
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getLayoutInflater().getContext(), RecyclerView.VERTICAL, false);
                    ReviewListAdapter adapter = new ReviewListAdapter(getLayoutInflater(), list, ReviewListActivity.this, ReviewListActivity.this);
                    recv_reviewlist.setLayoutManager(layoutManager);
                    recv_reviewlist.setAdapter(adapter);


                }


            }
        });


    }*/
}