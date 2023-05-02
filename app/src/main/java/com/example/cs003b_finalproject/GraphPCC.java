package com.example.cs003b_finalproject;
import androidx.annotation.NonNull;

import com.example.cs003b_finalproject.utils.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GraphPCC
{
    private static GraphPCC _instance;
    private final Graph _graph;
    private final PCC_Coordinates _pcc_coordinates;

    private Dijkstra _dijkstraObj;

    public static GraphPCC getInstance()
    {
        if (_instance == null)
        {
            _instance = new GraphPCC();
        }
        return _instance;
    }

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

    // TODO fix this!
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

    public ArrayList<Integer> getMinPathTo(String srcVertexName, String desVertexName)
    {
        this._dijkstraObj = new Dijkstra(this._graph, srcVertexName);
//        for (int i = 0; i < this._dijkstraObj.getDistances().size(); ++i)
//        {
//            System.out.printf("%s --> %s : minimum cost = %f\n", srcVertexName, this._graph.getVertex(i).getName(), this._dijkstraObj.getDistances().get(i));
//        }
        return this._dijkstraObj.getMinPathTo(desVertexName);
    }

    public ArrayList<Float> getMinDistances()
    {
        if (this._dijkstraObj != null)
        {
            return this._dijkstraObj.getDistances();
        }
        return null;
    }

    @NonNull
    @Override
    public String toString()
    {
        return this._graph.toString();
    }
}