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

package com.manihot.xpc.lang;

import com.manihot.xpc.util.LinkedList;

/**
 * Doubly linked node in a LinkedList. Most LinkedList implementations keep the
 * equivalent of this class private. We make it public so that references to
 * each node in the list can be maintained externally.
 * 
 * Exposing this class lets us make remove operations very fast. Remove is built
 * into this class and only requires to reference reassignments. If remove was
 * built into the main LinkedList class, a linear scan would have to be
 * performed to find the correct node to delete.
 * 
 * The linked list implementation was specifically written for the CoolServlets
 * cache system. While it can be used as a general purpose linked list, for most
 * applications, it is more suitable to use the linked list that is part of the
 * Java Collections package.
 * 
 * @see LinkedList
 */
public class LinkedListNode {

	public LinkedListNode previous;
	public LinkedListNode next;
	public Object object;

	/**
	 * This class is further customized for the CoolServlets cache system. It
	 * maintains a timestamp of when a Cacheable object was first added to
	 * cache. Timestamps are stored as long values and represent the number of
	 * milleseconds passed since January 1, 1970 00:00:00.000 GMT.
	 * <p>
	 * 
	 * The creation timestamp is used in the case that the cache has a maximum
	 * lifetime set. In that case, when [current time] - [creation time] > [max
	 * lifetime], the object will be deleted from cache.
	 */
	public long timestamp;

	/**
	 * Constructs a new linked list node.
	 * 
	 * @param object
	 *            the Object that the node represents.
	 * @param next
	 *            a reference to the next LinkedListNode in the list.
	 * @param previous
	 *            a reference to the previous LinkedListNode in the list.
	 */
	public LinkedListNode(Object object, LinkedListNode next,
			LinkedListNode previous) {
		this.object = object;
		this.next = next;
		this.previous = previous;
	}

	/**
	 * Removes this node from the linked list that it is a part of.
	 */
	public void remove() {
		previous.next = next;
		next.previous = previous;
	}

	/**
	 * Returns a String representation of the linked list node by calling the
	 * toString method of the node's object.
	 * 
	 * @return a String representation of the LinkedListNode.
	 */
	public String toString() {
		return object.toString();
	}
}
