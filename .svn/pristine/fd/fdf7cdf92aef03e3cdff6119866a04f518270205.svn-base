package com.skyrss.utils;

import java.io.File;
import java.io.FileOutputStream;

import android.text.GetChars;

public class SetCssUtils {
	
	private static String headString;
	private static String neededString;

	public static String setCssToHtml(String html, int widthPixels) {
		String finalString = "";
		finalString = html.replaceAll("<p", "<p style=\"text-wrap:unrestricted;width:"+(widthPixels-50)+"px\" ");
		finalString = finalString.replaceAll("<ul", "<ul style=\"text-wrap:unrestricted;width:"+(widthPixels-50)+"px\" ");
		finalString = finalString.replaceAll("<ol", "<ol style=\"text-wrap:unrestricted;width:"+(widthPixels-50)+"px\" ");
		return finalString.replaceAll("<img ", "<img style=\"max-width:"+(widthPixels-40)+"px\" ");
	}

}
/*if (html.contains("<body")) {
	int index = html.indexOf("<body");
	String headString = html.substring(0, index+5);
	String neededString = html.substring(index+5);
	finalString = headString+" style=\"word-break:break-all;width:"+(widthPixels-10)+"px\" "+neededString;
}*/