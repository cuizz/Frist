package com.example.frist;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/5/10.
 */

public class WebActivity extends AppCompatActivity {
    private WebView webview;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private ProgressBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_activity);
        webview=(WebView)findViewById(R.id.webview);
        Button btn=(Button)findViewById(R.id.button);
        Button btn1=(Button)findViewById(R.id.button2);
        bar=(ProgressBar)findViewById(R.id.firstBar2);
        //支持App内部JavaScript交互
        webview.getSettings().setJavaScriptEnabled(true);
        //自适应屏幕
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                WebActivity.this.setTitle("Loading...");
                WebActivity.this.setProgress(progress);
                bar.setProgress(progress);
                if (progress >= 80) {
                    WebActivity.this.setTitle("JsAndroid Test");
                }
            }
        });
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                bar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                bar.setVisibility(View.GONE);
            }
        });
        webview.loadUrl("file:///android_asset/second.html");
        //js调用android
        webview.addJavascriptInterface(WebActivity.this,"android");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webview.loadUrl("javascript:javacalljs()");
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webview.loadUrl("javascript:javacalljswith('android调用带参数的js')");
            }
        });
    }
    //js调用android
    @JavascriptInterface
    public void startFunction(final String text){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+text));
                //   MainActivity.this.startActivity(intent);
                testCall();
            }
        });

    }
    public void testCall() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
        } else {
            callPhone();
        }
    }

    public void callPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + "10086");
        intent.setData(data);
        WebActivity.this.startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callPhone();
            } else {
                // Permission Denied
                Toast.makeText(WebActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
