package com.example.team_project01.search;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.team_project01.R;
import com.example.team_project01.common.BasketVO;
import com.example.team_project01.conn.CommonAskTask;
import com.example.team_project01.list.Store_infoDTO;
import com.example.team_project01.store.StoreActivity;
import com.example.team_project01.store.StoreMenuDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class SearchAdapter extends BaseAdapter {

    ArrayList<Store_infoDTO> list;
    LayoutInflater inflater;
    String newText;
    Context context;
    BasketVO basketDTO;

    public SearchAdapter(ArrayList<Store_infoDTO> list, LayoutInflater inflater, String newText, Context context, BasketVO basketDTO) {
        this.list = list;
        this.inflater = inflater;
        this.newText = newText;
        this.context = context;
        this.basketDTO = basketDTO;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item_search_list, parent, false);

        TextView tv_search = convertView.findViewById(R.id.tv_search);

        //검색어와 일치한 문자열만 색 바꾸기

        String content = list.get(i).getStore_name();


        SpannableString spannableString = new SpannableString(content);

        int begin = content.indexOf(newText);
        int end = begin + newText.length();

        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#F25C05")), begin, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new StyleSpan(Typeface.BOLD), begin, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        tv_search.setText(spannableString);

        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonAskTask askTask = new CommonAskTask(context, "storeMenuList");
                askTask.addParams("store_code", list.get(i).getStore_code());
                askTask.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        if (isResult) {
                            ArrayList<StoreMenuDTO> list1 = new Gson().fromJson(data, new TypeToken<ArrayList<StoreMenuDTO>>() {
                            }.getType());
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

        return convertView;
    }
}