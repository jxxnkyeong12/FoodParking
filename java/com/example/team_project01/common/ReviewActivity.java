package com.example.team_project01.common;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
<<<<<<< HEAD:Team_Project01/app/src/main/java/com/example/team_project01/common/ReviewActivity.java


import androidx.fragment.app.Fragment;
=======
>>>>>>> 9cb002ba616cae74e12e5a50d2a549397d4d6f88:java/com/example/team_project01/common/ReviewActivity.java

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.team_project01.R;
import com.example.team_project01.conn.CommonAskTask;

public class ReviewActivity extends AppCompatActivity implements View.OnClickListener{

    //FrameLayout review_container;
    RatingBar rating_bar1, rating_bar2, rating_bar3, rating_bar4;
    EditText review_write;

<<<<<<< HEAD:Team_Project01/app/src/main/java/com/example/team_project01/common/ReviewActivity.java
=======
    AlertDialog dialog;

    private final int SEARCH_ADDR_CODE = 1002;


>>>>>>> 9cb002ba616cae74e12e5a50d2a549397d4d6f88:java/com/example/team_project01/common/ReviewActivity.java
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


    @Override
    public void onClick(View v) {

    }
}