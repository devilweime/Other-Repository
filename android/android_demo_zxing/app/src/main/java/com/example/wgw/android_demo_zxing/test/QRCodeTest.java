package com.example.wgw.android_demo_zxing.test;


//http://hejiawangjava.iteye.com/blog/2256632
//https://blog.csdn.net/bear_huangzhen/article/details/46053327


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

import java.io.IOException;
import java.util.Hashtable;

public class QRCodeTest {

    public static void main(String[] args) throws FormatException, ChecksumException, NotFoundException, IOException {

        Result result = decodeQR("android_assets/hello_world_QR.png");
        System.out.println(result.getText());


    }

    public static Result decodeQR(String path) throws IOException, FormatException, ChecksumException, NotFoundException {
        Bitmap bitmap = readImage2Bitmap(path);
        int[] pixels = new int[bitmap.getWidth()*bitmap.getHeight()];
        RGBLuminanceSource source = new RGBLuminanceSource(bitmap.getWidth(),bitmap.getHeight(),pixels);

        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));

        Hashtable<DecodeHintType, String> hints = new Hashtable();
        hints.put(DecodeHintType.CHARACTER_SET, "UTF-8"); // 设置二维码内容的编码

        QRCodeReader reader = new QRCodeReader();
        Result result = reader.decode(binaryBitmap, hints);

      /*  Reader reader = new MultiFormatReader();
        Result result = reader.decode(binaryBitmap);*/

            return result;
    }


    public void encodeQR(){

    }

    public static Bitmap readImage2Bitmap(String path){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//先获取原大小
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        return bitmap;
    }

}
