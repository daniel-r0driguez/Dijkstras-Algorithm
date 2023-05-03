package com.example.cs003b_finalproject;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Simple class which keeps track of the PCC building's coordinates on the surface of the Earth.
 * This means the coordinates are in latitude and longitude.
 * This class also follows the singleton pattern.
 */
public class PCC_Coordinates
{
    private static PCC_Coordinates _instance;
    private final HashMap<String, ArrayList<Float>> _buildingCoordinates;

    /**
     * Returns the instance of the PCC_Coordinates object.
     * @return the sole instance of the PCC_Coordinates
     */
    public static PCC_Coordinates getInstance()
    {
        if (_instance == null)
        {
            _instance = new PCC_Coordinates();
        }
        return _instance;
    }


    /**
     * Private default constructor.
     * Constructs a HashMap which has a building name as its key and its position (in latitude and longitude).
     */
    private PCC_Coordinates()
    {
        final int BUILDING_COUNT = 23;
        this._buildingCoordinates = new HashMap<>(BUILDING_COUNT);
        ArrayList<Float> coordinates;

        coordinates = setNewCoords(34.14502488318315f, -118.12015331712082f);
        addEntry("CC Building", coordinates);

        coordinates = setNewCoords(34.14542586429799f, -118.11950881071225f);
        addEntry("L Building", coordinates);

        coordinates = setNewCoords(34.145253950736596f, -118.11888267940695f);
        addEntry("D Building", coordinates);

        coordinates = setNewCoords(34.14524412709184f, -118.11751171878346f);
        addEntry("E Building", coordinates);

        coordinates = setNewCoords(34.145283421655584f, -118.11675798725956f);
        addEntry("Shatford Library", coordinates);

        coordinates = setNewCoords(34.14399405922061f, -118.12064831040232f);
        addEntry("IT Building", coordinates);

        coordinates = setNewCoords(34.14444104041833f, -118.12019132357287f);
        addEntry("Bookstore", coordinates);

        coordinates = setNewCoords(34.14461541156305f, -118.11959783418396f);
        addEntry("G Building", coordinates);

        coordinates = setNewCoords(34.1444361285499f, -118.11935153608759f);
        addEntry("R Building", coordinates);

        coordinates = setNewCoords(34.14426666891374f, -118.1196126714187f);
        addEntry("Z Building", coordinates);

        coordinates = setNewCoords(34.1445756604397f, -118.11820170524082f);
        addEntry("C Building", coordinates);

        coordinates = setNewCoords(34.1444828025932f, -118.11639123644072f);
        addEntry("Construction Area", coordinates);

        coordinates = setNewCoords(34.143883444921f, -118.12002237352264f);
        addEntry("W Building", coordinates);

        coordinates = setNewCoords(34.14391561443586f, -118.11919424465304f);
        addEntry("LP Building", coordinates);

        coordinates = setNewCoords(34.14409502160319f, -118.1180990511975f);
        addEntry("V Building", coordinates);

        coordinates = setNewCoords(34.14368464581402f, -118.1180264655118f);
        addEntry("Center for the Arts", coordinates);

        coordinates = setNewCoords(34.14323841874923f, -118.12033280872525f);
        addEntry("P Building", coordinates);

        coordinates = setNewCoords(34.14310683629729f, -118.12036191875862f);
        addEntry("O Building", coordinates);

        coordinates = setNewCoords(34.1432069131104f, -118.11980882812483f);
        addEntry("FS Building", coordinates);

        coordinates = setNewCoords(34.14317911400753f, -118.11935426375777f);
        addEntry("FB Building", coordinates);

        coordinates = setNewCoords(34.14334220194705f, -118.11898702949078f);
        addEntry("FC Building", coordinates);

        coordinates = setNewCoords(34.143282897279555f, -118.11817642683246f);
        addEntry("GM Building", coordinates);

        coordinates = setNewCoords(34.14332181597318f, -118.11729864736508f);
        addEntry("A Building", coordinates);
    }

    /**
     * Sets an ArrayList<Float> to new coordinates.
     * @param lat the latitude
     * @param lon the longitude
     * @return a new ArrayList<Float> with the position in latitude and longitude
     */
    private ArrayList<Float> setNewCoords(float lat, float lon)
    {
        ArrayList<Float> temp = new ArrayList<>(2);
        temp.add(lat);
        temp.add(lon);
        return temp;
    }

    /**
     * Adds an entry to the HashMap
     * @param buildingName the key, which is the name of the building
     * @param coordinates the value, which is the position of the building (in latitude and longitude)
     */
    private void addEntry(String buildingName, ArrayList<Float> coordinates)
    {
        this._buildingCoordinates.put(buildingName, coordinates);
    }

    /**
     * Returns the HashMap containing the coordinates of the buildings.
     * @return a HashMap<String, ArrayList<Float>> which represents the coordinates of each building.
     */
    public HashMap<String, ArrayList<Float>> getBuildingCoordinates()
    {
        return this._buildingCoordinates;
    }

    /**
     * Calculates the distance (in meters) between two points.
     * These points must be in degrees and should be latitude and longitude of the object.
     * This algorithm was implemented thanks to the help of
     * <a href="https://en.wikipedia.org/wiki/Haversine_formula">...</a>
     * @param lat1 the latitude of the first object
     * @param lat2 the latitude of the second object
     * @param lon1 the longitude of the first object
     * @param lon2 the longitude of the second object
     * @return the distance (in meters) between the two objects.
     */
    public double getDistance(float lat1, float lat2, float lon1, float lon2)
    {
        final float RADIUS = 6_371_000.f; // Radius of Earth in meters
        double rad_lat1 = Math.toRadians(lat1);
        double rad_lat2 = Math.toRadians(lat2);
        double rad_lon1 = Math.toRadians(lon1);
        double rad_lon2 = Math.toRadians(lon2);

        double a = Math.sqrt(haversine(rad_lat2 - rad_lat1) +
                ((1 - haversine(rad_lat1 - rad_lat2) - haversine(rad_lat1 - rad_lat2)) *
                        haversine(rad_lon2 - rad_lon1)));
        return 2 * RADIUS * Math.asin(a);
    }

    /**
     * Helper function used to calculate the distance from one latitude/longitude point to another.
     * @param angleInRadians the angle in radian form
     * @return the result of the haversine function
     */
    private double haversine(double angleInRadians)
    {
        return Math.pow((Math.sin(angleInRadians) / 2.f), 2);
    }
}
