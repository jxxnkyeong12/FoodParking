package com.example.team_project01.order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.team_project01.R;
import com.example.team_project01.common.BasketVO;

import java.util.ArrayList;

public class BillActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView bill_cash, bill_pay, bill_card;
    TextView bill_price, bill_price2, bill_payment;
    int cho_num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        bill_cash = findViewById(R.id.bill_cash);
        bill_pay = findViewById(R.id.bill_pay);
        bill_card = findViewById(R.id.bill_card);
        bill_price = findViewById(R.id.bill_price);
        bill_price2 = findViewById(R.id.bill_price2);
        bill_payment = findViewById(R.id.bill_payment);

        Intent intent = getIntent();
        Order_infoVO vo = (Order_infoVO) intent.getSerializableExtra("vo");
        ArrayList<BasketVO> list = (ArrayList<BasketVO>) intent.getSerializableExtra("list");
        String store_name = intent.getStringExtra("store_name");

        bill_price.setText( "+" + vo.getPrice());
        bill_price2.setText("-" + vo.getPrice());
        bill_cash.setOnClickListener(this);
        bill_pay.setOnClickListener(this);
        bill_card.setOnClickListener(this);


        //결제하기 버튼 클릭시
        bill_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(BillActivity.this, vo.getPrice() + "이 정상적으로 결제가 왼료 되었습니다", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(BillActivity.this, OrderDetailActivity.class);
                intent1.putExtra("vo", vo);
                intent1.putExtra("list", list);
                intent1.putExtra("store_name", store_name);

                startActivity(intent1);
            }
        });

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bill_cash){
            Toast.makeText(BillActivity.this, "현금으로 결제 하겠습니다", Toast.LENGTH_SHORT).show();
            cho_num = 1;
        }else if (v.getId() == R.id.bill_pay){
            Toast.makeText(BillActivity.this, "페이로 결제 하겠습니다", Toast.LENGTH_SHORT).show();
            cho_num = 2;
        }else if(v.getId() == R.id.bill_card){
            Toast.makeText(BillActivity.this, "카드 결제 하겠습니다", Toast.LENGTH_SHORT).show();
            cho_num = 3;
        }

    }
}