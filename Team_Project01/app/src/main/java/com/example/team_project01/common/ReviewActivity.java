package com.example.team_project01.common;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.team_project01.R;
import com.example.team_project01.conn.CommonAskTask;

public class ReviewActivity extends AppCompatActivity {

    //FrameLayout review_container;
    RatingBar rating_bar1, rating_bar2, rating_bar3, rating_bar4;
    EditText review_write;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        review_write = findViewById(R.id.review_write);
        rating_bar1 = findViewById(R.id.rating_bar1);
        rating_bar2 = findViewById(R.id.rating_bar2);
        rating_bar3 = findViewById(R.id.rating_bar3);
        rating_bar4 = findViewById(R.id.rating_bar4);
      //  review_container = findViewById(R.id.review_container);


        review_write.setText(review_write.getText().toString());
        CommonAskTask askTask = new CommonAskTask(ReviewActivity.this, "andReview");
        //conn.addParams("content", );

    }
}