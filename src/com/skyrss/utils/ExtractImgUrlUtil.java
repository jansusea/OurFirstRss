package com.skyrss.utils;

import java.util.Locale;

public class ExtractImgUrlUtil {

	public static String extractUrl(String data) {
		String url = "";

		if (data.contains("<img")) {
			int imgIndex = data.toLowerCase().indexOf("<img");
			data = data.substring(imgIndex);
			int i = data.toLowerCase().indexOf("src=\"");

			if (i != -1) {
				String subString = data.substring(i + 5);
				int j = subString.indexOf("\"");
				url = subString.substring(0, j);
			}
		}
//		System.out.println(url);
		return url;
	}

}
