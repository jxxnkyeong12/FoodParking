package com.example.team_project01.myinfo;


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
import com.example.team_project01.common.CommonVal;
import com.example.team_project01.order.OrderDetailActivity;

import java.util.ArrayList;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.ViewHolder> {

    LayoutInflater inflater;
    ArrayList<OrderHistoryVO> list;
    Context context;


    public OrderHistoryAdapter(LayoutInflater inflater, ArrayList<OrderHistoryVO> list, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_order_history,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            holder.bind(holder,position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView oh_menu_img;
        TextView oh_store_name, oh_menucnt, oh_total_price, oh_orderdetail;
        LinearLayout oh_review;


        public ViewHolder(@NonNull View v) {
            super(v);
            oh_menu_img = v.findViewById(R.id.oh_menu_img);
            oh_store_name = v.findViewById(R.id.oh_store_name);
            oh_menucnt = v.findViewById(R.id.oh_menucnt);
            oh_orderdetail = v.findViewById(R.id.oh_orderdetail);
            oh_review = v.findViewById(R.id.oh_review);
            oh_total_price = v.findViewById(R.id.oh_total_price);

        }



        public void bind(@NonNull ViewHolder h, int i) {
            h.oh_store_name.setText(list.get(i).getStore_name());
            h.oh_menucnt.setText(list.get(i).getMenu_name() + " " + list.get(i).getMenu_cnt());
            h.oh_total_price.setText(list.get(i).getPrice()+"");
           // Glide.with(context).load(CommonVal.orderHistory.getMenu_image()).into(h.oh_menu_img);

            h.oh_review.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, OrderDetailActivity.class);
                    context.startActivity(intent);
                }
            });

        }
    }


}
