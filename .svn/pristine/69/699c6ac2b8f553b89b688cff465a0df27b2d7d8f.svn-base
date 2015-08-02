package com.skyrss;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class GuideActivity extends Activity {

	public static final String TAG ="FirstIntroductionActivity";
	int[] images = new int[] { R.drawable.guidebg1, R.drawable.guidebg2
			};
	List<ImageView> imagelist;//不能直接用上面的资源ID，还是要实例化图像的列表
	Button bt_enter;
	SharedPreferences sp;
	LinearLayout ll_pointgroup;
	View view_redpoint;
	int mpwidth;//两个灰点之间的距离
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		ViewPager vp = (ViewPager) findViewById(R.id.vp_firstentersplash_introduce);
		bt_enter=(Button) findViewById(R.id.bt_firstentersplash_enter);
		sp=getSharedPreferences("config", MODE_PRIVATE);
		imagelist = new ArrayList<ImageView>();
	
		initviews();
		vp.setAdapter(new MyPagerAdapter());//都是要适配器的
		vp.setOnPageChangeListener(new MyListener());//要搞状态需要设置监听
		
	}
	private void initviews() {
		//搞一个图片的list出来,这里的遍历是针对对象来的，分别是当images[0],当images[1],当images[2],是一个数组的地址，而不是计数
		for (int i : images) {
			ImageView iv = new ImageView(this);
			iv.setBackgroundResource(i);//一般给一个imageview设置一个背景资源就是给这个图像赋值
			imagelist.add(iv);
		}
		
		
	}

	class MyPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return imagelist.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override  //相当于listview的getview
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(imagelist.get(position));
			return imagelist.get(position);
		}

		@Override  //走的时候也要销毁掉项目
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);//直接销毁传来的object即可
		}

	}

	class MyListener implements OnPageChangeListener {

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
		}

		@Override
		public void onPageSelected(int position) {
			if(position==imagelist.size()-1){
				bt_enter.setVisibility(View.VISIBLE);
			}else{
				bt_enter.setVisibility(View.INVISIBLE);
			}
			
		}

		@Override
		public void onPageScrollStateChanged(int state) {
		}

	}
	public void enter(View v){
		Intent intent=new Intent(this,HomeActivity.class);
		Editor et=sp.edit();
		et.putBoolean("firstenter", false);
		et.commit();
		startActivity(intent);
		finish();
	}
}
