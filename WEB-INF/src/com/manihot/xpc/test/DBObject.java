package com.manihot.xpc.test;

import com.manihot.xpc.cache.*;

public class DBObject implements Cacheable {

	private int id = -1;
	private String name;
	private String description;
	private int forumGroupID;
	private java.util.Date creationDate;
	private java.util.Date modifiedDate;
	private boolean moderated;
	private boolean isarticle = false;
	// Lock for saving state to database.
	private Object saveLock = new Object();
	private int order;
	
	public int getSize() {
		// Approximate the size of the object in bytes by calculating the size
		// of each field.
		/*int size = 0;
		size += CacheSizes.sizeOfObject(); // overhead of object
		size += CacheSizes.sizeOfInt(); // id
		size += CacheSizes.sizeOfString(name); // name
		size += CacheSizes.sizeOfString(description); // description
		size += CacheSizes.sizeOfDate(); // creation date
		size += CacheSizes.sizeOfDate(); // modified date
		size += CacheSizes.sizeOfBoolean(); // moderated
		size += filters.length * 8; // each filter is 8 bytes
		size += CacheSizes.sizeOfProperties(properties);// properties object
		size += CacheSizes.sizeOfObject(); // save lock
		size += CacheSizes.sizeOfBoolean(); // isarticle

		return size;
	*/
		// Approximate the size of the object in bytes by calculating the size
		// of each field.
		int size = 0;
		size += CacheSizes.sizeOfObject(); // overhead of object
		size += CacheSizes.sizeOfInt(); // id
		size += CacheSizes.sizeOfString(name); // name
		size += CacheSizes.sizeOfString(description); // description
		size += CacheSizes.sizeOfDate(); // creation date
		size += CacheSizes.sizeOfDate(); // modified date
		size += CacheSizes.sizeOfBoolean(); // moderated
		size += CacheSizes.sizeOfObject(); // save lock
		size += CacheSizes.sizeOfBoolean(); // isarticle

		return size;
	}
	
}
