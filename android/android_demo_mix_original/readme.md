Android混合开发
---

### 前期核心
 ```
    WebView载入html

    //asset文件夹需带android前缀
     mWebView.loadUrl("file:///android_asset/inner.html");

     //启用javascript
     webSettings.setJavaScriptEnabled(true);

     mWebView.setWebChromeClient(new WebChromeClient() {
     });  //不设置alert无法弹出
```

### Android控件调js
```
     1.
      //调用javascript的androidCallJs
      mWebView.loadUrl("javascript:androidCallJs('我来自Android')");

      或
      mWebView.evaluateJavascript("doSum(424121,2)", new ValueCallback<String>() {
        @Override
        public void onReceiveValue(String valueCallback) {//doSum的结果回调
        Log.i("sum", "onReceiveValue:---------> " + valueCallback);
        Toast.makeText(MainActivity.this, valueCallback, Toast.LENGTH_SHORT).show();
         }
      });


     2.
     html写好function androidCallJs(text){...}
```
### js调Android后台服务
```
    1.
     //设置html的window.android变量
     mWebView.addJavascriptInterface(MainActivity.this, "android");

     2.
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

     3.
     html调用window.android.alertDialog("I am come from JS")
```