/**
 * Copyright (C) 2001 Yasna.com. All rights reserved.
 *
 * ===================================================================
 * The Apache Software License, Version 1.1
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by
 *        Yasna.com (http://www.yasna.com)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Yazd" and "Yasna.com" must not be used to
 *    endorse or promote products derived from this software without
 *    prior written permission. For written permission, please
 *    contact yazd@yasna.com.
 *
 * 5. Products derived from this software may not be called "Yazd",
 *    nor may "Yazd" appear in their name, without prior written
 *    permission of Yasna.com.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL YASNA.COM OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of Yasna.com. For more information
 * on Yasna.com, please see <http://www.yasna.com>.
 */

package com.manihot.xpc.util;

import java.util.*;
import java.io.*;

/**
 * obtains the properties for the table name
 */
public class PropertyManager {

	private static PropertyManager manager = null;
	private static Object managerLock = new Object();
	private static String propsName = "/System.xml";

	/**
	 * Returns a Yazd property.
	 * 
	 * @param name
	 *            the name of the property to return.
	 * @return the property value specified by name.
	 */
	public static String getProperty(String name) {
		if (manager == null) {
			synchronized (managerLock) {
				if (manager == null) {
					manager = new PropertyManager(propsName);
				}
			}
		}
		return manager.getProp(name);
	}

	/**
	 * Returns the names of the Yazd properties.
	 * 
	 * @return an Enumeration of the Yazd property names.
	 */
	public static Enumeration<?> propertyNames() {
		if (manager == null) {
			synchronized (managerLock) {
				if (manager == null) {
					manager = new PropertyManager(propsName);
				}
			}
		}
		return manager.propNames();
	}

	/**
	 * Returns true if the properties are readable. This method is mainly
	 * valuable at setup time to ensure that the properties file is setup
	 * correctly.
	 */
	public static boolean propertyFileIsReadable() {
		if (manager == null) {
			synchronized (managerLock) {
				if (manager == null) {
					manager = new PropertyManager(propsName);
				}
			}
		}
		return manager.propFileIsReadable();
	}

	/**
	 * Returns true if the properties file exists where the path property
	 * purports that it does.
	 */
	public static boolean propertyFileExists() {
		if (manager == null) {
			synchronized (managerLock) {
				if (manager == null) {
					manager = new PropertyManager(propsName);
				}
			}
		}
		return manager.propFileExists();
	}

	private Properties properties = null;
	private Object propertiesLock = new Object();
	private String resourceURI;

	/**
	 * Creates a new SystemProperty. Singleton access only.
	 */
	private PropertyManager(String resourceURI) {
		this.resourceURI = resourceURI;
	}

	/**
	 * Gets a Yazd property. Yazd properties are stored in yazd.properties. The
	 * properties file should be accesible from the classpath. Additionally, it
	 * should have a path field that gives the full path to where the file is
	 * located. Getting properties is a fast operation.
	 * 
	 * @param name
	 *            the name of the property to get.
	 * @return the property specified by name.
	 */
	protected String getProp(String name) {
		// If properties aren't loaded yet. We also need to make this thread
		// safe, so synchronize...
		if (properties == null) {
			synchronized (propertiesLock) {
				// Need an additional check
				if (properties == null) {
					loadProps();
				}
			}
		}
		String property = properties.getProperty(name);
		if (property == null) {
			return null;
		} else {
			return property.trim();
		}
	}

	/**
	 * Sets a Yazd property. If the property doesn't already exists, a new one
	 * will be created.
	 * 
	 * @param name
	 *            the name of the property being set.
	 * @param value
	 *            the value of the property being set.
	 */
	public static void setProperty(String name, String value) {
		if (manager == null) {
			synchronized (managerLock) {
				if (manager == null) {
					manager = new PropertyManager(propsName);
				}
			}
		}
		manager.setProp(name, value);
	}

	/**
	 * Sets a Yazd property. Because the properties must be saved to disk every
	 * time a property is set, property setting is relatively slow.
	 */
	protected void setProp(String name, String value) {
		// Only one thread should be writing to the file system at once.
		synchronized (propertiesLock) {
			// Create the properties object if necessary.
			if (properties == null) {
				loadProps();
			}
			properties.setProperty(name, value);
			// saveProps();
		}
	}

	protected Enumeration<?> propNames() {
		// If properties aren't loaded yet. We also need to make this thread
		// safe, so synchronize...
		if (properties == null) {
			synchronized (propertiesLock) {
				// Need an additional check
				if (properties == null) {
					loadProps();
				}
			}
		}
		return properties.propertyNames();
	}

	/**
	 * Loads Yazd properties from the disk.
	 */
	private void loadProps() {
		properties = new Properties();
		InputStream in = null;
		try {
			in = getClass().getResourceAsStream(resourceURI);
			properties.loadFromXML(in);

			if (properties.getProperty("PeriodBetweenPosts") == null) {
				properties.setProperty("PeriodBetweenPosts", "60000"); // 1
				// minutes
			}
			if (properties.getProperty("WaitPeriod") == null) {
				properties.setProperty("WaitPeriod", "10000"); // 10 seconds
			}
			if (properties.getProperty("JNDI.dataprovider") == null) {
				properties.setProperty("JNDI.dataprovider",
						"java:/comp/env/jdbc/yazd"); // default jndi name
			}
			if (properties.getProperty("RemoveNotActiveAccounts") == null) {
				properties.setProperty("RemoveNotActiveAccounts", "false"); // default
				// jndi
				// name
			}
		} catch (Exception e) {
			System.err
					.println("Error reading Yazd System properties in SystemProperty.loadProps() "
							+ e);
			System.err
					.println("loading the default properties for User table ");
			properties = getDefaultProperties();
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Returns true if the properties are readable. This method is mainly
	 * valuable at setup time to ensure that the properties file is setup
	 * correctly.
	 */
	public boolean propFileIsReadable() {
		try {
			getClass().getResourceAsStream(resourceURI);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Returns true if the yazd.properties file exists where the path property
	 * purports that it does.
	 */
	public boolean propFileExists() {
		String path = getProp("path");
		if (path == null) {
			return false;
		}
		File file = new File(path);
		if (file.isFile()) {
			return true;
		} else {
			return false;
		}
	}

	private Properties getDefaultProperties() {
		Properties prop = new Properties();
		prop.setProperty("User.Table", "yazdUser");
		prop.setProperty("User.Column.UserID", "userID");
		prop.setProperty("User.Column.Name", "name");
		prop.setProperty("User.Column.Username", "username");
		prop.setProperty("User.Column.PasswordHash", "passwordHash");
		prop.setProperty("User.Column.Email", "email");
		prop.setProperty("WaitPeriod", "10000"); // 10 seconds
		prop.setProperty("RemoveNotActiveAccounts", "false");
		prop.setProperty("PeriodBetweenPosts", "60000"); // 1 minutes
		prop.setProperty("JNDI.dataprovider", "java:/comp/env/jdbc/yazd");
		return prop;
	}

}
