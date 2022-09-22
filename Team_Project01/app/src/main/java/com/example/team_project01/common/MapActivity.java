package com.example.team_project01.common;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.team_project01.R;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraAnimation;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapSdk;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    Toolbar map_toolbar;
    MapView map_View ;
    NaverMap naverMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        map_toolbar = findViewById(R.id.map_toolbar);
        setSupportActionBar(map_toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //툴바 안보이게 설정
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        map_View = findViewById(R.id.map_view);


        //임시로 키를 넣음! jk 2022/9/17
        NaverMapSdk.getInstance(this).setClient(
                new NaverMapSdk.NaverCloudPlatformClient("7jbtb9ge0p"));



    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    //네이버 지도 API -jk 2022/09/19
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.1535583,126.8879957));
        //d35.150075!4d126.8924309  - 지금 현재는 광주 서구 농성동 갈매기봉이 나옴! jk

        naverMap.moveCamera(cameraUpdate);



    }



}