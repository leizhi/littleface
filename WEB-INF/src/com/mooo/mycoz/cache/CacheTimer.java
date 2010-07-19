package com.mooo.mycoz.cache;

public class CacheTimer extends Thread {

    private long updateInterval;

    /**
     * Creates a new CacheTimer object. The currentTime of Cache will be
     * updated at the specified update interval.
     *
     * @param updateInterval the interval in milleseconds that updates should
     *    be done.
     */
    public CacheTimer(long updateInterval) {
        this.updateInterval = updateInterval;
        //Make the timer be a daemon thread so that it won't keep the VM from
        //shutting down if there are no other threads.
        this.setDaemon(true);
        //Start the timer thread.
        start();
    }

    public void run() {
        //Run the timer indefinetly.
        while (true) {
            Cache.currentTime = System.currentTimeMillis();
            try {
                sleep(updateInterval);
            }
            catch (InterruptedException ie) { }
        }
    }
}