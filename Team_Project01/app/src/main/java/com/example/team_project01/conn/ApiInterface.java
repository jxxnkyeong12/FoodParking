package com.example.team_project01.conn;

import java.util.HashMap;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ApiInterface {

    @FormUrlEncoded
    @POST
    Call<String> getData(@Url String url, @FieldMap HashMap<String, Object> params );

    //현재 상태에서는 재사용 가능한게 POST방식만 만들어둠. (URL에 파라메터 노출이 없는 형태 보안 ↑ )
    //하지만 공공데이터 포털이나 공공의 목적으로 만들어진 API들은 GET방식이 많음. (URL에 파라메터 노출이 있음 ! 보안 ↓ 대신 접근성이 용이)

    
    @GET("{url}") //GET 방식은 BASE URL + URL (맵핑) + ?뒤에 나오는 파라메터 2. 맵핑해버리기! 
    Call<String> getDataGET(@Path("url") String url, @QueryMap HashMap<String, String> parameters); //1.스트링으로 담고 getlocalspecstbaseinfo<-이거면 맨날 해줘야하고, 바뀌니까 url로 바꾸고 해주는거야
                                //입력은 url로 받을거고, 스트링 변수(String url)에 넣어줄거고, 입력받는거를 맵핑 @GET("{url}")시킬거다!
                                //@QueryMap 눈에 보이는 파라메터를 의미.모든 파라메터를 String으로 넘기자

    @GET("{url}") //GET 방식은 BASE URL + URL (맵핑) + ?뒤에 나오는 파라메터 2. 맵핑해버리기!
    Call<String> getFile(@Path("url") String url, @QueryMap HashMap<String, String> parameters);


    @POST("file.f")
    @Multipart
    Call<String> sendFile(@Part MultipartBody.Part file );

}
