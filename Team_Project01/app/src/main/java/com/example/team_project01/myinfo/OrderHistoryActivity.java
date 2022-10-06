package com.example.team_project01.myinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.team_project01.R;

public class OrderHistoryActivity extends AppCompatActivity {

    FrameLayout recv_order_hitory;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        recv_order_hitory = findViewById(R.id.recv_order_hitory);
        
        //item_order_history FrameLayout 에 붙일예정
    }
}