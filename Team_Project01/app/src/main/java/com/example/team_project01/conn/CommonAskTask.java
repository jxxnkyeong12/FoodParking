package com.example.team_project01.conn;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;

public class CommonAskTask extends AsyncTask<String , String , String> {
    public AsynckTaskCallBack callBack;
    Context context; // 토스트 메세지나 , 프로그레스 다이얼로그 사용 시 필요함.
    ProgressDialog dialog;
    HashMap<String, Object> params;
    String url ;

    //생성자
    public CommonAskTask(Context context, String url) {
        this.context = context;
        this.url = url;
        this.dialog = new ProgressDialog(context);
        this.params = new HashMap<>();
    }

    public void addParams(String key , Object value){
        if(value == null) return;
        this.params.put(key , value);
    }

    public void excuteAsk(AsynckTaskCallBack callBack){
        this.callBack = callBack;
        this.execute();
    }

    @Override
    protected void onPreExecute() {
        if(dialog == null) return;
        dialog.setProgress(ProgressDialog.STYLE_SPINNER);
        dialog.setTitle("데이터 처리");
        dialog.setMessage("데이터를 가져오는 중...");
        dialog.show(); //<= 실제 보이게 처리 ※
    }


    @Override
    protected String doInBackground(String... strings) {
        String rtnData = null;
        ApiInterface apiInterface = ApiClient.getApiclient().create(ApiInterface.class);
        try {
            rtnData = apiInterface.getData(url , params).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rtnData;
    }

    @Override
    protected void onPostExecute(String data) {
        if(dialog == null) return;
        dialog.dismiss();
        Log.d("콜백데이터"  , "onPostExecute: " + data);
        if(data == null || data.length() == 0 || data.equals("null")){
            callBack.onResult(data , false);
        }else{
            callBack.onResult(data , true);
        }

    }

    public interface AsynckTaskCallBack{
        void onResult(String data , boolean isResult);
    }

}
