package com.example.team_project01.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.team_project01.R;
import com.example.team_project01.common.BasketVO;
import com.example.team_project01.conn.CommonAskTask;
import com.example.team_project01.list.Store_infoDTO;
import com.example.team_project01.conn.CommonAskTask;
import com.google.android.gms.common.internal.service.Common;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    SearchView searchview;
    TextView tv_search_cancel;
    ListView search_listview;
    LinearLayout layout_category;
    RecyclerView recv_search;


    public ArrayList<Store_infoDTO> list = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);

        searchview = v.findViewById(R.id.searchview);
        tv_search_cancel = v.findViewById(R.id.tv_search_cancel);
        search_listview = v.findViewById(R.id.search_listview);
        layout_category = v.findViewById(R.id.layout_category);
        recv_search = v.findViewById(R.id.recv_search);

        search();
        searching();

        return v;
    }

    //기본 검색페이지 화면
    public void search() {
        ArrayList<CategoryDTO> list = new ArrayList<>();
        list.add(new CategoryDTO("한식", "양식", R.drawable.koreanfood, R.drawable.pasta));
        list.add(new CategoryDTO("중식", "일식", R.drawable.chinafood, R.drawable.japanfood));
        list.add(new CategoryDTO("분식", "아시안", R.drawable.ddeokbokki, R.drawable.asianfood));
        list.add(new CategoryDTO("패스트푸드", "카페", R.drawable.fastfood, R.drawable.cafe));
        list.add(new CategoryDTO("브런치", "뷔페", R.drawable.brunch, R.drawable.buffet));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        CategoryAdapter adapter = new CategoryAdapter(getLayoutInflater(), list);
        recv_search.setLayoutManager(layoutManager);
        recv_search.setAdapter(adapter);
    }

    //검색어 입력중
    public void searching() {

        CommonAskTask askTask = new CommonAskTask(getContext(), "andSearchList");
        askTask.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
            @Override
            public void onResult(String data, boolean isResult) {
                list = new Gson().fromJson(data, new TypeToken<ArrayList<Store_infoDTO>>(){}.getType());
            }
        });


        searchview.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_search_cancel.setVisibility(View.VISIBLE);

                //취소 글자 눌렀을 때
                tv_search_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        search_listview.setVisibility(View.GONE);
                        layout_category.setVisibility(View.VISIBLE);
                        search();
                    }
                });


                //검색어 입력 중
                searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {

                        ArrayList<Store_infoDTO> filter = new ArrayList<>();
                        for (int i = 0; i < list.size(); i++) {
                            Store_infoDTO vo = list.get(i);
                            vo.setStore_code(list.get(i).getStore_code());
                            if(vo.getStore_name().toLowerCase().contains(newText.toLowerCase())) {

                                filter.add(vo);
                            }
                        }

                        layout_category.setVisibility(View.GONE);
                        search_listview.setVisibility(View.VISIBLE);

                        BasketVO basketDTO = (BasketVO) new Intent().getSerializableExtra("basketDTO");
                        SearchAdapter adapter = new SearchAdapter(filter, getLayoutInflater(), newText, getContext(), basketDTO);
                        search_listview.setAdapter(adapter);


                        return false;
                    }
                });

            }
        });

        searchview.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                search_listview.setVisibility(View.GONE);
                layout_category.setVisibility(View.VISIBLE);
                search();
                return false;
            }
        });

    }
}