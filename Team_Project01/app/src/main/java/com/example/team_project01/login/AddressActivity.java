package com.example.team_project01.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.PermissionRequest;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.team_project01.R;

public class AddressActivity extends AppCompatActivity {

    private WebView browser;
    ImageView post_back;

    class MyJavaScriptInterface {
        @JavascriptInterface
        @SuppressWarnings("unused")
        public void processDATA(String addr) {

            Bundle extra = new Bundle();
            Intent intent = new Intent();
            String[] dataArr = addr.split(",");
            addr = dataArr[1].toString().substring(0, dataArr[1].toString().indexOf("("));
            extra.putString("addr", addr);
            extra.putString("post", dataArr[0]);
            intent.putExtras(extra);
            setResult(RESULT_OK, intent);

            Log.d("TAG", "주소: " + addr + ", " + dataArr.toString());

            finish();

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        post_back = findViewById(R.id.post_back);
        browser = (WebView) findViewById(R.id.wv_search_address);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.addJavascriptInterface(new MyJavaScriptInterface(), "Android");

        browser.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {

                browser.loadUrl("javascript:sample2_execDaumPostcode();");
            }
        });

        post_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        browser.loadUrl("http://www.inspond.com/daum.html");

    }
}