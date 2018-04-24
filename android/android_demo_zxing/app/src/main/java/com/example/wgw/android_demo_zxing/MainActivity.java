package com.example.wgw.android_demo_zxing;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.google.zxing.Result;

//https://github.com/zxing/zxing
//https://github.com/bingoogolapple/BGAQRCode-Android.git
//https://github.com/yuzhiqiang1993/zxing/tree/master/app/src/main
//https://blog.csdn.net/u012721519/article/details/51737058
//https://blog.csdn.net/u010618194/article/details/77891313
//https://github.com/journeyapps/zxing-android-embedded
//https://www.cnblogs.com/mythou/p/3280023.html
//https://segmentfault.com/a/1190000003945592
//https://www.jianshu.com/p/f862b73d07f7

//原生
//

public class MainActivity extends Activity {

    Button btnScan01;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnScan01 = (Button)findViewById(R.id.btn_scan01);

    }


    private Result parseQRcodeBitmap(String bitmapPath){

        return null;
    }

}
