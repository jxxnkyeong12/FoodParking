package com.example.team_project01.common;

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
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.team_project01.MainActivity;
import com.example.team_project01.R;
import com.example.team_project01.conn.ApiClient;
import com.example.team_project01.conn.ApiInterface;
import com.example.team_project01.conn.CommonAskTask;
import com.example.team_project01.login.MemberVO;
import com.example.team_project01.myinfo.ModifyActivity;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewDetailActivity extends AppCompatActivity implements View.OnClickListener{

    RatingBar rating_dt1, rating_dt2, rating_dt3, rating_dt4;
    EditText review_dt_write;
    ImageView review_dt_imgv;
    Button rv_dt_update, rv_dt_cancle;
    Intent intent;
    TextView rvdt_store_name;
    AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_detail);


        checkDangerousPermissions();

        rating_dt1 = findViewById(R.id.rating_dt1);
        rating_dt2 = findViewById(R.id.rating_dt2);
        rating_dt3 = findViewById(R.id.rating_dt3);
        rating_dt4 = findViewById(R.id.rating_dt4);
        review_dt_write = findViewById(R.id.review_dt_write);
        review_dt_imgv = findViewById(R.id.review_dt_imgv);
        rv_dt_update = findViewById(R.id.review_dt_update);
        rv_dt_cancle = findViewById(R.id.rview_dt_cancle);
        rvdt_store_name = findViewById(R.id.rvdt_store_name);


        rv_dt_cancle.setOnClickListener(this);
        rv_dt_update.setOnClickListener(this);
        review_dt_imgv.setOnClickListener(this);


         intent = getIntent();
         int store_adater = intent.getIntExtra("store_adater", -1);
         int star_code = intent.getIntExtra("star_code", -1);
       if( store_adater == 1 ){
          //  int id = intent.getIntExtra("id", -1);
            float taste = intent.getFloatExtra("taste", -1);
            float mood = intent.getFloatExtra("mood", -1);
            float kind = intent.getFloatExtra("kind", -1);
            float clean = intent.getFloatExtra("clean", -1);
            String write = intent.getStringExtra("review_content");
            String image = intent.getStringExtra("image");

            rating_dt1.setRating(taste);
            rating_dt2.setRating(mood);
            rating_dt3.setRating(kind);
            rating_dt4.setRating(clean);
            review_dt_write.setText(write);


           if (image == null){
               Glide.with(ReviewDetailActivity.this).load(R.drawable.camera).into(review_dt_imgv);
           }

       }else if (star_code > 0){
           CommonAskTask askTask = new CommonAskTask(ReviewDetailActivity.this, "andReviewDetail");
           askTask.addParams("star_code", star_code);
           askTask.addParams("id", CommonVal.loginInfo.getId());
           askTask.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
               @Override
               public void onResult(String data, boolean isResult) {
                   if(data != null){
                       Log.d("데이타", "onResult: " + data);
                       ReviewVO vo = new Gson().fromJson(data, ReviewVO.class);
                       CommonVal.reviewdetail = vo;
                      rvdt_store_name.setText(vo.getStore_name());
                      rating_dt1.setRating(vo.getTaste());
                      rating_dt2.setRating(vo.getMood());
                      rating_dt3.setRating(vo.getKind());
                      rating_dt4.setRating(vo.getClean());
                      review_dt_write.setText(vo.getReview_content());

                     if(vo.getReview_image() == null){
                         Glide.with(ReviewDetailActivity.this).load(R.drawable.camera).into(review_dt_imgv);
                     }else {
                         Glide.with(ReviewDetailActivity.this).load(vo.getReview_image()).into(review_dt_imgv);

                     }

                   }

               }
           });
       }




    }

    //버튼 클릭했을때
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.review_dt_imgv){
            showDialog();
        }else if(v.getId() == R.id.rview_dt_cancle){
            onBackPressed();

            Log.d("취소", "onClick: ");
        }else if (v.getId() == R.id.review_dt_update){

            ReviewVO vo = new ReviewVO();
          
            vo.setStore_code(CommonVal.reviewdetail.getStore_code());
            vo.setStar_code(CommonVal.reviewdetail.getStar_code());
            vo.setTaste(rating_dt1.getRating());
            vo.setMood(rating_dt1.getRating());
            vo.setKind(rating_dt1.getRating());
            vo.setClean(rating_dt1.getRating());
            vo.setReview_content(review_dt_write.getText().toString());
            vo.setId(CommonVal.loginInfo.getId());



            float star = vo.getTaste() + vo.getMood() + vo.getKind() + vo.getClean();
            int na = 4;
            float v1 = star / na;

            vo.setStar_rating(v1);

            vo.setReview_image(CommonVal.reviewdetail.getReview_image());
            if(imgFilePath == null){
                vo.setReview_image("");
            }else {
             Glide.with(ReviewDetailActivity.this).load(CommonVal.reviewdetail.getReview_image()).into(review_dt_imgv);

            }






            //파일 업로드 해주는
            if(imgFilePath != null){
                filesubmit(vo);
            }else {
                nofilesubmit(vo);
            }



        }


    }


    //파일과 함께 보내는 메소드
    public void filesubmit(ReviewVO vo) {
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpeg"), new File(imgFilePath)); //MediaType은 무슨타입인지 지정, 스트링형태의 파일패스
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", "rdupdate.png", fileBody);

        //데이터를 보내는거
        RequestBody data = RequestBody.create( MediaType.parse("multipart/form-data"), new Gson().toJson(vo));
        ApiInterface apiInterface = ApiClient.getApiclient().create(ApiInterface.class);
        new Thread(new Runnable() {
            @Override
            public void run() {
                apiInterface.sendReviewDetailUpdate( data , filePart ).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        Log.d("반응", "onResponse: " + response);
                        Intent intent = new Intent(ReviewDetailActivity.this, MainActivity.class);
                        intent.putExtra("update", 1);
                        startActivity(intent);

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
        }).start();
    }


    //파일없이 다른 정보 변경만 보내는 메소드
    public void nofilesubmit(ReviewVO vo){
        CommonAskTask task = new CommonAskTask(ReviewDetailActivity.this, "andReviewUpdateNofile");
        task.addParams("vo", new Gson().toJson(vo));
        task.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
            @Override
            public void onResult(String data, boolean isResult) {
                Log.d("수정성공", "onResult: " + data);

                CommonVal.reviewdetail =  new Gson().fromJson(data, ReviewVO.class);

                Intent intent = new Intent(ReviewDetailActivity.this, ReviewListActivity.class);
                intent.putExtra("update", 1);
                startActivity(intent);
                finish(); //끝냈는데 왜 뒤로가했을때 나오지...?
            }


        });
    }


    //jk가 한거
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /*if(dialog_item != null){
           dialog.dismiss();

        }*/

        if(requestCode == LOAD_IMG && resultCode == RESULT_OK) {

            Log.d("갤러리", "onActivityResult: "+ data.getData());
            Log.d("갤러리", "onActivityResult: "+ data.getData().getPath());

            imgFilePath = getRealPath(data.getData());
            Glide.with(ReviewDetailActivity.this).load(imgFilePath).into(review_dt_imgv);


        }else if (requestCode == CAMERA_CODE && resultCode == RESULT_OK){

            Glide.with(ReviewDetailActivity.this).load(imgFilePath).into(review_dt_imgv); //붙여주는 처리
            RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpeg"), new File(imgFilePath));
            MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", "rdupdate.png", fileBody);
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



    }

    String[] dialog_item = {"카메라", "갤러리", "기본이미지"};
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
                            CommonVal.loginInfo.setProfile_image(imgFilePath);
                        }else if(dialog_item[i].equals("갤러리")) {
                            Log.d("다이얼로그", "onClick: 갤러리 " + i);
                            Intent intent = new Intent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_PICK);
                            startActivityForResult(
                                    Intent.createChooser(intent, "Select Picture"), LOAD_IMG
                            );
                        }else if (dialog_item[i].equals("기본이미지")){
                            Glide.with(ReviewDetailActivity.this).load(R.drawable.camera).into(review_dt_imgv);
                            imgFilePath = null;

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