package com.example.team_project01.common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.team_project01.R;

public class BasketActivity extends AppCompatActivity {
    RecyclerView recv_basket;
    ImageView imag_plus, imag_min, bask_back;
    TextView tv_menu_cnt;
    int menu_cnt = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        recv_basket = findViewById(R.id.recv_basket);
        imag_min = findViewById(R.id.imag_min);
        imag_plus = findViewById(R.id.imag_plus);
        tv_menu_cnt = findViewById(R.id.tv_menu_cnt);
        bask_back = findViewById(R.id.bask_back);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getLayoutInflater().getContext(), RecyclerView.VERTICAL, false);
        BasketAdapter adapter = new BasketAdapter(getLayoutInflater());

        recv_basket.setLayoutManager(layoutManager);
        recv_basket.setAdapter(adapter);

        bask_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}