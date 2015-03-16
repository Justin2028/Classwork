package edu.govschool.threads;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import javax.swing.*;

/**
 * Problems 2 and 3 solutions from the threads assignment.
 *
 * @author Justin Miller
 */
public class BouncingBall extends JApplet {

    ExecutorService threads = Executors.newCachedThreadPool();
    private static BufferedImage image;

    private static final int SIZE = 600;
    private static final int MULT = 3;

    @Override
    public void init() {
        setSize(SIZE, SIZE);
        // Create a thread pool. A "thread pool" is a collection of threads
        // managed by the JVM for you. All we have to do is add threads to it
        // later on

        image = new BufferedImage(getWidth(), getHeight(),
                BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        
        // Here, we start our five projectiles as threads
        threads.execute(new BallThread());
        addMouseListener(new MouseHandler());
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }

    // The <code>Runnable</code> interface defines in code the concept of a
    // block of code to be called and executed elsewhere. It is the actual
    // code we want to run on different threads. In this case, we want to 
    // update the projectile's location as it travels and redraw it to the
    // screen
    private class BallThread implements Runnable {

        private int xCoord = 0;
        private int xMult = 1;
        private int yMult = 1;
        private int yCoord = (SIZE - 20);

        public BallThread() {
            Random rand = new Random();
            xCoord = rand.nextInt(600)+1;
            yCoord = rand.nextInt(600) - 20;
            boolean n = rand.nextBoolean();
            if(n)xMult = 1;
            else xMult = -1;
            n = rand.nextBoolean();
            if(n)yMult = 1;
            else yMult = -1;
        }

        // The 'run()' method contains all of the code we want to run
        // on threads. It contains an infinite loop, so we never actually
        // stop execution on our threads until we quit the application
        @Override
        public void run() {
            while (true) {
                Graphics g = image.getGraphics();
                g.setColor(Color.LIGHT_GRAY);
                g.fillOval(xCoord, yCoord, 10, 10);
                g.fillOval(xCoord - ((SIZE - yCoord) / 60), SIZE - 3, 10 + 2 * ((SIZE - yCoord) / 60), 5);
                g.setColor(Color.RED);
                xCoord = this.xCoord + 2 * MULT * xMult;
                if (xCoord > SIZE - 10 || xCoord < 0) {
                    xMult *= -1;
                }
                yCoord = this.yCoord - (MULT * yMult);
                if (yCoord > SIZE - 10 || yCoord < 0) {
                    yMult *= -1;
                }
                g.fillOval(xCoord, yCoord, 10, 10);
                g.setColor(Color.BLACK);
                g.fillOval(xCoord - ((SIZE - yCoord) / 60), SIZE - 3, 10 + 2 * ((SIZE - yCoord) / 60), 5);
                try {
                    Thread.sleep(75);
                } catch (InterruptedException e) {
                }
                repaint();
            }
        }
    }

    private class MouseHandler extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e)
        {
            threads.execute(new BallThread());
        }
    }
}
