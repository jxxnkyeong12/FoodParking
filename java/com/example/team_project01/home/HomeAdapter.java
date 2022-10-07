package com.example.team_project01.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.team_project01.R;
import com.example.team_project01.common.BasketVO;
import com.example.team_project01.conn.CommonAskTask;

import com.example.team_project01.list.Store_infoDTO;
import com.example.team_project01.store.StoreActivity;
import com.example.team_project01.store.StoreMenuDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    ArrayList<Store_infoDTO> list;
    LayoutInflater inflater;
    Context context;
    BasketVO basketDTO;

    public HomeAdapter(BasketVO basketDTO, Context context, ArrayList<Store_infoDTO> list, LayoutInflater inflater) {
        this.list = list;
        this.inflater = inflater;
        this.context = context;
        this.basketDTO = basketDTO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_home, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(holder, position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgv_test;
        TextView tv_item_storename, tv_item_addr;
        LinearLayout linear_home;

        public ViewHolder(@NonNull View v) {
            super(v);

            imgv_test = v.findViewById(R.id.imgv_test);
            tv_item_storename = v.findViewById(R.id.tv_item_storename);
            tv_item_addr = v.findViewById(R.id.tv_item_addr);
            linear_home = v.findViewById(R.id.linear_home);

        }

        public void bind(@NonNull ViewHolder h, int i) {
            //h.imgv_test.setImageResource(Glide.get(imgv_test.getContext()))
            h.tv_item_storename.setText(list.get(i).getStore_name());
            h.tv_item_addr.setText(list.get(i).getStore_addr());

            h.linear_home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CommonAskTask askTask = new CommonAskTask(context, "storeMenuList");
                    askTask.addParams("store_code", list.get(i).getStore_code());
                    askTask.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
                        @Override
                        public void onResult(String data, boolean isResult) {
                            if (isResult) {
                                ArrayList<StoreMenuDTO> list1 = new Gson().fromJson(data, new TypeToken<ArrayList<StoreMenuDTO>>() {
                                }.getType());
                                Intent intent = new Intent(context, StoreActivity.class);
                                intent.putExtra("basketDTO", basketDTO);
                                intent.putExtra("list1", list1);
                                intent.putExtra("vo", list.get(i));
                                context.startActivity(intent);
                            }
                        }
                    });
                }
            });


        }
    }
}