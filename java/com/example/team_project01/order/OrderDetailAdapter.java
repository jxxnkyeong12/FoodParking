package com.example.team_project01.order;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team_project01.R;
import com.example.team_project01.common.BasketAdapter;
import com.example.team_project01.common.BasketVO;

import java.util.ArrayList;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder>{
    LayoutInflater inflater;
    Order_infoVO vo;
    ArrayList<BasketVO> list;

    public OrderDetailAdapter(LayoutInflater inflater, Order_infoVO vo, ArrayList<BasketVO> list) {
        this.inflater = inflater;
        this.vo = vo;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_order_detail, parent, false);
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
        TextView menu_cnt, menu_price, menu_name;
        public ViewHolder(@NonNull View v) {
            super(v);
            menu_cnt = v.findViewById(R.id.menu_cnt);
            menu_name = v.findViewById(R.id.menu_name);
            menu_price = v.findViewById(R.id.menu_price);
        }
        public void bind(@NonNull ViewHolder holder, int i){
            menu_name.setText(list.get(i).getMenu_name() + "");
            menu_cnt.setText(list.get(i).getMenu_cnt() + "");
            menu_price.setText(list.get(i).getMenu_price() + "인");
        }
    }
}
