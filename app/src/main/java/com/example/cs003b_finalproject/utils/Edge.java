package com.example.cs003b_finalproject.utils;// Daniel Rodriguez and Daniel Dosti
// CS003B : Final Project
/**
 * Simple class which holds information of a connection between two Vertexes. For example, it stores the source Vertex (frm)
 * and the destination Vertex (to) with a traversal cost between them.
 */
public class Edge
{
    private final Vertex _frm;
    private final Vertex _to;
    private final int _cost;

    /**
     * Private default constructor.
     */
    private Edge()
    {
        this(null, null, 0);
    }

    /**
     * Overloaded constructor. Constructs an edge with the passed in information.
     * @param from the source Vertex
     * @param to the destination Vertex
     * @param cost the traversal cost between them
     */
    public Edge(Vertex from, Vertex to, int cost)
    {
        this._frm = from;
        this._to = to;
        this._cost = cost;
    }

    /**
     * @return the source Vertex object
     */
    public Vertex getFrom()
    {
        return this._frm;
    }

    /**
     * @return the destination Vertex object
     */
    public Vertex getTo()
    {
        return this._to;
    }

    /**
     * @return the traversal cost between the two Vertexes
     */
    public int getCost()
    {
        return this._cost;
    }

    /**
     * Basit toString() method
     * @return a String representing the data of the Edge object such as the name and ID of both "frm" and "to"...
     * ...as well as the traversal cost between them.
     */
    @Override
    public String toString()
    {
        return  "[From Vertex] name = " + this._frm.getName() + " | ID = " + this._frm.getID() + "\n" +
                "[ To Vertex ] name = "  + this.getTo().getName() + " | ID = " + this._to.getID() + "\n" +
                "[   Cost    ] cost = " + this._cost;
    }
}