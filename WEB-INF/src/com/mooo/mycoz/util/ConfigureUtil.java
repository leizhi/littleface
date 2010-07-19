package com.mooo.mycoz.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.mooo.mycoz.cache.Cache;
import com.mooo.mycoz.cache.CacheManager;

public class ConfigureUtil {

	private static Log log = LogFactory.getLog(ConfigureUtil.class);

	private static InputStream in = null;

	public ConfigureUtil() {
		in = getClass().getResourceAsStream("/mymain.xml");
		//parseXML();
	}

	public HashMap<String,Cache> confCache() {
		HashMap<String,Cache> caches = new HashMap<String,Cache>();
		try {
			System.out.println(" confCache start..");

			SAXReader saxReader = new SAXReader();
			Document doc = saxReader.read(in);
			Element root = doc.getRootElement();
			System.out.println(" confCache root..");

			Iterator<?> itrCache = root.selectNodes("package/cache").iterator();
			Element cacheNode;
			String value = "K";
			System.out.println(" confCache loop");

			while (itrCache.hasNext()) {
				cacheNode = (Element) itrCache.next();
				System.out.println(" confCache next");

				System.out.println(" confCache loop");

				int size = Integer.valueOf(cacheNode.attributeValue("size")).intValue();
				value = cacheNode.attributeValue("sizeUnit");
				if(value.equals("K"))
					size = size * CacheManager.K;
				else if (value.equals("M"))
					size = size * CacheManager.M;

				int time = Integer.valueOf(cacheNode.attributeValue("time")).intValue();
				value = cacheNode.attributeValue("timeUnit");
				if(value.equals("SECOND"))
					time = time * CacheManager.SECOND;
				else if (value.equals("MINUTE"))
					time = time * CacheManager.MINUTE;
				else if (value.equals("HOUR"))
					time = time * CacheManager.HOUR;
				
				System.out.println(" confCache add");

				caches.put(cacheNode.attributeValue("name"), new Cache(size,time));
			}
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return caches;
	}

	private Map<String, List<String>> controllerXmlMap;
	public void parseXML() {
		try {
			if (log.isDebugEnabled())
				log.debug("ConfigureUtil loading..");

			SAXReader saxReader = new SAXReader();
			Document doc = saxReader.read(in);
			Element root = doc.getRootElement();

			// parse action methods

			controllerXmlMap = new HashMap<String, List<String>>();
			String action = null;
			// String clzName = null;
			List<String> methods = null;

			Iterator<?> itrAction = root.selectNodes("package/action")
					.iterator();

			Element actionNode;
			Element methodNode;

			while (itrAction.hasNext()) {
				actionNode = (Element) itrAction.next();

				action = actionNode.attributeValue("name");

				// System.out.println("----------"+action+"----------");
				methods = new ArrayList<String>();

				for (Iterator<?> itrResult = actionNode.selectNodes("result")
						.iterator(); itrResult.hasNext();) {
					methodNode = (Element) itrResult.next();

					String forwardName = methodNode.attributeValue("name");
					// String forwardType = methodNode.attributeValue("type");
					// System.out.println("forwardName="+forwardName);

					methods.add(forwardName);

				}
				controllerXmlMap.put(action, methods);

			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public void print() {
		try {
			Iterator<?> iterator;
			String key;
			List<String> actions;

			System.out.println(".............XML...........");

			iterator = controllerXmlMap.keySet().iterator();
			while (iterator.hasNext()) {
				key = iterator.next().toString();
				System.out.println("............." + key + "...........");

				actions = (List<String>) controllerXmlMap.get(key);
				for (Iterator<String> it = actions.iterator(); it.hasNext();) {
					System.out.println(it.next());
					// it.next();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
