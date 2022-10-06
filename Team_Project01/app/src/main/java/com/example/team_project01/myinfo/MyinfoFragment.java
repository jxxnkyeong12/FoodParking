package com.example.team_project01.myinfo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.team_project01.MainActivity;
import com.example.team_project01.R;
import com.example.team_project01.common.CommonVal;
import com.example.team_project01.common.ReviewListActivity;
import com.example.team_project01.conn.CommonAskTask;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class MyinfoFragment extends Fragment implements View.OnClickListener {

    CircleImageView myinfo_image, myinfo_orderhistory, myinfo_review, myinfo_like;
    CardView myinfo_cardview;
    LinearLayout myinfo_liner, myinfo_modify, myinfo_logout, myinfo_delete;
    Intent intent;
    TextView myinfo_nickname, myinfo_email, btn_no, btn_cancel, di_title,di_content ;
    View myinfo_view;
    MainActivity mainActivity;




    //로그아웃 처리하기.- 완 jk 다이얼로그도 띄워짐


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
        myinfo_delete = v.findViewById(R.id.myinfo_delete);
        myinfo_view = v.findViewById(R.id.myinfo_view);
        //다이얼로그 버튼
        btn_no = v.findViewById(R.id.btn_no);
        btn_cancel = v.findViewById(R.id.btn_cancel);
        di_title = v.findViewById(R.id.di_title);
        di_content = v.findViewById(R.id.di_content);

        myinfo_orderhistory.setOnClickListener(this);
        myinfo_like.setOnClickListener(this);
        myinfo_review.setOnClickListener(this);
        myinfo_modify.setOnClickListener(this);
        myinfo_logout.setOnClickListener(this);
        myinfo_delete.setOnClickListener(this);


        //회원정보가 있다면, DB에서 회원정보 가져오기
        if (CommonVal.loginInfo != null) {
            Glide.with(MyinfoFragment.this).load(CommonVal.loginInfo.getProfile_image()).into(myinfo_image);
            myinfo_nickname.setText(CommonVal.loginInfo.getNickname());
            myinfo_email.setText(CommonVal.loginInfo.getEmail());
            if (CommonVal.loginInfo.getProfile_image() == null) {
                Glide.with(MyinfoFragment.this).load(R.drawable.profile_image).into(myinfo_image);
            }

        }else {
            myinfo_logout.setVisibility(View.GONE);
            myinfo_delete.setVisibility(View.GONE);
            myinfo_view.setVisibility(View.GONE);
        }

        return v;
    }


    @Override
    public void onClick(View v) {

        //로그인 한 상태라면
        if (CommonVal.loginInfo != null) {
            if (v.getId() == R.id.myinfo_orderhistory) {
                intent = new Intent(getContext(), OrderHistoryActivity.class);
                startActivity(intent);
            } else if (v.getId() == R.id.myinfo_review) {
                intent = new Intent(getContext(), ReviewListActivity.class);
                startActivity(intent);
            } else if (v.getId() == R.id.myinfo_like) {
                intent = new Intent(getContext(), LikeHistoryActivity.class);
                startActivity(intent);
            } else if (v.getId() == R.id.myinfo_modify) {
                Intent intent = new Intent(getContext(), ModifyActivity.class);
                startActivity(intent);

                //로그아웃
            } else if (v.getId() == R.id.myinfo_logout) {
                DialogInterface.OnClickListener confirm = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CommonVal.loginInfo = null;
                        Toast.makeText(getContext(), "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                        intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);

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

                //회원탈퇴할때 한번더 확인하고 삭제시키기... 다이얼로그 or activity에 하기
            } else if (v.getId() == R.id.myinfo_delete) {
                DialogInterface.OnClickListener confirm = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CommonAskTask askTask = new CommonAskTask(getContext(), "andDelete");
                        askTask.addParams("email", CommonVal.loginInfo.getEmail());
                        askTask.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
                            @Override
                            public void onResult(String data, boolean isResult) {
                                Log.d("삭제", "onResult: " + data);
                                Intent intent = new Intent(getContext(), MainActivity.class);
                                startActivity(intent);
                            }

                        });
                    }
                };


                DialogInterface.OnClickListener cancle = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("TAG", "onClick: 아니요");
                    }
                };

                new AlertDialog.Builder(getContext())
                        .setTitle("회원탈퇴 하시겠습니까?")
                        .setPositiveButton("네", confirm)
                        .setNegativeButton("아니요", cancle)
                        .show();
            }

            //로그인을 하지 않은 경우 서비스 이용 x
        } else if (CommonVal.loginInfo == null) {

            if (v.getId() == R.id.myinfo_orderhistory) {
                Toast.makeText(getContext(), "로그인이 핑료한 서비스입니다", Toast.LENGTH_SHORT).show();
                Log.d("마이인포", "onClick: + 로그인 아닌 상태에서 눌렀을때" );
               // dialog();
            } else if (v.getId() == R.id.myinfo_review) {
                Toast.makeText(getContext(), "로그인이 핑료한 서비스입니다", Toast.LENGTH_SHORT).show();
               // dialog();
                Log.d("마이인포", "onClick: + 로그인 아닌 상태에서 눌렀을때" );
            } else if (v.getId() == R.id.myinfo_like) {
                Toast.makeText(getContext(), "로그인이 핑료한 서비스입니다", Toast.LENGTH_SHORT).show();
               // dialog();
                Log.d("마이인포", "onClick: + 로그인 아닌 상태에서 눌렀을때" );
            } else if (v.getId() == R.id.myinfo_modify) {
                Toast.makeText(getContext(), "로그인이 핑료한 서비스입니다", Toast.LENGTH_SHORT).show();
               // dialog();
                Log.d("마이인포", "onClick: + 로그인 아닌 상태에서 눌렀을때" );

            }


        }


    }




   /* public void dialog() {

        Log.d("TAG", "onClick: 다이얼로그 뜨니?");
        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);


        dialog.setContentView(R.layout.item_dialog);
        dialog.show();


        //다이얼로그 둥글게 ㅎㅎㅎ
        btn_no = dialog.findViewById(R.id.btn_no);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //취소 버튼을 눌렀을때
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        //로그인 버튼을 눌렀을때
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

 *//*       DialogInterface.OnClickListener cancle2 = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("TAG", "onClick: 취소");
            }
        };


        DialogInterface.OnClickListener login = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("TAG", "onClick: 취소");
                intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        };

        new AlertDialog.Builder(getContext())
                .setTitle("로그인이 필요한 서비스 입니다.")
                .setPositiveButton("로그인하러가기", login)
                .setNegativeButton("취소", cancle2)
                .show();
*//*

    }*/
}