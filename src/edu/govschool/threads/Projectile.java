package edu.govschool.threads;

public class Projectile 
{
    // The initial velocity of the projectile
    private double initVelo;
    // The initial angle fired
    private double theta;
    // Gravity, or, THE LAW
    private static final double GRAVITY = 9.81;
    
    /**
     * Default constructor.
     * Creates a new <code>Projectile</code>
     * @param initVelo the initial velocity in m/s
     * @param theta the initial angle fired in degrees
     */
    public Projectile(double initVelo, double theta)
    {
        this.initVelo = initVelo;
        // The trig functions need our angle in radians
        this.theta = Math.toRadians(theta);
    }
    
    /**
     * Gets the current x-coordinate.
     * @param time the current time
     * @return the current x-coordinate
     */
    public int getX(double time)
    {
        return (int) (this.initVeloX() * time);
    }
    
    /**
     * Gets the current y-coordinate.
     * @param time the current time
     * @return the current y-coordinate
     */
    public int getY(double time)
    {
        return (int) ((this.initVeloY() * time) - (0.5 * GRAVITY * Math.pow(time, 2)));
    }
    
    /**
     * Calculates the initial velocity vector in the x-direction
     * @return the magnitude of the x-direction vector
     */
    private double initVeloX()
    {
        return initVelo * Math.sin(theta);
    }
    
    /**
     * Calculates the initial velocity vector in the y-direction
     * @return the magnitude of the y-direction vector
     */
    private double initVeloY()
    {
        return initVelo * Math.cos(theta);
    }
}