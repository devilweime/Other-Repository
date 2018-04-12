package com.example.wgw.android_demo_mix_original;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

//https://blog.csdn.net/lantiankongmo/article/details/52302429
//https://blog.csdn.net/tyyj90/article/details/49700833
public class MainActivity extends Activity {


    Button buttonNull,buttonVar;
    WebView mWebView;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonNull = (Button) findViewById(R.id.button_null);
        buttonVar = (Button) findViewById(R.id.button_var);
        mWebView = (WebView) findViewById(R.id.webview);

        buttonNull.setOnClickListener(onClickListener);
        buttonVar.setOnClickListener(onClickListener);

        WebSettings webSettings = mWebView.getSettings();

        //asset文件夹需带android前缀
        mWebView.loadUrl("file:///android_asset/inner.html");

        //启用javascript
        webSettings.setJavaScriptEnabled(true);

        mWebView.setWebChromeClient(new WebChromeClient() {
        });  //不设置alert无法弹出

        //设置html的window.android变量,扫描MainActivity类所有添加@JavascriptInterface
        mWebView.addJavascriptInterface(MainActivity.this, "android");
    }

    View.OnClickListener onClickListener = new View.OnClickListener(){
        @TargetApi(Build.VERSION_CODES.KITKAT)
        @Override
        public void onClick(View view) {
           switch (view.getId()){
               case R.id.button_null:

                   //调用javascript的androidCallJs
                   mWebView.loadUrl("javascript:androidCallJs('我来自Android')");
                   break;
               case R.id.button_var:
                   mWebView.evaluateJavascript("doSum(424121,2)", new ValueCallback<String>() {
                       @Override
                       public void onReceiveValue(String valueCallback) {//doSum的结果回调
                           Log.i("sum", "onReceiveValue:---------> " + valueCallback);
                           Toast.makeText(MainActivity.this, valueCallback, Toast.LENGTH_SHORT).show();
                       }
                   });
                   break;
           }
        }
    };


    //声明为window.android的alertDialog方法
    @JavascriptInterface
    public void alertDialog(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AlertDialog.Builder(MainActivity.this).
                        setMessage(text).show();
            }
        });
    }

    @JavascriptInterface
    public void onSumResult(int sum) {
        Log.i("sum", "onSumResult: ------>" + sum);
        Toast.makeText(MainActivity.this, sum + "",
                Toast.LENGTH_SHORT).show();
    }


}
