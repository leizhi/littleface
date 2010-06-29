package com.manihot.xpc.tools;

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

public class SampleParseXML {

	private static Log log = LogFactory.getLog(SampleParseXML.class);

	InputStream in = null;

	public SampleParseXML() {
		in = getClass().getResourceAsStream("/mysocket.xml");
		parseXML();
	}

	private Map<String, List<String>> controllerXmlMap;

	public void parseXML() {
		try {
			if(log.isDebugEnabled()) log.debug("parseXML start..");
			
			SAXReader saxReader = new SAXReader();
			Document doc = saxReader.read(in);
			Element root = doc.getRootElement();

			// parse action methods

			controllerXmlMap = new HashMap<String, List<String>>();
			String action = null;
			// String clzName = null;
			List<String> methods = null;

			Iterator<?> itrAction = root.selectNodes("package/action").iterator();

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
