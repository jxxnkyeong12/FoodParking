package com.example.team_project01.myinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.team_project01.R;
import com.example.team_project01.common.CommonVal;
import com.example.team_project01.conn.CommonAskTask;
import com.example.team_project01.store.AndBookmarkVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class LikeHistoryActivity extends AppCompatActivity {

    RecyclerView like_recv;
    ImageView like_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_history);
        
        //item_like_history recv 붙일 예정

        like_recv = findViewById(R.id.like_recv);
        like_back = findViewById(R.id.like_back);

        like_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        CommonAskTask askTask = new CommonAskTask(LikeHistoryActivity.this, "andBMList");
        AndBookmarkVO vo = new AndBookmarkVO();
        vo.setId(CommonVal.loginInfo.getId());

        askTask.addParams("id", vo.getId());
        askTask.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
            @Override
            public void onResult(String data, boolean isResult) {
                Log.d("TAG", "onResut: " + data);
                //list = new Gson().fromJson(data, new TypeToken<ArrayList<AndSearchVO>>(){}.getType());
                ArrayList<AndBookmarkVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<AndBookmarkVO>>(){}.getType());

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(LikeHistoryActivity.this, RecyclerView.VERTICAL, false);
                LikeAdapter adapter = new LikeAdapter(getLayoutInflater(), list);
                like_recv.setLayoutManager(layoutManager);
                like_recv.setAdapter(adapter);
            }


        });
    }
}