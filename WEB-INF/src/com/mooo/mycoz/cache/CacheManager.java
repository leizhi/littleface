package com.mooo.mycoz.cache;

import java.util.HashMap;

import com.mooo.mycoz.util.ConfigureUtil;

public class CacheManager {
    private static Object initLock = new Object();
    private static CacheManager factory = null;

	final public static int SECOND = 1000;
	final public static int MINUTE = SECOND*60;
	final public static int HOUR = MINUTE*60;
	final public static int K = 1024;
	final public static int M = 1024 * K;

    private boolean cacheEnabled = true;

    protected HashMap<String,Cache> caches;
    
    protected int cacheSize=0;

    private CacheManager() {
    	caches = new HashMap<String,Cache>();
    	
    	caches = new ConfigureUtil().confCache();
    	System.out.println("caches.size="+caches.size());
		caches.put((caches.size()+1)+"", new UserPermsCache(128 * K,1 * HOUR));

    	/*
        caches = new Cache[13];

        //Initialize all cache structures
        caches[0] = new Cache(256*1024, 10*MINUTE);
        caches[1] = new Cache(128*1024, 10*MINUTE);
        caches[2] = new Cache(128*1024, 1*HOUR);
        caches[3] = new Cache(128*1024, 1*HOUR);
        caches[4] = new Cache(128*1024, 20*MINUTE);
        caches[5] = new Cache(128*1024, 20*MINUTE);
        caches[6] = new Cache(128*1024, 20*MINUTE);
        caches[7] = new Cache(512*1024, 1*HOUR);
        caches[8] = new Cache(128*1024, 1*HOUR);
        caches[9] = new Cache(128*1024, 1*HOUR);
        caches[10] = new Cache(128*1024, 1*HOUR);
        //The user permissions cache is a special one. It's actually a Cache
        //of Cache objects. Each of the cache objects in the main cache
        //corresponds to a particular forum, and is used to cache the
        //permissions that a user has for a forum. In order to handle this
        //requirement, we use a special subclass of Cache.
        caches[12] = new UserPermsCache(256*1024, 2*HOUR);
        caches[13] = new Cache(128*1024, 1*HOUR);
        */
    }
    
    /**
     * Returns a concrete ForumFactory instance. Permissions corresponding
     * to the Authorization will be used. If getting the factory fails, null
     * will be returned.
     *
     * @param authorization the auth token for the user.
     * @return a concrete ForumFactory instance.
     */
    public static CacheManager getInstance() {

        if (factory == null) {
            synchronized(initLock) {
                if (factory == null) {
                	factory = new CacheManager();
                	/*
                    String classNameProp = PropertyManager.getProperty("ForumFactory.className");
                    if (classNameProp != null) {
                        className = classNameProp;
                    }
                    try {
                        //Load the class and create an instance.
                        Class c = Class.forName(className);
                        factory = (ForumFactory)c.newInstance();
                    }
                    catch (Exception e) {
                        System.err.println("Failed to load ForumFactory class "
                            + className + ". Yazd cannot function normally.");
                        e.printStackTrace();
                        return null;
                    }
                    */
                }
            }
        }

        //Wrap the factory with a proxy to provide security. We also pass
        //in the username and password to the proxy for its special
        //implementation of the getForum() method. See below for more details.
        /*
        ForumFactoryProxy proxy = new ForumFactoryProxy(
                                    factory,
                                    authorization,
                                    factory.getPermissions(authorization)
                                  );
        return proxy;
        */
        return factory;
    }
	
    public Cache getCache(String cacheName) {
        return caches.get(cacheName);
    }

    public void add(String cacheName, Object key, Object object) {
    	caches.get(cacheName).add(key, object);
    }
    
    public Object get(String cacheName, Object key) {
        if (!cacheEnabled) {
            return null;
        }
        return caches.get(cacheName).get(key);
    }
    
    public void remove(String cacheName, Object key) {
    	caches.get(cacheName).remove(key);
        //when cache becomes distributed, we'd send out an expire message
        //here to all other yazd servers.
    }

    public void removeUserPerm(Object userID) {
    	/*
        Object [] values  = caches[USER_PERMS_CACHE].values().toArray();
        for (int i=0; i<values.length; i++) {
            Cache cache = (Cache)((CacheObject)values[i]).object;
            cache.remove(userID);
        }
        */
        //when cache becomes distributed, we'd send out an expire message
        //here to all other yazd servers.
    }

    public void removeUserPerm(Object userID, Object forumID) {
    	/*
        Cache cache = (Cache)caches[USER_PERMS_CACHE].get(forumID);
        if (cache != null) {
            cache.remove(userID);
        }
        */
        //when cache becomes distributed, we'd send out an expire message
        //here to all other yazd servers.
    }

    public void clear(String cacheName) {
    	caches.get(cacheName).clear();
        //when cache becomes distributed, we'd send out an expire message
        //here to all other yazd servers.
    }
    
    public boolean isCacheEnabled() {
        return cacheEnabled;
    }

    public void setCacheEnabled(boolean cacheEnabled) {
        this.cacheEnabled = cacheEnabled;
    }
}

/**
 * Special purpose Cache to hold all of the different user permission cache
 * objects. The main feature is that new caches are automatically created so
 * that calling get() never returns null.
 */
class UserPermsCache extends Cache {

    public UserPermsCache(int size, long expireTime) {
        super(size, expireTime);
    }

    public synchronized Object get(Object key) {
        Cache subCache = (Cache)super.get(key);
        if (subCache == null) {
            //cache has expired, or is not there, so put a new one in there.
            //Cache objects only need to last as long as a user's session
            //does. Half an hour is a reasonable amount of time for this.
            subCache = new Cache(2*1024, 30*1000*60);
            add(key, subCache);
        }
        return subCache;
    }

    public synchronized void remove(Object key) {
        CacheObject cacheObject = (CacheObject)cachedObjectsHash.get(key);
        //If the object is not in cache, stop trying to remove it.
        if (cacheObject == null) {
            return;
        }
        //remove from the hash map
        cachedObjectsHash.remove(key);
        //remove from the cache order list
        cacheObject.lastAccessedListNode.remove();
        cacheObject.ageListNode.remove();
        //remove references to linked list nodes
        cacheObject.ageListNode = null;
        cacheObject.lastAccessedListNode = null;
        //removed the object, so subtract its size from the total.
        size -= cacheObject.size;

        //Finally, clear the sub-cache to make sure memory is released.
        ((Cache)cacheObject.object).clear();
    }

}
