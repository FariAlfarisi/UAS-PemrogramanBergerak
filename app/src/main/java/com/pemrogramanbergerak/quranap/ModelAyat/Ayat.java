package com.pemrogramanbergerak.quranap.ModelAyat;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Ayat{

	@SerializedName("verses")
	private List<VersesItem> verses;

	public List<VersesItem> getVerses(){
		return verses;
	}

	@Override
 	public String toString(){
		return 
			"Ayat{" + 
			"verses = '" + verses + '\'' + 
			"}";
		}
}