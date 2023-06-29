package com.pemrogramanbergerak.quranap.ModelTerjemahan;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Terjemahan{

	@SerializedName("translations")
	private List<TranslationsItem> translations;

	public List<TranslationsItem> getTranslations(){
		return translations;
	}

	@Override
 	public String toString(){
		return 
			"Terjemahan{" + 
			"translations = '" + translations + '\'' + 
			"}";
		}
}