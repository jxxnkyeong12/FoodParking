package com.example.team_project01.common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.team_project01.R;
import com.example.team_project01.store.StoreMenuDTO;

import java.util.ArrayList;

public class BasketActivity extends AppCompatActivity {
    RecyclerView recv_basket;
    ImageView imag_plus, imag_min, bask_back;
    TextView tv_menu_cnt, tv_store_name, basket_total_cnt, basket_total_price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        recv_basket = findViewById(R.id.recv_basket);
        imag_min = findViewById(R.id.imag_min);
        imag_plus = findViewById(R.id.imag_plus);
        tv_menu_cnt = findViewById(R.id.tv_menu_cnt);
        bask_back = findViewById(R.id.bask_back);
        tv_store_name = findViewById(R.id.tv_store_name);
        basket_total_cnt = findViewById(R.id.basket_total_cnt);
        basket_total_price = findViewById(R.id.basket_total_price);


        Intent intent = getIntent();
        ArrayList<StoreMenuDTO> list = (ArrayList<StoreMenuDTO>) intent.getSerializableExtra("basketlist");
        basket_total_cnt.setText(list.size()+ "");
        basket_total_price.setText(total_price(list) + "");



        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getLayoutInflater().getContext(), RecyclerView.VERTICAL, false);
        BasketAdapter adapter = new BasketAdapter(getLayoutInflater(), list, basket_total_price);

        recv_basket.setLayoutManager(layoutManager);
        recv_basket.setAdapter(adapter);

        //뒤로가기
        bask_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    public int total_price(ArrayList<StoreMenuDTO> list){
        int total_price = 0;

        for (int i = 0; i < list.size(); i++) {
            total_price += list.get(i).getPrice();
        }

        return total_price;
    }
}