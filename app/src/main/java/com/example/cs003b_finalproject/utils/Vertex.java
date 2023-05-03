package com.example.cs003b_finalproject.utils;// Daniel Rodriguez and Daniel Dosti
// CS003B : Final Project

import java.util.HashMap;

/**
 * Simple class represents a vertex stored in graph. This Vertex does not store x and y coordinates, but rather it only
 * stores their name, an integer ID, and a collection of adjacent vertices (connections).
 * Note: the name's and ID of the Vertexes are immutable.
 * By itself, the Vertex class may not seem useful. But the Graph class heavily depends on the Vertex class.
 */
public class Vertex
{
    private final String _name;
    private final int _ID;
    private final HashMap<Vertex, Float> _adjacentVertices;

    /**
     * Private default constructor.
     */
    private Vertex()
    {
        this(null, -1);
    }

    /**
     * Overloaded constructor. Constructs a new Vertex object with a name and ID.
     * @param name the name of the Vertex
     * @param ID the ID of the Vertex
     */
    public Vertex(String name, int ID)
    {
        this._name = name;
        this._ID = ID;
        this._adjacentVertices = new HashMap<>();
    }

    /**
     * @return the ID of the Vertex
     */
    public int getID()
    {
        return this._ID;
    }

    /**
     * @return the name of the Vertex
     */
    public String getName()
    {
        return this._name;
    }

    /**
     * Returns a HashMap containing any adjacent Vertexes
     * @return a HashMap<Vertex, Integer> representing any adjacent Vertexes
     */
    public HashMap<Vertex, Float> getAdjacentVertices()
    {
        return this._adjacentVertices;
    }

    /**
     * Sets a new adjacent (connection) Vertex for the invoking Vertex object.
     * @param vertex the Vertex which will be adjacent to the invoking Vertex object (this)
     * @param cost the cost of traversal between the two adjacent Vertexes
     */
    public void setAdjacent(Vertex vertex, float cost)
    {
        this._adjacentVertices.put(vertex, cost);
    }

    /**
     * Basic toString() method.
     * @return a String represent the data of the Vertex such as its name and ID
     */
    @Override
    public String toString()
    {
        return "[Vertex] name = " + this._name + " | ID = " + this._ID;
    }

    /**
     * Basic hashCode method.
     * @return a hash code which is calculated using the ID and name of the Vertex
     */
    @Override
    public int hashCode()
    {
        int primeNum = 37;
        int hashCode = 31;
        hashCode = (hashCode * primeNum + this._ID);
        hashCode = (hashCode * primeNum + this._name.hashCode());
        return hashCode;
    }

    /**
     * Basic equals() method.
     * @param object the object to compare to
     * @return true if the objects are the equal, false otherwise
     */
    @Override
    public boolean equals(Object object)
    {
        // Check if the object references are the same.
        if (this == object)
        {
            return true;
        }
        // Check if the object is an instance of the Vertex class.
        if (!(object instanceof Vertex))
        {
            return false;
        }
        Vertex otherVertex = (Vertex) object;
        // Compare the Vertex objects data.
        return this._name.equals(otherVertex._name) && this._ID == otherVertex._ID;
    }
}