package com.example.instagramsample;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.instagramsample.interfaces.TaskCallback;
import com.example.instagramsample.parse.Data;
import com.example.instagramsample.parse.Info;
import com.example.instagramsample.tasks.DownloadTask;
import com.example.instagramsample.views.SuperGalleryView;

public class MainActivity extends ActionBarActivity implements TaskCallback{

	private Button downloadBtn;
	private final String TAG = MainActivity.class.getSimpleName();
	private ProgressDialog pd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();
		
	}
	
	private void init(){
		downloadBtn = (Button)findViewById(R.id.downloadBtn);
		
		
		downloadBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Download the images
				//Start AsyncTask
				
				DownloadTask task = new DownloadTask(MainActivity.this);
				task.execute();
				pd = ProgressDialog.show(MainActivity.this, "Loading..", "");
			}
		});
	}

	@Override
	public void getPictureData(Info info) {
		if(info != null){
			Data data = info.getData()[0];
			
			if(data != null){
				Log.i(TAG, "url  = "+data.getImages().getStandard_resolution().getUrl());
				
				RelativeLayout layout = (RelativeLayout)findViewById(R.id.mainContainer);
				SuperGalleryView galleryView = new SuperGalleryView(MainActivity.this);
				
				galleryView.setInfo(info);
				galleryView.start();
				
				layout.addView(galleryView);
				
				if(pd.isShowing())
					pd.dismiss();
				
			}
				
		}
		
	}


}
