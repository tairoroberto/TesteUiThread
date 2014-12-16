package com.example.testeuithread;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

public class GetImageWeb {
	private static Bitmap img;
	
	public static void getImageWeb(final Activity activity, final ImageView imageView) {
		new Thread(){
			public void run() {
				try {
					/*Pega uma Url e faz o download da imagem*/ 
					URL url = new URL("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT6ctxgW55J10izazSSe2ImnmcB5QtDgij76ZmRBrbi-Im4OyHQ");
					HttpURLConnection conexao = (HttpURLConnection)url.openConnection();
					InputStream inputStream = conexao.getInputStream();
					img = BitmapFactory.decodeStream(inputStream);
					
				} catch (IOException e) {
					// TODO: handle exception
				}
				
				//handle.post(new Runnable() {
				activity.runOnUiThread(new Runnable() {	
					@Override
					public void run() {
						/* Seta a imagem baixada no ImageView da tela principal*/
						imageView.setImageBitmap(img);						
					}
				});
			}
		}.start();
	}
}
