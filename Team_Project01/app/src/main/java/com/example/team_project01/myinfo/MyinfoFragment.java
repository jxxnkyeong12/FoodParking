package com.example.team_project01.myinfo;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.team_project01.R;
import com.example.team_project01.common.ReviewActivity;
import com.example.team_project01.common.ReviewListActivity;
import com.example.team_project01.myinfo.modify.ModifyActivity;

import de.hdodenhof.circleimageview.CircleImageView;


public class MyinfoFragment extends Fragment {

   CircleImageView profile_image, myinfo_orderhistory, myinfo_review, myinfo_like;
   CardView myinfo_cardview;
   LinearLayout myinfo_liner, myinfo_modify;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_myinfo, container, false);
        profile_image = v.findViewById(R.id.profile_image);
        myinfo_cardview = v.findViewById(R.id.myinfo_cardview);
        myinfo_liner = v.findViewById(R.id.myinfo_liner);
        myinfo_orderhistory = v.findViewById(R.id.myinfo_orderhistory);
        myinfo_review = v.findViewById(R.id.myinfo_review);
        myinfo_modify = v.findViewById(R.id.myinfo_modify);
        myinfo_like = v.findViewById(R.id.myinfo_like);

        myinfo_orderhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), OrderHistoryActivity.class);
                startActivity(intent);
            }
        });

        myinfo_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ReviewListActivity.class);
                startActivity(intent);
            }
        });

        myinfo_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ModifyActivity.class);
                startActivity(intent);
            }
        });

        myinfo_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LikeHistoryActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }
}