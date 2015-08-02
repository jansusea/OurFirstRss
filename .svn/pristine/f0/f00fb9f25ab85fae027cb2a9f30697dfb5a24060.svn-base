package com.skyrss.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class CheckUpdate {
	// 获取版本号
	public static String getVersion(Context ctx) {
		PackageManager packageManager = ctx.getPackageManager();
		String version = "";
		try {
			PackageInfo packageInfo = packageManager.getPackageInfo(
					ctx.getPackageName(), 0);
			version = packageInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return version;
	}
}
