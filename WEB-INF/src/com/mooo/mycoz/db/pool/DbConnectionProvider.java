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

package com.mooo.mycoz.db.pool;

import java.sql.Connection;

/**
 * Abstract class that defines the connection provider framework. Other classes
 * extend this abstract class to make connection to actual data sources.
 */
public abstract class DbConnectionProvider {

	private static final boolean POOLED = false;

	/**
	 * Returns true if this connection provider provides connections out of a
	 * connection pool. Implementing and using connection providers that are
	 * pooled is strongly recommended, as they greatly increase the speed of
	 * Yazd.
	 * 
	 * @return true if the Connection objects returned by this provider are
	 *         pooled.
	 */
	public boolean isPooled() {
		return POOLED;
	}

	/**
	 * Returns a database connection. When a Yazd component is done with a
	 * connection, it will call the close method of that connection. Therefore,
	 * connection pools with special release methods are not directly supported
	 * by the connection provider infrastructure. Instead, connections from
	 * those pools should be wrapped such that calling the close method on the
	 * wrapper class will release the connection from the pool.
	 * 
	 * @return a Connection object.
	 */
	public abstract Connection getConnection();

	/**
	 * Starts the connection provider. For some connection providers, this will
	 * be a no-op. However, connection provider users should always call this
	 * method to make sure the connection provider is started.
	 */
	protected abstract void start();

	/**
	 * This method should be called whenever properties have been changed so
	 * that the changes will take effect.
	 */
	protected abstract void restart();

	/**
	 * Tells the connection provider to destroy itself. For many connection
	 * providers, this will essentially result in a no-op. However, connection
	 * provider users should always call this method when changing from one
	 * connection provider to another to ensure that there are no dangling
	 * database connections.
	 */
	protected abstract void destroy();

}
