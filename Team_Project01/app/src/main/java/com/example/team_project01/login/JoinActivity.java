package com.example.team_project01.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;


import com.airbnb.lottie.L;
import com.example.team_project01.R;
import com.example.team_project01.conn.CommonAskTask;
import com.example.team_project01.conn.CommonConn;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class JoinActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imgv_join_back;
    Button btn_join_address, btn_join, join_btn_emailChk, join_btn_emailUse;
    EditText edtv_join_email, edtv_join_pw, edtv_join_pwchk, edtv_join_name, edtv_join_nickname, edtv_join_phone, edtv_join_zipcode, edtv_join_address, edtv_join_address_more;
    TextView tv_email_chk, tv_pw_chk, tv_pw_chk_chk, tv_name_chk, tv_nickname_chk, tv_phone_chk;

    private final int SEARCH_ADDR_CODE = 1001;
    int emailChk = 0;

    //비밀번호 ** 처리 --> 완료
    //비밀번호와 비밀번호 확인 일치 --> 완료
    //유효성검사 --> 완료
    //값이 없을 때 입력하라는 토스트 띄우기 --> 완료

    //값이 없을 때 기본 문구로 뜨게
    //프로필 사진 추가  --> 나중에
    //DB 데이터와 이메일 비교 --> 완료

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        imgv_join_back = findViewById(R.id.imgv_join_back);

        btn_join_address = findViewById(R.id.btn_join_address);
        btn_join_address = findViewById(R.id.btn_join_address);
        btn_join = findViewById(R.id.btn_join);
        join_btn_emailChk = findViewById(R.id.join_btn_emailChk);
        join_btn_emailUse = findViewById(R.id.join_btn_emailUse);

        tv_email_chk = findViewById(R.id.tv_email_chk);
        tv_pw_chk = findViewById(R.id.tv_pw_chk);
        tv_pw_chk_chk = findViewById(R.id.tv_pw_chk_chk);
        tv_name_chk = findViewById(R.id.tv_name_chk);
        tv_nickname_chk = findViewById(R.id.tv_nickname_chk);
        tv_phone_chk = findViewById(R.id.tv_phone_chk);

        edtv_join_email = findViewById(R.id.edtv_join_email);
        edtv_join_pw = findViewById(R.id.edtv_join_pw);
        edtv_join_pwchk = findViewById(R.id.edtv_join_pwchk);
        edtv_join_name = findViewById(R.id.edtv_join_name);
        edtv_join_nickname = findViewById(R.id.edtv_join_nickname);
        edtv_join_phone = findViewById(R.id.edtv_join_phone);
        edtv_join_zipcode = findViewById(R.id.edtv_join_zipcode);
        edtv_join_address = findViewById(R.id.edtv_join_address);
        edtv_join_address_more = findViewById(R.id.edtv_join_address_more);

        edtv_join_zipcode.setEnabled(false);
        edtv_join_address.setEnabled(false);

        //onClick 메소드
        imgv_join_back.setOnClickListener(this);
        btn_join_address.setOnClickListener(this);
        btn_join.setOnClickListener(this);
        join_btn_emailChk.setOnClickListener(this);
        join_btn_emailUse.setOnClickListener(this);

        //유효성 검사, 09.22 추가
        edtv_join_email.addTextChangedListener(textWatcher);
        edtv_join_pw.addTextChangedListener(textWatcher);
        edtv_join_pwchk.addTextChangedListener(textWatcher);
        edtv_join_name.addTextChangedListener(textWatcher);
        edtv_join_nickname.addTextChangedListener(textWatcher);
        edtv_join_phone.addTextChangedListener(textWatcher);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.imgv_join_back) {  //뒤로가기 버튼
            onBackPressed();


        }else if(v.getId() == R.id.btn_join_address) {  //우편번호 찾기
            Intent intent = new Intent(JoinActivity.this, AddressActivity.class);
            startActivityForResult(intent, SEARCH_ADDR_CODE);


        }else if(v.getId() == R.id.join_btn_emailChk) {  //이메일 중복확인
            MemberVO vo = new MemberVO();
            vo.setEmail(edtv_join_email.getText().toString());

            CommonConn conn = new CommonConn(JoinActivity.this, "andEmailChk");
            conn.addParams("email", vo.getEmail());
            conn.excuteConn(new CommonConn.ConnCallback() {
                @Override
                public void onResult(boolean isResult, String data) {
                    if(data.equals("있음")) {
                        tv_email_chk.setText("❌ 존재하는 이메일입니다.");
                        tv_email_chk.setTextColor(Color.parseColor("#FF0000"));
                        join_btn_emailUse.setEnabled(false);
                        join_btn_emailUse.setBackgroundColor(Color.parseColor("#898989"));
                        emailChk = 1;

                    }else if(data.equals("없음")) {
                        join_btn_emailUse.setBackgroundColor(Color.parseColor("#F25C05"));
                        tv_email_chk.setText("✔ 사용 가능한 이메일입니다.");
                        join_btn_emailUse.setEnabled(true);
                        emailChk = 0;

                    }
                }
            });

        }if(v.getId() == R.id.join_btn_emailUse){
            edtv_join_email.setEnabled(false);


        }else if(v.getId() == R.id.btn_join) {  //회원가입 처리
            if(emailChk == 0) {
                Toast.makeText(JoinActivity.this, "이메일 중복체크를 진행해주세요", Toast.LENGTH_SHORT).show();

            }else {
                MemberVO vo = new MemberVO();
                vo.setEmail(edtv_join_email.getText().toString());
                vo.setPw(edtv_join_pw.getText().toString());
                vo.setName(edtv_join_name.getText().toString());
                vo.setNickname(edtv_join_nickname.getText().toString());
                //주소 추가 입력
                vo.setAddr(edtv_join_address.getText().toString() + " " + edtv_join_address_more.getText().toString());
                vo.setPhone(edtv_join_phone.getText().toString());
                // 우편 저장되게 추가 수정 가넝한! jk -2022/09/21
                vo.setPost(edtv_join_zipcode.getText().toString());
                vo.setManager("N");
                vo.setSocial("N");

                CommonConn conn = new CommonConn(JoinActivity.this, "andJoin");
                conn.addParams("vo", new Gson().toJson(vo));
                conn.excuteConn(new CommonConn.ConnCallback() {
                    @Override
                    public void onResult(boolean isResult, String data) {
                    }
                });
                emptyChk();
            }
        }
    }

    //09.22 hs 추가
    public void emptyChk() {
        if (edtv_join_email.getText().length() == 0) {
            Toast.makeText(JoinActivity.this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show();
        } else if (edtv_join_pw.getText().length() == 0) {
            Toast.makeText(JoinActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
        } else if (edtv_join_pwchk.getText().length() == 0) {
            Toast.makeText(JoinActivity.this, "비밀번호 확인을 진행해주세요", Toast.LENGTH_SHORT).show();
        } else if (edtv_join_name.getText().length() == 0) {
            Toast.makeText(JoinActivity.this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
        } else if (edtv_join_phone.getText().length() == 0) {
            Toast.makeText(JoinActivity.this, "핸드폰 번호를 입력해주세요", Toast.LENGTH_SHORT).show();
        } else if (edtv_join_address.getText().length() == 0) {
            Toast.makeText(JoinActivity.this, "주소 입력해주세요", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    //09.22 hs 추가
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            String email_chk = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
            String pw_chk = "^[A-Za-z0-9_@./#&+-]*.{6,20}$";
            String name = "^[가-힣]{2,4}$";
            String nickname = "^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$";
            String phone = "^010-\\d{4}-\\d{4}$";

            //이메일
            if (!Pattern.matches(email_chk, edtv_join_email.getText().toString())) {
                tv_email_chk.setVisibility(View.VISIBLE);
                tv_email_chk.setText("이메일 형식으로 입력하세요.");
                tv_email_chk.setTextColor(Color.parseColor("#FF0000"));
            } else if (Pattern.matches(email_chk, edtv_join_email.getText().toString())) {
                tv_email_chk.setText("올바른 형식의 이메일입니다.");
                tv_email_chk.setTextColor(Color.parseColor("#008EFF"));
            }else {
                tv_email_chk.setText("이메일을 입력해주세요.");
                tv_email_chk.setTextColor(Color.parseColor("#898989"));
            }

            //비밀번호
            if (!Pattern.matches(pw_chk, edtv_join_pw.getText().toString())) {
                tv_pw_chk.setVisibility(View.VISIBLE);
                tv_pw_chk.setText("영어 대소문자, 숫자, 특수문자 최소 1개 이상만 가능합니다.");
                tv_pw_chk.setTextColor(Color.parseColor("#FF0000"));
            } else if (Pattern.matches(pw_chk, edtv_join_pw.getText().toString())) {
                tv_pw_chk.setText("사용 가능한 비밀번호입니다.");
                tv_pw_chk.setTextColor(Color.parseColor("#008EFF"));
            }

            //비밀번호 확인
            if (!Pattern.matches(edtv_join_pw.getText().toString(), edtv_join_pwchk.getText().toString())) {
                tv_pw_chk_chk.setVisibility(View.VISIBLE);
                tv_pw_chk_chk.setText("비밀번호가 일치하지 않습니다.");
                tv_pw_chk_chk.setTextColor(Color.parseColor("#FF0000"));
            } else if (Pattern.matches(edtv_join_pw.getText().toString(), edtv_join_pwchk.getText().toString())) {
                tv_pw_chk_chk.setText("비밀번호가 일치합니다");
                tv_pw_chk_chk.setTextColor(Color.parseColor("#008EFF"));
            }

            //이름
            if (!Pattern.matches(name, edtv_join_name.getText().toString())) {
                tv_name_chk.setVisibility(View.VISIBLE);
                tv_name_chk.setText("한글 두 글자 이상 세 글자 이하로 입력해주세요.");
                tv_name_chk.setTextColor(Color.parseColor("#FF0000"));
            } else if (Pattern.matches(name, edtv_join_name.getText().toString())) {
                tv_name_chk.setText("사용할 수 있는 이름입니다.");
                tv_name_chk.setTextColor(Color.parseColor("#008EFF"));
            }

            //닉네임
            if (!Pattern.matches(nickname, edtv_join_nickname.getText().toString())) {
                tv_nickname_chk.setVisibility(View.VISIBLE);
                tv_nickname_chk.setText("한글 초성과 모음은 허락하지 않습니다.");
                tv_nickname_chk.setTextColor(Color.parseColor("#FF0000"));
            } else if (Pattern.matches(nickname, edtv_join_nickname.getText().toString())) {
                tv_nickname_chk.setText("사용 가능한 닉네임입니다.");
                tv_nickname_chk.setTextColor(Color.parseColor("#008EFF"));
            }


            //전화번호
            if (!Pattern.matches(phone, edtv_join_phone.getText().toString())) {
                tv_phone_chk.setVisibility(View.VISIBLE);
                tv_phone_chk.setText("정확한 핸드폰 번호를 입력하세요.");
                tv_phone_chk.setTextColor(Color.parseColor("#FF0000"));
            } else if (Pattern.matches(phone, edtv_join_phone.getText().toString())) {
                tv_phone_chk.setText("올바른 형식의 번호입니다.");
                tv_phone_chk.setTextColor(Color.parseColor("#008EFF"));
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SEARCH_ADDR_CODE) {
            Log.d("TAG", "processDATA: " + data);

            String addr = data.getExtras().getString("addr");
            String post = data.getExtras().getString("post");
            if (data != null) {
                edtv_join_address.setText(addr);
                edtv_join_zipcode.setText(post);
            }
        }
    }
}