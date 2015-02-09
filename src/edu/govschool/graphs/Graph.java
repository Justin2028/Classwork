package edu.govschool.graphs;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * A representation of an undirected, weighted <code>Graph</code>.
 * @author Mr. Davis
 */
public class Graph 
{
    private SortedSet<Node> nodes;
    private Set<Edge> edges;
    
    public Graph()
    {
        this(new TreeSet<>(), new HashSet<>());
    }
    
    public Graph(SortedSet<Node> nodes, Set<Edge> edges)
    {
        this.nodes = nodes;
        this.edges = edges;
    }
    
    public SortedSet<Node> getNodes()
    {
        return this.nodes;
    }
    
    public Set<Edge> getEdges()
    {
        return this.edges;
    }
    
    public void addNode(Node node)
    {
        this.nodes.add(node);
    }
    
    public void addEdge(Node a, Node b, int weight)
    {
        a.addNeighbor(b);
        b.addNeighbor(a);
        
        this.nodes.add(a);
        this.nodes.add(b);
        
        this.edges.add(new Edge(a, b, weight));
    }
    
    public void resetVisits()
    {
        for (Node node : this.nodes) {
            node.setVisited(false);
        }
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        
        for (Edge e : this.edges) {
            sb.append(e).append("\n");
        }
        
        return sb.toString();
    }
    
    public void printMinimumSpanningTree()
    {
        Graph temp = new Graph();
        temp.addNode(this.nodes.first());
        
        while(!temp.nodes.equals(this.nodes))
        {
            Edge tempEdge = null;
            for(Edge tmp : this.edges)
            {
                if(temp.nodes.contains(tmp.getA()) && !temp.nodes.contains(tmp.getB()))
                {
                    if(tempEdge == null || tmp.getWeight() < tempEdge.getWeight())
                    {
                        tempEdge = tmp;
                    }
                } else if(temp.nodes.contains(tmp.getB()) && !temp.nodes.contains(tmp.getA()))
                {
                    if(tempEdge == null || tmp.getWeight() < tempEdge.getWeight())
                    {
                        tempEdge = tmp;
                    }
                }
            }
            temp.addEdge(tempEdge.getA(), tempEdge.getB(), tempEdge.getWeight());
            
        }
        
        System.out.println(temp.toString());
    }
    
    public boolean hasCycles()
    {
        return edges.size() >= nodes.size();
    }
}