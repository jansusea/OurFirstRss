package com.skyrss;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.e;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.skyrss.bean.News;
import com.skyrss.bean.NewsDetail;
import com.skyrss.bean.NewsDetailForOther;
import com.skyrss.bean.SourceBean;
import com.skyrss.bean.NewsDetail.NewsImage;
import com.skyrss.bean.NewsDetail.NewsItem;
import com.skyrss.bean.NewsDetailForOther.NewsEntry;
import com.skyrss.dao.SourceDao;
import com.skyrss.global.NightModeUpdate;
import com.skyrss.ui.RefreshListView;
import com.skyrss.ui.RefreshListView.onRefreshListener;
import com.skyrss.utils.ChangeAppBrightness;
import com.skyrss.utils.ExtractImgUrlUtil;
import com.skyrss.utils.PubDateFormatUtils;
import com.skyrss.utils.XmlparserUtils;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SourceActivity extends Activity {

	private NewsDetail detail;
	protected NewsDetailForOther detailForOther;
	private News news;
	private ArrayList<NewsItem> itemsShowed;
	private ArrayList<NewsEntry> entriesShowed;
	private TextView tv_source_title;
	private RefreshListView lv_source_titlelist;
	private MyNewsTitleListAdapter adapter;
	private String sourceName;
	private int source;
	private SharedPreferences sp;
	private int count;
	private TextView tv_loading;
	private List<SourceBean> chooseList;
	private SourceDao dao;
	
	private int[] iconlist = {R.drawable.ic_engadget,R.drawable.ic_zhihu,R.drawable.ic_36kr,R.drawable.ic_ifanr,R.drawable.ic_geekpark};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_source);
		initdbSource();
		sp = getSharedPreferences("config", MODE_PRIVATE);

		tv_source_title = (TextView) findViewById(R.id.tv_source_title);
		lv_source_titlelist = (RefreshListView) findViewById(R.id.lv_source_titlelist);
		tv_loading = (TextView) findViewById(R.id.tv_source_loading);

		Intent intent = getIntent();
		source = intent.getIntExtra("source", 0);
		sourceName = intent.getStringExtra("sourcename");
		
		itemsShowed = new ArrayList<NewsDetail.NewsItem>();
		entriesShowed = new ArrayList<NewsDetailForOther.NewsEntry>();
		
		lv_source_titlelist.setOnRefreshListner(new onRefreshListener() {
			
			@Override
			public void onRrefesh() {
				// TODO Auto-generated method stub
				getDataFromInternet(source, false);
			}
			
			@Override
			public void onLoadmore(int lastPosition) {
				// TODO Auto-generated method stub
				if (lastPosition == (detail==null?detailForOther.entries.size():detail.items.size())) {
				}else {
					getDataFromInternet(source, true);
				}
			}
			
		});
		getDataFromInternet(source, false);

	}
	
	private void initdbSource(){
		chooseList = new ArrayList<SourceBean>();
		dao = new SourceDao(this);
		chooseList = dao.getAllSource();//获取所有的，不是所有选中。。。
	}

	private void getDataFromInternet(int sourceid, boolean isLoadingMore) {

		if (!isLoadingMore) {
			HttpUtils httpUtils = new HttpUtils();
			httpUtils.send(HttpMethod.GET, chooseList.get(sourceid - 1).getUrl(),
					new RequestCallBack<String>() {
				
				//sourceid应该不用减1吧？？

				@Override
				public void onFailure(HttpException error, String msg) {
					// TODO Auto-generated method stub
					error.printStackTrace();
					Log.w("connect", msg);
					Toast.makeText(getApplicationContext(), "网络连接失败", 0).show();
				}
				
				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					news = XmlparserUtils.Xmlparse(arg0.result);
					if (news instanceof NewsDetail) {
						detail = (NewsDetail) news;
					} else {
						detailForOther = (NewsDetailForOther) news;
					}
					if (detail == null) {
						entriesShowed.clear();
					} else {
						itemsShowed.clear();
					}
					for (count = 0; count < 10 && count < (detail==null?detailForOther.entries.size():detail.items.size()); count++) {
						if (detail == null) {
							entriesShowed.add(detailForOther.entries.get(count));
						} else {
							itemsShowed.add(detail.items.get(count));
						}
					}
					
					tv_source_title.setText(sourceName);
					adapter = new MyNewsTitleListAdapter();
					lv_source_titlelist.setAdapter(adapter);
					
					lv_source_titlelist.setVisibility(View.VISIBLE);
					tv_loading.setVisibility(View.INVISIBLE);
					
					lv_source_titlelist
					.setOnItemClickListener(new OnItemClickListener() {
						
						@Override
						public void onItemClick(
								AdapterView<?> parent, View view,
								int position, long id) {
							// TODO Auto-generated method stub
							NewsEntry newsEntry = null;
							NewsItem newsItem = null;
							if (detail == null) {
								newsEntry = detailForOther.entries.get(position - 1);
							} else {
								newsItem = detail.items.get(position - 1);
							}
							setTextColorAndSave(view, detail==null?newsEntry.title:newsItem.title);
							System.out.println(position);
							String date = PubDateFormatUtils
									.formatDate(detail==null?newsEntry.published:newsItem.pubDate);
							
							Intent intent = new Intent(
									SourceActivity.this,
									DetailsActivity.class);
							intent.putExtra("date", date);
							intent.putExtra("title", detail==null?newsEntry.title:newsItem.title);
							intent.putExtra("url", detail==null?newsEntry.id:newsItem.link);
							intent.putExtra("sourcename",
									sourceName);
							startActivity(intent);
						}
						
					});
					
					lv_source_titlelist.onRefreshComplete();
					
					
				}
			});
		} else {
			for (int i = count ; i < count + 10 && i < (detail==null?detailForOther.entries.size():detail.items.size()); i++) {
				if (detail == null) {
					entriesShowed.add(detailForOther.entries.get(i));
				} else {
					itemsShowed.add(detail.items.get(i));
				}
			}
			count += 10;
			if (adapter != null) {
				adapter.notifyDataSetChanged();
			}
			
			lv_source_titlelist.onRefreshComplete();
		}
	}
	
	private void setTextColorAndSave(View view, String readNew) {
		TextView tView = (TextView) view.findViewById(R.id.tv_newstitlelist_title);
		tView.setTextColor(Color.GRAY);
		
		String read = sp.getString("read", "");
		Editor editor = sp.edit();
		editor.putString("read", read + readNew + ",");
		editor.commit();
	}

	class MyNewsTitleListAdapter extends BaseAdapter {

		private BitmapUtils bitmapUtils;

		public MyNewsTitleListAdapter() {
			bitmapUtils = new BitmapUtils(SourceActivity.this);
			if (source - 1 >= iconlist.length) {
				bitmapUtils.configDefaultLoadFailedImage(R.drawable.ic_default);
			} else {
				bitmapUtils.configDefaultLoadFailedImage(iconlist[source - 1]);
			}
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return detail==null?entriesShowed.size():itemsShowed.size();
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
			NewsEntry newsEntry = null;
			NewsItem newsItem = null;
			if (detail == null) {
				newsEntry = entriesShowed.get(position);
			}else {
				newsItem = itemsShowed.get(position);
			}
			View v;
			ViewHolder holder;
			if (convertView == null) {
				v = View.inflate(SourceActivity.this,
						R.layout.items_newstitlelist, null);
				holder = new ViewHolder();
				holder.tv_newstitlelist_title = (TextView) v
						.findViewById(R.id.tv_newstitlelist_title);
				holder.tv_newstitlelist_creator = (TextView) v
						.findViewById(R.id.tv_newstitlelist_creator);
				holder.iv_newstitlelist_img = (ImageView) v
						.findViewById(R.id.iv_newstitlelist_img);
				v.setTag(holder);
			} else {
				v = convertView;
				holder = (ViewHolder) v.getTag();
			}

			holder.tv_newstitlelist_title.setText(detail==null?newsEntry.title:newsItem.title);
			if (newsItem != null) {
				if (newsItem.author == null) {
					holder.tv_newstitlelist_creator.setText(newsItem.creator==null?sourceName:newsItem.creator);
				} else {
					holder.tv_newstitlelist_creator.setText(newsItem.author.isEmpty()?sourceName:newsItem.author);
				}
			} else {
				if (newsEntry.author == null) {
					holder.tv_newstitlelist_creator.setText(sourceName);
				} else {
					holder.tv_newstitlelist_creator.setText(newsEntry.author.isEmpty()?sourceName:newsEntry.author);
				}
			}
			
			String url = "";
			if (newsItem != null) {
				if (newsItem.image == null) {
					if (newsItem.encoded == null) {
						url = ExtractImgUrlUtil.extractUrl(newsItem.description);
					} else {
						url = ExtractImgUrlUtil.extractUrl(newsItem.encoded);
					}
				} else {
					url = newsItem.image;
				}
			} else {
				url = ExtractImgUrlUtil.extractUrl(newsEntry.content);
			}
			
			bitmapUtils.display(holder.iv_newstitlelist_img, url);
			
			String readNews = sp.getString("read", "");
			if (!readNews.isEmpty()) {
				if(readNews.contains(detail==null?newsEntry.title+",":newsItem.title+",")) {
					holder.tv_newstitlelist_title.setTextColor(Color.GRAY);
				} else {
					holder.tv_newstitlelist_title.setTextColor(Color.BLACK);
				}
			}

			return v;
		}

		class ViewHolder {
			public TextView tv_newstitlelist_title;
			public TextView tv_newstitlelist_creator;
			public ImageView iv_newstitlelist_img;
		}

	}
	
	@Override
	protected void onResume() {
		NightModeUpdate.update(this);
		super.onResume();
	}
	
	public void back(View v) {
		back();
	}

	
	@Override
	public void onBackPressed() {
		back();
	}

	private void back() {
		finish();
		overridePendingTransition(R.anim.pre_enteranim, R.anim.pre_exitanim);
	}
}
