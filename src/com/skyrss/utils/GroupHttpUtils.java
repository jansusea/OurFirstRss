package com.skyrss.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GroupHttpUtils {
	public static void sendHttpRequest(final String adress,
			final HttpCallbackListener listener) {

		new Thread(new Runnable() {

			@Override
			public void run() {
				HttpURLConnection conn = null;
				try {
					URL url = new URL(adress);
					conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setConnectTimeout(8000);
					conn.setReadTimeout(8000);

					if (conn.getResponseCode() == 200) {

						InputStream in = conn.getInputStream();
						BufferedReader reader = new BufferedReader(
								new InputStreamReader(in));
						StringBuilder reponse = new StringBuilder();
						String line;
						while ((line = reader.readLine()) != null) {
							reponse.append(line);
						}
						if (listener != null) {
							listener.onFinish(reponse.toString());
						}
					}

				} catch (Exception e) {
					if (listener != null) {
						listener.onError(e);
					}
					e.printStackTrace();
				} finally {

					if (conn != null) {
						conn.disconnect();
					}
				}

			}
		}).start();
	}
}
