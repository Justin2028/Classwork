package edu.govschool.threads;

import java.awt.*;
import java.awt.image.*;
// We need these classes to make a "thread pool"
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import javax.swing.*;


/**
 * Refactored solution to the midterm using threads instead of 
 * <code>Timer</code>.
 * @author Mr. Davis
 */
public class ThreadsExample extends JApplet
{
    private static BufferedImage image;
    
    private static final int SIZE = 600;
    private static final int MULT = 12;
    
    @Override
    public void init()
    {   
        setSize(SIZE, SIZE);
        // Create a thread pool. A "thread pool" is a collection of threads
        // managed by the JVM for you. All we have to do is add threads to it
        // later on
        ExecutorService threads = Executors.newCachedThreadPool();
        
        image = new BufferedImage(getWidth(), getHeight(), 
                                    BufferedImage.TYPE_INT_RGB);
        
        Graphics g = image.getGraphics();
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        
        // Here, we start our five projectiles as threads
        for (int i = 0; i < 5; i++) {
            threads.execute(new ProjectileThread(15.0, 15.0 * (i+1)));
        }
        
        // After all of the threads are finished, shutdown the thread pool
        threads.shutdown();
    }
    
    @Override
    public void paint(Graphics g)
    {
        g.drawImage(image, 0, 0, this);
    }
    
    // The <code>Runnable</code> interface defines in code the concept of a
    // block of code to be called and executed elsewhere. It is the actual
    // code we want to run on different threads. In this case, we want to 
    // update the projectile's location as it travels and redraw it to the
    // screen
    private class ProjectileThread extends Projectile implements Runnable
    {
        private double time;
        private int xCoord = 0;
        private int yCoord = (SIZE - 20);
        
        public ProjectileThread(double initVelo, double theta)
        {
            super(initVelo, theta);
            this.time = 0;
        }
        
        // The 'run()' method contains all of the code we want to run
        // on threads. It contains an infinite loop, so we never actually
        // stop execution on our threads until we quit the application
        @Override
        public void run()
        {
            while (true) {
                Graphics g = image.getGraphics();
                g.setColor(Color.LIGHT_GRAY);
                g.fillOval(xCoord, yCoord, 10, 10);

                g.setColor(Color.RED);
                xCoord = this.getX(this.time) * MULT;
                yCoord = (SIZE - 20) - (this.getY(this.time) * MULT);
                g.fillOval(xCoord, yCoord, 10, 10);

                this.time += 0.25;

                try {
                    Thread.sleep(75);
                } catch (InterruptedException e) {}
                
                repaint();
            }
        }
    }
}