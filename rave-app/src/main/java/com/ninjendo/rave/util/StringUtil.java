package com.ninjendo.rave.util;

import org.apache.commons.lang3.text.WordUtils;

public class StringUtil {

	private StringUtil(){}
	
	public static String convertToCamelCase(String str){
		return WordUtils.capitalizeFully(str);
	}
	
//	public boolean isLengthTooLong(){
//		
//	}
}
