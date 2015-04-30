package com.devtf_l.app.entry;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/**
 * @desc XmlPullParser单例，避免每次解析的时候创建解析器
 * @author ljh
 * lijunhuayc@sina.com 2015-4-25
 */
public class XmlUtils {
	private static XmlUtils xmlUtils = null;
	private static XmlPullParserFactory xpf = null;
	private static XmlPullParser xpp = null;
	
	private XmlUtils() {
	}
	
	public static final XmlUtils getInstance(){
		if(null == xmlUtils){
			synchronized (XmlUtils.class) {
				if(null == xmlUtils){
					xmlUtils = new XmlUtils();
					try {
						xpf = XmlPullParserFactory.newInstance();
						xpf.setNamespaceAware(true);
						xpp = xpf.newPullParser();
					} catch (XmlPullParserException e) {
						xmlUtils = null;
						xpf = null;
						xpp = null;
					}
				}
			}
		}
		return xmlUtils;
	}
	
	public XmlPullParser getXpp() {
		return xpp;
	}
}
