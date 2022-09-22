package com.example.team_project01.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.team_project01.R;
import com.google.android.material.snackbar.Snackbar;

public class JoinActivity extends AppCompatActivity {

    Button btn_join_address;
    EditText edtv_join_zipcode, edtv_join_address;

    private final int SEARCH_ADDR_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        btn_join_address = findViewById(R.id.btn_join_address);
        edtv_join_zipcode = findViewById(R.id.edtv_join_zipcode);
        edtv_join_address = findViewById(R.id.edtv_join_address);

        edtv_join_zipcode.setEnabled(false);

        btn_join_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JoinActivity.this, AddressActivity.class);
                startActivityForResult(intent, SEARCH_ADDR_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SEARCH_ADDR_CODE){
            Log.d("TAG", "processDATA: " + data);

            String addr = data.getExtras().getString("addr");
            String post = data.getExtras().getString("post");
            if(data != null){
                edtv_join_address.setText(addr);
                edtv_join_zipcode.setText(post);
            }
        }
    }
}