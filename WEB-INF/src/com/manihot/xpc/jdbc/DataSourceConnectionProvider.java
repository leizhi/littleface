/*
 * NEMESIS-FORUM.
 * Copyright (C) 2002  David Laurent(lithium2@free.fr). All rights reserved.
 * 
 * Copyright (c) 2000 The Apache Software Foundation. All rights reserved.
 * 
 * Copyright (C) 2001 Yasna.com. All rights reserved.
 * 
 * Copyright (C) 2000 CoolServlets.com. All rights reserved.
 * 
 * NEMESIS-FORUM. is free software; you can redistribute it and/or
 * modify it under the terms of the Apache Software License, Version 1.1,
 * or (at your option) any later version.
 * 
 * NEMESIS-FORUM core framework, NEMESIS-FORUM backoffice, NEMESIS-FORUM frontoffice
 * application are parts of NEMESIS-FORUM and are distributed under
 * same terms of licence.
 * 
 * 
 * NEMESIS-FORUM includes software developed by the Apache Software Foundation (http://www.apache.org/)
 * and software developed by CoolServlets.com (http://www.coolservlets.com).
 * and software developed by Yasna.com (http://www.yasna.com).
 * 
 */

package com.manihot.xpc.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * An implementation of DbConnectionProvider that utilizes a JDBC 2.0 DataSource
 * made available via JNDI. This is useful for application servers where a
 * pooled data connection is already provided so can share the pool with the
 * other applications.
 * <p>
 * 
 * The JNDI location of the DataSource is retrieved from the
 * {@link com.manihot.xpc.util.Yasna.forum.PropertyManager} as the
 * <code>JNDIDataSource.name</code> property. This can be overiden by setting
 * the provider's <code>name</code> property if required.
 * 
 * @author <a href="mailto:joe@truemesh.com">Joe Walnes</a>
 * 
 * @see com.Yasna.forum.database.DbConnectionProvider
 */
public class DataSourceConnectionProvider extends DbConnectionProvider {

	static protected Log log = LogFactory
			.getLog(DataSourceConnectionProvider.class);

	// private static final boolean POOLED = true;

	private DataSource dataSource;

	/**
	 * Keys of JNDI properties to query PropertyManager for.
	 */
	/*
	 * private static final String[] jndiPropertyKeys = { Context.APPLET ,
	 * Context.AUTHORITATIVE , Context.BATCHSIZE , Context.DNS_URL ,
	 * Context.INITIAL_CONTEXT_FACTORY , Context.LANGUAGE ,
	 * Context.OBJECT_FACTORIES , Context.PROVIDER_URL , Context.REFERRAL ,
	 * Context.SECURITY_AUTHENTICATION , Context.SECURITY_CREDENTIALS ,
	 * Context.SECURITY_PRINCIPAL , Context.SECURITY_PROTOCOL ,
	 * Context.STATE_FACTORIES , Context.URL_PKG_PREFIXES };
	 */
	/**
	 * Initialize.
	 */
	public DataSourceConnectionProvider() {

		// properties = new Properties();
		// properties.setProperty("name",Config.DB_JNDI_DATASOURCE);

	}

	/**
	 * Lookup DataSource from JNDI context.
	 */
	protected void start() {
		// java:comp/env/
		Properties props = new Properties();
		props.put("JNDIDataSource", "");

		String name = props.getProperty("JNDIDataSource");
		if (name == null || name.length() == 0) {
			log.error("No name specified for DataSource JNDI lookup - 'name' "
					+ "Property should be set.");
			return;
		}
		try {
			Properties contextProperties = new Properties();
			contextProperties.setProperty("name", name);

			/*
			 * for (int i=0; i<jndiPropertyKeys.length; i++) { String k =
			 * jndiPropertyKeys[i]; String v = PropertyManager.getProperty(k);
			 * if (v != null) { contextProperties.setProperty(k,v); } }
			 */
			Context context = new InitialContext(contextProperties);
			dataSource = (DataSource) context.lookup(name);

			// Context initCtx = new InitialContext();
			// Context envCtx = (Context) initCtx.lookup("java:comp/env");
			// dataSource = (DataSource) envCtx.lookup("jdbc/MyDataSource");

		} catch (Exception e) {
			log.error("Could not lookup DataSource at '" + name + "'"/* ,e */);
		}
	}

	/**
	 * Destroy then start.
	 */
	protected void restart() {

		destroy();
		start();
	}

	/**
	 * Save properties.
	 */
	protected void destroy() {

		return;
	}

	/**
	 * Get new Connection from DataSource.
	 */
	public Connection getConnection() {

		if (dataSource == null) {
			log.error("DataSource has not yet been looked up", null);
			return null;
		}
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			log.error("Could not retrieve Connection from DataSource", e);
			return null;
		}
	}

}
