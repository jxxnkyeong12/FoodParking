package com.example.team_project01.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.team_project01.R;
import com.example.team_project01.common.BasketActivity;
import com.example.team_project01.common.MapActivity;
import com.example.team_project01.login.JoinActivity;
import com.example.team_project01.login.LoginActivity;
import com.example.team_project01.login.LoginSocialActivity;
import com.example.team_project01.store.StoreActivity;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ImageView imgv_test, home_basket;
    TextView tv_home_address;
    ViewPager2 pager2;
    SpringDotsIndicator indicator;

    ArrayList<Integer> img_list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        imgv_test = v.findViewById(R.id.imgv_test);
        tv_home_address = v.findViewById(R.id.tv_home_address);
        pager2 = v.findViewById(R.id.pager2);
        indicator = v.findViewById(R.id.indicator);
        home_basket = v.findViewById(R.id.home_basket);

        img_list.add(R.drawable.banner1);
        img_list.add(R.drawable.banner2);
        img_list.add(R.drawable.banner3);
        img_list.add(R.drawable.banner4);
        img_list.add(R.drawable.banner5);

        Pager2Adapter adapter = new Pager2Adapter(inflater, img_list);
        pager2.setAdapter(adapter);
        //pager2.setPageTransformer(new ZoomOutPageTransformer());

        indicator.setViewPager2(pager2);

        //autoSlide();

        tv_home_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MapActivity.class);
                startActivity(intent);
            }
        });

        /* 임시로 연결 삭제 예정 */
        imgv_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginSocialActivity.class);
                startActivity(intent);
            }
        });

        home_basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), BasketActivity.class);
                startActivity(intent);
            }
        });


        return v;
    }



    public void autoSlide() {
        //new Handler : SplashActivity 페이지 전환할 때 사용
        //runOnUiThread : 페이지 내부에서 디자인이 바뀌는 용으로, 쓰레드 사용 시 활용, Activity에서만 접근 가능
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    for (int i = 0; i < img_list.size(); i++) {
                        final int value = i;
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //비동기로 디자인작업
                                pager2.setCurrentItem(value); //인터페이스 중첩, 부모 클래스이 변수를 사용하려면 final로 선언
                            }
                        });

                        try {
                            Thread.sleep(1000);
                            //if(i == img_list.size() - 1) i = -1; //이 방법도 무한반복
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    public class ZoomOutPageTransformer implements ViewPager2.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0f);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0f);
            }
        }
    }

}