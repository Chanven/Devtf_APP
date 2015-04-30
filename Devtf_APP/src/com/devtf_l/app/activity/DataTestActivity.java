package com.devtf_l.app.activity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.devtf_l.app.R;
import com.devtf_l.app.entry.RssHead;
import com.devtf_l.app.entry.RssItem;
import com.devtf_l.app.entry.XmlUtils;

public class DataTestActivity extends Activity {
	String rss2Url = "http://www.devtf.cn/?feed=rss2";
	RequestQueue mRequestQueue;
	Context mContext;
	
	RssHead rssHead = null;
	List<RssItem> rssList = null;
	RssItem rssItem = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		setContentView(R.layout.fragment_about_layout);
		mRequestQueue = Volley.newRequestQueue(mContext);
		
		StringRequest mStrRequest = new StringRequest(Request.Method.POST, rss2Url, new Listener<String>() {
			@Override
			public void onResponse(String response) {
				
			}
		}, new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
			}
		});
		
		new Thread() {
			public void run() {
				try {
					URL url = new URL(rss2Url);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.connect();
					if (conn.getResponseCode() == 200) {
						InputStream in = conn.getInputStream();
						ParseRss(0, in);
					}
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (XmlPullParserException e) {
					e.printStackTrace();
				}
			};
		}.start();
	}

	private void ParseRss(int thd, InputStream in) throws IOException, XmlPullParserException {
		XmlPullParser xpp = XmlUtils.getInstance().getXpp();
//		xpp.setInput(in, null);//null表示不对流编码
		Reader reader = new InputStreamReader(in);
		xpp.setInput(reader);
		int eventType = xpp.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					System.out.println("文档开始");
					rssList = new ArrayList<RssItem>();
					break;
				case XmlPullParser.START_TAG:
					String tagName = xpp.getName();
					if("channel".equals(tagName)){
						rssHead = new RssHead();
					}else if("item".equals(tagName)){
						rssItem = new RssItem();
					}
					
					if(null != rssItem){
						parseRssItem(xpp, tagName);
					}else if(null != rssHead){
						/**
						 * RSS分页数据解析的时候，如果RssHead已被解析过了，则不在重复解析
						 */
						//TODO ...
						parseRssHead(xpp, tagName);
					}
					break;
				case XmlPullParser.END_TAG:
					String tag = xpp.getName();
					if("channel".equals(tag)){
					}else if("item".equals(tag)){
						rssList.add(rssItem);
						rssItem = null;
					}
					break;
				default:
					break;
			}
			eventType = xpp.next();
		}
		System.out.println(rssHead.toString());
		System.out.println(rssList.get(0).toString());
		
	}

	/**
	 * 解析item信息
	 * @param xpp
	 * @param tagName
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	private void parseRssItem(XmlPullParser xpp, String tagName) throws XmlPullParserException, IOException {
		/**
		 * 解析item
		 */
		if("title".equals(tagName)){
			xpp.next();
			rssItem.setTitle(xpp.getText());
		}else if("link".equals(tagName)){
			xpp.next();
			rssItem.setLink(xpp.getText());
		}else if("comments".equals(tagName) && null == xpp.getPrefix()){
			xpp.next();
			rssItem.setComments(xpp.getText());
		}else if("pubDate".equals(tagName)){
			xpp.next();
			rssItem.setPubDate(xpp.getText());
		}else if("creator".equals(tagName) && "dc".equals(xpp.getPrefix())){
			xpp.next();
			rssItem.setDc_creator(xpp.getText());
		}else if("category".equals(tagName)){
			xpp.next();
			rssItem.setCategory(xpp.getText());
		}else if("guid".equals(tagName)){
			xpp.next();
			rssItem.setGuid(xpp.getText());
		}else if("description".equals(tagName)){
			xpp.next();
			rssItem.setDescription(xpp.getText());
		}else if("encoded".equals(tagName) && "content".equals(xpp.getPrefix())){
			xpp.next();
			rssItem.setContent_encoded(xpp.getText());
		}else if("commentRss".equals(tagName) && "wfw".equals(xpp.getPrefix())){
			xpp.next();
			rssItem.setWfw_commentRss(xpp.getText());
		}else if("comments".equals(tagName) && "slash".equals(xpp.getPrefix())){
			xpp.next();
			rssItem.setSlash_comments(xpp.getText());
		}
	}
	
	/**
	 * 解析channel信息
	 * @param xpp
	 * @param tagName
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	private void parseRssHead(XmlPullParser xpp, String tagName) throws XmlPullParserException, IOException {
		/**
		 * 解析channel信息
		 */
		if ("title".equals(tagName)) {
			xpp.next();
			rssHead.setTitle(xpp.getText());
		} else if ("link".equals(tagName) && null == xpp.getPrefix()) {
			//xpp.getNamespace().isEmpty()
			xpp.next();
			rssHead.setLink(xpp.getText());
		} else if ("description".equals(tagName)) {
			xpp.next();
			rssHead.setDescription(xpp.getText());
		} else if ("lastBuildDate".equals(tagName)) {
			xpp.next();
			rssHead.setLastBuildDate(xpp.getText());
		} else if ("language".equals(tagName)) {
			xpp.next();
			rssHead.setLanguage(xpp.getText());
		}
		//**以下属性基本不使用，不做解析，提供解析速度 
		else if("updatePeriod".equals(tagName) && "sy".equals(xpp.getPrefix())){
			xpp.next();
			rssHead.setSy_updatePeriod(xpp.getText());
		}else if("updateFrequency".equals(tagName) && "sy".equals(xpp.getPrefix())){
			xpp.next();
			rssHead.setSy_updateFrequency(xpp.getText());
		}else if("generator".equals(tagName)){
			xpp.next();
			rssHead.setGenerator(xpp.getText());
		}
		//*/						
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		mRequestQueue.cancelAll(this);
	}
}
