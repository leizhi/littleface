package com.mooo.mycoz.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXParseException;
import org.xml.sax.SAXException;
import org.xml.sax.Locator;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;

public class SAXParserConf extends DefaultHandler {

	private static Log log = LogFactory.getLog(SAXParserConf.class);

	public String subNode = null;
	public Locator locator = null;
	public String value = null;

	public SAXParserConf() {
		super();
		try {
			SAXParserFactory sf = SAXParserFactory.newInstance();
			SAXParser sp = sf.newSAXParser();
			sp.parse(new InputSource(""), this);

		} catch (Exception e) {
			if (log.isDebugEnabled())
				log.debug("DBLoad Exception:" + e.getMessage());
			e.printStackTrace();
		}
	}

	public void startDocument() throws SAXException {
		if (log.isDebugEnabled())
			log.debug("PigLoad for startDocument");
	}

	public void endDocument() throws SAXException {
		if (log.isDebugEnabled())
			log.debug("PigLoad for endDocument");
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attrs) throws SAXException {

		String attrName;
		for (int i = 0; i < attrs.getLength(); i++) {
			attrName = attrs.getQName(i);
			if (qName.equalsIgnoreCase("action")) {
				subNode = qName;

				attrName.equalsIgnoreCase("type");
				attrName.equalsIgnoreCase("path");
				attrName.equalsIgnoreCase("name");
			}

			if (qName.equalsIgnoreCase("forward")) {
				if (attrName.equalsIgnoreCase("name")) {
				}
				if (attrName.equalsIgnoreCase("path")) {
				}
			}

		} // end for
	}

	public void endElement(String uri, String localName, String qName) {

		if (subNode != null && qName.equals("action")) {
			subNode = null;
		}
	}

	public void fatalError(SAXParseException e) {
		if (log.isDebugEnabled())
			log.debug("SAXParseException:" + e.getMessage() + " At line "
					+ locator.getLineNumber() + ",column "
					+ locator.getColumnNumber());
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		value = new String(ch, start, length);
	}

	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
		// if (log.isDebugEnabled()) log.debug("space" + new String(ch, start,
		// length));
	}

	public void error(SAXParseException e) {
		if (log.isDebugEnabled())
			log.debug("Error:" + e.getMessage() + " At line "
					+ locator.getLineNumber() + ",column "
					+ locator.getColumnNumber());
	}

	public void warning(SAXParseException e) {
		if (log.isDebugEnabled())
			log.debug("Warning:" + e.getMessage() + " At line "
					+ locator.getLineNumber() + ",column "
					+ locator.getColumnNumber());
	}

	// Store the error locator object
	public void setDocumentLocator(Locator lct) {
		locator = lct;
	}
}
