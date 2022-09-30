package com.example.team_project01.home;

import android.content.Intent;
import android.location.Address;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.team_project01.MainActivity;
import com.example.team_project01.R;
import com.example.team_project01.common.BasketActivity;
import com.example.team_project01.common.BasketVO;
import com.example.team_project01.common.CommonVal;
import com.example.team_project01.common.MapActivity;

import com.example.team_project01.conn.CommonConn;
import com.example.team_project01.list.Store_infoDTO;
import com.example.team_project01.login.LoginSocialActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class HomeFragment extends Fragment implements View.OnClickListener {

    ImageView imgv_test;
    TextView tv_home_address, home_tv_name;
    ViewPager2 pager2;
    SpringDotsIndicator indicator;
    RecyclerView home_recv1;

    MainActivity mainActivity = new MainActivity();
    ArrayList<Integer> img_list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        imgv_test = v.findViewById(R.id.imgv_test);
        tv_home_address = v.findViewById(R.id.tv_home_address);
        home_tv_name = v.findViewById(R.id.home_tv_name);
        pager2 = v.findViewById(R.id.pager2);
        indicator = v.findViewById(R.id.indicator);
        home_recv1 = v.findViewById(R.id.home_recv1);

        img_list.add(R.drawable.banner1);
        img_list.add(R.drawable.banner2);
        img_list.add(R.drawable.banner3);
        img_list.add(R.drawable.banner4);
        img_list.add(R.drawable.banner5);

        Pager2Adapter pager2Adapter = new Pager2Adapter(inflater, img_list);
        pager2.setAdapter(pager2Adapter);

        indicator.setViewPager2(pager2);

        if (CommonVal.loginInfo == null) {  //로그인 안 했을 경우
            home_tv_name.setText("로그인 필요");

        } else if (CommonVal.loginInfo != null) {  //로그인 했을 경우
            if (CommonVal.loginInfo.getNickname().isEmpty()) {
                home_tv_name.setText(CommonVal.loginInfo.getName().toString() + " 님 안녕하세요!");
            } else {
                home_tv_name.setText(CommonVal.loginInfo.getNickname().toString() + " 님 안녕하세요!");
            }
        }

        autoSlide();

        CommonConn conn = new CommonConn(getContext(), "andStoreList");
        conn.excuteConn(new CommonConn.ConnCallback() {
            @Override
            public void onResult(boolean isResult, String data) {
                ArrayList<Store_infoDTO> list = new Gson().fromJson(data, new TypeToken<ArrayList<Store_infoDTO>>(){}.getType());
                for (int i = 0; i < list.size(); i++) {
                    Collections.shuffle(list);
                    BasketVO basketDTO = (BasketVO) new Intent().getSerializableExtra("basketDTO");
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
                    HomeAdapter adapter = new HomeAdapter(basketDTO, getContext(), list, inflater);
                    home_recv1.setLayoutManager(layoutManager);
                    home_recv1.setAdapter(adapter);
                }
            }
        });

        //onClickListner
        tv_home_address.setOnClickListener(this);
        home_tv_name.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tv_home_address) {
            Intent intent = new Intent(getContext(), MapActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.home_tv_name) {
            Intent intent = new Intent(getContext(), LoginSocialActivity.class);
            startActivity(intent);
        }
    }

    //가게 홍보 배너 자동 넘기기
    public void autoSlide() {
        //new Handler : SplashActivity 페이지 전환할 때 사용
        //runOnUiThread : 페이지 내부에서 디자인이 바뀌는 용으로, 쓰레드 사용 시 활용, Activity에서만 접근 가능
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    for (int i = 0; i < img_list.size(); i++) {
                        final int value = i;
                        mainActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pager2.setCurrentItem(value);
                            }
                        });

                        try {
                            Thread.sleep(1000);
                            //if(i == img_list.size() - 1) i = -1; //이 방법도 무한반복
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
}