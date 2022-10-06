package com.example.team_project01.common;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.team_project01.R;
import com.example.team_project01.conn.CommonAskTask;
import com.google.gson.Gson;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraAnimation;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapSdk;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.util.FusedLocationSource;



public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {


    MapView map_View;
    NaverMap naverMap;
    TextView map_text, map_btn;
    Marker marker = new Marker();
    LinearLayout layout_map_search;

    private double lat, lon;
    String con;

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

        layout_map_search = findViewById(R.id.layout_map_search);
        map_btn = findViewById(R.id.map_btn);
        map_btn.setOnClickListener(this);

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
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (locationSource.onRequestPermissionsResult(
                requestCode, permissions, grantResults)) {

            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }



    //네이버 지도 API -jk 2022/09/19
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;
        //가게 위치 잡기 - hs
        Intent mapintent = getIntent();
        int store_code = mapintent.getIntExtra("store_code", -1);

        if(store_code > 0) {
            CommonAskTask askTask = new CommonAskTask(MapActivity.this, "andStoreMap");
            askTask.addParams("store_code", store_code);
            askTask.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
                @Override
                public void onResult(String data, boolean isResult) {
                    Log.d("TAG", "onResult: " + data);
                    map_btn.setVisibility(View.GONE);
                    map_text.setVisibility(View.GONE);
                    layout_map_search.setVisibility(View.GONE);

                    AndMapVO vo = new Gson().fromJson(data, AndMapVO.class);
                    lat = vo.getStore_lat();
                    lon = vo.getStore_lon();

                    con = lat + "," + lon;
                    marker.setPosition(new LatLng(lat, lon));
                    marker.setMap(naverMap);

                    CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(lat, lon)).animate(CameraAnimation.Easing);
                    naverMap.moveCamera(cameraUpdate);

                    Log.d("TAG", "onResult: " + con);
                }

            });
        }else {
            naverMap.setLocationSource(locationSource);  //현재 위치
            ActivityCompat.requestPermissions(this, PERMISSIONS, LOCATION_PERMISSION_REQUEST_CODE);  //현재위치 표시할때 권한 확인
            naverMap.setLocationSource(locationSource);
            naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);

            //내 위치 잡아내는
            naverMap.addOnLocationChangeListener(new NaverMap.OnLocationChangeListener() {
                @Override
                public void onLocationChange(@NonNull Location location) {

                    lat = location.getLatitude();
                    lon = location.getLongitude();

                    con = lat + "," + lon;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    }).start();

                    Log.d("TAG", "onLocationChange: " + lat + lon);
                    marker.setPosition(new LatLng(lat, lon));
                    marker.setMap(naverMap);
                    //  Toast.makeText(getApplicationContext(),  lat+" , "+lon, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.map_btn) {
            onBackPressed();
            //Intent intent = new Intent(MapActivity.this, MainActivity.class);
            //intent.putExtra("my_location", "map_text");
            //startActivity(intent);
        }
    }

 
}