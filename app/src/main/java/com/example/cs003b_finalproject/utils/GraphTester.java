package com.example.cs003b_finalproject.utils;// Daniel Rodriguez and Daniel Dosti
// CS003B : Final Project
import java.util.ArrayList;

public class GraphTester
{
    public static void main(String[] args)
    {
        // Graph will be directed.
        Graph g1 = new Graph(true);

        // Add vertexes first, so it's easier to control which Vertex gets which ID.
        g1.addVertex("a"); // ID = 0
        g1.addVertex("b"); // ID = 1
        g1.addVertex("c"); // ID = 2
        g1.addVertex("d"); // ID = 3
        g1.addVertex("e"); // ID = 4
        g1.addVertex("f"); // ID = 5
        g1.addVertex("g"); // ID = 6

        // Add edges.
        g1.addEdge("f", "a", 1);
        g1.addEdge("a", "f", 13);
        g1.addEdge("a", "g", 25);
        g1.addEdge("a", "b", 20);
        g1.addEdge("f", "c", 20);
        g1.addEdge("f", "e", 30);
        g1.addEdge("f", "d", 2);
        g1.addEdge("d", "e", 4);
        g1.addEdge("d", "g", 10);
        g1.addEdge("c", "d", 3);
        g1.addEdge("b", "c", 15);
        g1.addEdge("b", "g", 3);
        g1.addEdge("g", "c", 2);
        g1.addEdge("g", "f", 3);

        // Set the source vertex name
        String sourceVertexName = "a";

        // Construct a Dijkstra object which will automatically perform the algorithm.
        Dijkstra d1 = new Dijkstra(g1, sourceVertexName);

        // Get the results.
        ArrayList<Integer> distances = d1.getDistances();

        // Print out the results
        System.out.println("Shortest Path from \"" + sourceVertexName + "\"\n=========================");
        for (int i = 0; i < distances.size(); ++i)
        {
            System.out.printf("%s --> %s : minimum cost = %d\n", sourceVertexName, g1.getVertex(i).getName(), distances.get(i));
        }

        ArrayList<Integer> path = d1.getMinPathTo("c");
        for (Integer i : path)
        {
            System.out.println("Visit: " + g1.getVertex(i).getName());
        }
    }
}