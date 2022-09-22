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
     ImageView imgv_join_email, btn_kakao;


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



        btn_login.setOnClickListener(this);
        imgv_join_email.setOnClickListener(this);





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

       /*
          나중에 지울 예정 -sb 09/21
        //카카오 로그아웃
        UserApiClient.getInstance().logout(new Function1<Throwable, Unit>() {
            @Override
            public Unit invoke(Throwable throwable) {
                return null;
            }
        });
        //카카오 열결끊기
        UserApiClient.getInstance().unlink(new Function1<Throwable, Unit>() {
            @Override
            public Unit invoke(Throwable throwable) {
                return null;
            }
        });*/

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
                /*
                // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
                if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                    UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                        if (error != null) {
                            Log.e(TAG, "카카오톡으로 로그인 실패", error)

                            // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                            // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                            if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                                return@loginWithKakaoTalk
                            }

                            // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
                        } else if (token != null) {
                            Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
                        }
                    }
                } else {
                    UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
                }*/

            }
        });
    }





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

                // 소셜로그인했을때 회원가입이 되어있는 소셜계정인지 아닌지를 판단해서
                // 회원가입이되어있으면 => MainActivity
                // 안되어있으면 해당 정보로 => JoinActivity
                Intent intent = new Intent(LoginSocialActivity.this, SocialJoinActivity.class);
                intent.putExtra("email", res.getProfile().getEmail());
                intent.putExtra("phone", res.getProfile().getMobile());
                intent.putExtra("name", res.getProfile().getName());
                startActivity(intent);

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


    public void kakao_profile(){
        UserApiClient.getInstance().me((user, throwable) -> {
            if(throwable != null){
                //오류가 났을때 어떤 오류인지 코드로 줌 KOE + 숫자 ( 단무지가 있음 )
            }else{
                Log.d("카카오", "kakao_profile: " + user.getKakaoAccount().getEmail());
                //카카오 로그인 성공지 이메일정보를 가지고 회원가입(joinactivity)로 이동 -sb 09/21
                Intent intent = new Intent(LoginSocialActivity.this, SocialJoinActivity.class);
                intent.putExtra("email", user.getKakaoAccount().getEmail());
                startActivity(intent);
            }


            return null;
        });

       /* UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            public Unit invoke(User user, Throwable throwable) {
                return null;
            }
        });*/

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