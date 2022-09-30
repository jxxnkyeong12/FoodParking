package com.example.team_project01.store;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team_project01.R;
import com.example.team_project01.common.BasketActivity;
import com.example.team_project01.common.BasketVO;
import com.example.team_project01.common.CommonVal;
import com.example.team_project01.conn.CommonConn;
import com.example.team_project01.list.Store_infoDTO;
import com.example.team_project01.order.Order_infoVO;

import java.util.ArrayList;

public class StoreMenuAdapter extends RecyclerView.Adapter<StoreMenuAdapter.ViewHolder> {

    ArrayList<StoreMenuDTO> list;
    LayoutInflater inflater;
    Activity activity;
    Context context;
    ArrayList<StoreMenuDTO> basketlist = new ArrayList<>();
    Order_infoVO vo;

    public StoreMenuAdapter(ArrayList<StoreMenuDTO> list, LayoutInflater inflater, Activity activity, Context context, Order_infoVO vo) {
        this.list = list;
        this.inflater = inflater;
        this.activity = activity;
        this.context = context;
        this.vo = vo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_store_menu, parent, false);
        return new ViewHolder(view);
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
        ImageView imgv_menu_image;
        TextView tv_menu_name, tv_menu_price, store_name1, store_name2;
        LinearLayout menu_cho;


        public ViewHolder(@NonNull View v) {
            super(v);

            imgv_menu_image = v.findViewById(R.id.imgv_menu_image);
            tv_menu_name = v.findViewById(R.id.tv_menu_name);
            tv_menu_price = v.findViewById(R.id.tv_menu_price);
            menu_cho = v.findViewById(R.id.menu_cho);
            store_name1 = v.findViewById(R.id.store_name1);
            store_name2 = v.findViewById(R.id.store_name2);

        }

        public void bind(@NonNull ViewHolder h, int pod) {
            h.tv_menu_name.setText(list.get(pod).getMenu_name());
            h.tv_menu_price.setText(list.get(pod).getPrice() + "");


            //메뉴 클릭시 다이얼로그 뛰우기
            menu_cho.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    DialogInterface.OnClickListener confirm = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                                    Log.d("TAG", "onClick: 네");
                                    basketlist.add(list.get(pod));
                                    Toast.makeText(context, basketlist.get(pod).getMenu_name() + " 장바구니에 담겼습니다", Toast.LENGTH_SHORT).show();
                                    Log.d("TAG", "onClick: " + basketlist.size());
                        }
                    };


                    DialogInterface.OnClickListener cancle = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Log.d("TAG", "onClick: 아니요");
                        }
                    };

                    new AlertDialog.Builder(activity)
                            .setTitle(h.tv_menu_name.getText() + " 를 장바구니에 담으시겠습니까")
                            .setPositiveButton("네", confirm)
                            .setNegativeButton("아니요", cancle)
                            .show();
                }
            });


        }


    }

    public ArrayList<StoreMenuDTO> getBasketlist() {
        return basketlist;
    }
}