package com.example.team_project01.order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.team_project01.R;
import com.example.team_project01.common.BasketVO;
import com.example.team_project01.common.CommonVal;
import com.example.team_project01.conn.CommonAskTask;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReserveActivity extends AppCompatActivity implements View.OnClickListener {

    TextView res_timetxt,res_datetxt, order_human ;
    //DatePicker calendarView;
    CalendarView calendarView;
    ImageView back, res_up, res_up2, res_up3, res_down, res_down2, res_down3;
    Button order1000, order1030, order1100, order1130, order1200, order1230, order0100, order0130, order0200, order0230
            , order0300, order0330, order0400, order0430, order0500, order0530, pe2, pe3, pe4, pe5, btn_modify;

    // FrameLayout res_container;
    LinearLayout res_time;


    boolean arrow = true;
    int arr = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);
        back = findViewById(R.id.back);

        res_datetxt = findViewById(R.id.res_datetxt);
        res_timetxt = findViewById(R.id.res_timetxt);
        order_human = findViewById(R.id.order_human);
        btn_modify = findViewById(R.id.btn_modify);
        order1000 = findViewById(R.id.order1000);
        order1030 = findViewById(R.id.order1030);
        order1100 = findViewById(R.id.order1100);
        order1130 = findViewById(R.id.order1130);
        order1200 = findViewById(R.id.order1200);
        order1230 = findViewById(R.id.order1230);
        order0100 = findViewById(R.id.order0100);
        order0130 = findViewById(R.id.order0130);
        order0200 = findViewById(R.id.order0200);
        order0230 = findViewById(R.id.order0230);
        order0300 = findViewById(R.id.order0300);
        order0330 = findViewById(R.id.order0330);
        order0400 = findViewById(R.id.order0400);
        order0430 = findViewById(R.id.order0430);
        order0500 = findViewById(R.id.order0500);
        order0530 = findViewById(R.id.order0530);
        pe2 = findViewById(R.id.pe2);
        pe3 = findViewById(R.id.pe3);
        pe4 = findViewById(R.id.pe4);
        pe5 = findViewById(R.id.pe5);

        pe2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getpeple(pe2);
            }
        });
        pe3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getpeple(pe3);
            }
        });
        pe4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getpeple(pe4);
            }
        });
        pe5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getpeple(pe5);
            }
        });

        order1000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHour(order1000);
            }
        });

        order1030.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHour(order1030);
            }
        });
        order1100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHour(order1100);
            }
        });
        order1130.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHour(order1130);
            }
        });
        order1200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHour(order1200);
            }
        });
        order1230.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHour(order1230);
            }
        });
        order0100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHour(order0100);
            }
        });
        order0130.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHour(order0130);
            }
        });
        order0200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHour(order0200);
            }
        });
        order0230.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHour(order0230);
            }
        });
        order0300.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHour(order0300);
            }
        });
        order0330.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHour(order0330);
            }
        });
        order0400.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHour(order0400);
            }
        });
        order0430.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHour(order0430);
            }
        });
        order0500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHour(order0500);
            }
        });
        order0530.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHour(order0530);
            }
        });


        calendarView = findViewById(R.id.calendarView);
        res_time = findViewById(R.id.res_time);

        res_up = findViewById(R.id.res_up);
        res_up2 = findViewById(R.id.res_up2);
        res_up3 = findViewById(R.id.res_up3);

        res_down = findViewById(R.id.res_down);
        res_down2 = findViewById(R.id.res_down2);
        res_down3 = findViewById(R.id.res_down3);

        // res_container = findViewById(R.id.res_container);




        back.setOnClickListener(this);

        res_up.setOnClickListener(this);
        res_up2.setOnClickListener(this);
        res_up3.setOnClickListener(this);

        res_down.setOnClickListener(this);
        res_down2.setOnClickListener(this);
        res_down3.setOnClickListener(this);





        // getSupportFragmentManager().beginTransaction().replace(R.id.res_container, new ReserveFragment()).commit();




        //날짜변환
        DateFormat formatter = new SimpleDateFormat("M월dd일");
        Date date = new Date(calendarView.getDate());
        res_datetxt.setText(formatter.format(date));
        calendarView.setMinDate(System.currentTimeMillis() -1000 ); //오늘기준 이전날짜 선택불가능하게

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                if (month + 1 < 10) {
                    res_datetxt.setText(year + "년" + "0" + (month + 1) + "월" + dayOfMonth + "일");
                    if (dayOfMonth < 10){
                        res_datetxt.setText(year + "년" + "0" + (month + 1) + "월" + "0" + dayOfMonth + "일");
                    }
                }else{
                    res_datetxt.setText(year + "년" + (month + 1) + "월" + dayOfMonth + "일");
                    if (dayOfMonth < 10){
                        res_datetxt.setText(year + "년" + (month + 1) + "월" +  "0" + dayOfMonth + "일");
                    }
                }
            }
        });

        //예약하기 클릭
        btn_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonVal.loginInfo == null) {
                    Toast.makeText(ReserveActivity.this, "로그인 후 이용해주세요", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = getIntent();
                    //장바구니 리스트
                    ArrayList<BasketVO> list = (ArrayList<BasketVO>) intent.getSerializableExtra("list");
                    //전체 가격
                    String total_price = intent.getStringExtra("total_price");
                    //가게 이름 가져온거
                    String store_name = intent.getStringExtra("store_name");
                    //주문날짜
                    String year = res_datetxt.getText().toString().substring(0, 4);
                    String month = res_datetxt.getText().toString().substring(5, 7);
                    String day = res_datetxt.getText().toString().substring(8, 10);
                    Log.d("TAG", "onClick: " + year + month + day);

                    //주문시간
                    String hour = res_timetxt.getText().toString().substring(0, 2);
                    String minit = res_timetxt.getText().toString().substring(3, 5);
                    Log.d("TAG", "onClick: " + hour + minit);

                    //예약 인원
                    String peple = order_human.getText().toString().substring(0, 1);
                    Log.d("TAG", "onClick: " + peple);

                    Order_infoVO vo = new Order_infoVO();
                    vo.setId(CommonVal.loginInfo.getId());
                    vo.setOrder_date(year + month + day + "");
                    vo.setPhone(CommonVal.loginInfo.getPhone());
                    vo.setMenu_cnt(list.size());
                    vo.setOrder_state(1);
                    vo.setOrder_time(hour + minit + "");
                    vo.setOrder_peple(peple);
                    vo.setPrice(Integer.parseInt(total_price));
                    vo.setStore_code(list.get(0).getStore_code());
                    vo.setTotal_info(new Gson().toJson(list));


                    Intent intent1 = new Intent(ReserveActivity.this, BillActivity.class);
                    intent1.putExtra("vo", vo);
                    intent1.putExtra("store_name", store_name);
                    intent1.putExtra("list", list);

                    //디비에 주문정보 넣음
                    CommonAskTask askTask = new CommonAskTask(ReserveActivity.this, "reserve_store");
                    askTask.addParams("vo", new Gson().toJson(vo));
                    askTask.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
                        @Override
                        public void onResult(String data, boolean isResult) {
                            if (isResult) {
                                Log.d("TAG", "onResult: 성공");

                            }
                        }
                    });

                    startActivity(intent1);
                }


            }
        });


    }


    @Override
    public void onClick(View v) {

        //날짜선택 옆 화살표들 누르면 접혔다가 펴졌다가 하는...!
        if(v.getId() == R.id.res_up){
            if(arrow || arr ==1){
                calendarView.setVisibility(View.GONE);
                res_up.setVisibility(View.GONE);
                res_down.setVisibility(View.VISIBLE);
                arrow = false;
                arr = 0;
            }
        }else if (v.getId() == R.id.res_down){

            calendarView.setVisibility(View.VISIBLE);
            res_down.setVisibility(View.GONE);
            res_up.setVisibility(View.VISIBLE);
            arrow = true;
            arr = 1;

            //시간 선택 펼쳤다 접었다
        }else if (v.getId() == R.id.res_up2) {
            if(arr == 0){
                res_time.setVisibility(View.GONE);
                res_up2.setVisibility(View.GONE);
                res_down2.setVisibility(View.VISIBLE);
                arrow = false;
                arr = 1;

            }
        }else if (v.getId() == R.id.res_down2){

            res_time.setVisibility(View.VISIBLE);
            res_up2.setVisibility(View.VISIBLE);
            res_down2.setVisibility(View.GONE);
            arrow = true;
            arr = 0;

            //뒤로가기
        }else if (v.getId() == R.id.back){
            onBackPressed();
        }
    }

    public void getHour(Button order_time){
        String time = order_time.getText().toString();
        res_timetxt.setText(time.toString());
    }

    public void getpeple(Button order_peple){
        String peple = order_peple.getText().toString();
        order_human.setText(peple.toString());
    }
}