package com.example.instagramsample.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.instagramsample.R;
import com.example.instagramsample.parse.Info;
import com.example.instagramsample.tasks.DownloadImageTask;

public class SuperGalleryView extends ScrollView{

	private Info info;
	private LinearLayout container;
	
	
	public SuperGalleryView(Context context) {
		super(context);
		init();
	}
	
	public SuperGalleryView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	
	public SuperGalleryView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}
	
	
	public SuperGalleryView(Context context, AttributeSet attrs,
			int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init();
	}
	
	private void init(){
		container = new LinearLayout(getContext());
		container.setOrientation(LinearLayout.VERTICAL);
		SuperGalleryView.this.addView(container);		
	}
	
	public void setInfo(Info info){
		this.info = info;
	}
	
	
	public void start(){
		
		//Add 5 rows
		int j=0;
		for(int i=0; i<5;i++){
			LayoutInflater inflater = LayoutInflater.from(getContext());
			View view = inflater.inflate(R.layout.gallery_view, null);
			new DownloadImageTask((ImageView)view.findViewById(R.id.iv1)).execute(info.getData()[j++].getImages().getStandard_resolution().getUrl());
			new DownloadImageTask((ImageView)view.findViewById(R.id.iv2)).execute(info.getData()[j++].getImages().getStandard_resolution().getUrl());
			new DownloadImageTask((ImageView)view.findViewById(R.id.iv3)).execute(info.getData()[j++].getImages().getStandard_resolution().getUrl());
			container.addView(view);			
		}
		
		this.requestLayout();
	}

}
