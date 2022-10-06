package com.example.team_project01.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team_project01.R;
import com.example.team_project01.conn.CommonAskTask;
import com.example.team_project01.store.StoreMenuDTO;
import com.google.gson.Gson;

import java.util.ArrayList;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder>{
    LayoutInflater inflater;
    ArrayList<BasketVO> list;
    TextView basket_total_price, basket_total_cnt;
    Context context;

    public BasketAdapter(LayoutInflater inflater, ArrayList<BasketVO> list, TextView basket_total_price, TextView basket_total_cnt, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.basket_total_price = basket_total_price;
        this.basket_total_cnt = basket_total_cnt;
        this.context = context;
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
        ImageView imag_close,imag_min, imag_plus;
        TextView basket_menu_name, basket_menu_price, tv_menu_cnt;

        public ViewHolder(@NonNull View v) {
            super(v);
            basket_menu_name = v.findViewById(R.id.basket_menu_name);
            basket_menu_price = v.findViewById(R.id.basket_menu_price);
            imag_close = v.findViewById(R.id.imag_close);
            imag_min = v.findViewById(R.id.imag_min);
            tv_menu_cnt = v.findViewById(R.id.tv_menu_cnt);
            imag_plus = v.findViewById(R.id.imag_plus);

        }
        public void bind(@NonNull ViewHolder holder, int i){
            basket_menu_name.setText(list.get(i).getMenu_name());
            basket_menu_price.setText(list.get(i).getMenu_price() + "");
            basket_total_cnt.setText(list.size() + "");
            basket_total_price.setText(total_price(list) + "");


            //메뉴 갯수 더하기
            imag_plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int num2 = Integer.parseInt((String) tv_menu_cnt.getText());
                    num2++;
                    tv_menu_cnt.setText(num2 + "");
                    int num3 = Integer.parseInt((String) basket_total_price.getText()) + list.get(i).getMenu_price();
                    basket_total_price.setText(num3 + "");
                    list.get(i).setMenu_cnt(num2);
                    Log.d("TAG", "onClick: " + list.get(i).getMenu_cnt());
                }
            });

            //메뉴 갯수 빼기
            imag_min.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int num1 = Integer.parseInt((String) tv_menu_cnt.getText());
                    if (num1 > 1){
                        num1--;
                        tv_menu_cnt.setText(num1 + "");
                        int num3 = Integer.parseInt((String) basket_total_price.getText()) - list.get(i).getMenu_price();
                        basket_total_price.setText(num3 + "");
                        list.get(i).setMenu_cnt(num1);
                        Log.d("TAG", "onClick: " + list.get(i).getMenu_cnt());
                    }
                }
            });


            //장바구니에서 아이템 빼기
            imag_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (list.size() > 0) {
                        CommonAskTask askTask = new CommonAskTask(context, "andBasketDelete");
                        askTask.addParams("menu", new Gson().toJson(list.get(i)));
                        askTask.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
                            @Override
                            public void onResult(String data, boolean isResult) {
                                if (isResult){
                                    Intent intent = ((Activity)context).getIntent();
                                    ((Activity)context).finish(); //현재 액티비티 종료 실시
                                    ((Activity)context).overridePendingTransition(0, 0); //효과 없애기
                                    ((Activity)context).startActivity(intent); //현재 액티비티 재실행 실시
                                    ((Activity)context).overridePendingTransition(0, 0); //효과 없애기
                                }
                            }
                        });

                    }
                }
            });



        }
    }




    //토탈 가격을 계산해주는 메소드
    public int total_price(ArrayList<BasketVO> list){
        int total_price = 0;

        for (int i = 0; i < list.size(); i++) {
            total_price += list.get(i).getTotal_price();
        }

        return total_price;
    }

    //리스트를 리턴하는 메소드
    public ArrayList<BasketVO> getlist(){
        return list;
    }

    //전체 가격을 리턴해주는 메소드
    public String getprice(){
        return (String) basket_total_price.getText();
    }

}
