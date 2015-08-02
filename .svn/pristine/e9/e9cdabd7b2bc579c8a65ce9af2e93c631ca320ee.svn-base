package com.skyrss.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.provider.ContactsContract.Contacts.Data;
import android.widget.Toast;

public class PubDateFormatUtils {
	
	private static Date datt;
	private static Date newdate;

	public static String formatDate(String date) {
		
		String realDate = "";

		Date time = new Date();
		//Z 对于格式化来说，使用 RFC 822 4-digit 时区格式 ,Locale.US表示使用了美国时间 
		try {
			
			//格式1，如果无法解析则在catch中捕捉到然后用别的格式解析
			SimpleDateFormat sdf =new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.US);
			time = sdf.parse(date);
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			
			//格式2，格式不一样，catch
			SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
			try {
				time = sdf.parse(date);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		realDate = sdf2.format(time);
		
		return realDate;
	}
	

}
