package com.example.team_project01.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.team_project01.R;

import com.example.team_project01.common.CommonVal;
import com.example.team_project01.conn.CommonAskTask;
import com.example.team_project01.store.AndBookmarkVO;
import com.example.team_project01.store.StoreActivity;

import com.example.team_project01.common.BasketVO;
import com.example.team_project01.conn.CommonConn;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    RecyclerView recv_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recv_list = findViewById(R.id.recv_list);

        recv_select();



    }


    public void recv_select(){
        CommonConn conn = new CommonConn(ListActivity.this, "andStoreList");
        conn.excuteConn(new CommonConn.ConnCallback() {
            @Override
            public void onResult(boolean isResult, String data) {
                if(isResult) { //서버와 통신이 성공적으로 끝났을때.
                    Log.d("가게리스트", "onResult: " + data);
                    ArrayList<Store_infoDTO> list = new Gson().fromJson(data,
                            new TypeToken<ArrayList<Store_infoDTO>>(){}.getType());
                    Log.d("사이즈", "onResult: " + list.size());
                    Intent intent = getIntent();
                    BasketVO basketDTO = (BasketVO) intent.getSerializableExtra("basketDTO");
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getLayoutInflater().getContext(), RecyclerView.VERTICAL, false);
                    ListAdapter adapter = new ListAdapter(getLayoutInflater() , list, ListActivity.this, basketDTO);
                    recv_list.setLayoutManager(layoutManager);
                    recv_list.setAdapter(adapter);

                }
            }
        });


    }

}