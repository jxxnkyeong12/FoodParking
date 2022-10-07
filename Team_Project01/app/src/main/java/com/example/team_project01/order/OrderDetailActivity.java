package com.example.team_project01.order;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.team_project01.R;

import android.content.Intent;
import android.os.Bundle;

import com.example.team_project01.R;
import com.example.team_project01.conn.CommonConn;
import com.google.gson.Gson;


public class OrderDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        Intent intent = getIntent();
        Order_infoVO vo = (Order_infoVO) intent.getSerializableExtra("vo");

        
        CommonConn conn = new CommonConn(OrderDetailActivity.this, "order_detail");
        conn.addParams("vo", new Gson().toJson(vo));
        conn.excuteConn(new CommonConn.ConnCallback() {
            @Override
            public void onResult(boolean isResult, String data) {

            }
        });


    }
}