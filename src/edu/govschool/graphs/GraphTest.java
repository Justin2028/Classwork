/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.govschool.graphs;

/**
 *
 * @author Glaedwyn
 */
public class GraphTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Graph graph=new Graph();
        Node a = new Node(0);
        Node b = new Node(1);
        Node c = new Node(2);
        Node d = new Node(3);
        Node e = new Node(4);
        Node f = new Node(5);
        Node g = new Node(6);
        Node h = new Node(7);
        Node i = new Node(8);
        Node j = new Node(9);
        graph.addEdge(a, b, 18);
        graph.addEdge(a, c, 10);
        graph.addEdge(a, d, 3);
        graph.addEdge(a, e, 4);
        graph.addEdge(b, j, 9);
        graph.addEdge(b, i, 9);
        graph.addEdge(c, b, 8);
        graph.addEdge(c, i, 9);
        graph.addEdge(c, g, 8);
        graph.addEdge(d, e, 1);
        graph.addEdge(d, c, 9);
        graph.addEdge(d, f, 5);
        graph.addEdge(e, f, 4);
        graph.addEdge(f, c, 7);
        graph.addEdge(f, g, 9);
        graph.addEdge(f, h, 9);
        graph.addEdge(g, h, 2);
        graph.addEdge(g, i, 2);
        graph.addEdge(h, i, 4);
        graph.addEdge(h, j, 6);
        graph.addEdge(i, j, 3);
        graph.printMinimumSpanningTree();
        System.out.println(graph.hasCycles());
    }

}
