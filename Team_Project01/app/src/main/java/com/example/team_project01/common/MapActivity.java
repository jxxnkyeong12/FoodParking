package com.example.team_project01.common;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.team_project01.R;
import com.example.team_project01.home.HomeFragment;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraAnimation;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapOptions;
import com.naver.maps.map.NaverMapSdk;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.util.FusedLocationSource;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {

    Toolbar map_toolbar;
    MapView map_View ;
    NaverMap naverMap;
    TextView map_text, map_btn;


    private double lat, lon;
    private FusedLocationSource locationSource;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private static final String[] PERMISSIONS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        map_btn = findViewById(R.id.map_btn);
        map_btn.setOnClickListener(this);

        map_toolbar = findViewById(R.id.map_toolbar);
        setSupportActionBar(map_toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //툴바 안보이게 설정
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        map_View = findViewById(R.id.map_view);
        map_text = findViewById(R.id.map_text);


        //임시로 키를 넣음! jk 2022/9/17
        NaverMapSdk.getInstance(this).setClient(
                new NaverMapSdk.NaverCloudPlatformClient("7jbtb9ge0p"));

        // 내 위치 jk - 2022/09/22
        map_View.getMapAsync(this);
        locationSource = new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);





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


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,  @NonNull int[] grantResults) {
        if (locationSource.onRequestPermissionsResult(
                requestCode, permissions, grantResults)) {

            return;
        }
        super.onRequestPermissionsResult( requestCode, permissions, grantResults);
    }





    //네이버 지도 API -jk 2022/09/19
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {

//        CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.1535583,126.8879957));
//        //d35.150075!4d126.8924309  - 지금 현재는 광주 서구 농성동 갈매기봉이 나옴! jk
//
//        naverMap.moveCamera(cameraUpdate);



        naverMap.setLocationSource(locationSource);  //현재 위치
        ActivityCompat.requestPermissions(this, PERMISSIONS, LOCATION_PERMISSION_REQUEST_CODE);  //현재위치 표시할때 권한 확인
        map_text.setText(locationSource.toString());


        naverMap.setLocationSource(locationSource);
        naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);



        naverMap.addOnLocationChangeListener(new NaverMap.OnLocationChangeListener() {
            @Override
            public void onLocationChange(@NonNull Location location) {
                lat = location.getLatitude();
                lon = location.getLongitude();



                Log.d("지도", "onLocationChange: " + lat + lon);

//                  마커 위치
//                Marker marker = new Marker();
//                marker.setPosition(new LatLng(lat, lon));
//                marker.setMap(naverMap);



                Toast.makeText(getApplicationContext(),  lat+" , "+lon, Toast.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.map_btn){

            Intent intent = new Intent(MapActivity.this, HomeFragment.class);
            intent.putExtra("주소", "map_text");
            startActivity(intent);
        }
    }
}