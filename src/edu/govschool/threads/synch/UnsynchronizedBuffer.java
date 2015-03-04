package edu.govschool.threads.synch;

/**
 * Class representing an unsynchronized buffer. If more than one thread
 * attempts to access the data within, chances are their accessing will be out 
 * of sync.
 * @author Mr. Davis
 */
public class UnsynchronizedBuffer implements Buffer
{
    // The stored data. The Producer will write to this, and the Consumer will
    // read from it
    private int buffer = -1;
    
    /**
     * Set the value of the buffer.
     * @param val the value to set
     * @throws InterruptedException the buffer could not be accessed
     */
    @Override
    public void set(int val) throws InterruptedException
    {
        System.out.printf("Producer writes\t" + val);
        buffer = val;
    }
    
    /**
     * Get the value of the buffer.
     * @return the value of the buffer
     * @throws InterruptedException the buffer could not be accessed
     */
    @Override
    public int get() throws InterruptedException
    {
        System.out.printf("Consumer reads\t" + buffer);
        return buffer;
    }
}