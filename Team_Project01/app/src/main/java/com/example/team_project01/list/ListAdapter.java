package com.example.team_project01.list;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team_project01.R;
import com.example.team_project01.common.BasketVO;
import com.example.team_project01.conn.CommonAskTask;
import com.example.team_project01.conn.CommonConn;
import com.example.team_project01.store.StoreActivity;
import com.example.team_project01.store.StoreMenuDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{
    LayoutInflater inflater;
    ArrayList<Store_infoDTO> list;
    Context context;
    BasketVO basketDTO;


    public ListAdapter(LayoutInflater inflater, ArrayList<Store_infoDTO> list, Context context, BasketVO basketDTO) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
        this.basketDTO = basketDTO;
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
        TextView tv_category, tv_store_name, tv_point, tv_location, store_name;
        public ViewHolder(@NonNull View v) {
            super(v);
            imag_store_imag = v.findViewById(R.id.imag_store_imag);
            tv_category = v.findViewById(R.id.tv_category);
            tv_store_name = v.findViewById(R.id.tv_store_name);
            tv_point = v.findViewById(R.id.tv_point);
            tv_location = v.findViewById(R.id.tv_location);
            store_list = v.findViewById(R.id.store_detail);
        }

        public void bind(@NonNull ViewHolder holder, int i){
            tv_category.setText(category(list.get(i).getStore_category()));
            tv_store_name.setText(list.get(i).getStore_name().toString());
            tv_point.setText(list.get(i).getOpen_close().toString());
            tv_location.setText(list.get(i).getStore_addr().toString());

            store_list.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    CommonConn conn = new CommonConn(context, "storeMenuList");
                    conn.addParams("store_code", list.get(i).getStore_code());
                    conn.excuteConn(new CommonConn.ConnCallback() {
                        @Override
                        public void onResult(boolean isResult, String data) {
                            if (isResult){
                                Log.d("가게별 메뉴리스트", "onResult: " + data);
                                ArrayList<StoreMenuDTO> list1 = new Gson().fromJson(data,
                                        new TypeToken<ArrayList<StoreMenuDTO>>(){}.getType());
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

        public String category(int ctg){
            if (ctg == 4){
                return "중식";
            }else if (ctg == 2){
                return "한식";
            }else if (ctg == 5){
                return "카페";
            }else {
                return "일식";
            }
        }



    }
}
