package com.mooo.mycoz.test;

import com.mooo.mycoz.cache.CacheManager;
import com.mooo.mycoz.example.SingleDemo;

public class CacheTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		new Thread(new WriteRun()).start();
		new Thread(new ReadRun()).start();
		/*
		System.out.println(Single.getInstance().getName());
		Single.getInstance().setName("ok");
		System.out.println(Single.getInstance().getName());
		Single.getInstance().setName("ok1");
		System.out.println(Single.getInstance().getName());
		System.out.println(Single.getInstance().getName());
		System.out.println(Single.getInstance().getName());
		 */
		
		//CacheManager.getInstance();
		//ConfigureUtil cu = new ConfigureUtil();
		//cu.confCache();
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

class ReadRun implements Runnable {

	int count = 1;
	CacheManager cm = CacheManager.getInstance();
	
	public ReadRun() {
		System.out.println("创建线程 ");
	}

	public void run() {
		//try {
			while (true) {
				// synchronized (Singleton.name) {
				//Singleton.setName("start" + (count++));
				System.out.println("get cache"+cm.get("tmp3", count));
				System.out.println("get cache"+SingleDemo.getInstance().getName());

				if((count++)%100==0)
					break;
				//Thread.sleep(10);
				// }
			}
		//} catch (InterruptedException e) {
		//	e.printStackTrace();
			//}
	}
}

class WriteRun implements Runnable {

	int count = 1;
	CacheManager cm = CacheManager.getInstance();

	public WriteRun() {
		System.out.println("创建线程 ");
	}

	public void run() {
		//try {
			while (true) {
				// synchronized (Singleton.name) {
				System.out.println("add  cache"+count);
				cm.add("tmp3", count, "String"+count);
				SingleDemo.getInstance().setName("ok"+count);
				System.out.println("write  cache"+count);

				if((count++)%100==0)
					break;
				
				//Thread.sleep(10);
				 }
		//} catch (InterruptedException e) {
		//	e.printStackTrace();
		///}
	}
}
