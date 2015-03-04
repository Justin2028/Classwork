package edu.govschool.threads.synch;

import java.util.Random;

/**
 * Class to continually write to a shared buffer. The <code>Runnable</code>
 * interface defines in code the concept of a block of code to be called and
 * executed elsewhere. We will pass instances of this and the
 * <code>Producer</code> class to our threads to execute.
 * @author Mr. Davis
 */
public class Consumer implements Runnable
{
    // We want to sleep, or pause, for a random amount of time
    private final static Random time = new Random();
    // A reference to our shared buffer
    private final Buffer sharedLocation;
    
    /**
     * The constructor simply sets the reference to the shared buffer.
     * @param shared the buffer to share with Producer
     */ 
    public Consumer(Buffer shared)
    {
        this.sharedLocation = shared;
    }
    
    /**
     * The code to run in our thread. We loop 10 times, sleeping for a random
     * time up to three seconds, then we read from the buffer. We also sum the
     * values read from the buffer.
     */
    @Override
    public void run()
    {
        int sum = 0;
        
        for (int count = 1; count <= 10; count++) {
            try {
                Thread.sleep(time.nextInt(3000));
                sum += this.sharedLocation.get();
                System.out.printf("\t\t\t%2d\n", sum);
            } catch (InterruptedException e) {}
        }
        
        System.out.printf("\n%s %d\n%s\n", "Consumer reads vals totaling", sum, 
                "Terminating Consumer");
    }
}