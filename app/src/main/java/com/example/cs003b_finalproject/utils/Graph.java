package com.example.cs003b_finalproject.utils;// Daniel Rodriguez and Daniel Dosti
// CS003B : Final Project
import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class represents a Graph, which contains a collection of vertexes and edges between vertexes. This Graph object
 * supports the ability to create more Vertexes and Edges as necessary. However, Vertex names must be unique.
 * No exception will be thrown, but if a Vertex name is already recorded in the Graph then this would simply add a
 * new edge from the already existing Vertex in the Graph. This may lead to unintended behavior or edges between vertexes.
 * The Graph also has the ability to be directed or undirected upon construction.
 * This, however, cannot be changed in the object's lifetime.
 */
public class Graph
{
    private int _numOfVertices;
    private final boolean  _isDirected;
    private final ArrayList<Vertex> _vertexes;
    private final ArrayList<Edge> _edges;
    private final HashMap<String, Integer> _vertexNames;

    /**
     * Default constructor. Constructs a Graph object which is undirected.
     */
    public Graph()
    {
        this(false);
    }

    /**
     * Overloaded constructor. Constructs a Graph object which can be directed/undirected.
     * @param isDirected boolean representing whether the graph is directed or not
     */
    public Graph(boolean isDirected)
    {
        this._numOfVertices = 0;
        this._isDirected = isDirected;
        this._vertexes = new ArrayList<>();
        this._edges = new ArrayList<>();
        this._vertexNames = new HashMap<>();
    }

    /**
     * Returns the size of the Graph, that is the number of Vertexes in the graph.
     * @return the number of Vertexes in the Graph
     */
    public int size()
    {
        return this._numOfVertices;
    }

    /**
     * Returns a collection of the Vertex objects stored in the Graph.
     * @return an ArrayList<Vertex> of the Vertex objects of the Graph
     */
    public ArrayList<Vertex> getVertexes()
    {
        return this._vertexes;
    }

    /**
     * @return true if the graph was set to directed, false otherwise
     */
    public boolean isDirected()
    {
        return this._isDirected;
    }

    /**
     * Add a new Vertex object to the Graph. If a Vertex with the exact same name is already stored in the Graph,
     * this method will do nothing.
     * @param name the name of the Vertex object.
     */
    public void addVertex(String name)
    {
        if (!this._vertexNames.containsKey(name))
        {
            this._vertexes.add(new Vertex(name, this._numOfVertices));
            this._vertexNames.put(name, this._numOfVertices);
            ++this._numOfVertices;
        }
    }

    /**
     * Returns a Vertex object when given a name.
     * @param name the name of the Vertex to try to get
     * @return a Vertex object if successful, but may be null if the desired name does not exist in the Graph
     */
    public Vertex getVertex(String name)
    {
        if (!(this._vertexNames.containsKey(name)))
        {
            return null;
        }
        int vertexID = this._vertexNames.get(name);
        return this._vertexes.get(vertexID);
    }

    /**
     * Returns a Vertex object when given an ID.
     * @param ID the ID of the Vertex to try to get
     * @return a Vertex object, but may be null if there is no Vertex with the desired ID
     */
    public Vertex getVertex(int ID)
    {
        if (ID < 0 || ID >= this._numOfVertices)
        {
            return null;
        }
        return this._vertexes.get(ID);
    }

    /**
     * Adds an edge between two existing Vertexes with a traversal cost between them.
     * Note: If the names (frm or to) have not been previously stored in the Graph, don't worry.
     * This function will automatically create the new Vertexes with the new names (frm or to).
     * @param from the name of the source Vertex
     * @param to the name of the destination Vertex.
     * @param cost the cost of traversal between the two Vertexes.
     */
    public void addEdge(String from, String to, float cost)
    {
        if (!(this._vertexNames.containsKey(from)))
        {
            addVertex(from);
        }
        if (!(this._vertexNames.containsKey(to)))
        {
            addVertex(to);
        }
        Vertex fromVertex = this._vertexes.get(this._vertexNames.get(from));
        Vertex toVertex = this._vertexes.get(this._vertexNames.get(to));

        fromVertex.setAdjacent(toVertex, cost);
        if (!this._isDirected)
        {
            toVertex.setAdjacent(fromVertex, cost);
        }
        this._edges.add(new Edge(fromVertex, toVertex, cost));
    }

    /**
     * Returns a collection of edges of the graph.
     * @return an ArrayList<Edge> which represents all the edges of the graph
     */
    public ArrayList<Edge> getEdges()
    {
        return this._edges;
    }

    /**
     * Basic toString() method.
     * @return a String representing the data of the Graph such as whether it is directed and its size,
     * The Vertexes and Edges of the Graph are also pushed to the String.
     */
    @NonNull
    @Override
    public String toString()
    {
        StringBuilder data = new StringBuilder();
        String directed = (this._isDirected) ? "True" : "False";
        data.append("Directed: ").append(directed).append("\n");
        data.append("Size: ").append(this._numOfVertices).append("\n");
        data.append("========================\n");
        data.append("=====Vertexes=====\n");
        for (Vertex vertex : this._vertexes)
        {
            data.append(vertex.toString()).append("\n");
        }
        data.append("=====Edges=====\n");
        for (Edge edge : this._edges)
        {
            data.append(edge.toString()).append("\n");
        }
        return data.toString();
    }
}