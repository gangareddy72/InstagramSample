package com.example.instagramsample.parse;

import com.google.gson.Gson;

public class ParseUtil {

	public static Info getPictureData(String rawJson){
		
		Gson gson = new Gson();
		return gson.fromJson(rawJson, Info.class);
	}	
}