package com.skyrss;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.skyrss.bean.SourceBean;
import com.skyrss.dao.SourceDao;
import com.skyrss.global.NightModeUpdate;

public class FindmoreActivity extends Activity {
	
	private ListView lv_findmore_itemlist;
	private SourceDao dao;
	private List<SourceBean> sourcelist;
	private List<String> addlist;
	private List<String> removelist;
	private List<String> alllist;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_findmore);
		
		dao = new SourceDao(this);
		sourcelist = new ArrayList<SourceBean>();
		sourcelist = dao.getAllSource();
		
		addlist = new ArrayList<String>();
		removelist = new ArrayList<String>();
		alllist = new ArrayList<String>();
		
		lv_findmore_itemlist=(ListView) findViewById(R.id.lv_findmore_itemlist);
		lv_findmore_itemlist.setAdapter(new MyNewsSourceAdapter());
		lv_findmore_itemlist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//跳转到listView中显示该源的数据
				SourceBean source = sourcelist.get(position);
				//Toast.makeText(FindmoreActivity.this, source.getName(), 0).show();
				Intent intent = new Intent(FindmoreActivity.this,SourceActivity.class);
				intent.putExtra("source", source.getId());
				intent.putExtra("sourcename", source.getName());
				startActivity(intent);
			}
		});
	}
	
	class MyNewsSourceAdapter extends BaseAdapter {

		private SourceBean sourceitem;

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return sourcelist.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			
			sourceitem = sourcelist.get(position);
			View v;
			final viewholder holder;
			if (convertView == null) {
				v = View.inflate(FindmoreActivity.this, R.layout.items_findlist,
						null);
				holder = new viewholder();
				holder.tv_sourcelist_name = (TextView) v.findViewById(R.id.tv_itemsfind_itemname);
				holder.cb_add = (CheckBox) v.findViewById(R.id.ib_itemsfind_add);
				v.setTag(holder);
			}else {
				v = convertView;
				holder = (viewholder) v.getTag();
			}
			holder.cb_add.setText(sourceitem.getName());//将cb添加名字，以便后续中获取数据
			holder.tv_sourcelist_name.setText(sourceitem.getName());
		
			//就是一个初始化的操作   只有在alllist里面没有source的时候才进入添加到相应的
				if (!alllist.contains(sourceitem.getName())) {
					alllist.add(sourceitem.getName());
				
					if (!addlist.contains(sourceitem.getName())) {
						if (sourceitem.isIschoosed()) {
						addlist.add(sourceitem.getName());
						}
					}
					if ((!removelist.contains(sourceitem.getName()))) {
					if (!sourceitem.isIschoosed()) {
						removelist.add(sourceitem.getName());
						}
					}	
				}
			
			holder.cb_add.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					CheckBox box = (CheckBox) v;
					
					if (box.isChecked() && (!addlist.contains(box.getText().toString()))) {
						addlist.add(box.getText().toString());
						if (removelist.contains(box.getText().toString())) {
							removelist.remove(box.getText().toString());
						}
						
					}
					if ((!box.isChecked()) && (!removelist.contains(box.getText().toString()))) {
						removelist.add(box.getText().toString());
						if (addlist.contains(box.getText().toString())) {
							addlist.remove(box.getText().toString());
						}
					}
				}
			});
			//回显，要注意这里只是实现界面，添加到数据库操作在其他地方
			if (addlist.contains(sourceitem.getName())) {
				holder.cb_add.setChecked(true);
			}else if(removelist.contains(sourceitem.getName())){
				holder.cb_add.setChecked(false);
			}
			return v;
		}
		
		class viewholder{
			TextView tv_sourcelist_name;
			CheckBox cb_add;
		}

	}
	public void addchecked(View v){
		//确认选择，添加到gridview中，返回.homeActivity  类似完成功能
		for (String additem:addlist) {
				dao.addtohome(additem);
			
		}
		for (String removitem:removelist) {
			dao.removefromhome(removitem);
		}
		back();
	}

	private void back() {
		finish();
		overridePendingTransition(R.anim.pre_enteranim, R.anim.pre_exitanim);
	}
	
	public void backhome(View v) {
		back();
	}
	
	@Override
	protected void onResume() {
		NightModeUpdate.update(this);
		super.onResume();
	}
	
	@Override
	public void onBackPressed() {
		back();
	}
	
}
