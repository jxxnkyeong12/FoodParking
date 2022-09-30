package com.example.team_project01.order;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.team_project01.R;
import com.example.team_project01.common.BasketVO;
import com.example.team_project01.common.MapActivity;
import com.example.team_project01.list.ListActivity;


public class OrderFragment extends Fragment {
    CardView order, order_pack;
    BasketVO basketDTO = new BasketVO();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            View v =  inflater.inflate(R.layout.fragment_order, container, false);
            order = v.findViewById(R.id.order);
        order_pack = v.findViewById(R.id.order_pack);




            order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(inflater.getContext(), ListActivity.class);
                    //예약인지 포장인지 구분 1이면 예약
                    basketDTO.setCategory_code(1);
                    intent.putExtra("basketDTO", basketDTO);
                    startActivity(intent);

                }
            });


            order_pack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(inflater.getContext(), MapActivity.class);
                    //예약인지 포장인지 구분 2이면 포장
                    basketDTO.setCategory_code(2);
                    intent.putExtra("basketDTO", basketDTO);
                    startActivity(intent);
                }
            });
        return v;
    }
}