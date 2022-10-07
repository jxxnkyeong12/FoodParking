package com.example.team_project01.common;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.team_project01.R;



import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.team_project01.R;
import com.example.team_project01.conn.CommonConn;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class ReviewListActivity extends AppCompatActivity {

    RecyclerView recv_reviewlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_list);
        recv_reviewlist = findViewById(R.id.recv_reviewlist);
        recv_reviewlist();


        
        
    }
    
    public void recv_reviewlist() {
        CommonConn conn = new CommonConn(ReviewListActivity.this, "andReviewList");
        conn.addParams("email", CommonVal.loginInfo.getEmail());
        conn.excuteConn(new CommonConn.ConnCallback() {
            @Override
            public void onResult(boolean isResult, String data) {
                if(isResult){
                    ArrayList<ReviewVO> list = new Gson().fromJson(data,new TypeToken<ArrayList<ReviewVO>>(){}.getType());
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getLayoutInflater().getContext(), RecyclerView.VERTICAL, false);
                    ReviewListAdapter adapter = new ReviewListAdapter(getLayoutInflater(), list, ReviewListActivity.this, ReviewListActivity.this);
                    recv_reviewlist.setLayoutManager(layoutManager);
                    recv_reviewlist.setAdapter(adapter);
                }
            }
        });
    }


}