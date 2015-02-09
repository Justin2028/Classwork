package edu.govschool.graphs;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * A representation of a <code>Node</code> for an undirected <code>Graph</code>.
 * @author Mr. Davis
 */
public class Node implements Comparable<Node>
{
    private final int data;
    private final SortedSet<Node> neighbors;
    private boolean visited;
    
    /**
     * Empty constructor.
     * Creates an empty, unvisited <code>Node</code>.
     */
    public Node()
    {
        this(0, new TreeSet<>(), false);
    }
    
    /**
     * Convenience constructor.
     * Creates an unvisited <code>Node</code> with the given data and no 
     * neighbors.
     * @param data the data to store
     */
    public Node(int data)
    {
        this(data, new TreeSet<>(), false);
    }
    
    /**
     * Default constructor.
     * Creates a <code>Node</code> with the given data, neighbors, and visit
     * status.
     * @param data the data to store
     * @param neighbors a SortedSet of connected Nodes
     * @param visited a status flag for having visited Node
     */
    public Node(int data, SortedSet<Node> neighbors, boolean visited)
    {
        this.data = data;
        this.neighbors = neighbors;
        this.visited = visited;
    }
    
    /**
     * Gets the stored data.
     * @return the stored data
     */
    public int getData()
    {
        return this.data;
    }
    
    /**
     * Gets the neighboring <code>Node</code>..
     * @return the connected Nodes
     */
    public SortedSet<Node> getNeighbors()
    {
        return this.neighbors;
    }
    
    /**
     * Test if the <code>Node</code> has been visited or not.
     * @return true if the Node has been visited, false otherwise
     */
    public boolean isVisited()
    {
        return this.visited;
    }
    
    /**
     * Test if another <code>Node</code> is connected to this one.
     * @param that the Node to test for
     * @return true if the Node is a neighbor, false otherwise
     */
    public boolean hasNeighbor(Node that)
    {
        return this.neighbors.contains(that);
    }
    
    /**
     * Adds a <code>Node</code> to the <code>SortedSet</code> of neighbors.
     * @param that the Node to add
     */
    public void addNeighbor(Node that)
    {
        this.neighbors.add(that);
    }
    
    /**
     * Adds multiple <code>Node</code>s to the <code>SortedSet</code> of 
     * neighbors.
     * @param ofThose a SortedSet of Nodes to add
     */
    public void addNeighbors(SortedSet<Node> ofThose)
    {
        this.neighbors.addAll(ofThose);
    }
    
    /**
     * Set the visit status of this <code>Node</code>
     * @param visited the new visit status
     */
    public void setVisited(boolean visited)
    {
        this.visited = visited;
    }
    
    /**
     * Returns a string representation of the <code>Node</code>.
     * @return a String representation of the Node
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append(this.data).append(": { ");
        
        for(Node node : this.neighbors) {
            sb.append(node.getData()).append(" ");
        }
        
        return sb.append("}").toString();
    }
    
    /**
     * Compares this <code>Node</code> with the specified <code>Node</code> for 
     * order.
     * @param that the Node to be compared
     * @return a negative integer, zero, or a positive integer as this Node is 
     * less than, equal to, or greater than the specified Node
     */
    @Override
    public int compareTo(Node that)
    {
        return ((Integer) this.data).compareTo(that.getData());
    }
    
    public boolean isAllNeighborsVisited()
    {
        for(Node node : this.neighbors)
        {
            if(!node.isVisited())return false;
        }
        return true;
    }
}