package com.example.team_project01.common;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team_project01.R;
import com.example.team_project01.store.StoreMenuDTO;

import java.util.ArrayList;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder>{
    LayoutInflater inflater;
    ArrayList<StoreMenuDTO> list;
    TextView basket_total_price,  tv_menu_cnt;
    int menu_cnt = 1;
    public BasketAdapter(LayoutInflater inflater, ArrayList<StoreMenuDTO> list, TextView basket_total_price) {
        this.inflater = inflater;
        this.list = list;
        this.basket_total_price = basket_total_price;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_basket_recv, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(holder, position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imag_plus, imag_min;
        TextView basket_menu_name, basket_menu_price;

        public ViewHolder(@NonNull View v) {
            super(v);
            imag_min = v.findViewById(R.id.imag_min);
            imag_plus = v.findViewById(R.id.imag_plus);
            tv_menu_cnt = v.findViewById(R.id.tv_menu_cnt);
            basket_menu_name = v.findViewById(R.id.basket_menu_name);
            basket_menu_price = v.findViewById(R.id.basket_menu_price);

        }
        public void bind(@NonNull ViewHolder holder, int i){
                basket_menu_name.setText(list.get(i).getMenu_name());
                basket_menu_price.setText(list.get(i).getPrice() + "원");

            //메뉴 갯수 플러스
            imag_plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    menu_cnt++;
                    tv_menu_cnt.setText(menu_cnt+"");
                    int total_price = Integer.parseInt((String) basket_total_price.getText());
                    basket_total_price.setText((total_price + list.get(i).getPrice()) + "");
                }
            });


            //메뉴갯수 빼기
            imag_min.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (menu_cnt > 1) {
                        menu_cnt--;
                        tv_menu_cnt.setText(menu_cnt+"");
                        int total_price = Integer.parseInt((String) basket_total_price.getText());
                        basket_total_price.setText((total_price - list.get(i).getPrice()) + "");
                    }
                }
            });
        }
    }

}
