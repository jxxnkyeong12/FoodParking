package com.example.team_project01.store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.team_project01.R;
import com.example.team_project01.common.CommonVal;
import com.example.team_project01.common.BasketActivity;
import com.example.team_project01.common.BasketVO;
import com.example.team_project01.common.MapActivity;
import com.example.team_project01.common.ReviewDetailActivity;
import com.example.team_project01.conn.CommonAskTask;
import com.example.team_project01.list.Store_infoDTO;
import com.example.team_project01.order.Order_infoVO;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class StoreActivity extends AppCompatActivity implements View.OnClickListener {

    TabLayout tab_store;
    RecyclerView recv_store_menu, recv_store_review;
    View layout_store_info;
    LinearLayout layout_store_tab_info, layout_store_tab_review;
    Spinner store_spinner;
    TextView store_tv_spinner, store_name1,store_name2,tv_store_location
            , store_score1, store_score2, store_score3,store_score4, store_total, store_cnt
            , store_total_tv1;
    ImageView store_imgv_back, store_imgv_favEmp, store_imgv_favFill, store_basket;
    RatingBar store_rattotal;
    ProgressBar progressbar1, progressbar2, progressbar3, progressbar4;

    String[] items = {"최신순", "평점 높은 순", "평점 낮은 순"};
    Order_infoVO vo;

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
        recv_store_review = findViewById(R.id.recv_store_review);
        tv_store_location = findViewById(R.id.tv_store_location);
        store_name1 = findViewById(R.id.store_name1);
        store_name2 = findViewById(R.id.store_name2);
        store_basket = findViewById(R.id.store_basket);

        //jk 2022/10/02
        store_rattotal = findViewById(R.id.store_rattotal);
        store_total = findViewById(R.id.store_total);
        store_cnt = findViewById(R.id.store_cnt);
        progressbar1 = findViewById(R.id.progressbar1);
        progressbar2 = findViewById(R.id.progressbar2);
        progressbar3 = findViewById(R.id.progressbar3);
        progressbar4 = findViewById(R.id.progressbar4);
        store_score1 = findViewById(R.id.store_score1);
        store_score2 = findViewById(R.id.store_score2);
        store_score3 = findViewById(R.id.store_score3);
        store_score4 = findViewById(R.id.store_score4);
        store_total_tv1 = findViewById(R.id.store_total_tv1);

        //onClickListner
        store_imgv_back.setOnClickListener(this);
        store_imgv_favEmp.setOnClickListener(this);
        store_imgv_favFill.setOnClickListener(this);
        recv_store_review.setOnClickListener(this);
        tv_store_location.setOnClickListener(this);

        Intent intent = getIntent();
        BasketVO basketDTO = (BasketVO) intent.getSerializableExtra("basketDTO");
        ArrayList<StoreMenuDTO> list = (ArrayList<StoreMenuDTO>) intent.getSerializableExtra("list1");

        //가게정보
        Store_infoDTO dto = (Store_infoDTO) intent.getSerializableExtra("vo");

        store_name1.setText(dto.getStore_name());
        store_name2.setText(dto.getStore_name());

        //기본화면
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(StoreActivity.this, RecyclerView.VERTICAL, false);
        StoreMenuAdapter adapter = new StoreMenuAdapter(list, getLayoutInflater(), StoreActivity.this, StoreActivity.this, vo);

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
                    Log.d("찜 리스트", "onResut: " + data);
                    ArrayList<AndBookmarkVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<AndBookmarkVO>>(){}.getType());
                    for (int i = 0; i < list.size(); i++) {
                        vo.setStore_code(list.get(i).getStore_code());
                        vo.setBookmark(list.get(i).getBookmark());
                        vo.setStore_name(list.get(i).getStore_name());

                        if(vo.getBookmark() == 1 && dto.getStore_code() == vo.getStore_code()) {
                            store_imgv_favEmp.setVisibility(View.GONE);
                            store_imgv_favFill.setVisibility(View.VISIBLE);
                        }
                    }
                }
            });
        }

        store_basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(StoreActivity.this, BasketActivity.class);
                intent1.putExtra("store_name", dto.getStore_name());
                startActivity(intent1);
            }
        });




        //탭
        tab_store.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position =  tab.getPosition();
                if(position == 0) {
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(StoreActivity.this, RecyclerView.VERTICAL, false);
                    StoreMenuAdapter adapter = new StoreMenuAdapter(list, getLayoutInflater(), StoreActivity.this, StoreActivity.this, vo);
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
                    spinner(dto.getStore_code());

                    recv_store_menu.setVisibility(View.GONE);
                    layout_store_tab_info.setVisibility(View.GONE);
                    layout_store_tab_review.setVisibility(View.VISIBLE);
                    recv_store_review.setVisibility(View.VISIBLE);


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
                    StoreMenuAdapter adapter = new StoreMenuAdapter(list, getLayoutInflater(), StoreActivity.this, StoreActivity.this, vo);
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
                    spinner(dto.getStore_code());

                    recv_store_menu.setVisibility(View.GONE);
                    layout_store_tab_info.setVisibility(View.GONE);

                    layout_store_tab_review.setVisibility(View.VISIBLE);





                }
            }
        });



    }

    //뒤로가기 버튼 활성화
    @Override
    public void onClick(View v) {
        Intent intent = getIntent();
        Store_infoDTO dto = (Store_infoDTO) intent.getSerializableExtra("vo");


        //뒤로가기
        if(v.getId() == R.id.store_imgv_back) {
            onBackPressed();

            //찜 눌렸을 때
        }else if(v.getId() == R.id.store_imgv_favEmp) {
            store_imgv_favEmp.setVisibility(View.GONE);
            store_imgv_favFill.setVisibility(View.VISIBLE);

            AndBookmarkVO vo = new AndBookmarkVO();
            vo.setId(CommonVal.loginInfo.getId());
            vo.setStore_code(dto.getStore_code());
            vo.setStore_name(dto.getStore_name());

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
            vo.setStore_code(dto.getStore_code());
            vo.setStore_name(dto.getStore_name());

            CommonAskTask askTask = new CommonAskTask(StoreActivity.this, "andBMDelete");
            askTask.addParams("vo", new Gson().toJson(vo));
            askTask.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
                @Override
                public void onResult(String data, boolean isResult) {

                }

            });

            //지도보기 눌렀을 때
        }else if(v.getId() == R.id.tv_store_location) {
            Intent mapintent = new Intent(StoreActivity.this, MapActivity.class);
            mapintent.putExtra("store_code", dto.getStore_code());
            startActivity(mapintent);
        }
    }


    //스피너
    public void spinner(int store_code) {
        ArrayAdapter<String> sp_adapter = new ArrayAdapter<String>(
                StoreActivity.this, R.layout.support_simple_spinner_dropdown_item, items
        );

        sp_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        store_spinner.setAdapter(sp_adapter);
        store_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                store_tv_spinner.setText(items[position]);
                if(position == 0) {
                    //가게 리뷰 뽑는중 - jk
                    stroe_review(store_code);

                    //가게 평점 구하는 메소드 (너무 길어서 밑에 빼둠) -jk
                    score(store_code);

                }else if (position ==1){
                    //평점 높은 순 jk
                    stroe_max(store_code);

                }else if (position == 2){
                    //평점 낮은 순 jk
                    stroe_min(store_code);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                store_tv_spinner.setText(items[0]);
            }
        });
    }



    //가게에 달린 리뷰 구하는 메소드 -jk 2022/10/02
    public void stroe_review(int store_code) {
        Log.d("나와", "onTabReselected: ");
        StoreInfoVO vo = new StoreInfoVO();
        vo.setStore_code(store_code);

        CommonAskTask askTask = new CommonAskTask(StoreActivity.this, "andStoreReview");
        askTask.addParams("store_code",vo.getStore_code());
        askTask.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
            @Override
            public void onResult(String data, boolean isResult) {
                Log.d("가게코드 왔니", "onResult: "+ data);
                ArrayList<StoreInfoVO> reviewlist = new Gson().fromJson(data, new TypeToken<ArrayList<StoreInfoVO>>(){}.getType());
                RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getLayoutInflater().getContext(),RecyclerView.VERTICAL, false);
                StoreAdapter adapter1 = new StoreAdapter(getLayoutInflater(), reviewlist,StoreActivity.this, StoreActivity.this);
                recv_store_review.setLayoutManager(layoutManager1);
                recv_store_review.setAdapter(adapter1);
            }

        });
    }



    //가게에 달린 리뷰 평점 낮은순 조회 메소드 -jk 2022/10/02
    public void stroe_min(int store_code) {
        Log.d("나와", "onTabReselected: ");
        StoreInfoVO vo = new StoreInfoVO();
        vo.setStore_code(store_code);

        CommonAskTask askTask = new CommonAskTask(StoreActivity.this, "andStoreMin");
        askTask.addParams("store_code",vo.getStore_code());
        askTask.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
            @Override
            public void onResult(String data, boolean isResult) {
                Log.d("가게코드 왔니", "onResult: "+ data);
                ArrayList<StoreInfoVO> reviewlist = new Gson().fromJson(data, new TypeToken<ArrayList<StoreInfoVO>>(){}.getType());
                RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getLayoutInflater().getContext(),RecyclerView.VERTICAL, false);
                StoreAdapter adapter1 = new StoreAdapter(getLayoutInflater(), reviewlist,StoreActivity.this, StoreActivity.this);
                recv_store_review.setLayoutManager(layoutManager1);
                recv_store_review.setAdapter(adapter1);
            }

        });
    }




    //가게에 달린 리뷰 구하는 메소드 -jk 2022/10/02
    public void stroe_max(int store_code) {
        Log.d("나와", "onTabReselected: ");
        StoreInfoVO vo = new StoreInfoVO();
        vo.setStore_code(store_code);

        CommonAskTask askTask = new CommonAskTask(StoreActivity.this, "andStoreMax");
        askTask.addParams("store_code",vo.getStore_code());
        askTask.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
            @Override
            public void onResult(String data, boolean isResult) {
                Log.d("가게코드 왔니", "onResult: "+ data);
                ArrayList<StoreInfoVO> reviewlist = new Gson().fromJson(data, new TypeToken<ArrayList<StoreInfoVO>>(){}.getType());
                RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getLayoutInflater().getContext(),RecyclerView.VERTICAL, false);
                StoreAdapter adapter1 = new StoreAdapter(getLayoutInflater(), reviewlist,StoreActivity.this, StoreActivity.this);
                recv_store_review.setLayoutManager(layoutManager1);
                recv_store_review.setAdapter(adapter1);
            }

        });
    }


    //가게 평점 구하는 메소드 jk -2022/10/02
    public void score(int store_code) {
        StoreInfoVO vo = new StoreInfoVO();
        vo.setStore_code(store_code); //store_code를 담아줘야 얘가 서로 파라메터 주고받아서 정보 얻어온다!

        CommonAskTask askTask = new CommonAskTask(StoreActivity.this, "andStoreScore");
        askTask.addParams("store_code",vo.getStore_code());
        askTask.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
            @Override
            public void onResult(String data, boolean isResult) {
                //전체 리뷰 카드뷰 안에 있는 평균 점수 test 여기다가 전체평균 가게 평균 구해야함 jk....
                float tv_total, st_total, tast, mood, kind,clean
                        ,sc_tast, sc_mood, sc_kind, sc_clean, store_total_tv;


                Log.d("가게평균점수 왔니", "onResult: "+ data);
                ArrayList<StoreInfoVO> reviewlist = new Gson().fromJson(data, new TypeToken<ArrayList<StoreInfoVO>>(){}.getType());
                //전체 리뷰 평점 구하기 성공 jk
                for(int i = 0; i < reviewlist.size(); i++){
                    tv_total = reviewlist.get(i).getStar_rating();
                    st_total = reviewlist.get(i).getStar_rating();
                    store_total_tv = reviewlist.get(i).getStar_rating();

                    //가게 별점들 평점에 넣을 데이터 변수 담기 - jk
                    sc_tast = reviewlist.get(i).getTaste();
                    sc_mood = reviewlist.get(i).getMood();
                    sc_kind = reviewlist.get(i).getKind();
                    sc_clean = reviewlist.get(i).getClean();

                    //프로그래스바에 넣을 데이터 변수에 담기 - jk
                    tast = reviewlist.get(i).getClean();
                    mood = reviewlist.get(i).getMood();
                    kind = reviewlist.get(i).getKind();
                    clean = reviewlist.get(i).getClean();

                    store_total.setText(tv_total+"");
                    store_total_tv1.setText(store_total_tv+"");
                    store_rattotal.setRating(st_total);
                    progressbar1.setProgress((int)tast);
                    progressbar2.setProgress((int)mood);
                    progressbar3.setProgress((int)kind);
                    progressbar4.setProgress((int)clean);

                    store_score1.setText(sc_tast+"점");
                    store_score2.setText(sc_mood+"점");
                    store_score3.setText(sc_kind+"점");
                    store_score4.setText(sc_clean+"점");

                    store_cnt.setText("리뷰" + reviewlist.get(i).getCnt()+"건");

                }
            }


        });
    }

}