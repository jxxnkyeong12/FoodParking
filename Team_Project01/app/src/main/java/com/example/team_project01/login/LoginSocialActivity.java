package com.example.team_project01.login;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.team_project01.R;
import com.example.team_project01.conn.CommonAskTask;
import com.example.team_project01.conn.CommonConn;
import com.example.team_project01.home.HomeFragment;
import com.example.team_project01.store.StoreActivity;
import com.google.gson.Gson;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.common.KakaoSdk;
import com.kakao.sdk.user.UserApiClient;
import com.navercorp.nid.NaverIdLoginSDK;
import com.navercorp.nid.oauth.NidOAuthLogin;
import com.navercorp.nid.oauth.OAuthLoginCallback;
import com.navercorp.nid.oauth.view.NidOAuthLoginButton;
import com.navercorp.nid.profile.NidProfileCallback;
import com.navercorp.nid.profile.data.NidProfileResponse;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

public class LoginSocialActivity extends AppCompatActivity implements View.OnClickListener {

     NidOAuthLoginButton btn_naver;
     Button btn_login;
     ImageView imgv_join_email, btn_kakao, btn_google;

     CommonAskTask askTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_social);

        getHashKey();

        NaverIdLoginSDK.INSTANCE.initialize(this,
                "AjoMyNyYbiTJqPYaup6o",
                "S7SKuN5P0O",
                "Team_Project01");

        btn_login =findViewById(R.id.btn_login);
        imgv_join_email = findViewById(R.id.imgv_join_email);
        btn_naver = findViewById(R.id.btn_naver);
        btn_kakao = findViewById(R.id.btn_kakao);
        btn_google = findViewById(R.id.btn_google);

        btn_login.setOnClickListener(this);
        imgv_join_email.setOnClickListener(this);

        btn_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginSocialActivity.this, StoreActivity.class);
                startActivity(intent);
            }
        });

        btn_naver.setOAuthLoginCallback(new OAuthLoginCallback() {
            @Override
            public void onSuccess() {
                Log.d("네이버", "onSuccess: " + NaverIdLoginSDK.INSTANCE.getAccessToken());
                naver_profile();

            }

            @Override
            public void onFailure(int i, @NonNull String s) {
                Log.d("네이버", "onFailure: ");
            }

            @Override
            public void onError(int i, @NonNull String s) {
                Log.d("네이버", "onError: ");
            }
        });

        KakaoSdk.init(this, "1c40100fd84e7b943cf17cb4ec9b7413");


        btn_kakao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Function2<OAuthToken, Throwable , Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
                    @Override
                    public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                        if (oAuthToken != null){
                            Log.d("토큰", "invoke: 받아옴");
                            kakao_profile();
                        }

                        if (throwable != null){
                            Log.d("토큰", "invoke: 오류있음");
                        }
                        return null;
                    }
                };


                UserApiClient apiClient = new UserApiClient();
                if (apiClient.isKakaoTalkLoginAvailable(LoginSocialActivity.this)){
                    apiClient.loginWithKakaoAccount(LoginSocialActivity.this, callback);
                }else{
                    apiClient.loginWithKakaoAccount(LoginSocialActivity.this, callback);
                }

            }
        });
    }



    //네이버 로그인
    public void naver_profile(){
        //NidOAuthLogin().callProfileApi(nidProfileCallback) Kotiln
        NidOAuthLogin authLogin = new NidOAuthLogin();
        authLogin.callProfileApi(new NidProfileCallback<NidProfileResponse>() {
            @Override
            public void onSuccess(NidProfileResponse res) {
                Log.d("프로필", "onSuccess: ");
                Log.d("프로필", "onSuccess: " + res.getProfile().getEmail());
                Log.d("프로필", "onSuccess: " + res.getProfile().getMobile());
                Log.d("프로필", "onSuccess: " + res.getProfile().getName());

                MemberVO vo = new MemberVO();
                vo.setEmail(res.getProfile().getEmail());
                vo.setName(res.getProfile().getName());
                vo.setPhone(res.getProfile().getMobile());
                vo.setSocial("Y");

                //회원가입 하기전에 우선 멤버테이블에 이메일이 있는지 select 하고 없으면
                askTask = new CommonAskTask(LoginSocialActivity.this, "andEmailChk");
                askTask.addParams("email", vo.getEmail());
                askTask.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
                    @Override
                    public void onResult(String data, boolean isResult) {
                        if(data.trim().equals("있음")) {
                            Intent intent = new Intent(LoginSocialActivity.this, HomeFragment.class);
                            startActivity(intent);

                        }else{
                            //네이버로 회원가입시 필요한 정보를 가져올 수 있으므로 바로 회원가입 진행
                            askTask = new CommonAskTask(LoginSocialActivity.this, "andJoin");
                            askTask.addParams("vo", new Gson().toJson(vo));
                            askTask.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
                                @Override
                                public void onResult(String data, boolean isResult) {
                                    if (isResult){

                                    }
                                }
                            });
                        }

                    }
                });
            }

            @Override
            public void onFailure(int i, @NonNull String s) {
                Log.d("프로필", "onFailure: " + s);
            }

            @Override
            public void onError(int i, @NonNull String s) {
                Log.d("프로필", "onError: " + s);
            }
        });

    }

    //카카오 로그인
    public void kakao_profile(){
        UserApiClient.getInstance().me((user, throwable) -> {
            if(throwable != null){
                //오류가 났을때 어떤 오류인지 코드로 줌 KOE + 숫자 ( 단무지가 있음 )
            }else{
                Log.d("카카오", "kakao_profile: " + user.getKakaoAccount().getEmail());
                Log.d("카카오", "kakao_profile: "+ user.getKakaoAccount().getProfile().getNickname());
                Log.d("카카오", "kakao_profile: "+ user.getKakaoAccount().getPhoneNumber());



                //카카오로 회원가입시 전화번호를 가져 올수 없으므로 전화번호를 따로 입력할수 있는 화면으로 이동 후 회원가입 진행
                Intent intent = new Intent(LoginSocialActivity.this, SocialJoinActivity.class);
                intent.putExtra("email", user.getKakaoAccount().getEmail());
                intent.putExtra("name", user.getKakaoAccount().getProfile().getNickname());
                intent.putExtra("social", "K");
                startActivity(intent);
            }

            return null;
        });
    }



    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_login){
            Intent l_intent = new Intent(LoginSocialActivity.this, LoginActivity.class);
            startActivity(l_intent);
        }else if(v.getId() == R.id.imgv_join_email) {
            Intent e_intent = new Intent(LoginSocialActivity.this, JoinActivity.class);
            startActivity(e_intent);
        }
    }


    private void getHashKey(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }

}