package com.example.cs003b_finalproject;

import com.example.cs003b_finalproject.utils.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This class is follows a singleton pattern since the map of PCC hardly ever changes drastically.
 * Use the getInstance() method to grab the instance of the class.
 */
public class GraphPCC
{
    private static GraphPCC _instance;
    private final Graph _graph;
    private final PCC_Coordinates _pcc_coordinates;

    private Dijkstra _dijkstraObj;

    /**
     * Allows for the retrieval of the GraphPCC instance.
     * This prevents from instantiation of the same Graph over an over again.
     * @return the instance of the GraphPCC
     */
    public static GraphPCC getInstance()
    {
        if (_instance == null)
        {
            _instance = new GraphPCC();
        }
        return _instance;
    }

    /**
     * Private default constructor. Initializes the the Graph object of the GraphPCC
     * to reflect the buildings edge weights relative to each other.
     */
    private GraphPCC()
    {
        this._graph = new Graph(false);
        this._pcc_coordinates = PCC_Coordinates.getInstance();
        setupGraph();
//        setupGraph2();
        this._dijkstraObj = null;
    }

    private void setupGraph2()
    {
        this._graph.addVertex("a"); // ID = 0
        this._graph.addVertex("b"); // ID = 1
        this._graph.addVertex("c"); // ID = 2

        this._graph.addEdge("a", "b", 2);
        this._graph.addEdge("a", "c", 19);
        this._graph.addEdge("b", "c", 2);
        this._graph.addEdge("b", "a", 10);
        this._graph.addEdge("c", "a", 19);
        this._graph.addEdge("c", "b", 2);

    }

    /**
     * Private helper function which sets up the Graph object.
     */
    private void setupGraph()
    {
        HashMap<String, ArrayList<Float>> buildingHashMap = this._pcc_coordinates.getBuildingCoordinates();

        for (String buildingName : buildingHashMap.keySet())
        {
            this._graph.addVertex(buildingName);
        }

        int count = 0;
        for (Map.Entry<String, ArrayList<Float>> outerSet : buildingHashMap.entrySet())
        {
            Vertex curVertex = this._graph.getVertex(outerSet.getKey());
            for (Map.Entry<String, ArrayList<Float>> innerSet : buildingHashMap.entrySet())
            {
                ++count;
                Vertex otherVertex = this._graph.getVertex(innerSet.getKey());

                if (count == 23)
                {
                    return;
                }

                if (!curVertex.equals(otherVertex))
                {
                    float lat1 = outerSet.getValue().get(0);
                    float lon1 = outerSet.getValue().get(1);
                    float lat2 = innerSet.getValue().get(0);
                    float lon2 = innerSet.getValue().get(1);

                    float distance = (float) this._pcc_coordinates.getDistance(lat1, lat2, lon1, lon2);

                    System.out.println(curVertex + " --> " + otherVertex);
                    System.out.println("Distance: " + distance);

                    this._graph.addEdge(curVertex.getName(), otherVertex.getName(), distance);
                }
            }
        }
    }

    /**
     * Calculates the minimum path from one building to the other.
     * @param srcBuildingName the name of the src building
     * @param desBuildingName the name of the destination building
     * @return
     */
    public ArrayList<Integer> getMinPathTo(String srcBuildingName, String desBuildingName)
    {
        this._dijkstraObj = new Dijkstra(this._graph, srcBuildingName);
//        for (int i = 0; i < this._dijkstraObj.getDistances().size(); ++i)
//        {
//            System.out.printf("%s --> %s : minimum cost = %f\n", srcVertexName, this._graph.getVertex(i).getName(), this._dijkstraObj.getDistances().get(i));
//        }
        return this._dijkstraObj.getMinPathTo(desBuildingName);
    }

    /**
     * Returns an ArrayList<Float> representing the minimum distances necessary to go from the source building
     * to all other buildings.
     * Note: getMinPathTo() should be called first since the algorithm requires a source Vertex to function.
     * @return an ArrayList<Float> with minimum distances from one building to all other buildings
     */
    public ArrayList<Float> getMinDistances()
    {
        if (this._dijkstraObj != null)
        {
            return this._dijkstraObj.getDistances();
        }
        return null;
    }

    /**
     * Returns the String representation of the GraphPCC object.
     * @return a String representation of GraphPCC
     */
    @Override
    public String toString()
    {
        return this._graph.toString();
    }
}