package com.news;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class NewsActivity extends Activity {

	WebView mWebView;
	
	static String TAG = "News";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		mWebView = (WebView) findViewById(R.id.webview);
		if(null == mWebView ){
			Log.e(TAG, "mWebView is null!");
		}
		mWebView.setWebViewClient(new NewsClient());
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setDomStorageEnabled(true);
		mWebView.loadUrl("file:///android_asset/www/index.html");
		Log.d(TAG, "onCreate Method done.");
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
			mWebView.goBack();
			Log.d(TAG, "onKeyDown method done.");
			return true;
		}	
		return super.onKeyDown(keyCode, event);
	}

	private class NewsClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			Log.d(TAG, "URL: "+ url);
			view.loadUrl("javascript:changeLocation(" + url + ")");
			Log.d(TAG, "Back from loading url");
			return true;
		}
	}
	
}