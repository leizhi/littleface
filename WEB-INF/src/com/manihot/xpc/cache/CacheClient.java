package com.manihot.xpc.cache;

public class CacheClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CacheManager cm = CacheManager.getInstance();
		
		for (int i=0;i<800000;i++)
			cm.add(0+"", i, "String"+i);
		
		for (int i=0;i<800000;i++) {
			System.out.println(cm.get(0+"", i));
		}
		
		cm.clear(0+"");
		
		/*
		int SECOND = 1000;
        int MINUTE = SECOND*60;
        int HOUR = MINUTE*60;
        
		Cache cache = new Cache(64 * 1,4*SECOND);
		
		System.out.println(cache.getMaxSize());
		System.out.println(cache.getSize());
		
		for (int i=0;i<8;i++)
			cache.add(i, "String"+i);
		
		System.out.println("maxSize="+cache.getMaxSize()+" Size="+cache.getSize());
		System.out.println("hits="+cache.getCacheHits() + " misses="+cache.getCacheMisses());

		for (int i=0;i<8;i++) {
			System.out.println("no get");

			System.out.println(" lastAccessedList first="+cache.lastAccessedList.getFirst()+
					" lastAccessedList last="+cache.lastAccessedList.getLast());
			System.out.println(" ageList first="+cache.ageList.getFirst()+
					" ageList last="+cache.ageList.getLast());
			
			System.out.println("get=" +cache.get(i));
			System.out.println("get after");

			System.out.println(" lastAccessedList first="+cache.lastAccessedList.getFirst()+
					" lastAccessedList last="+cache.lastAccessedList.getLast());
			System.out.println(" ageList first="+cache.ageList.getFirst()+
					" ageList last="+cache.ageList.getLast());
		}
		
		System.out.println("hits="+cache.getCacheHits() + " misses="+cache.getCacheMisses());
		System.out.println("maxSize="+cache.getMaxSize()+" Size="+cache.getSize());

		cache.get(0);
		
		for (int i=8;i<10;i++) {
			cache.add(i, "String"+i);
		}
		
		System.out.println("==============================================");

		System.out.println("get 0=" +cache.get(0));
		try {
			Thread.sleep(6*SECOND);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("get 0=" +cache.get(0));
		System.out.println("sleep after maxSize="+cache.getMaxSize()+" Size="+cache.getSize());

		for (int i=0;i<8;i++) {
			System.out.println("no get");

			System.out.println(" lastAccessedList first="+cache.lastAccessedList.getFirst()+
					" lastAccessedList last="+cache.lastAccessedList.getLast());
			System.out.println(" ageList first="+cache.ageList.getFirst()+
					" ageList last="+cache.ageList.getLast());
			
			System.out.println("get "+i+"=" +cache.get(i));
			System.out.println("get after");

			System.out.println(" lastAccessedList first="+cache.lastAccessedList.getFirst()+
					" lastAccessedList last="+cache.lastAccessedList.getLast());
			System.out.println(" ageList first="+cache.ageList.getFirst()+
					" ageList last="+cache.ageList.getLast());
		}
		System.out.println("==============================================");

		//cache.clear();
		
		System.out.println("maxSize="+cache.getMaxSize()+" Size="+cache.getSize());
*/
	}

}
