package com.example.team_project01.common;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.team_project01.R;
import com.example.team_project01.home.HomeFragment;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {

<<<<<<< HEAD
    MapView map_View;
    NaverMap naverMap;
    TextView map_text, map_btn;
    ImageView map_back;

=======

    MapView map_View ;
  private NaverMap naverMap;
    TextView map_text, map_btn;

    Marker marker = new Marker();
>>>>>>> 65a2ed0ce81c63afee67c195a7b646fc36702252
    private double lat, lon;
    String area1, area2,area3,area4, con;
    private FusedLocationSource locationSource;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private static final String[] PERMISSIONS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

<<<<<<< HEAD
=======



>>>>>>> 65a2ed0ce81c63afee67c195a7b646fc36702252
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        map_btn = findViewById(R.id.map_btn);
<<<<<<< HEAD
        map_back = findViewById(R.id.map_back);
=======
        map_btn.setOnClickListener(this);

>>>>>>> 65a2ed0ce81c63afee67c195a7b646fc36702252

        map_View = findViewById(R.id.map_view);
        map_text = findViewById(R.id.map_text);

        //임시로 키를 넣음! jk 2022/9/17
        NaverMapSdk.getInstance(this).setClient(
                new NaverMapSdk.NaverCloudPlatformClient("7jbtb9ge0p"));

        // 내 위치 jk - 2022/09/22
        map_View.getMapAsync(this);
        locationSource = new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);
<<<<<<< HEAD
=======





    }

//"https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc?coords={입력_좌표}
// &sourcecrs={좌표계}
// &orders={변환_작업_이름}
// &output={출력_형식}" \


    public void requestGeocode(String con) {
       // con = 35.1535583+" , " + 126.8879957 ;

        try {
            BufferedReader bufferedReader;
            StringBuilder stringBuilder = new StringBuilder();
            //여기가 변경되게 해야 하는데.. 왜 안되지...?

            String query = "https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc?request=coordsToaddr&coords="
                    + con + "&sourcecrs=epsg:4326&output=json&orders=roadaddr&output=xml";
            URL url = new URL(query);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if (conn != null) {
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);
                conn.setRequestMethod("GET");
                conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", "7jbtb9ge0p");
                conn.setRequestProperty("X-NCP-APIGW-API-KEY", "2bqUlTSNPzdiWxAXBZzPJCggKxdhtBNn4JFPyskd");
                conn.setDoInput(true);

                int responseCode = conn.getResponseCode();

                if (responseCode == 200) {
                    bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                } else {
                    bufferedReader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                }

                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                    Log.d("되니", "requestGeocode: " + line);
                    JSONObject json = new JSONObject(line);
                    JSONArray resultArray = json.getJSONArray("results");

                    JSONObject json1 = resultArray.getJSONObject(0);

                    JSONObject dataObject = (JSONObject) json1.get("region");
                    JSONObject area1Object =(JSONObject) dataObject.get("area1");
                    JSONObject area2Object =(JSONObject) dataObject.get("area2");
                    JSONObject area3Object =(JSONObject) dataObject.get("area3");
                    JSONObject area4Object =(JSONObject) dataObject.get("area4");

                    Log.d("지도", "area1 name : " + area1Object.getString("name") + area2Object.getString("name") + area3Object.getString("name") + area4Object.getString("name"));

                    area1 = area1Object.getString("name");
                    area2 = area2Object.getString("name");
                    area3 = area3Object.getString("name");
                    area4 = area4Object.getString("name");

                    map_text.setText(area1 + " " + area2 + " " + area3 + " " + area4);

                }


                Gson gson = new Gson();
                Address address = gson.fromJson(String.valueOf(stringBuilder), Address.class);

                bufferedReader.close();
                conn.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
>>>>>>> 65a2ed0ce81c63afee67c195a7b646fc36702252
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

<<<<<<< HEAD
//        CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.1535583,126.8879957));
//        //d35.150075!4d126.8924309  - 지금 현재는 광주 서구 농성동 갈매기봉이 나옴! jk
//
//        naverMap.moveCamera(cameraUpdate);
=======
>>>>>>> 65a2ed0ce81c63afee67c195a7b646fc36702252

        naverMap.setLocationSource(locationSource);  //현재 위치
        ActivityCompat.requestPermissions(this, PERMISSIONS, LOCATION_PERMISSION_REQUEST_CODE);  //현재위치 표시할때 권한 확인

        naverMap.setLocationSource(locationSource);
        naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);

<<<<<<< HEAD
=======
        //내 위치 잡아내는
>>>>>>> 65a2ed0ce81c63afee67c195a7b646fc36702252
        naverMap.addOnLocationChangeListener(new NaverMap.OnLocationChangeListener() {
            @Override
            public void onLocationChange(@NonNull Location location) {
                lat = location.getLatitude();
                lon = location.getLongitude();
<<<<<<< HEAD
=======
                con = lat + "," + lon;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        requestGeocode(con);
                    }
                }).start();

>>>>>>> 65a2ed0ce81c63afee67c195a7b646fc36702252

                Log.d("지도", "onLocationChange: " + lat + lon);


                marker.setPosition(new LatLng(lat, lon));
                marker.setMap(naverMap);

<<<<<<< HEAD
                Toast.makeText(getApplicationContext(), lat + " , " + lon, Toast.LENGTH_SHORT).show();
=======

              //  Toast.makeText(getApplicationContext(),  lat+" , "+lon, Toast.LENGTH_SHORT).show();
>>>>>>> 65a2ed0ce81c63afee67c195a7b646fc36702252
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.map_btn) {
            Intent intent = new Intent(MapActivity.this, HomeFragment.class);
            intent.putExtra("주소", "map_text");
            startActivity(intent);

        }else if(v.getId() == R.id.map_back) {
            onBackPressed();
        }
    }




}