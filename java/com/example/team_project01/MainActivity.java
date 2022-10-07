package com.example.team_project01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.team_project01.common.CommonVal;
import com.example.team_project01.common.MapActivity;
import com.example.team_project01.conn.CommonAskTask;
import com.example.team_project01.home.HomeFragment;
import com.example.team_project01.login.LoginActivity;
import com.example.team_project01.login.MemberVO;
import com.example.team_project01.more.MoreFragment;
import com.example.team_project01.myinfo.MyinfoFragment;
import com.example.team_project01.order.OrderFragment;
import com.example.team_project01.search.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

public class MainActivity extends AppCompatActivity {


    LinearLayout home_map;
    FrameLayout container;
    SpaceNavigationView bottom_nav;
    TextView tv_home_address;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.container);
        bottom_nav = (SpaceNavigationView) findViewById(R.id.bottom_nav);

        home_map = findViewById(R.id.home_map);
        tv_home_address = findViewById(R.id.tv_home_address);


        //하단 네비게이션 버튼 아이디 찾아주기
        bottom_nav.initWithSaveInstanceState(savedInstanceState);
        bottom_nav.addSpaceItem(new SpaceItem("홈", R.drawable.ic_nav_home));
        bottom_nav.addSpaceItem(new SpaceItem("검색", R.drawable.ic_nav_search));
        bottom_nav.addSpaceItem(new SpaceItem("내 정보", R.drawable.ic_nav_myinfo));
        bottom_nav.addSpaceItem(new SpaceItem("더보기", R.drawable.ic_nav_more));
        bottom_nav.showIconOnly();

        bottom_nav.requestLayout();

        //어플 메인화면
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment(MainActivity.this, MainActivity.this)).commit();

        //상단 지도 눌렀을 때
        home_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });



        //하단네비바 클릭시
        bottom_nav.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new OrderFragment()).commit();
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                if(itemIndex == 0) {
                    home_map.setVisibility(View.VISIBLE);
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment(MainActivity.this, MainActivity.this)).commit();
                }else if(itemIndex == 1) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new SearchFragment()).commit();
                }else if(itemIndex == 2) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new MyinfoFragment()).commit();
                }else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new MoreFragment()).commit();
                }
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                if(itemIndex == 0) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment(MainActivity.this, MainActivity.this)).commit();
                }else if(itemIndex == 1) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new SearchFragment()).commit();
                }else if(itemIndex == 2) {

                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new MyinfoFragment()).commit();
                }else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new MoreFragment()).commit();
                }
            }
        });



        //내정보 modifyActivity에서 변경된 정보를 다시 로그인 정보에 담기 - jk 2022/10/01
        intent = getIntent();
        int i = intent.getIntExtra("modify", -1);
        if(i == 1){
            CommonAskTask askTask = new CommonAskTask(MainActivity.this, "andResume");
            askTask.addParams("email",CommonVal.loginInfo.getEmail());
            askTask.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
                @Override
                public void onResult(String data, boolean isResult) {
                    CommonVal.loginInfo = new Gson().fromJson(data, MemberVO.class);
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new MyinfoFragment()).commit();
                    }

            });

        }




    }




    @Override
    protected  void onSaveInstanceState( Bundle outState) {
        super . onSaveInstanceState(outState);
        bottom_nav . onSaveInstanceState(outState);
    }

}