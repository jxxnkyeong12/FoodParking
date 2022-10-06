package com.example.team_project01.myinfo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.os.UserManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.team_project01.MainActivity;
import com.example.team_project01.R;
import com.example.team_project01.common.CommonVal;
import com.example.team_project01.common.ReviewListActivity;
import com.example.team_project01.conn.CommonConn;
import com.example.team_project01.login.LoginActivity;
import com.google.gson.Gson;


import de.hdodenhof.circleimageview.CircleImageView;


public class MyinfoFragment extends Fragment implements View.OnClickListener{

    CircleImageView myinfo_image, myinfo_orderhistory, myinfo_review, myinfo_like;
    CardView myinfo_cardview;
    LinearLayout myinfo_liner, myinfo_modify, myinfo_logout, myinfo_login, myinfo_delete;
    Intent intent ;
    TextView myinfo_nickname, myinfo_email;
    ImageView myinfo_back;


    //로그아웃 처리하기.


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_myinfo, container, false);
        myinfo_image = v.findViewById(R.id.myinfo_image);
        myinfo_cardview = v.findViewById(R.id.myinfo_cardview);
        myinfo_liner = v.findViewById(R.id.myinfo_liner);
        myinfo_orderhistory = v.findViewById(R.id.myinfo_orderhistory);
        myinfo_review = v.findViewById(R.id.myinfo_review);
        myinfo_modify = v.findViewById(R.id.myinfo_modify);
        myinfo_like = v.findViewById(R.id.myinfo_like);
        myinfo_nickname = v.findViewById(R.id.myinfo_nickname);
        myinfo_email = v.findViewById(R.id.myinfo_email);
        myinfo_logout = v.findViewById(R.id.myinfo_logout);
        myinfo_back = v.findViewById(R.id.myinfo_back);
        myinfo_login = v.findViewById(R.id.myinfo_login);
        myinfo_delete = v.findViewById(R.id.myinfo_delete);

        myinfo_orderhistory.setOnClickListener(this);
        myinfo_logout.setOnClickListener(this);
        myinfo_like.setOnClickListener(this);
        myinfo_review.setOnClickListener(this);
        myinfo_modify.setOnClickListener(this);
        myinfo_delete.setOnClickListener(this);
        myinfo_login.setOnClickListener(this);

        //회원프로필 사진 디비에 있던거 가져와서 붙이는 처리
        if(CommonVal.loginInfo !=null){
            Glide.with(MyinfoFragment.this).load(CommonVal.loginInfo.getProfile_image()).into(myinfo_image);
            myinfo_nickname.setText(CommonVal.loginInfo.getNickname());
            myinfo_email.setText(CommonVal.loginInfo.getEmail());
            myinfo_login.setVisibility(View.GONE);
            if(CommonVal.loginInfo.getProfile_image() == null){
                Glide.with(MyinfoFragment.this).load(R.drawable.profile_image).into(myinfo_image);
            }

        }




       //modify에서 받아온 정보 입히기
       Bundle extra = this.getArguments();
       if(extra !=null){
           extra = getArguments();
           String nickname = extra.getString("nickname");
           myinfo_nickname.setText(nickname);
           Glide.with(MyinfoFragment.this).load(CommonVal.loginInfo.getProfile_image()).into(myinfo_image);
       }
       
     
        return v;
    }



    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.myinfo_orderhistory) {
            intent = new Intent(getContext(), OrderHistoryActivity.class);
            startActivity(intent);
        }else if(v.getId() ==R.id.myinfo_review) {
            intent = new Intent(getContext(), ReviewListActivity.class);
            startActivity(intent);
        }else if(v.getId() ==R.id.myinfo_like) {
            intent = new Intent(getContext(), LikeHistoryActivity.class);
            startActivity(intent);
        }else if(v.getId() ==R.id.myinfo_modify){
            Intent intent = new Intent(getContext(), ModifyActivity.class);
            startActivity(intent);
            
         //로그아웃
        }else if (v.getId() == R.id.myinfo_logout){
            DialogInterface.OnClickListener confirm = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                            CommonVal.loginInfo = null;
                            Toast.makeText(getContext(), "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();

                }
            };


            DialogInterface.OnClickListener cancle = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Log.d("TAG", "onClick: 아니요");
                }
            };

            new AlertDialog.Builder(getContext())
                    .setTitle("로그아웃 하시겠습니까?")
                    .setPositiveButton("네", confirm)
                    .setNegativeButton("아니요", cancle)
                    .show();





        }else if (v.getId() ==R.id.myinfo_delete){
            CommonConn conn = new CommonConn(getContext(), "andDelete");
            conn.addParams("email", CommonVal.loginInfo.getEmail());
            conn.excuteConn(new CommonConn.ConnCallback() {
                @Override
                public void onResult(boolean isResult, String data) {
                    Log.d("수정", "onResult: " + data);
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                }
            });
        }else if(v.getId() ==R.id.myinfo_login){
            intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
        }
    }
}