package com.example.team_project01.myinfo;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.team_project01.R;
import com.example.team_project01.common.CommonVal;
import com.example.team_project01.common.ReviewListActivity;


import de.hdodenhof.circleimageview.CircleImageView;


public class MyinfoFragment extends Fragment implements View.OnClickListener{

   CircleImageView profile_image, myinfo_orderhistory, myinfo_review, myinfo_like;
   CardView myinfo_cardview;
   LinearLayout myinfo_liner, myinfo_modify, myinfo_logout;
    Intent intent ;
    TextView myinfo_nickname, myinfo_email;

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
        myinfo_nickname = v.findViewById(R.id.myinfo_nickname);
        myinfo_email = v.findViewById(R.id.myinfo_email);
        myinfo_logout = v.findViewById(R.id.myinfo_logout);

        myinfo_orderhistory.setOnClickListener(this);
        myinfo_logout.setOnClickListener(this);

        //회원프로필 사진 디비에 있던거 가져와서 붙이는 처리
        if(CommonVal.loginInfo !=null){
            Glide.with(MyinfoFragment.this).load(CommonVal.loginInfo.getProfile_image()).into(profile_image);
            myinfo_nickname.setText(CommonVal.loginInfo.getNickname());
            myinfo_email.setText(CommonVal.loginInfo.getEmail());


        }


        //modify에서 받아온 정보 입히기
       Bundle extra = this.getArguments();
       if(extra !=null){
           extra = getArguments();
          // String nickname = extra.getString("nickname");
           String pw = extra.getString("pw");
           String phone = extra.getString("nickname");

           myinfo_nickname.setText(extra.getString("nickname"));
           Glide.with(MyinfoFragment.this).load(CommonVal.loginInfo.getProfile_image()).into(profile_image);
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
            CommonVal.loginInfo = null;

          /*  FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(this).attach(this).commit();*/

        }
    }
}