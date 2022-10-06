package com.example.team_project01.conn;

import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
    
    @GET("{url}")
    Call<String> getDataGET(@Path("url") String url, @QueryMap HashMap<String, String> parameters);


    @GET("{url}")
    Call<String> getFile(@Path("url") String url, @QueryMap HashMap<String, String> parameters);


    @POST("file.f")
    @Multipart
    Call<String> sendFile(@Part MultipartBody.Part file );

    @POST("andInsert")
    @Multipart
    Call<String> sendTest(@Part("vo") RequestBody data , @Part MultipartBody.Part file); //@FieldMap 접속 되고 추가  파일만 보낼때

    @POST("andModify")
    @Multipart
    Call<String> sendModify(@Part("vo") RequestBody data , @Part MultipartBody.Part file); //@FieldMap 접속 되고 추가  파일만 보낼때


}
