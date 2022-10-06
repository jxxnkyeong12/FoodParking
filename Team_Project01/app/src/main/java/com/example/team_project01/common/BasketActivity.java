package com.example.team_project01.common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.team_project01.R;
import com.example.team_project01.conn.CommonAskTask;
import com.example.team_project01.order.Order_infoVO;
import com.example.team_project01.order.ReserveActivity;
import com.example.team_project01.store.StoreMenuDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class BasketActivity extends AppCompatActivity {
    RecyclerView recv_basket;
    ImageView imag_plus, imag_min, bask_back;
    TextView tv_menu_cnt, tv_store_name, basket_total_cnt, basket_total_price;
    BasketAdapter adapter;
    Button reservation;
    Order_infoVO vo;
    ArrayList<BasketVO> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        recv_basket = findViewById(R.id.recv_basket);
        imag_min = findViewById(R.id.imag_min);
        imag_plus = findViewById(R.id.imag_plus);
        tv_menu_cnt = findViewById(R.id.tv_menu_cnt);
        reservation = findViewById(R.id.reservation);

        bask_back = findViewById(R.id.bask_back);
        tv_store_name = findViewById(R.id.tv_store_name);
        basket_total_cnt = findViewById(R.id.basket_total_cnt);
        basket_total_price = findViewById(R.id.basket_total_price);

        Intent intent = getIntent();
        String store_name = intent.getStringExtra("store_name");

        tv_store_name.setText(store_name);



        CommonAskTask commonAskTask = new CommonAskTask(BasketActivity.this, "andBasketList");
        commonAskTask.addParams("id", CommonVal.loginInfo.getId());
        commonAskTask.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
            @Override
            public void onResult(String data, boolean isResult) {
                list = new Gson().fromJson(data,
                        new TypeToken<ArrayList<BasketVO>>(){}.getType());

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getLayoutInflater().getContext(), RecyclerView.VERTICAL, false);
                adapter = new BasketAdapter(getLayoutInflater(),list, basket_total_price, basket_total_cnt, BasketActivity.this);


                recv_basket.setLayoutManager(layoutManager);
                recv_basket.setAdapter(adapter);
            }
        });







        //뒤로가기
        bask_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        //자리예약을 클릭 했을 시
        reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<BasketVO> list = adapter.getlist();
                String total_price = adapter.getprice();
                Intent intent1 = new Intent(BasketActivity.this, ReserveActivity.class);
                intent1.putExtra("list", list);
                intent1.putExtra("total_price", total_price);
                intent1.putExtra("store_name", store_name);
                startActivity(intent1);
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