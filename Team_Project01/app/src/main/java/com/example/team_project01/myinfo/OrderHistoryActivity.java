package com.example.team_project01.myinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.example.team_project01.R;
import com.example.team_project01.common.CommonVal;
import com.example.team_project01.conn.CommonAskTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class OrderHistoryActivity extends AppCompatActivity {

    RecyclerView recv_order_hitory;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        recv_order_hitory = findViewById(R.id.recv_order_hitory);
        recv_list();
        //item_order_history FrameLayout 에 붙일예정

    }


    public void recv_list() {
        CommonAskTask askTask = new CommonAskTask(OrderHistoryActivity.this, "andOrderHistory");
        askTask.addParams("id", CommonVal.loginInfo.getId());
        askTask.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
            @Override
            public void onResult(String data, boolean isResult) {
                ArrayList<OrderHistoryVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<OrderHistoryVO>>(){}.getType());
                Log.d("사이즈", "onResult: " + list.size());

                RecyclerView.LayoutManager lyManager= new LinearLayoutManager(getLayoutInflater().getContext(), RecyclerView.VERTICAL, false);
                OrderHistoryAdapter adapter = new OrderHistoryAdapter(getLayoutInflater(), list, OrderHistoryActivity.this);
                recv_order_hitory.setLayoutManager(lyManager);
                recv_order_hitory.setAdapter(adapter);
            }
        });
    }

}