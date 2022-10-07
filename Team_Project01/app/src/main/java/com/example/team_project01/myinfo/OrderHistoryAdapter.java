package com.example.team_project01.myinfo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team_project01.R;

import java.util.ArrayList;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.ViewHolder> {

    LayoutInflater inflater;
    ArrayList<OrderHistoryVO> list;



    public OrderHistoryAdapter(LayoutInflater inflater, ArrayList<OrderHistoryVO> list) {
        this.inflater = inflater;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_order_history,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView oh_menu_img;
        TextView oh_store_namem, oh_menucnt, oh_total_price, oh_orderdetail;



        public ViewHolder(@NonNull View itemView) {

            super(itemView);


        }
    }


    public void bind(@NonNull ViewHolder holder, int position) {

    }
}
