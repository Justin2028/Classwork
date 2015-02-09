package edu.govschool.graphs;

/**
 * A representation of an undirected, weighted <code>Edge</code> for a 
 * <code>Graph</code>
 * @author Mr. Davis
 */
public class Edge implements Comparable<Edge>
{
    private Node a;
    private Node b;
    private final int weight;
    
    /**
     * Convenience constructor.
     * Creates an <code>Edge</code> with the given <code>Node</code>s as
     * endpoints. Since we are building an undirected graph, neither can be
     * considered the start or end, so we'll just refer to them as 'A' and 'B'.
     * We'll set the weight to zero by default.
     * @param a the first endpoint
     * @param b the second endpoint
     */
    public Edge(Node a, Node b)
    {
        this(a, b, 0);
    }
    
    /**
     * Default constructor.
     * Creates an <code>Edge</code> with the given <code>Node</code>s as 
     * endpoints and the given weight.
     * @param a the first endpoint
     * @param b the second endpoint
     * @param weight the length of the connection
     */
    public Edge(Node a, Node b, int weight)
    {
        this.a = a;
        this.b = b;
        this.weight = weight;
    }
    
    /**
     * Gets the first endpoint.
     * @return the first endpoint
     */
    public Node getA() { 
        return this.a; 
    }
    
    /**
     * Gets the second endpoint.
     * @return the second endpoint
     */
    public Node getB() { 
        return this.b; 
    }
    
    /**
     * Gets the weight of the <code>Edge</code>.
     * @return the length of the Edge
     */
    public int getWeight() { 
        return this.weight; 
    }
    
    /**
     * Sets the first endpoint.
     * @param a the new first endpoint
     */
    public void setA(Node a)
    {
        this.a = a;
    }
    
    /**
     * Sets the second endpoint.
     * @param b the new second endpoint
     */
    public void setB(Node b)
    {
        this.b = b;
    }
    
    /**
     * Returns a string representation of the <code>Edge</code>.
     * @return a String representation of the Edge
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("(");
        
        sb.append(this.a.getData());
        sb.append(")<-");
        sb.append(this.weight);
        sb.append("->(");
        sb.append(this.b.getData());
        
        return sb.append(")").toString();
    }
    
    /**
     * Compares this <code>Edge</code> with the specified <code>Edge</code> for 
     * order.
     * @param that the Edge to be compared
     * @return a negative integer, zero, or a positive integer as this Edge is 
     * less than, equal to, or greater than the specified Edge
     */
    @Override
    public int compareTo(Edge that)
    {
        return ((Integer) this.weight).compareTo(that.getWeight());
    }
}