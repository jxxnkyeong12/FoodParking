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

import com.example.team_project01.R;
import com.example.team_project01.common.BasketActivity;
import com.example.team_project01.common.BasketVO;
import com.example.team_project01.common.CommonVal;
import com.example.team_project01.common.MapActivity;

import com.example.team_project01.common.ReviewVO;
import com.example.team_project01.conn.CommonAskTask;
import com.example.team_project01.list.Store_infoDTO;
import com.example.team_project01.login.LoginActivity;
import com.example.team_project01.login.LoginSocialActivity;
import com.google.android.gms.common.internal.service.Common;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

import java.util.ArrayList;
import java.util.Collections;

public class HomeFragment extends Fragment {

    TextView home_tv_name;
    ViewPager2 pager2;
    SpringDotsIndicator indicator;
    LinearLayout home_map;
    RecyclerView home_recv1;
    RecyclerView home_recv2;

    boolean auto = true;

    ArrayList<Integer> img_list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        home_tv_name = v.findViewById(R.id.home_tv_name);
        pager2 = v.findViewById(R.id.pager2);
        indicator = v.findViewById(R.id.indicator);
        home_map = v.findViewById(R.id.home_map);
        home_recv1 = v.findViewById(R.id.home_recv1);
        home_recv2 = v.findViewById(R.id.home_recv2);

        img_list.add(R.drawable.banner1);
        img_list.add(R.drawable.banner2);
        img_list.add(R.drawable.banner3);
        img_list.add(R.drawable.banner4);
        img_list.add(R.drawable.banner5);
        img_list.add(R.drawable.banner5);

        Pager2Adapter adapter = new Pager2Adapter(inflater, img_list);
        pager2.setAdapter(adapter);

        indicator.setViewPager2(pager2);

        if (CommonVal.loginInfo == null) {  //로그인 안 했을 경우
            home_tv_name.setText("로그인 필요");
            home_tv_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                }
            });

        } else if (CommonVal.loginInfo != null) {  //로그인 했을 경우
            if (CommonVal.loginInfo.getNickname().isEmpty()) {
                home_tv_name.setText(CommonVal.loginInfo.getName().toString() + " 님 안녕하세요!");
            } else {
                home_tv_name.setText(CommonVal.loginInfo.getNickname().toString() + " 님 안녕하세요!");
            }
        }

        CommonAskTask askTask_random = new CommonAskTask(getContext(), "andStoreList");
        askTask_random.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
            @Override
            public void onResult(String data, boolean isResult) {
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

        CommonAskTask askTask_review = new CommonAskTask(getContext(), "andReviewAsc");
        askTask_review.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
            @Override
            public void onResult(String data, boolean isResult) {
                ArrayList<Store_infoDTO> list = new Gson().fromJson(data, new TypeToken<ArrayList<Store_infoDTO>>(){}.getType());
                for (int i = 0; i < list.size(); i++) {
                    BasketVO basketDTO = (BasketVO) new Intent().getSerializableExtra("basketDTO");
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
                    HomeAdapter adapter = new HomeAdapter(basketDTO, getContext(), list, inflater);
                    home_recv2.setLayoutManager(layoutManager);
                    home_recv2.setAdapter(adapter);
                }
            }
        });

        autoSlide();

        return v;
    }


    public void autoSlide() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < img_list.size(); i++) {
                    if(auto){
                        final  int value = i;
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pager2.setCurrentItem(value);
                            }
                        });
                        try {
                            Thread.sleep(4000);
                            if(i == img_list.size()-1){
                                i = -1; //0이 되면! for문 위쪽으로가서 증감식을 타고 1부터 동작함.
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else {
                        break;
                    }

                }
            }
        }).start();
    }

    // 이미지 auto메소드 꺼주기=====================================================================
    @Override
    public void onDestroy() {
        super.onDestroy();
        auto = false;
    }

}