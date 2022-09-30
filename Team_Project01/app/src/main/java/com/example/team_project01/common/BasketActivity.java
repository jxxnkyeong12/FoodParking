package com.example.team_project01.common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.team_project01.R;
import com.example.team_project01.order.Order_infoVO;
import com.example.team_project01.order.ReserveActivity;
import com.example.team_project01.store.StoreMenuDTO;

import java.util.ArrayList;

public class BasketActivity extends AppCompatActivity {
    RecyclerView recv_basket;
    ImageView  bask_back;
    TextView tv_store_name, basket_total_cnt, basket_total_price;
    BasketAdapter adapter;
    Button reservation;
    Order_infoVO vo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        recv_basket = findViewById(R.id.recv_basket);
        bask_back = findViewById(R.id.bask_back);
        tv_store_name = findViewById(R.id.tv_store_name);
        basket_total_cnt = findViewById(R.id.basket_total_cnt);
        basket_total_price = findViewById(R.id.basket_total_price);
        reservation = findViewById(R.id.reservation);


        Intent intent = getIntent();
        ArrayList<StoreMenuDTO> list = (ArrayList<StoreMenuDTO>) intent.getSerializableExtra("basketlist");
        vo = (Order_infoVO) intent.getSerializableExtra("order_info");
        basket_total_cnt.setText(list.size()+ "");
        basket_total_price.setText(total_price(list) + "");



        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getLayoutInflater().getContext(), RecyclerView.VERTICAL, false);
        adapter = new BasketAdapter(getLayoutInflater(), list, basket_total_price, basket_total_cnt);

        recv_basket.setLayoutManager(layoutManager);
        recv_basket.setAdapter(adapter);

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
                ArrayList<StoreMenuDTO> list1 = adapter.getlist();
                vo.setCategory_code(2);
                vo.setPrice(Integer.parseInt((String) basket_total_price.getText()));
                Log.d("TAG", "onClick: " + list1.size());
                Intent intent1 = new Intent(BasketActivity.this, ReserveActivity.class);
                intent1.putExtra("basketlist", list1);
                intent1.putExtra("order_info", vo);
                startActivity(intent1);
                //클릭시 예약인지 포장인지 구별 1은 포장, 2는 예약

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