package com.example.instagramsample.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import android.os.AsyncTask;
import android.util.Log;

import com.example.instagramsample.common.IConstants;
import com.example.instagramsample.interfaces.TaskCallback;
import com.example.instagramsample.parse.Info;
import com.example.instagramsample.parse.ParseUtil;

public class DownloadTask extends AsyncTask<Void, Void, Info>{
	
	private TaskCallback taskCallback;
	private String TAG = "DownloadTask";
	
	public DownloadTask(TaskCallback taskCallback){
		this.taskCallback = taskCallback;
	}

	@Override
	protected Info doInBackground(Void... params) {

		try {
			URL url = new URL(IConstants.INS_URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(10000);
			conn.setConnectTimeout(15000);

			//GET or POST
			conn.setRequestMethod("GET");

			conn.setDoInput(true);
			conn.connect();

			InputStream is = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String data = null;
			String rawJson = "";

			//message contains all the response (JSON)
			while((data = reader.readLine()) != null)
				rawJson += data + "\n";
			
			Log.i(TAG,"rawJson = "+rawJson);
			
			return ParseUtil.getPictureData(rawJson);
			
			
			
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void onPostExecute(Info data){
		if(taskCallback != null)
			taskCallback.getPictureData(data);
	}


}
