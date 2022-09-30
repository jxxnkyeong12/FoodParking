package com.example.team_project01.store;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team_project01.R;
import com.example.team_project01.myinfo.LikeAdapter;

import java.util.ArrayList;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder>{
    LayoutInflater inflater;
    ArrayList<StoreInfoVO> list;

    public StoreAdapter(LayoutInflater inflater, ArrayList<StoreInfoVO> list) {
        this.inflater = inflater;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_store_review, parent, false);
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
        ImageView star_1, star_2, star_3, star_4, star_5;
        TextView star_tv, store_rev_date,store_con;



        public ViewHolder(@NonNull View v) {
            super(v);
            star_1 = v.findViewById(R.id.star_1);
            star_2 = v.findViewById(R.id.star_2);
            star_3 = v.findViewById(R.id.star_3);
            star_4 = v.findViewById(R.id.star_4);
            star_5 = v.findViewById(R.id.star_5);
            star_tv = v.findViewById(R.id.star_tv);
            store_rev_date = v.findViewById(R.id.store_rev_date);
            store_con = v.findViewById(R.id.store_con);




        }

        public void bind(@NonNull ViewHolder h, int i){

            h.star_tv.setText(list.get(i).getStar_rating()+"");
            h.store_rev_date.setText(list.get(i).getWritedate());
            h.store_con.setText(list.get(i).getContent());


        }

    }
}
