package com.example.instagramsample.tasks;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
	 
	private ImageView iv;
	
	public DownloadImageTask(ImageView iv) {
		this.iv = iv;
	}

	@Override
	protected Bitmap doInBackground(String... URL) {

		String imageURL = URL[0];

		Bitmap bitmap = null;
		try {
			// Download Image from URL
			InputStream input = new java.net.URL(imageURL).openStream();
			// Decode Bitmap
			bitmap = BitmapFactory.decodeStream(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		iv.setImageBitmap(result);
	}
}
