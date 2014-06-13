package edu.vuum.mocca;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;

/**
 * @class SimpleSemaphore
 *
 * @brief This class provides a simple counting semaphore
 *        implementation using Java a ReentrantLock and a
 *        ConditionObject.  It must implement both "Fair" and
 *        "NonFair" semaphore semantics, just liked Java Semaphores. 
 */
public class SimpleSemaphore {
    /**
     * Constructor initialize the data members.  
     */
    public SimpleSemaphore (int permits,
                            boolean fair)
    { 
        // TODO - you fill in here
    	mLock = new ReentrantLock(fair);
    	nPermits = permits;
    	mCondition = mLock.newCondition();
    }

    /**
     * Acquire one permit from the semaphore in a manner that can
     * be interrupted.
     */
    public void acquire() throws InterruptedException {
        // TODO - you fill in here
    	while(availablePermits()==0)
    		mCondition.await();

    	mLock.lockInterruptibly();
    	nPermits--;
    }

    /**
     * Acquire one permit from the semaphore in a manner that
     * cannot be interrupted.
     * @throws InterruptedException 
     */
    public void acquireUninterruptibly() throws InterruptedException {
        // TODO - you fill in here

    	while(availablePermits()==0)
    		mCondition.await();

    	mLock.lock();
    	nPermits--;
    }

    /**
     * Return one permit to the semaphore.
     */
    void release() {
        // TODO - you fill in here
    	mLock.unlock();
    	nPermits++;
    }
    
    /**
     * Return the number of permits available.
     */
    public int availablePermits(){
    	// TODO - you fill in here
    	return nPermits; // You will change this value. 
    }
    
    /**
     * Define a ReentrantLock to protect the critical section.
     */
    // TODO - you fill in here

    private final ReentrantLock mLock;
    
    /**
     * Define a ConditionObject to wait while the number of
     * permits is 0.
     */
    // TODO - you fill in here
    private final Condition mCondition;

    /**
     * Define a count of the number of available permits.
     */
    // TODO - you fill in here
    private int nPermits; 
}

