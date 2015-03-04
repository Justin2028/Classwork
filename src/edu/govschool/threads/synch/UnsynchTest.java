package edu.govschool.threads.synch;

// We need these classes to make our thread pool.
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

/**
 * Class to test the use of the <code>UnsynchronizedBuffer</code>.
 * @author Mr. Davis
 */
public class UnsynchTest 
{
    public static void main(String[] args)
    {
        // Create our thread pool
        ExecutorService threadPool = Executors.newCachedThreadPool();
        
        // Create an unsynchronized buffer to share between Producer
        // and Consumer
        Buffer sharedLocation = new UnsynchronizedBuffer();
        
        // Print some nice formatting
        System.out.println("Action\t\tValue\tSum of Produced\tSum of Consumed");
        System.out.println("------\t\t-----\t---------------\t---------------");
        
        // Start our threads
        threadPool.execute(new Producer(sharedLocation));
        threadPool.execute(new Consumer(sharedLocation));
        
        // Stop our thread pool after the threads finish executing
        threadPool.shutdown();
    }
}