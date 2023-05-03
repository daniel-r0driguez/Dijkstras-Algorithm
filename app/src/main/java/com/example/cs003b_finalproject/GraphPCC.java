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

        String srcBuildingName = "CC Building";
        String destinationBuildingName;

        destinationBuildingName = "L Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName ="Lot 1";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Bookstore";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "L Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "D Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        srcBuildingName = "L Building";

        destinationBuildingName = "CC Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "D Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "G Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Bookstore";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "R Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "C Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        srcBuildingName = "D Building";

        destinationBuildingName = "L Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "C Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "G Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "R Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Bookstore";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "E Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        srcBuildingName = "E Building";

        destinationBuildingName = "Shatford Library";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "C Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Construction Area";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Lot 5";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        srcBuildingName = "Shatford Library";

        destinationBuildingName = "E Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Construction Area";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "C Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "V Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));


        srcBuildingName = "Lot 7";
        destinationBuildingName = "Shatford Library";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Construction Area";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        srcBuildingName = "Lot 5";

        destinationBuildingName = "Construction Area";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Sci Vil";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Center for the Arts";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        srcBuildingName = "Lot 4";

        destinationBuildingName = "Lot 3";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "FS Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "FB Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "FC Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "GM Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        srcBuildingName = "Lot 3";

        destinationBuildingName = "FS Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "W Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Bookstore";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        srcBuildingName = "Lot 1";

        destinationBuildingName = "CC Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Bookstore";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        srcBuildingName = "Sci Vil";

        destinationBuildingName = "Lot 5";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "GM Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "A Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        srcBuildingName = "Bookstore";
        destinationBuildingName = "W Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "CC Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "R Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Z Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "G Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Lot 3";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        srcBuildingName = "G Building";

        destinationBuildingName = "R Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Bookstore";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "CC Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "L Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "D Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        srcBuildingName = "R Building";

        destinationBuildingName = "G Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Z Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Bookstore";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "CC Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "L Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "D Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "C Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "V Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Center for the Arts";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "GM Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        srcBuildingName = "Z Building";

        destinationBuildingName = "R Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Bookstore";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "W Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));


        srcBuildingName = "C Building";

        destinationBuildingName = "R Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "G Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Construction Area";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "E Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Shatford Library";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "V Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "D Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "CC Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "L Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        srcBuildingName = "Construction Area";

        destinationBuildingName = "Shatford Library";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "C Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Lot 5";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "V Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Center for the Arts";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Lot 7";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "A Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        srcBuildingName = "W Building";

        destinationBuildingName = "Bookstore";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Z Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "FS Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Lot 3";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        srcBuildingName = "V Building";

        destinationBuildingName = "C Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Construction Area";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Center for the Arts";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Z Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Lot 5";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        srcBuildingName = "Center for the Arts";

        destinationBuildingName = "GM Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "A Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Lot 5";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "V Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "FC Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        srcBuildingName = "FS Building";

        destinationBuildingName = "FB Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "W Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Lot 4";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Lot 3";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        srcBuildingName = "FB Building";

        destinationBuildingName = "FS Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "FC Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        srcBuildingName = "FC Building";

        destinationBuildingName = "FB Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "GM Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Center for the Arts";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        srcBuildingName = "GM Building";

        destinationBuildingName = "A Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "FC Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Center for the Arts";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Lot 4";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        srcBuildingName = "A Building";

        destinationBuildingName = "Lot 5";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "GM Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Sci Vil";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Center for the Arts";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "Shatford Library";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));

        destinationBuildingName = "E Building";
        this._graph.addEdge(srcBuildingName, destinationBuildingName,calculateDistance(srcBuildingName, destinationBuildingName));
    }

    public float calculateDistance(String src, String des)
    {
        HashMap<String, ArrayList<Float>> buildingHashMap = this._pcc_coordinates.getBuildingCoordinates();
        ArrayList<Float> srcPos = buildingHashMap.get(src);
        ArrayList<Float> desPos = buildingHashMap.get(des);

        return (float) this._pcc_coordinates.getDistance(srcPos.get(0), desPos.get(0), srcPos.get(1), desPos.get(1));
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
        for (int i : this._dijkstraObj.getMinPathTo(desBuildingName))
        {
            System.out.println(this._graph.getVertex(i).getName());
        }
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