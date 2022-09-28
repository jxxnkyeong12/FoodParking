package com.example.team_project01.store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.team_project01.MainActivity;
import com.example.team_project01.R;
import com.example.team_project01.common.CommonVal;
import com.example.team_project01.conn.CommonAskTask;
import com.example.team_project01.login.MemberVO;
import com.example.team_project01.myinfo.LikeAdapter;
import com.example.team_project01.myinfo.LikeHistoryActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;

public class StoreActivity extends AppCompatActivity implements View.OnClickListener {

    TabLayout tab_store;
    RecyclerView recv_store_menu;
    View layout_store_info;
    LinearLayout layout_store_tab_info, layout_store_tab_review;
    Spinner store_spinner;
    TextView store_tv_spinner;
    ImageView store_imgv_back, store_imgv_favEmp, store_imgv_favFill;

    String[] items = {"최신순", "평점 높은 순", "평점 낮은 순"};

    AndBookmarkVO vo = new AndBookmarkVO();

    //이전 페이지(가게리스트)와 데이터 연동

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        tab_store = findViewById(R.id.tab_store);
        recv_store_menu = findViewById(R.id.recv_store_menu);
        layout_store_info = findViewById(R.id.layout_store_info);
        layout_store_tab_info = findViewById(R.id.layout_store_tab_info);
        layout_store_tab_review = findViewById(R.id.layout_store_tab_review);
        store_spinner = findViewById(R.id.store_spinner);
        store_tv_spinner = findViewById(R.id.store_tv_spinner);
        store_imgv_back = findViewById(R.id.store_imgv_back);
        store_imgv_favEmp = findViewById(R.id.store_imgv_favEmp);
        store_imgv_favFill = findViewById(R.id.store_imgv_favFill);

        //onClickListner
        store_imgv_back.setOnClickListener(this);
        store_imgv_favEmp.setOnClickListener(this);
        store_imgv_favFill.setOnClickListener(this);

        //기본화면
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(StoreActivity.this, RecyclerView.VERTICAL, false);
        StoreMenuAdapter adapter = new StoreMenuAdapter(menu_list(), getLayoutInflater());
        recv_store_menu.setLayoutManager(layoutManager);
        recv_store_menu.setAdapter(adapter);

        recv_store_menu.setVisibility(View.VISIBLE);
        layout_store_tab_info.setVisibility(View.GONE);
        layout_store_tab_review.setVisibility(View.GONE);

        if(CommonVal.loginInfo == null) {
            store_imgv_favFill.setVisibility(View.GONE);
            store_imgv_favEmp.setVisibility(View.VISIBLE);

        }else {
            CommonAskTask askTask = new CommonAskTask(StoreActivity.this, "andBMList");
            AndBookmarkVO vo = new AndBookmarkVO();
            vo.setId(CommonVal.loginInfo.getId());

            askTask.addParams("id", vo.getId());
            askTask.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
                @Override
                public void onResult(String data, boolean isResult) {
                    Log.d("TAG", "onResut: " + data);
                    ArrayList<AndBookmarkVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<AndBookmarkVO>>(){}.getType());
                    for (int i = 0; i < list.size(); i++) {
                        vo.setStore_code(list.get(i).getStore_code());
                        vo.setBookmark(list.get(i).getBookmark());

                        if(vo.getBookmark() == 1 && vo.getStore_code() == 1) {
                            store_imgv_favEmp.setVisibility(View.GONE);
                            store_imgv_favFill.setVisibility(View.VISIBLE);
                        }
                    }
                }
            });
        }

        //탭
        tab_store.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position =  tab.getPosition();
                if(position == 0) {
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(StoreActivity.this, RecyclerView.VERTICAL, false);
                    StoreMenuAdapter adapter = new StoreMenuAdapter(menu_list(), getLayoutInflater());
                    recv_store_menu.setLayoutManager(layoutManager);
                    recv_store_menu.setAdapter(adapter);

                    recv_store_menu.setVisibility(View.VISIBLE);
                    layout_store_tab_info.setVisibility(View.GONE);
                    layout_store_tab_review.setVisibility(View.GONE);

                }else if(position == 1) {
                    recv_store_menu.setVisibility(View.GONE);
                    layout_store_tab_info.setVisibility(View.VISIBLE);
                    layout_store_tab_review.setVisibility(View.GONE);

                }else {
                    spinner();

                    recv_store_menu.setVisibility(View.GONE);
                    layout_store_tab_info.setVisibility(View.GONE);
                    layout_store_tab_review.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                int position =  tab.getPosition();
                if(position == 0) {
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(StoreActivity.this, RecyclerView.VERTICAL, false);
                    StoreMenuAdapter adapter = new StoreMenuAdapter(menu_list(), getLayoutInflater());
                    recv_store_menu.setLayoutManager(layoutManager);
                    recv_store_menu.setAdapter(adapter);

                    recv_store_menu.setVisibility(View.VISIBLE);
                    layout_store_tab_info.setVisibility(View.GONE);
                    layout_store_tab_review.setVisibility(View.GONE);

                }else if(position == 1) {
                    recv_store_menu.setVisibility(View.GONE);
                    layout_store_tab_info.setVisibility(View.VISIBLE);
                    layout_store_tab_review.setVisibility(View.GONE);

                }else {
                    spinner();

                    recv_store_menu.setVisibility(View.GONE);
                    layout_store_tab_info.setVisibility(View.GONE);

                    layout_store_tab_review.setVisibility(View.VISIBLE);

                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        //뒤로가기
        if(v.getId() == R.id.store_imgv_back) {
            onBackPressed();

        //찜 눌렸을 때
        }else if(v.getId() == R.id.store_imgv_favEmp) {
            store_imgv_favEmp.setVisibility(View.GONE);
            store_imgv_favFill.setVisibility(View.VISIBLE);

            AndBookmarkVO vo = new AndBookmarkVO();
            vo.setId(CommonVal.loginInfo.getId());
            vo.setStore_code(1);

            CommonAskTask askTask = new CommonAskTask(StoreActivity.this, "andBMInsert");
            askTask.addParams("vo", new Gson().toJson(vo));
            askTask.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
                @Override
                public void onResult(String data, boolean isResult) {

                }
            });

        //찜 해제
        }else if(v.getId() == R.id.store_imgv_favFill) {
            store_imgv_favFill.setVisibility(View.GONE);
            store_imgv_favEmp.setVisibility(View.VISIBLE);

            AndBookmarkVO vo = new AndBookmarkVO();
            vo.setId(CommonVal.loginInfo.getId());
            vo.setStore_code(1);

            CommonAskTask askTask = new CommonAskTask(StoreActivity.this, "andBMDelete");
            askTask.addParams("vo", new Gson().toJson(vo));
            askTask.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
                @Override
                public void onResult(String data, boolean isResult) {

                }
            });

        }
    }

    //스피너
    public void spinner() {
        ArrayAdapter<String> sp_adapter = new ArrayAdapter<String>(
                StoreActivity.this, R.layout.support_simple_spinner_dropdown_item, items
        );

        sp_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        store_spinner.setAdapter(sp_adapter);
        store_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                store_tv_spinner.setText(items[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                store_tv_spinner.setText(items[0]);
            }
        });
    }

    //탭 - 메뉴
    public ArrayList<StoreMenuDTO> menu_list() {
        ArrayList<StoreMenuDTO> list = new ArrayList<>();
        list.add(new StoreMenuDTO(R.drawable.black, "순희비빔밥", "8000원"));
        list.add(new StoreMenuDTO(R.drawable.black, "열무비빔밥", "9000원"));
        list.add(new StoreMenuDTO(R.drawable.black, "순희비빔밥", "8000원"));
        list.add(new StoreMenuDTO(R.drawable.black, "열무비빔밥", "9000원"));
        list.add(new StoreMenuDTO(R.drawable.black, "순희비빔밥", "8000원"));
        list.add(new StoreMenuDTO(R.drawable.black, "열무비빔밥", "9000원"));

        return list;
    }
}