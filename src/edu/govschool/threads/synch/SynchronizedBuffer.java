package edu.govschool.threads.synch;

/**
 * Class representing a synchronized buffer. This class enables a built-in Java
 * features called <i>locks</i> to prevent threads accessing data when another
 * thread is working with it.
 * @author Mr. Davis
 */
public class SynchronizedBuffer implements Buffer
{
    private int buffer = -1;
    private boolean occupied = false;
    
    // The synchronized keyword ensures that our lock, the occupied variable,
    // will prevent the Producer and Consumer from accessing the buffer in the
    // incorrect order
    @Override
    public synchronized void set(int val)
    {
        while (occupied) {
            try {
                System.out.println("Producer tries to write.");
                statusUpdate("Buffer full. Producer waits.");
                wait();
            } catch (InterruptedException e) {}
        }
        
        buffer = val;
        
        occupied = true;
        
        statusUpdate("Producer writes " + buffer);
        
        notifyAll();
    }
    
    @Override
    public synchronized int get()
    {
        while (!occupied) {
            try {
                System.out.println("Consumer tries to read.");
                statusUpdate("Buffer empty. Consumer waits.");
                wait();
            } catch (InterruptedException e) {}
        }
        
        occupied = false;
        statusUpdate("Consumer reads " + buffer);
        
        notifyAll();
        
        return buffer;
    }
    
    public void statusUpdate(String status)
    {
        System.out.printf("%-40s%d\t\t%b\n\n", status, buffer, occupied);
    }
}