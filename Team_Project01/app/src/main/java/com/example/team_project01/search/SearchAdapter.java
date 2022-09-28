package com.example.team_project01.search;

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

import java.util.ArrayList;

public class SearchAdapter extends BaseAdapter {

    ArrayList<AndSearchVO> list;
    LayoutInflater inflater;
    String newText;


    public SearchAdapter(ArrayList<AndSearchVO> list, LayoutInflater inflater, String newText) {
        this.list = list;
        this.inflater = inflater;
        this.newText = newText;
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
        String content = list.get(i).getMenu_name();
        SpannableString spannableString = new SpannableString(content);

        int begin = content.indexOf(newText);
        int end = begin + newText.length();

        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#F25C05")), begin, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new StyleSpan(Typeface.BOLD), begin, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        tv_search.setText(spannableString);

        return convertView;
    }
}