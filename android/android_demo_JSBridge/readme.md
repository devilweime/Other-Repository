jsBridge的使用
---

## Js调Android

### 1.调用默认的
```
java code
    webView.setDefaultHandler(new DefaultHandler());

    * 可通过继承DefaultHandler实现自定义
js code
    function callAndroidDefault(){
            window.WebViewJavascriptBridge.send(
                    data//data发送的数据
                    , function(responseData) {//响应回调
                        document.getElementById("show").innerHTML = "data = " + responseData
                    }
                );
    }
```

### 2.调用自己注册
```
java code
    mWebView.registerHandler("callAndroid", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.i(TAG,"handler = callAndroid, data from web = " + data);
                function.onCallBack("Js call Android:"+data);//回调数据
                Toast.makeText(MainActivity.this, "Js call Android by handler name:"+data, Toast.LENGTH_SHORT).show();
            }
    });

js code
     function callAndroid(){

                //call native method
                window.WebViewJavascriptBridge.callHandler(
                    'callAndroid'
                    , {'param': data}
                    , function(responseData) {
                        document.getElementById("show").innerHTML = responseData;
                    }
                );
            }
```

## Android调Js
```
js code
    window.WebViewJavascriptBridge.registerHandler("callJs", function(data, responseCallback) {
        document.getElementById("show").innerHTML = ( data);
        if (responseCallback) {
            var responseData = getData();;
            responseCallback(responseData);
        }
    });

java code
    mWebView.callHandler("callJs", "Android call Js", new CallBackFunction() {//js方法名，方法参数，回调实现
        @Override
        public void onCallBack(String data) { //对应js registerHandler--->responseCallback(responseData);
            Toast.makeText(MainActivity.this, "Android call Js："+data, Toast.LENGTH_SHORT).show();
        }
    });
```
## 参考
```
    jsBridge github地址:https://github.com/lzyzsd/JsBridge
    WebView与 JS 交互方式:https://blog.csdn.net/carson_ho/article/details/64904691
```



