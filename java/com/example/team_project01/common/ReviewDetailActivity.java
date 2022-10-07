package com.example.team_project01.common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.team_project01.R;
import com.example.team_project01.conn.CommonAskTask;
import com.google.gson.Gson;

import org.w3c.dom.Text;

public class ReviewDetailActivity extends AppCompatActivity implements View.OnClickListener{

    RatingBar rating_dt1, rating_dt2, rating_dt3, rating_dt4;
    EditText review_dt_write;
    ImageView review_dt_imgv;
    Button rv_dt_update, rv_dt_cancle;
    Intent intent;
    TextView rvdt_store_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_detail);

        rating_dt1 = findViewById(R.id.rating_dt1);
        rating_dt2 = findViewById(R.id.rating_dt2);
        rating_dt3 = findViewById(R.id.rating_dt3);
        rating_dt4 = findViewById(R.id.rating_dt4);
        review_dt_write = findViewById(R.id.review_dt_write);
        review_dt_imgv = findViewById(R.id.review_dt_imgv);
        rv_dt_update = findViewById(R.id.rv_dt_update);
        rv_dt_cancle = findViewById(R.id.rv_dt_cancle);
        rvdt_store_name = findViewById(R.id.rvdt_store_name);


        rv_dt_cancle.setOnClickListener(this);
        rv_dt_update.setOnClickListener(this);


         intent = getIntent();
         int store_adater = intent.getIntExtra("store_adater", -1);
         int star_code = intent.getIntExtra("star_code", -1);
       if( store_adater == 1 ){
            int id = intent.getIntExtra("id", -1);
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

/*


          rating_dt1.setRating(CommonVal.reviewdetail.getTaste());
          rating_dt2.setRating(CommonVal.reviewdetail.getMood());
          rating_dt3.setRating(CommonVal.reviewdetail.getKind());
          rating_dt4.setRating(CommonVal.reviewdetail.getClean());*/

        /*  //작성글 없거나, 사진이 없다면 기본화면 보여주기
          if(CommonVal.reviewdetail.getReview_content() == null){
              review_dt_write.setText(review_dt_write.getText().toString());
          }else if( CommonVal.reviewdetail.getReview_image() == null) {
            Glide.with(ReviewDetailActivity.this).load(R.drawable.camera).into(review_dt_imgv);
          }else {
             review_dt_write.setText(CommonVal.reviewdetail.getReview_content());
             Glide.with(ReviewDetailActivity.this).load(CommonVal.reviewdetail.getReview_image()).into(review_dt_imgv);
          }
*/


    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.rv_dt_cancle){
            onBackPressed();

            Log.d("취소", "onClick: ");
        }else if (v.getId() == R.id.rv_dt_update){


            ReviewVO vo = new ReviewVO();
            vo.setStore_code(CommonVal.reviewdetail.getStore_code());
            vo.setStar_code(CommonVal.reviewdetail.getStore_code());
            vo.setTaste(rating_dt1.getRating());
            vo.setMood(rating_dt1.getRating());
            vo.setKind(rating_dt1.getRating());
            vo.setClean(rating_dt1.getRating());
            vo.setWritedate(CommonVal.reviewdetail.getWritedate());
            vo.setId(CommonVal.loginInfo.getId());


            float star = vo.getTaste() + vo.getMood() + vo.getKind() + vo.getClean();
            int na = 4;
            float v1 = star / na;

            vo.setStar_rating(v1);
            vo.setReview_content(review_dt_write.getText().toString());
            if(vo.getReview_image() != null){
                vo.setReview_image(CommonVal.reviewdetail.getReview_image());

            }


            //update 발사 jk 
            CommonAskTask askTask = new CommonAskTask(ReviewDetailActivity.this, "andReviewUpdate");
            askTask.addParams("vo",new Gson().toJson(vo));
            askTask.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
                @Override
                public void onResult(String data, boolean isResult) {
                    Log.d("업데이트잘갔니", "onResult:" +data);
                        CommonVal.reviewdetail = new Gson().fromJson(data, ReviewVO.class);
                        intent = new Intent(ReviewDetailActivity.this,ReviewListActivity.class);
                        intent.putExtra("update", 1);
                        startActivity(intent);




                }
            });
        }
    }
}