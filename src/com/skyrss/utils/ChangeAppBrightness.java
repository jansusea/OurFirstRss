package com.skyrss.utils;

import android.app.Activity;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

public class ChangeAppBrightness {
	public static void change(Context context, int brightness) {
	     Window window = ((Activity) context).getWindow();
	     WindowManager.LayoutParams lp = window.getAttributes();
	     if (brightness == -1) {
	         lp.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE;
	     } else {
	          lp.screenBrightness = (brightness <= 0 ? 1 : brightness) / 255f;
	      }
	     window.setAttributes(lp);
	 }
}
