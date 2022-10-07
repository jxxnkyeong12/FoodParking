package com.example.team_project01.conn;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {

<<<<<<< HEAD:Team_Project01/app/src/main/java/com/example/team_project01/conn/ApiClient.java
    private static final String BASE_URL =  "http://192.168.0.113/cteam/";
=======


    //집 private static final String BASE_URL =  "http://192.168.35.205/cteam/";
    //집 private static final String BASE_URL =  "http://192.168.35.60/cteam/";
    //학원 놋북
    private static final String BASE_URL = "http://192.168.0.113/cteam/";
    //학원 private static final String BASE_URL =  "http://192.168.35.176/cteam/";
>>>>>>> 9cb002ba616cae74e12e5a50d2a549397d4d6f88:java/com/example/team_project01/conn/ApiClient.java




    //Retrofit 객체의 설정을 넣고 인스턴스화(초기화) 할 수 있는 클래스 정의
    private static Retrofit retrofit;

    public static Retrofit getApiclient(){
        if( retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL) // 미들웨어의 주소를 의미함(맵핑을 제외하고)
                    .addConverterFactory(ScalarsConverterFactory.create())//json String형태를 사용가능하게 해준다.
                    .client(new OkHttpClient.Builder().readTimeout(10, TimeUnit.SECONDS).build())
                    //미들웨어가 꺼졌거나 응답이 불가능할때 몇초이상경과되면 통신을 끊기위한처리
                    .build();

        }
        return retrofit;
    }


    public static Retrofit getApiclient(String base_url){
        Retrofit retrofitGet = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(new OkHttpClient.Builder().readTimeout(10, TimeUnit.SECONDS).build())
                    .build();
        return retrofitGet;
    }

}
