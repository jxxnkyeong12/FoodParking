package com.example.team_project01.common;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.L;
import com.example.team_project01.MainActivity;
import com.example.team_project01.R;
import com.example.team_project01.conn.CommonAskTask;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraAnimation;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapSdk;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.util.FusedLocationSource;

import java.io.IOException;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    MapView map_View;
    NaverMap naverMap;
    TextView map_text, map_btn;
    FloatingActionButton fab_tracking;

    Marker marker = new Marker();

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
        map_View = findViewById(R.id.map_view);
        map_text = findViewById(R.id.map_text);
        fab_tracking = findViewById(R.id.fab_tracking);

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
        //가게 위치 잡기위한 변수 추가 - hs
        Intent mapintent = getIntent();
        int store_code = mapintent.getIntExtra("store_code", -1);

        marker.setIcon(OverlayImage.fromResource(R.drawable.icon_map_marker));
        marker.setWidth(120);
        marker.setHeight(120);

        if(store_code > 0) {  //가게 위치 불러오기 - hs 2022/10/06
            CommonAskTask askTask = new CommonAskTask(MapActivity.this, "andStoreMap");
            askTask.addParams("store_code", store_code);
            askTask.excuteAsk(new CommonAskTask.AsynckTaskCallBack() {
                @Override
                public void onResult(String data, boolean isResult) {
                    map_btn.setVisibility(View.GONE);
                    map_text.setVisibility(View.GONE);

                    AndMapVO vo = new Gson().fromJson(data, AndMapVO.class);
                    lat = vo.getStore_lat();
                    lon = vo.getStore_lon();

                    marker.setPosition(new LatLng(lat, lon));
                    marker.setMap(naverMap);

                    //CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(lat, lon)).animate(CameraAnimation.Easing);
                    //naverMap.moveCamera(cameraUpdate);

                    CameraUpdate cameraUpdate = CameraUpdate.scrollAndZoomTo(new LatLng(lat, lon),17).animate(CameraAnimation.Fly, 3000);
                    naverMap.moveCamera(cameraUpdate);
                }
            });
        }else { //내 위치 수정
            naverMap.setLocationSource(locationSource);  //현재 위치
            ActivityCompat.requestPermissions(this, PERMISSIONS, LOCATION_PERMISSION_REQUEST_CODE);  //현재위치 표시할때 권한 확인
            naverMap.setLocationSource(locationSource);
            naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);

            //마커 고정 - hs 2022/10/06
            marker.setPosition(new LatLng(naverMap.getCameraPosition().target.latitude, naverMap.getCameraPosition().target.longitude));
            marker.setIcon(OverlayImage.fromResource(R.drawable.icon_map_marker));
            marker.setMap(naverMap);

            //지도 이동 중일 때 - hs 2022/10/06
            naverMap.addOnCameraChangeListener(new NaverMap.OnCameraChangeListener() {
                @Override
                public void onCameraChange(int i, boolean b) {
                    marker.setPosition(new LatLng(naverMap.getCameraPosition().target.latitude, naverMap.getCameraPosition().target.longitude));
                    map_text.setText("위치 이동 중");
                    map_text.setTextColor(Color.parseColor("#c4c4c4"));
                    map_btn.setEnabled(false);
                    map_btn.setBackgroundColor(Color.parseColor("#898989"));
                }
            });

            //지도 이동 멈출 때 - hs 2022/10/06
            naverMap.addOnCameraIdleListener(new NaverMap.OnCameraIdleListener() {
                @Override
                public void onCameraIdle() {
                    lat = naverMap.getCameraPosition().target.latitude;
                    lon = naverMap.getCameraPosition().target.longitude;

                    marker.setPosition(new LatLng(lat, lon));
                    map_text.setText(getAddress(lat, lon));
                    map_text.setTextColor(Color.parseColor("#2d2d2d"));
                    map_btn.setBackgroundColor(Color.parseColor("#F25C05"));
                    map_btn.setEnabled(true);
                }
            });

            //현재 위치 찾기 - jk, hs수정
            fab_tracking.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    naverMap.addOnLocationChangeListener(new NaverMap.OnLocationChangeListener() {
                        @Override
                        public void onLocationChange(@NonNull Location location) {
                            lat = location.getLatitude();
                            lon = location.getLongitude();

                            new Thread(new Runnable() {
                                @Override
                                public void run() {

                                }
                            }).start();
                            marker.setPosition(new LatLng(lat, lon));

                            CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(lat, lon)).animate(CameraAnimation.Easing);
                            naverMap.moveCamera(cameraUpdate);
                        }
                    });
                }
            });

            map_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String addr = getAddress(lat, lon);
                    Intent intent = new Intent(MapActivity.this, MainActivity.class);
                    intent.putExtra("addr", addr);

                    startActivity(intent);
                }
            });
        }
    }

    //주소 변환  - hs 2022/10/06
    public String getAddress(double lat, double lon) {
        Geocoder geo = new Geocoder(MapActivity.this);
        List<Address> list = null;
        try {
            list = geo.getFromLocation(lat, lon, 2);

        } catch (IOException e) {
            e.printStackTrace();
        }

        String addr = list.get(0).getAddressLine(0);
        addr = addr.replace("대한민국", ""); //대한민국 글자 제거
        map_text.setText(addr);

        return addr;
    }
}