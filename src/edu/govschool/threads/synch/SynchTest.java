package edu.govschool.threads.synch;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

/**
 * Class to test the use of the <code>SynchronizedBuffer</code>.
 * @author Mr. Davis
 */
public class SynchTest 
{
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();

        // We need to use our new, synchronized buffer!
        Buffer sharedLocation = new SynchronizedBuffer();

        // The output is slightly different
        System.out.printf("%-40s%s\t\t%s\n%-40s%s\n\n", "Operation", "Buffer",
                "Occupied", "---------", "------\t\t--------");

        threadPool.execute(new Producer(sharedLocation));
        threadPool.execute(new Consumer(sharedLocation));

        threadPool.shutdown();
    }
}