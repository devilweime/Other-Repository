package com.wgw.test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class Base64Test {
	
	
	
	public static void main(String[] args) throws IOException {
		
		String encoderWithBase64 = encoderWithBase64("C:\\Users\\wgw\\Desktop\\KHYD7.pdf");
		System.out.println(encoderWithBase64);
	}
	
	
	private static String decodeWithBase64(InputStream inputStream) throws IOException{
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] b = new byte[1024];
		int len = 0;
		while ((len = inputStream.read())!=-1) {
			outputStream.write(b, 0, len);
		}
		byte[] byteArray = outputStream.toByteArray();
		
		inputStream.close();
		outputStream.close();
		
		Decoder decoder = Base64.getDecoder();
		decoder.decode(byteArray);
		return null;
	} 
	
	
	
	public static String encoderWithBase64(InputStream inputStream) throws IOException{
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] b = new byte[1024];
		int len = 0;
		while ((len = inputStream.read(b))!=-1) {
			outputStream.write(b, 0, len);
		}
		byte[] byteArray = outputStream.toByteArray();
		inputStream.close();
		outputStream.close();
		
		Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString(byteArray);
	}
	
	public static String encoderWithBase64(String filePath) throws IOException{
		
		FileInputStream inputStream = new FileInputStream(filePath); 
		
		return encoderWithBase64(inputStream);
	}
}
