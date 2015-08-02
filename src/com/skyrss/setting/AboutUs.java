package com.skyrss.setting;

import android.app.Activity;
import android.os.Bundle;

import com.skyrss.R;
import com.skyrss.global.NightModeUpdate;

public class AboutUs extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting_aboutus);
	}
	@Override
	protected void onResume() {
		NightModeUpdate.update(this);
		super.onResume();
	}
}
