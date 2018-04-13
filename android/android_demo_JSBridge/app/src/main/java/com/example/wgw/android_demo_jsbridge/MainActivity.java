package com.example.wgw.android_demo_jsbridge;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.widget.Button;
import com.github.lzyzsd.jsbridge.BridgeWebView;

//https://github.com/lzyzsd/JsBridge
//http://www.cnblogs.com/dailc/p/5931328.html

public class MainActivity extends Activity {
    Button btnAndroid;
    BridgeWebView mWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAndroid = (Button) findViewById(R.id.btn_android);
        mWebView = (BridgeWebView) findViewById(R.id.webView);

        WebSettings webSettings = mWebView.getSettings();
        mWebView.loadUrl("file:///android_assets/inner.html");
        webSettings.setJavaScriptEnabled(true);

        mWebView.setWebChromeClient(new WebChromeClient(){

        });//不设置alert无法弹出




    }
}
