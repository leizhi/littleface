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
import javax.sql.DataSource;
import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.manihot.xpc.util.PropertyManager;

// import org.nemesis.forum.config.ConfigLoader;

/**
 * Central manager of database connections. All methods are static so that they
 * can be easily accessed throughout the classes in the database package.
 */
public class DbConnectionManager {

	static protected Log log = LogFactory.getLog(DbConnectionManager.class);

	private static DbConnectionProvider connectionProvider;
	private static Object providerLock = new Object();
	private static boolean AppServerPooler = false;
	private static boolean checkedPooler = false;
	private static DataSource appServerSource;
	public static final String CONTEXT_JDBC_NAME = PropertyManager
			.getProperty("JNDI.dataprovider");

	/**
	 * Returns a database connection from the currently active connection
	 * provider.
	 */
	public static Connection getConnection() {
		Connection con = null;
		if (appServerSource == null && !checkedPooler) {
			checkedPooler = true;
			try {
				InitialContext ctxt = new InitialContext();
				appServerSource = (DataSource) ctxt.lookup(CONTEXT_JDBC_NAME);
				AppServerPooler = true;
				System.err
						.println("Yazd got a connection provider from app server ("
								+ CONTEXT_JDBC_NAME + ")");
			} catch (Exception e) {
				System.err.println("Failed to find an application datasource ("
						+ CONTEXT_JDBC_NAME + "): " + e.getMessage());
			}
		}
		if (connectionProvider == null && !AppServerPooler) {
			synchronized (providerLock) {
				if (connectionProvider == null) {
					// Attempt to load the connection provider classname as
					// a Yazd property.
					String className = PropertyManager
							.getProperty("connectionProvider.className");
					if (className != null) {
						// Attempt to load the class.
						try {
							Class<?> conClass = Class.forName(className);
							connectionProvider = (DbConnectionProvider) conClass
									.newInstance();
						} catch (Exception e) {
							System.err
									.println("Warning: failed to create the "
											+ "connection provider specified by connection"
											+ "Provider.className. Using the default pool.");
							connectionProvider = new DbConnectionDefaultPool();
						}
					} else {
						connectionProvider = new DbConnectionDefaultPool();
					}
					connectionProvider.start();
				}
			}
		}
		if (AppServerPooler) {
			try {
				con = appServerSource.getConnection();
			} catch (Exception e) {
				System.err
						.println("There was a problem obtaining a connection from application server :"
								+ e.getMessage());
			}
		} else {
			con = connectionProvider.getConnection();
		}
		if (con == null) {
			System.err.println("WARNING: DbConnectionManager.getConnection() "
					+ "failed to obtain a connection.");
		}
		return con;
	}

	/**
	 * Returns the current connection provider. The only case in which this
	 * method should be called is if more information about the current
	 * connection provider is needed. Database connections should always be
	 * obtained by calling the getConnection method of this class.
	 */
	public static DbConnectionProvider getDbConnectionProvider() {
		return connectionProvider;
	}

	/**
	 * Sets the connection provider. The old provider (if it exists) is shut
	 * down before the new one is started. A connection provider <b>should
	 * not</b> be started before being passed to the connection manager because
	 * the manager will call the start() method automatically.
	 */
	public static void setDbConnectionProvider(DbConnectionProvider provider) {
		synchronized (providerLock) {
			if (connectionProvider != null) {
				connectionProvider.destroy();
				connectionProvider = null;
			}
			connectionProvider = provider;
			provider.start();
		}
	}

}
