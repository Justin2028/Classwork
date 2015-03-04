package edu.govschool.threads.synch;

/**
 * Interface to represent a buffer to read and write from using threads
 * @author Mr. Davis
 */
public interface Buffer 
{
    /**
     * Set the value of the buffer
     * @param val the value to set
     * @throws InterruptedException the buffer could not be set 
     */
    public void set(int val) throws InterruptedException;
    
    /**
     * Get the value contained in the buffer
     * @return the current value of the buffer
     * @throws InterruptedException the buffer could not be accessed
     */
    public int get() throws InterruptedException;
}