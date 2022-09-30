package com.example.team_project01.myinfo;

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
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.team_project01.MainActivity;
import com.example.team_project01.R;
import com.example.team_project01.common.CommonVal;
import com.example.team_project01.conn.ApiClient;
import com.example.team_project01.conn.ApiInterface;
import com.example.team_project01.conn.CommonAskTask;
import com.example.team_project01.conn.CommonConn;
import com.example.team_project01.login.AddressActivity;
import com.example.team_project01.login.JoinActivity;
import com.example.team_project01.login.LoginActivity;
import com.example.team_project01.login.MemberVO;

import com.google.android.gms.common.internal.service.Common;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView modfiy_back;
    Button btn_modify, btn_modify_address;
    EditText modify_nick, modify_phone, modify_pw, modify_pwck,
            edtv_modify_zipcode, edtv_modify_address, edtv_modify_address_more;
    CircleImageView modify_image;
    AlertDialog dialog;
    private final int SEARCH_ADDR_CODE = 1002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);


        checkDangerousPermissions();

        modfiy_back = findViewById(R.id.modfiy_back);
        btn_modify = findViewById(R.id.btn_modify);
        btn_modify_address = findViewById(R.id.btn_modify_address);
        modify_nick = findViewById(R.id.modify_nick);
        modify_image = findViewById(R.id.modify_image);
        modify_phone = findViewById(R.id.modify_phone);
        modify_pw = findViewById(R.id.modify_pw);
        modify_pwck = findViewById(R.id.moidfy_pwck);
        edtv_modify_zipcode = findViewById(R.id.edtv_modify_zipcode);
        edtv_modify_address = findViewById(R.id.edtv_modify_address);
        edtv_modify_address_more = findViewById(R.id.edtv_modify_address_more);


        modify_image.setOnClickListener(this);

        //내정보 그대로 가져오기
        modify_nick.setText(CommonVal.loginInfo.getNickname());
        modify_pw.setText(CommonVal.loginInfo.getPw());
        modify_pwck.setText(CommonVal.loginInfo.getPw());
        modify_phone.setText(CommonVal.loginInfo.getPhone());
        edtv_modify_zipcode.setText(CommonVal.loginInfo.getPost());
        edtv_modify_address.setText(CommonVal.loginInfo.getAddr());
        edtv_modify_address_more.setText(CommonVal.loginInfo.getAddr_more());
        if(CommonVal.loginInfo.getProfile_image() !=null){
             Glide.with(ModifyActivity.this).load(CommonVal.loginInfo.getProfile_image()).into(modify_image);
        }else {
            Glide.with(ModifyActivity.this).load(R.drawable.profile_image).into(modify_image);
        }


        //우편번호 서비스
        btn_modify_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModifyActivity.this, AddressActivity.class);
                startActivityForResult(intent, SEARCH_ADDR_CODE);
            }
        });


        //뒤로가기
        modfiy_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //수정버튼을 눌렀을때
        btn_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemberVO vo = new MemberVO();
                vo.setEmail(CommonVal.loginInfo.getEmail());
                vo.setNickname(modify_nick.getText()+"");
                vo.setPhone(modify_phone.getText()+"");
                vo.setPw(modify_pw.getText()+"");
                vo.setAddr(edtv_modify_address.getText().toString());
                vo.setPost(edtv_modify_zipcode.getText().toString());
                vo.setAddr_more(edtv_modify_address_more.getText().toString());
                vo.setProfile_image(CommonVal.loginInfo.getProfile_image());
                Glide.with(ModifyActivity.this).load(CommonVal.loginInfo.getProfile_image()).into(modify_image);


              if(!Pattern.matches(modify_pw.getText().toString(), modify_pwck.getText().toString())){
                  Toast.makeText(ModifyActivity.this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();

              }else {

                  int test = 0;
                  Intent intent = new Intent(ModifyActivity.this, MainActivity.class);
                  intent.putExtra("nickname", vo.getNickname());
                  intent.putExtra("test", 0);
                  startActivity(intent);
         /*        Bundle bundle = new Bundle();
                 bundle.putString("nickname", vo.getNickname());
              *//*   bundle.putString("pw", vo.getPw().toString());
                 bundle.putString("phone", vo.getPhone().toString());*//*

                 MyinfoFragment myinfoFragment= new MyinfoFragment();
                 myinfoFragment.setArguments(bundle);*/



              }



                //데이터 파일받은
                if(imgFilePath !=null) {


                RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpeg"), new File(imgFilePath)); //MediaType은 무슨타입인지 지정, 스트링형태의 파일패스
                MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", "profile.png", fileBody);

                //데이터를 보내는거
                RequestBody dataTest = RequestBody.create( MediaType.parse("multipart/form-data"), new Gson().toJson(vo));
                ApiInterface apiInterface = ApiClient.getApiclient().create(ApiInterface.class);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        apiInterface.sendModify( dataTest , filePart ).enqueue(new Callback<String>() {
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
                }else {

                    CommonAskTask task = new CommonAskTask(ModifyActivity.this, "andModify");

                    task.addParams("vo", new Gson().toJson(vo));
                    task.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
                        @Override
                        public void onResult(String data, boolean isResult) {

                            Log.d("회원가입", "onResult: " + data);


                        }
                    });
                }
            }
        });
    }


    
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.modify_image){
            showDialog();
        }
    }



    //jk가 한거
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

     //   dialog.dismiss();
        if(requestCode == LOAD_IMG && resultCode == RESULT_OK) {

            Log.d("갤러리", "onActivityResult: "+ data.getData());
            Log.d("갤러리", "onActivityResult: "+ data.getData().getPath());

            imgFilePath = getRealPath(data.getData());
            Glide.with(ModifyActivity.this).load(imgFilePath).into(modify_image);


        }else if (requestCode == CAMERA_CODE && resultCode == RESULT_OK){

            Glide.with(ModifyActivity.this).load(imgFilePath).into(modify_image); //붙여주는 처리
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
                edtv_modify_address.setText(addr);
                edtv_modify_zipcode.setText(post);
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