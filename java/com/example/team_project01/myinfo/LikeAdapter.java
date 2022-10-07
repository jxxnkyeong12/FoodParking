package com.example.team_project01.myinfo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team_project01.R;
import com.example.team_project01.store.AndBookmarkVO;

import java.util.ArrayList;

public class LikeAdapter extends RecyclerView.Adapter<LikeAdapter.ViewHolder> {
    LayoutInflater inflater;
    ArrayList<AndBookmarkVO> list;

    public LikeAdapter(LayoutInflater inflater, ArrayList<AndBookmarkVO> list) {
        this.inflater = inflater;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_like_history, parent, false);
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
        ImageView imgv_like_store;
        TextView like_tv_name;

        public ViewHolder(@NonNull View v) {
            super(v);
            imgv_like_store = v.findViewById(R.id.imgv_like_store);
            like_tv_name = v.findViewById(R.id.like_tv_name);
        }

        public void bind(@NonNull ViewHolder h, int i) {
            //h.imgv_like_store.setImageResource(list.get(i).getStore_logo());
            h.like_tv_name.setText(list.get(i).getStore_name());
        }
    }
}