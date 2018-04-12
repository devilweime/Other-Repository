package com.example.wgw.android_demo_webview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class MainActivity extends Activity {

    WebView webView;

    TextView mTitle,textBeginLoading,textEndLoading,textLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = (TextView) findViewById(R.id.title);
        textBeginLoading = (TextView) findViewById(R.id.text_beginLoading);
        textEndLoading = (TextView) findViewById(R.id.text_endLoading);
        textLoading = (TextView) findViewById(R.id.text_Loading);
        webView = (WebView) findViewById(R.id.webView);

        WebSettings webSettings = webView.getSettings();
        webView.loadUrl("http://www.baidu.com/");

        //设置不用系统浏览器打开,直接显示在当前Webview
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
                }
            }
        );

        //设置WebChromeClient类
        webView.setWebChromeClient(new WebChromeClient() {


            //获取网站标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                System.out.println("标题在这里");
                mTitle.setText(title);
            }

            //获取加载进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress < 100) {
                    String progress = newProgress + "%";
                    textLoading.setText(progress);
                } else if (newProgress == 100) {
                    String progress = newProgress + "%";
                    textLoading.setText(progress);
                }
            }
        });


        //设置WebViewClient类
        webView.setWebViewClient(new WebViewClient() {
            //设置加载前的函数
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                System.out.println("开始加载了");
                textBeginLoading.setText("开始加载了");

            }

            //设置结束加载函数
            @Override
            public void onPageFinished(WebView view, String url) {
                textEndLoading.setText("结束加载了");

            }
        });


    }

}
