package com.skyrss.global;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;

import com.skyrss.utils.ChangeAppBrightness;

public class NightModeUpdate {
	public static void update(Context ctx){
		SharedPreferences sp;
		sp=ctx.getSharedPreferences("config", ctx.MODE_PRIVATE);
		Boolean currentNightMode = sp.getBoolean("nightmode", false);
		int value = 0;
		ContentResolver cr = ctx.getContentResolver();
	    try {
	        value = Settings.System.getInt(cr, Settings.System.SCREEN_BRIGHTNESS);
	    } catch (SettingNotFoundException e) {
	        
	    }
		if (currentNightMode) {
			ChangeAppBrightness.change(ctx, 3);
		} else {
			ChangeAppBrightness.change(ctx, value);
		}
	}
}
