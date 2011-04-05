package com.mooo.mycoz.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

import com.mooo.mycoz.cache.Cache;
import com.mooo.mycoz.cache.CacheManager;

public class ConfigureUtil {

	private static Object initLock = new Object();
	private static ConfigureUtil factory = null;

	private static Log log = LogFactory.getLog(ConfigureUtil.class);

	private InputStream cacheStream = null;
	private InputStream mvcStream = null;

	private String cacheFile = "/myconfig.xml";
	private String mvcFile = "/myconfig.xml";
	//private String poolFile = "/mypool.xml";

	private HashMap<String, Cache> caches;
	
	private boolean enableAuth;
	
	private Map<String, ActionNode> actionMap;
	
	private boolean enableSample;
	private String sampleKey = "UserSessionKey";
	
	private Vector <String> allowUrl = new Vector<String>();
	
	public static ConfigureUtil getInstance() {

		if (factory == null) {
			synchronized (initLock) {
				if (factory == null) {
					factory = new ConfigureUtil();
				}
			}
		}
		return factory;
	}

	private ConfigureUtil() {
		flush();
		
		enableAuth = false;
		enableSample = false;
	}
	
	public String getCacheFile() {
		return cacheFile;
	}

	public void setCacheFile(String cacheFile) {
		this.cacheFile = cacheFile;
	}

	public String getMvcFile() {
		return mvcFile;
	}

	public void setMvcFile(String mvcFile) {
		this.mvcFile = mvcFile;
	}

	public HashMap<String, Cache> getCaches() {
		return caches;
	}

	public void setCaches(HashMap<String, Cache> caches) {
		this.caches = caches;
	}

	public boolean isEnableAuth() {
		return enableAuth;
	}

	public void setEnableAuth(boolean enableAuth) {
		this.enableAuth = enableAuth;
	}

	public Map<String, ActionNode> getActionMap() {
		return actionMap;
	}

	public void setActionMap(Map<String, ActionNode> actionMap) {
		this.actionMap = actionMap;
	}

	public String getSampleKey() {
		return sampleKey;
	}

	public void setSampleKey(String sampleKey) {
		this.sampleKey = sampleKey;
	}

	public Vector<String> getAllowUrl() {
		return allowUrl;
	}

	public void setAllowUrl(Vector<String> allowUrl) {
		this.allowUrl = allowUrl;
	}

	public boolean isEnableSample() {
		return enableSample;
	}

	public void setEnableSample(boolean enableSample) {
		this.enableSample = enableSample;
	}

	public void flush() {
		try {
			cacheStream = new FileInputStream(cacheFile);
			mvcStream = new FileInputStream(mvcFile);
		} catch (FileNotFoundException e) {
			cacheStream = getClass().getResourceAsStream(cacheFile);
			mvcStream = getClass().getResourceAsStream(mvcFile);
		}
	}

	public void configure() {
		try {
			flush();

			SAXReader saxReader = new SAXReader();
			saxReader.setEntityResolver(
					new EntityResolver() {
						public InputSource resolveEntity(String publicId,String systemId) {
							if (publicId.equals("-//Apache Software Foundation//DTD Struts Configuration 2.0//EN")) {
								InputStream in = getClass().getResourceAsStream("/struts-2.0.dtd");
								return new InputSource(in);
							}
							return null;
						}
					});
			if(log.isDebugEnabled()) log.debug("SAXReader start");
			
			Document doc;
			Element root;
			
			// cache configure
			doc = saxReader.read(cacheFile);
			root = doc.getRootElement();
			
			caches = new HashMap<String, Cache>();
			
			Iterator<?> itrCache = root.selectNodes("package/cache").iterator();
			Element cacheNode;
			String value = "K";

			while (itrCache.hasNext()) {
				cacheNode = (Element) itrCache.next();

				int size = Integer.valueOf(cacheNode.attributeValue("size"))
						.intValue();
				value = cacheNode.attributeValue("sizeUnit");
				if (value.equals("K"))
					size = size * CacheManager.K;
				else if (value.equals("M"))
					size = size * CacheManager.M;

				int time = Integer.valueOf(cacheNode.attributeValue("time")).intValue();
				
				value = cacheNode.attributeValue("timeUnit");
				
				if (value.equals("SECOND"))
					time = time * CacheManager.SECOND;
				else if (value.equals("MINUTE"))
					time = time * CacheManager.MINUTE;
				else if (value.equals("HOUR"))
					time = time * CacheManager.HOUR;

				caches.put(cacheNode.attributeValue("name"), new Cache(size,time));
			}

			//mvc configure
			doc = saxReader.read(mvcStream);
			root = doc.getRootElement();

			// parse action methods
			actionMap = new HashMap<String, ActionNode>();
			// String clzName = null;
			Iterator<?> itrAction = root.selectNodes("package/action").iterator();

			Element actionNode;
			Element methodNode;
			ActionNode aNode;
			String action;
			while (itrAction.hasNext()) {
				aNode = new ActionNode();

				actionNode = (Element) itrAction.next();
				// set filed
				action = actionNode.attributeValue("name");
				aNode.setName(action);
				aNode.setCls(actionNode.attributeValue("class"));
				aNode.setDefMethod(actionNode.attributeValue("default"));
				// System.out.println("----------"+action+"----------");
				for (Iterator<?> itrResult = actionNode.selectNodes("result")
						.iterator(); itrResult.hasNext();) {
					methodNode = (Element) itrResult.next();
					aNode.addResult(methodNode.attributeValue("name"),methodNode.getTextTrim());
					// String forwardName = methodNode.attributeValue("name");
					// System.out.println(methodNode.getTextTrim());
					// System.out.println(methodNode.getStringValue());
				}
				actionMap.put(action, aNode);
			}
			
			// auth configure
//			doc = saxReader.read(mvcStream);
//			root = doc.getRootElement();

			Iterator<?> itrAuth = root.selectNodes("package/plugins").iterator();
			Element plugNode;
			String plugName;
			while (itrAuth.hasNext()) {
				plugNode = (Element) itrAuth.next();
				plugName = plugNode.attributeValue("name");
				if(plugName.equals("Auth")){
					value = plugNode.attributeValue("enable");
					if(value.equals("true")){
						enableAuth = true;
					}else{
						enableAuth = false;
					}
				}
			}
			
			// sample configure
//			doc = saxReader.read(cacheStream);
//			root = doc.getRootElement();

			itrCache = root.selectNodes("package/sample").iterator();
			Element node;
			String typeName;
			String url="";

			while (itrCache.hasNext()) {
				node = (Element) itrCache.next();
				typeName = node.attributeValue("type");
				
				value = node.attributeValue("enable");
				if(value.equals("true")){
					enableSample = true;
				}else{
					enableSample = false;
				}
				
				if(typeName.equals("session")){
					sampleKey = node.attributeValue("key");
					Iterator<?> itrAllow = node.selectNodes("allow").iterator();
					while (itrAllow.hasNext()) {
						Element allow = (Element) itrAllow.next();;
						value = allow.attributeValue("name");
						
						if(value !=null && !value.equals("")){
							url=value;
							if(url !=null && !url.equals("")){
								value = allow.attributeValue("method");
								if(value !=null && !value.equals("")){
									url += "?"+value;
								}
							}
						}
					}
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public void printConf() {
		try {
			Iterator<?> iterator;
			String key;
			ActionNode action;

			System.out.println(".............XML...........");

			iterator = actionMap.keySet().iterator();
			while (iterator.hasNext()) {
				key = iterator.next().toString();
				System.out.println("............." + key + "...........");

				action = (ActionNode) actionMap.get(key);
				System.out.println("name=" + action.getName() + " class="
						+ action.getCls());
				Hashtable<String, String> results = action.getResults();
				Iterator<?> resultIterator = results.keySet().iterator();
				while (resultIterator.hasNext()) {
					key = resultIterator.next().toString();
					System.out.println("result key=" + key + " value="
							+ results.get(key));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
