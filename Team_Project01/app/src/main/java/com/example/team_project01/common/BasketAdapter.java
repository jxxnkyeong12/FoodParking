package com.example.team_project01.common;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.team_project01.R;
import com.example.team_project01.store.StoreMenuDTO;

import java.util.ArrayList;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder>{
    LayoutInflater inflater;
    ArrayList<StoreMenuDTO> list;
    TextView basket_total_price, basket_total_cnt;

    public BasketAdapter(LayoutInflater inflater, ArrayList<StoreMenuDTO> list, TextView basket_total_price, TextView basket_total_cnt) {
        this.inflater = inflater;
        this.list = list;
        this.basket_total_price = basket_total_price;
        this.basket_total_cnt = basket_total_cnt;
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
        ImageView imag_close;
        TextView basket_menu_name, basket_menu_price;

        public ViewHolder(@NonNull View v) {
            super(v);
            basket_menu_name = v.findViewById(R.id.basket_menu_name);
            basket_menu_price = v.findViewById(R.id.basket_menu_price);
            imag_close = v.findViewById(R.id.imag_close);

        }
        public void bind(@NonNull ViewHolder holder, int i){
            basket_menu_name.setText(list.get(i).getMenu_name());
            basket_menu_price.setText(list.get(i).getPrice() + "원");

            imag_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (list.size() > 0) {
                        list.remove(i);
                        notifyDataSetChanged();
                        basket_total_cnt.setText(list.size()+ "");
                        basket_total_price.setText(total_price(list) + "");
                    }
                }
            });



        }
    }
    public int total_price(ArrayList<StoreMenuDTO> list){
        int total_price = 0;

        for (int i = 0; i < list.size(); i++) {
            total_price += list.get(i).getPrice();
        }

        return total_price;
    }

    public ArrayList<StoreMenuDTO> getlist() {
        return list;
    }
}
