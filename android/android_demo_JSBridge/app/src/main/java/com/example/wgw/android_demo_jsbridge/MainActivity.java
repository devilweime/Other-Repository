package com.example.wgw.android_demo_jsbridge;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.Toast;

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.github.lzyzsd.jsbridge.DefaultHandler;

//https://github.com/lzyzsd/JsBridge
//http://www.cnblogs.com/dailc/p/5931328.html
//https://blog.csdn.net/carson_ho/article/details/64904691

public class MainActivity extends Activity {

    private final String TAG = "JsBridge";
    Button btnAndroid;
    BridgeWebView mWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAndroid = (Button) findViewById(R.id.btn_android);
        mWebView = (BridgeWebView) findViewById(R.id.webView);

        btnAndroid.setOnClickListener(onClickListener);

        WebSettings webSettings = mWebView.getSettings();
        mWebView.loadUrl("file:///android_asset/inner.html");
        webSettings.setJavaScriptEnabled(true);

        mWebView.setWebChromeClient(new WebChromeClient(){

        });//不设置alert无法弹出


        //js调android
        mWebView.registerHandler("callAndroid", new BridgeHandler() {//
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.i(TAG,"handler = callAndroid, data from web = " + data);
                function.onCallBack("Js call Android:"+data);
                Toast.makeText(MainActivity.this, "Js call Android by handler name:"+data, Toast.LENGTH_SHORT).show();
            }
        });


        //方式1
        //bridgeWebView.setDefaultHandler(new DefaultHandler());

        //方式2
        mWebView.setDefaultHandler(new myHadlerCallBack());

    }


    /**
     * 自定义回调
     *
     *  对应方式2
     * bridgeWebView.setDefaultHandler(new myHadlerCallBack());
     *
     */
    class myHadlerCallBack extends DefaultHandler {

        @Override
        public void handler(String data, CallBackFunction function) {
            if(function != null){
                Toast.makeText(MainActivity.this, "自定义类继承DefaultHandler："+data, Toast.LENGTH_SHORT).show();
            }
        }
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_android:

                    //Java 调JS的callJs方法,无返回值
                    //mWebView.callHandler("callJs",null,null);

                    //Java 调JS的callJs方法并得到返回值，并做处理
                    mWebView.callHandler("callJs", "Android call Js", new CallBackFunction() {//js方法名，方法参数，回调实现
                        @Override
                        public void onCallBack(String data) { //对应js registerHandler--->responseCallback(responseData);
                            Toast.makeText(MainActivity.this, "Android call Js："+data, Toast.LENGTH_SHORT).show();
                        }
                    });

                    break;
            }
        }
    };

}
