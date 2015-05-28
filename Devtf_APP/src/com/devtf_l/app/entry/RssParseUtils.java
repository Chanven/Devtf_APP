package com.devtf_l.app.entry;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.devtf_l.app.BuildConfig;

/**
 * @desc RSS2解析工具（网络请求拿到RSS2数据流后通过parseRss方法解析得到结果集）
 * @author ljh lijunhuayc@sina.com 2015-5-20 // * @deprecated 废弃，RSS2资源只有10条数据不全
 */
public class RssParseUtils {
	static RssHead rssHead = null;
	static RssItem rssItem = null;

	public final static List<RssItem> parseRss(InputStream in) throws IOException, XmlPullParserException {
		List<RssItem> rssList = null;
		XmlPullParser xpp = XmlUtils.getInstance().getXpp();
		// xpp.setInput(in, null);//null表示不对流编码
		Reader reader = new InputStreamReader(in);
		xpp.setInput(reader);
		int eventType = xpp.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					// System.out.println("文档开始");
					rssList = new ArrayList<RssItem>();
					break;
				case XmlPullParser.START_TAG:
					String tagName = xpp.getName();
					if ("channel".equals(tagName)) {
						rssHead = new RssHead();
					} else if ("item".equals(tagName)) {
						rssItem = new RssItem();
					}
					if (null != rssItem) {
						parseRssItem(xpp, tagName);
					} else if (null != rssHead) {
						/**
						 * RSS分页数据解析的时候，如果RssHead已被解析过了，则不在重复解析
						 */
						// parseRssHead(xpp, tagName);//不再解析head信息，只需要item信息即可
					}
					break;
				case XmlPullParser.END_TAG:
					String tag = xpp.getName();
					if ("channel".equals(tag)) {
					} else if ("item".equals(tag)) {
						rssList.add(rssItem);
						rssItem = null;
					}
					break;
				default:
					break;
			}
			eventType = xpp.next();
		}
		if (BuildConfig.DEBUG) {
			// Timber.d("RssHead信息：" + rssHead.toString() + "\n");
			// Timber.d("RssHead item0：" + rssList.get(0).toString());
		}
		return rssList;
	}

	/**
	 * 解析item信息
	 * 
	 * @param xpp
	 * @param tagName
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	protected final static void parseRssItem(XmlPullParser xpp, String tagName) throws XmlPullParserException, IOException {
		/**
		 * 解析item
		 */
		if ("title".equals(tagName)) {
			xpp.next();
			rssItem.setTitle(notNullString(xpp.getText()));
		} else if ("link".equals(tagName)) {
			xpp.next();
			rssItem.setLink(notNullString(xpp.getText()));
		} else if ("comments".equals(tagName) && null == xpp.getPrefix()) {
			xpp.next();
			rssItem.setComments(notNullString(xpp.getText()));
		} else if ("pubDate".equals(tagName)) {
			xpp.next();
			rssItem.setPubDate(notNullString(xpp.getText()));
		} else if ("creator".equals(tagName) && "dc".equals(xpp.getPrefix())) {
			xpp.next();
			rssItem.setDc_creator(notNullString(xpp.getText()));
		} else if ("category".equals(tagName)) {
			xpp.next();
			rssItem.setCategory(notNullString(xpp.getText()));
		} else if ("guid".equals(tagName)) {
			xpp.next();
			rssItem.setGuid(notNullString(xpp.getText()));
		} else if ("description".equals(tagName)) {
			xpp.next();
			rssItem.setDescription(notNullString(xpp.getText()));
		} else if ("encoded".equals(tagName) && "content".equals(xpp.getPrefix())) {
			xpp.next();
			rssItem.setContent_encoded(notNullString(xpp.getText()));
		} else if ("commentRss".equals(tagName) && "wfw".equals(xpp.getPrefix())) {
			xpp.next();
			rssItem.setWfw_commentRss(notNullString(xpp.getText()));
		} else if ("comments".equals(tagName) && "slash".equals(xpp.getPrefix())) {
			xpp.next();
			rssItem.setSlash_comments(notNullString(xpp.getText()));
		}
	}

	private static String notNullString(String src) {
		return null == src ? "" : src;
	}

	/**
	 * 解析channel信息
	 * 
	 * @param xpp
	 * @param tagName
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	protected final static void parseRssHead(XmlPullParser xpp, String tagName) throws XmlPullParserException, IOException {
		/**
		 * 解析channel信息
		 */
		if ("title".equals(tagName)) {
			xpp.next();
			rssHead.setTitle(notNullString(xpp.getText()));
		} else if ("link".equals(tagName) && null == xpp.getPrefix()) {
			// xpp.getNamespace().isEmpty()
			xpp.next();
			rssHead.setLink(notNullString(xpp.getText()));
		} else if ("description".equals(tagName)) {
			xpp.next();
			rssHead.setDescription(notNullString(xpp.getText()));
		} else if ("lastBuildDate".equals(tagName)) {
			xpp.next();
			rssHead.setLastBuildDate(notNullString(xpp.getText()));
		} else if ("language".equals(tagName)) {
			xpp.next();
			rssHead.setLanguage(notNullString(xpp.getText()));
		}
		// **以下属性基本不使用，不做解析，提供解析速度
		else if ("updatePeriod".equals(tagName) && "sy".equals(xpp.getPrefix())) {
			xpp.next();
			rssHead.setSy_updatePeriod(notNullString(xpp.getText()));
		} else if ("updateFrequency".equals(tagName) && "sy".equals(xpp.getPrefix())) {
			xpp.next();
			rssHead.setSy_updateFrequency(notNullString(xpp.getText()));
		} else if ("generator".equals(tagName)) {
			xpp.next();
			rssHead.setGenerator(notNullString(xpp.getText()));
		}
		// */
	}
}
