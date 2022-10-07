package com.example.team_project01.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.team_project01.R;
import com.example.team_project01.common.BasketVO;
import com.example.team_project01.common.CommonVal;
import com.example.team_project01.conn.CommonAskTask;

import java.util.ArrayList;

public class OrderDetailActivity extends AppCompatActivity {
    TextView order_detail_store_name, order_detail_date, order_detail_order_time
            , total_price, order_peple, phone_number;
    RecyclerView recv_order_detail;

    Order_infoVO vo;
    ArrayList<BasketVO> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        order_detail_store_name = findViewById(R.id.order_detail_store_name);
        order_detail_order_time = findViewById(R.id.order_detail_order_time);
        order_detail_date = findViewById(R.id.order_detail_date);
        recv_order_detail = findViewById(R.id.recv_order_detail);
        total_price = findViewById(R.id.total_price);
        order_peple = findViewById(R.id.order_peple);
        phone_number = findViewById(R.id.phone_number);

        Intent intent = getIntent();
        vo = (Order_infoVO) intent.getSerializableExtra("vo");
        String store_name = intent.getStringExtra("store_name");
        list = (ArrayList<BasketVO>) intent.getSerializableExtra("list");

        phone_number.setText(vo.getPhone());
        order_peple.setText(vo.getOrder_peple() + "");
        total_price.setText(vo.getPrice() + "");
        order_detail_store_name.setText(store_name);
        order_detail_date.setText("예약일:" + vo.getOrder_date() );
        order_detail_order_time.setText("예약시간 : " + vo.getOrder_time());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getLayoutInflater().getContext(), RecyclerView.VERTICAL, false);
        OrderDetailAdapter adapter = new OrderDetailAdapter(getLayoutInflater(), vo, list);

        recv_order_detail.setLayoutManager(layoutManager);
        recv_order_detail.setAdapter(adapter);


        CommonAskTask askTask = new CommonAskTask(OrderDetailActivity.this, "andBasketDeleteAll");
        askTask.addParams("id", CommonVal.loginInfo.getId());
        askTask.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
            @Override
            public void onResult(String data, boolean isResult) {

            }
        });
    }
    public ArrayList<BasketVO> getbasketList(){
        return list;
    }
    public Order_infoVO getbasketVO(){
        return vo;
    }
}