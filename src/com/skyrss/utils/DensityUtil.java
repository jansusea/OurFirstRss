package com.skyrss.utils;

import android.content.Context;

public class DensityUtil{
	public static int pix2dp(Context ctx,int pix){
		float scale=ctx.getResources().getDisplayMetrics().density;
		return (int) (pix/scale+0.5f);
	}
	public static int dp2pix(Context ctx,int dp){
		float scale=ctx.getResources().getDisplayMetrics().density;
		return (int) (scale*dp+0.5f);
	}
}
