package com.example.cs003b_finalproject.utils;// Daniel Rodriguez and Daniel Dosti
// CS003B : Final Project
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

/**
 * This class must be instantiated with a Graph object and the name of the source Vertex. Once the Dijkstra object is
 * being created, it AUTOMATICALLY starts calculating the shortest path from the source
 * Vertex to all other Vertexes in the graph. You can access the results of the algorithm by calling the getDistances()
 * method. This is a one and done class. This means that if you initialize a Dijkstra object with a Graph object, and modify
 * the Graph object in any way (adding more edges or vertices) after the calculation then the results of the
 * previous Dijkstra object do not apply anymore. You must create a new Dijkstra object to reflect the updated results
 * of the modification done to the Graph object.
 */
public class Dijkstra
{
    private final Graph _graph;
    private final ArrayList<Float> _distance;
    private final ArrayList<Boolean> _sptSet;
    private final ArrayList<Integer> _predecessor;

    /**
     * Private default constructor. We don't want anyone to instantiate a default object
     * of this class since Dijkstra's algorithm needs a graph to function.
     */
    private Dijkstra()
    {
        this(new Graph(), "");
    }

    /**
     * Overloaded constructor. Recall that this class AUTOMATICALLY starts calculating the shortest path
     * from the source vertex to the other. This means no other function is necessary to start the calculation.
     * @param graph the Graph object to work from
     * @param sourceVertexName the name of the source Vertex
     */
    public Dijkstra(Graph graph, String sourceVertexName)
    {
        this._graph = graph;
        this._distance = new ArrayList<>(_graph.size());
        fillDistance();
        this._sptSet = new ArrayList<>(_graph.size());
        fillSptSet();
        this._predecessor = new ArrayList<>(_graph.size());
        fillPredecessor();
        calculateShortestPath(sourceVertexName);
    }

    /**
     * Helper function which initializes the _distance ArrayList.
     */
    private void fillDistance()
    {
        for (int i = 0; i < _graph.size(); ++i)
        {
            this._distance.add(Float.MAX_VALUE);
        }
    }

    /**
     * Helper function which initializes the _sptSet (shortest path tree set) ArrayList.
     */
    private void fillSptSet()
    {
        for (int i = 0; i < _graph.size(); ++i)
        {
            this._sptSet.add(false);
        }
    }

    /**
     * Helper function which initializes the _predecessor array
     * (which holds the minimum vertexes necessary to traverse from the source vertex to the destination vertex)
     */
    private void fillPredecessor()
    {
        for (int i = 0; i < _graph.size(); ++i)
        {
            this._predecessor.add(-1);
        }
    }

    /**
     * GraphTester and essential function of the Dijkstra class. It simply calculates the shortest path from a source Vertex
     * to all other Vertexes in the graph.
     * @param sourceVertexName the name of the source Vertex
     */
    private void calculateShortestPath(String sourceVertexName)
    {
        // Get the ID of the source Vertex.
        int srcID = this._graph.getVertex(sourceVertexName).getID();

        // Set the distance from the source Vertex to itself to 0 (it's already in its location).
        this._distance.set(srcID, 0f);

        // From here, we will potentially loop through the all Vertexes of the graph.
        for (int k = 0; k < _graph.size(); ++k)
        {
            // Grab the closest Vertex from the source Vertex.
            // On the first iteration, this ID will always be the src Vertex's ID.
            int closetVertexID = minDistance();

            // Account for Vertexes that are out of reach of the Vertex source.
            if (closetVertexID < 0)
            {
                return;
            }

            // Mark the Vertex as visited in the shortest path tree set.
            this._sptSet.set(closetVertexID, true);

            // Grab the Vertex object from the graph using its ID.
            Vertex closeVertex = this._graph.getVertex(closetVertexID);

            // Grab the adjacents of the current Vertex object.
            HashMap<Vertex, Float> closeVertexAdjacents = closeVertex.getAdjacentVertices();

            // Here is where we loop through the adjacent Vertexes of the closeVertex object.
            for (Map.Entry<Vertex, Float> connection : closeVertexAdjacents.entrySet())
            {
                // Get the cost between the current (closest) Vertex object and its current adjacent Vertex.
                float costBetweenVertexes = closeVertexAdjacents.get(connection.getKey());
                // Check if the adjacent Vertex has already been visited...
                // ...(indicating a minimum path cost has already been calculated for it)
                boolean adjacentVertexVisited = this._sptSet.get(connection.getKey().getID());

                // Get the current cost from the source Vertex to this adjacent Vertex.
                float currentPathCost = this._distance.get(connection.getKey().getID());

                // Get the (minimal) potential cost.
                float potentialPathCost = this._distance.get(closetVertexID) + closeVertexAdjacents.get(connection.getKey());

                // Put it all together and check if the adjacent Vertex has a weight greater than 0, has not been visited,
                // and has a current path cost greater than the (minimal) potential path cost.
                if (costBetweenVertexes > 0 && !adjacentVertexVisited && currentPathCost > potentialPathCost)
                {
                    // If this results in true, update the distance from the source Vertex to the
                    // adjacent Vertex to the new smaller cost.
                    this._distance.set(connection.getKey().getID(), potentialPathCost);
                    this._predecessor.set(connection.getKey().getID(), closetVertexID);
                }
            }
        }
    }

    /**
     * Helper function which returns the next Vertex ID which has the lowest cost of travel from the source Vertex
     * @return a Vertex ID ranging from 0 to graph.size() - 1. However, this function may return (- 1) indicating that
     * there are Vertexes in the graph that the source Vertex has no way of reaching. Make sure to account for this.
     */
    private int minDistance()
    {
        float min = Float.MAX_VALUE;
        int minIndex = -1;

        // Loop through the distance array (these are the distances from the source Vertex to other Vertexes).
        for (int i = 0; i < this._distance.size(); ++i)
        {
            // If the distance from source Vertex to the other (current) Vertex ID...
            //...and the other Vertex ID has not been visited...
            if (this._distance.get(i) < min && !this._sptSet.get(i))
            {
                // ... update the min and minIndex variables.
                min = this._distance.get(i);
                minIndex = i;
            }
        }
        // Return the minIndex.
        return minIndex;
    }

    /**
     * After the calculation is performed, the distances from the source Vertex to other Vertexes is available.
     * @return an ArrayList<Integer> which represents the distances from the source Vertex to other Vertexes.
     * Each index of the ArrayList represents the ID of the Vertex in the graph.
     */
    public ArrayList<Float> getDistances()
    {
        return this._distance;
    }

    /**
     * This predecessor array is part of the calculation which stores the closest vertexes from the source vertex.
     * @return a ArrayList<Integer> which stores the closest vertexes from the source vertex
     */
    public ArrayList<Integer> getPredecessor()
    {
        return this._predecessor;
    }

    /**
     * Returns the minimum path to a destination vertex from the previously entered source vertex.
     * In more detail, it returns an ArrayList<Integer> containing the IDs of the minimum amount of vertexes
     * necessary to traverse (which is in order) to arrive at the destination vertex from the source vertex.
     * Note: Calling this function after the Graph object has been modified may return incorrect results.
     * Make a new Dijkstra object in order to receive the new and correct data.
     * @param destinationVertexName the name of the destination vertex in the Graph
     * @return an ArrayList<Integer> containing the IDs of the minimum amount of vertexes necessary to
     * traverse to a given destination vertex
     */
    public ArrayList<Integer> getMinPathTo(String destinationVertexName)
    {
        int destinationVertexID = this._graph.getVertex(destinationVertexName).getID();
        ArrayList<Integer> path = new ArrayList<>();
        while (destinationVertexID != -1)
        {
            path.add(destinationVertexID);
            destinationVertexID = this._predecessor.get(destinationVertexID);
        }
        Collections.reverse(path);
        return path;
    }
}