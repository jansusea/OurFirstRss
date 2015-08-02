package com.skyrss.setting;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.skyrss.R;
import com.skyrss.global.NightModeUpdate;

public class UserFeedbackActivity extends Activity {

	private ProgressDialog pDialog;

	JSONParser jsonParser;
	EditText inputName;
	EditText inputContacts;
	EditText inputDesc;
	ImageButton back;

	private static String url_upload = "http://skyrssreader.sinaapp.com/up.php";
	private static final String TAG_MESSAGE = "message";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting_userfeedback);
		jsonParser = new JSONParser();
		inputName = (EditText) findViewById(R.id.et_inputName);
		inputContacts = (EditText) findViewById(R.id.et_inputContacts);
		inputDesc = (EditText) findViewById(R.id.et_inputDesc);
		back = (ImageButton) findViewById(R.id.ib_back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

		Button bt_commit = (Button) findViewById(R.id.bt_commit);
		bt_commit.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				if (DescCheckNotNull()) {
					new Upload().execute();
				}
			}
		});
	}

	// 判断评论是否为空
	private boolean DescCheckNotNull() {
		String description = inputDesc.getText().toString().trim();
		if (description.equals("")) {
			AlertDialog.Builder dialog = new AlertDialog.Builder(this);
			dialog.setTitle("警告");
			dialog.setMessage("评论不能为空!");
			dialog.setPositiveButton("确定", null);
			dialog.show();
			return false;
		}
		return true;
	}

	//上传的异步消息处理类
	class Upload extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(UserFeedbackActivity.this);
			pDialog.setMessage("正在上传..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		protected String doInBackground(String... args) {
			String name = inputName.getText().toString();
			String contacts = inputContacts.getText().toString();
			String description = inputDesc.getText().toString();

			// 用list存储我们需要传入的键值对
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("username", name));
			params.add(new BasicNameValuePair("contact", contacts));
			params.add(new BasicNameValuePair("comments", description));

			// 将数据发送到服务器，并获得返回的json消息
			try {
				JSONObject json = jsonParser.makeHttpRequest(url_upload,
						"POST", params);
				if (json != null) {
					String message = json.getString(TAG_MESSAGE);
					return message;
				}else{
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return "";
			}
		}

		// 获取服务器返回消息
		protected void onPostExecute(String message) {
			pDialog.dismiss();
			if(message!=null){
				Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(getApplicationContext(), "网络连接错误，请检查网络 连接", Toast.LENGTH_SHORT).show();
			}
		}

	}

	@Override
	protected void onResume() {
		NightModeUpdate.update(this);
		super.onResume();
	}

}
