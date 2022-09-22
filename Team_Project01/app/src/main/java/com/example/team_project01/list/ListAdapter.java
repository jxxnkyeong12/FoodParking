package com.example.team_project01.list;

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

import com.example.team_project01.R;
import com.example.team_project01.store.StoreActivity;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{
    LayoutInflater inflater;
    ArrayList<ListDTO> list;
    Context context;


    public ListAdapter(LayoutInflater inflater, ArrayList<ListDTO> list, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_list_recv, parent, false);
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout store_list;
        ImageView imag_store_imag;
        TextView tv_category, tv_store_name, tv_point, tv_location;
        public ViewHolder(@NonNull View v) {
            super(v);
            imag_store_imag = v.findViewById(R.id.imag_store_imag);
            tv_category = v.findViewById(R.id.tv_category);
            tv_store_name = v.findViewById(R.id.tv_store_name);
            tv_point = v.findViewById(R.id.tv_point);
            tv_location = v.findViewById(R.id.tv_location);
            store_list = v.findViewById(R.id.store_list);
        }

        public void bind(@NonNull ViewHolder holder, int i){
            imag_store_imag.setImageResource(list.get(i).getImag_store_imag());
            tv_category.setText(list.get(i).getTv_category());
            tv_store_name.setText(list.get(i).getTv_store_name());
            tv_point.setText(list.get(i).getTv_point());
            tv_location.setText(list.get(i).getTv_location());

            store_list.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, StoreActivity.class);
                    context.startActivity(intent);

                }
            });
        }
    }
}
