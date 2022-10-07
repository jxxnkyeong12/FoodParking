package com.example.team_project01.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.team_project01.MainActivity;
import com.example.team_project01.R;
import com.example.team_project01.common.BasketVO;
import com.example.team_project01.common.CommonVal;
import com.example.team_project01.conn.CommonAskTask;
import com.example.team_project01.list.Store_infoDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class ManagerOrderDetailActivity extends AppCompatActivity {
    RecyclerView recv_manager_detail;
    TextView order_time, order_date, order_total_price;
    ArrayList<BasketVO> basketlist;
    Button order_fail, order_ok;
    ArrayList<Order_infoVO> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_order_detail);

        recv_manager_detail = findViewById(R.id.recv_manager_detail);
        order_time = findViewById(R.id.order_time);
        order_date = findViewById(R.id.order_date);
        order_total_price = findViewById(R.id.order_total_price);
        order_fail = findViewById(R.id.order_fail);
        order_ok = findViewById(R.id.order_ok);

        CommonAskTask askTask = new CommonAskTask(ManagerOrderDetailActivity.this, "andOrder_info_list");
        askTask.addParams("id", CommonVal.loginInfo.getId());
        askTask.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
            @Override
            public void onResult(String data, boolean isResult) {
                list =  new Gson().fromJson(data, new TypeToken<ArrayList<Order_infoVO>>() {
                }.getType());
                String strorder_date = list.get(0).getOrder_date();
                String strorder_time = list.get(0).getOrder_time();
                //년도
                String year = strorder_date.substring(0, 4);
                //월
                String month = strorder_date.substring(4, 6);
                //일
                String day = strorder_date.substring(6, 8);
                //시
                String hour = strorder_time.substring(0,2);
                //일
                String minit = strorder_time.substring(2, 4);

                order_date.setText(year+ "년 " + month + "월 " + day + "일");
                order_time.setText(hour + "시 " + minit + "분");
                order_total_price.setText(list.get(0).getPrice() + "원");
                String total_info = list.get(0).getTotal_info();
                basketlist = new Gson().fromJson(total_info, new TypeToken<ArrayList<BasketVO>>() {
                }.getType());
                Log.d("TAG", "onResult: " + basketlist.size());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getLayoutInflater().getContext(), RecyclerView.VERTICAL, false);
                ManagerOrderDetailAdapter managerOrderDetailAdapter = new ManagerOrderDetailAdapter(getLayoutInflater(), basketlist);
                recv_manager_detail.setLayoutManager(layoutManager);
                recv_manager_detail.setAdapter(managerOrderDetailAdapter);
            }
        });

        //진행완료 버튼 클릭시 order_info 테이블에서 삭제되며
        order_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //삭제메소드
                delete_order_info(list);

                OrderHistoryVO vo = new OrderHistoryVO();
                vo.setMenu_cnt(list.get(0).getMenu_cnt());
                vo.setPrice(list.get(0).getPrice());
                vo.setOrder_date(list.get(0).getOrder_date());
                vo.setOrder_num(list.get(0).getOrder_num());
                vo.setStore_code(list.get(0).getStore_code());
                vo.setId(list.get(0).getId());
                vo.setTotal_info(new Gson().toJson(list));

                CommonAskTask commonAskTask = new CommonAskTask(ManagerOrderDetailActivity.this, "andInsert_order_history");
                commonAskTask.addParams("vo", new Gson().toJson(vo));
                commonAskTask.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        if (isResult){
                            Intent intent = new Intent(ManagerOrderDetailActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                });


            }
        });



        //예약 취소버튼
        order_fail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener confirm = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        delete_order_info(list);
                        Intent intent = new Intent(ManagerOrderDetailActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                };

                DialogInterface.OnClickListener cancle = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                };

                new AlertDialog.Builder(ManagerOrderDetailActivity.this)
                        .setTitle("예약을 취소 하시겠습니까?")
                        .setPositiveButton("네", confirm)
                        .setNegativeButton("아니요", cancle)
                        .show();

            }
        });

    }

    public void delete_order_info(ArrayList<Order_infoVO> list){
        CommonAskTask askTask = new CommonAskTask(ManagerOrderDetailActivity.this, "andDelete_order_info");
        askTask.addParams("id", list.get(0).getId());
        askTask.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
            @Override
            public void onResult(String data, boolean isResult) {

            }
        });

    }
}