package com.example.team_project01.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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


import com.bumptech.glide.Glide;
import com.example.team_project01.R;
import com.example.team_project01.conn.ApiClient;
import com.example.team_project01.conn.ApiInterface;
import com.example.team_project01.conn.CommonAskTask;
import com.example.team_project01.conn.CommonConn;
import com.example.team_project01.myinfo.modify.ModifyActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinActivity extends AppCompatActivity {

    ImageView imgv_join_back;
    Button btn_join_address, btn_join;
    EditText edtv_join_email, edtv_join_pw, edtv_join_pwchk, edtv_join_name, edtv_join_nickname
            , edtv_join_phone, edtv_join_zipcode, edtv_join_address, edtv_join_address_more;
    TextView tv_email_chk, tv_pw_chk, tv_pw_chk_chk, tv_name_chk, tv_nickname_chk, tv_phone_chk;
    
    //두개 추가
    CircleImageView join_profile;
    AlertDialog dialog;
    private final int SEARCH_ADDR_CODE = 1002;


    //비밀번호 ** 처리 --> 완료
    //비밀번호와 비밀번호 확인 일치 --> 완료
    //유효성검사 --> 완료
    //값이 없을 때 입력하라는 토스트 띄우기 --> 완료

    //값이 없을 때 기본 문구로 뜨게
    //프로필 사진 추가
    //DB 데이터와 이메일 비교

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        imgv_join_back = findViewById(R.id.imgv_join_back);

        btn_join_address = findViewById(R.id.btn_join_address);
        btn_join_address = findViewById(R.id.btn_join_address);
        btn_join = findViewById(R.id.btn_join);

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



        //프로필 사진만 낑겨 넣음 jk
        checkDangerousPermissions();
        join_profile = findViewById(R.id.join_profile);
        join_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });



        //뒤로가기
        imgv_join_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //유효성 검사, 09.22 추가
        edtv_join_email.addTextChangedListener(textWatcher);
        edtv_join_pw.addTextChangedListener(textWatcher);
        edtv_join_pwchk.addTextChangedListener(textWatcher);
        edtv_join_name.addTextChangedListener(textWatcher);
        edtv_join_nickname.addTextChangedListener(textWatcher);
        edtv_join_phone.addTextChangedListener(textWatcher);


        //사진넣을려구 임시로 추가 - 지울예정 jk
        edtv_join_email.setText("jink12@naver.com");
        edtv_join_pw.setText("Wlsrud12");
        edtv_join_pwchk.setText("Wlsrud12");
        edtv_join_name.setText("파일");
        edtv_join_nickname.setText("실험중");
        edtv_join_phone.setText("010-1111-2222");



        //우편번호 서비스
        btn_join_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JoinActivity.this, AddressActivity.class);
                startActivityForResult(intent, SEARCH_ADDR_CODE);
            }
        });

        //회원가입 버튼 클릭 hs추가
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MemberVO vo = new MemberVO();
                vo.setEmail(edtv_join_email.getText().toString());
                vo.setPw(edtv_join_pw.getText().toString());
                vo.setName(edtv_join_name.getText().toString());
                vo.setNickname(edtv_join_nickname.getText().toString());
                //주소 추가 입력
                vo.setAddr(edtv_join_address.getText().toString() + "" + edtv_join_address_more.getText().toString());
                vo.setPhone(edtv_join_phone.getText().toString());
                vo.setPost(edtv_join_zipcode.getText().toString());
                vo.setManager("N");
                vo.setSocial("N");


               //데이터 파일받은
                RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpeg"), new File(imgFilePath)); //MediaType은 무슨타입인지 지정, 스트링형태의 파일패스
                MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", "profile.png", fileBody);

                //데이터를 보내는거
                RequestBody dataTest =
                        RequestBody.create(
                                MediaType.parse("multipart/form-data"), new Gson().toJson(vo));
                ApiInterface apiInterface = ApiClient.getApiclient().create(ApiInterface.class);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        apiInterface.sendTest( dataTest , filePart ).enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                Log.d("반응", "onResponse: " + response);
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
                    }
                }).start();


                emptyChk();

                Toast.makeText(JoinActivity.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //09.22 hs 추가
    public void emptyChk() {
        if(edtv_join_email.getText().length() == 0) {
            Toast.makeText(JoinActivity.this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show();
        }else if(edtv_join_pw.getText().length() == 0) {
            Toast.makeText(JoinActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
        }else if(edtv_join_pwchk.getText().length() == 0) {
            Toast.makeText(JoinActivity.this, "비밀번호 확인을 진행해주세요", Toast.LENGTH_SHORT).show();
        }else if(edtv_join_name.getText().length() == 0) {
            Toast.makeText(JoinActivity.this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
        }else if(edtv_join_phone.getText().length() == 0) {
            Toast.makeText(JoinActivity.this, "핸드폰 번호를 입력해주세요", Toast.LENGTH_SHORT).show();
        }else if(edtv_join_address.getText().length() == 0) {
            Toast.makeText(JoinActivity.this, "주소 입력해주세요", Toast.LENGTH_SHORT).show();
        }else{
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
                tv_email_chk.setText("⚠ 이메일 형식으로 입력하세요.");
                tv_email_chk.setTextColor(Color.parseColor("#FF0000"));
            }else if(Pattern.matches(email_chk, edtv_join_email.getText().toString())) {
                tv_email_chk.setText("✔ 올바른 형식의 이메일입니다.");
                tv_email_chk.setTextColor(Color.parseColor("#008EFF"));
            }

            //비밀번호
            if (!Pattern.matches(pw_chk, edtv_join_pw.getText().toString())) {
                tv_pw_chk.setVisibility(View.VISIBLE);
                tv_pw_chk.setText("⚠ 영어 대소문자, 숫자, 특수문자 최소 1개 이상만 가능합니다.");
                tv_pw_chk.setTextColor(Color.parseColor("#FF0000"));
            }else if(Pattern.matches(pw_chk, edtv_join_pw.getText().toString())) {
                tv_pw_chk.setText("✔ 사용 가능한 비밀번호입니다.");
                tv_pw_chk.setTextColor(Color.parseColor("#008EFF"));
            }

            //비밀번호 확인
            if(!Pattern.matches(edtv_join_pw.getText().toString(), edtv_join_pwchk.getText().toString())) {
                tv_pw_chk_chk.setVisibility(View.VISIBLE);
                tv_pw_chk_chk.setText("⚠ 비밀번호가 일치하지 않습니다.");
                tv_pw_chk_chk.setTextColor(Color.parseColor("#FF0000"));
            }else if(Pattern.matches(edtv_join_pw.getText().toString(), edtv_join_pwchk.getText().toString())) {
                tv_pw_chk_chk.setText("✔ 비밀번호가 일치합니다");
                tv_pw_chk_chk.setTextColor(Color.parseColor("#008EFF"));
            }

            //이름
            if(!Pattern.matches(name, edtv_join_name.getText().toString())) {
                tv_name_chk.setVisibility(View.VISIBLE);
                tv_name_chk.setText("⚠ 한글 두 글자 이상 세 글자 이하로 입력해주세요.");
                tv_name_chk.setTextColor(Color.parseColor("#FF0000"));
            }else if(Pattern.matches(name, edtv_join_name.getText().toString())) {
                tv_name_chk.setText("✔ 사용할 수 있는 이름입니다.");
                tv_name_chk.setTextColor(Color.parseColor("#008EFF"));
            }

            //닉네임
            if(!Pattern.matches(nickname, edtv_join_nickname.getText().toString())) {
                tv_nickname_chk.setVisibility(View.VISIBLE);
                tv_nickname_chk.setText("⚠ 한글 초성과 모음은 허락하지 않습니다.");
                tv_nickname_chk.setTextColor(Color.parseColor("#FF0000"));
            }else if(Pattern.matches(nickname, edtv_join_nickname.getText().toString())) {
                tv_nickname_chk.setText("✔ 사용 가능한 닉네임입니다.");
                tv_nickname_chk.setTextColor(Color.parseColor("#008EFF"));
            }


            //전화번호
            if (!Pattern.matches(phone, edtv_join_phone.getText().toString())) {
                tv_phone_chk.setVisibility(View.VISIBLE);
                tv_phone_chk.setText("⚠ 정확한 핸드폰 번호를 입력하세요.");
                tv_phone_chk.setTextColor(Color.parseColor("#FF0000"));
            }else if(Pattern.matches(phone, edtv_join_phone.getText().toString())) {
                tv_phone_chk.setText("✔ 올바른 형식의 번호입니다.");
                tv_phone_chk.setTextColor(Color.parseColor("#008EFF"));
            }
        }

    };



    //jk가 한거
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        dialog.dismiss();
        if(requestCode == LOAD_IMG && resultCode == RESULT_OK) {

            Log.d("갤러리", "onActivityResult: "+ data.getData());
            Log.d("갤러리", "onActivityResult: "+ data.getData().getPath());

            imgFilePath = getRealPath(data.getData());
            Glide.with(JoinActivity.this).load(imgFilePath).into(join_profile);


        }else if (requestCode == CAMERA_CODE && resultCode == RESULT_OK){

            Glide.with(JoinActivity.this).load(imgFilePath).into(join_profile); //붙여주는 처리
            RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpeg"), new File(imgFilePath));
            MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", "test.jpg", fileBody);
            ApiInterface apiInterface = ApiClient.getApiclient().create(ApiInterface.class);
            apiInterface.sendFile(filePart).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });
        }

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



    String[] dialog_item = {"카메라", "갤러리"};
    public final int LOAD_IMG = 1000;
    public final int CAMERA_CODE = 1001;

    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("선택하세요")
                .setSingleChoiceItems(dialog_item, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        if (dialog_item[i].equals("카메라")){
                            Log.d("다이얼로그", "onClick: 카메라 " + i);
                            Intent pickIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                            if(pickIntent.resolveActivity(getPackageManager()) !=null){
                                //임시파일
                                File file = createFile();
                                if(file !=null ){
                                    Uri imgUri = FileProvider.getUriForFile(getApplicationContext()
                                            ,getApplicationContext().getPackageName()+".fileprovider", file); //두번째는 인증
                                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { //API24
                                        pickIntent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
                                    }else {
                                        pickIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                                    }
                                }
                                startActivityForResult(pickIntent, CAMERA_CODE );
                            }



                        }else {
                            Log.d("다이얼로그", "onClick: 갤러리 " + i);
                            Intent intent = new Intent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_PICK);
                            startActivityForResult(
                                    Intent.createChooser(intent, "Select Picture"), LOAD_IMG
                            );
                        }
                    }
                });

        dialog = builder.create();
        dialog.show();

    }//showDialog


    public String imgFilePath;

    private File createFile() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName= "Pj03My" + timeStamp;
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File rtnFile = null;

        try {
            rtnFile = File.createTempFile( fileName, "jpg", storageDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imgFilePath = rtnFile.getAbsolutePath();
        return  rtnFile;
    }





    public String getRealPath(Uri contentUri) {
        String res = null;
        String[] project = {MediaStore.Images.Media.DATA} ;
        Cursor cursor = getContentResolver().query(contentUri, project, null, null, null);
        if(cursor.moveToFirst()){
            int colum_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(colum_index);

        }
        cursor.close();
        return res;
    }


    private void checkDangerousPermissions() {
        String[] permissions = {
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_MEDIA_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "권한 있음", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                Toast.makeText(this, "권한 설명 필요함.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, permissions, 1);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode ==1) {
            for(int i = 0; i<permissions.length; i++){
                if(grantResults[i] == PackageManager.PERMISSION_GRANTED){ //사용자가 승인한거
                    Log.d("TAG", "권한 승인 오퀘이 : " + permissions[i]);
                }else {
                    Log.d("TAG", "권한 승인 안됨 : " + permissions[i]);
                }
            }
        }

    }

}


