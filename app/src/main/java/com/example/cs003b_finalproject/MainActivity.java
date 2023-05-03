package com.example.cs003b_finalproject;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cs003b_finalproject.utils.Dijkstra;
import com.example.cs003b_finalproject.utils.Vertex;

import java.util.ArrayList;

/**
 * Main screen (activity) of the app.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private BuildingView startBuilding;
    private BuildingView destinationBuilding;
    private boolean isSelectingBuilding;
    private TextView startBuildingTxt;
    private TextView destinationBuildingTxt;
    private ArrayList<Integer> buildingIDs;
    private LinearLayout buildingList;

    /**
     * Generic onClick method. In this activity, it handles Views which are not BuildingViews.
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v)
    {
        if (v instanceof BuildingView)
        {
            Toast.makeText(this, "Not a building", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Runs when the activity is just being started.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        this.buildingList = findViewById(R.id.buildingListContainer);
        this.startBuildingTxt = findViewById(R.id.selectedSourceTxt);
        this.destinationBuildingTxt = findViewById(R.id.selectedDestinationTxt);
        this.isSelectingBuilding = true;
        this.startBuilding = null;
        this.destinationBuilding = null;
    }

    @SuppressLint("SetTextI18n")
    public void onBuildingClick(View v)
    {
        if (this.isSelectingBuilding)
        {
            this.startBuilding = (BuildingView) v;
            this.isSelectingBuilding = false;
            this.startBuildingTxt.setText("Start: " + this.startBuilding.getName());
        }
        else
        {
            this.destinationBuilding = (BuildingView) v;
            this.isSelectingBuilding = true;
            this.destinationBuildingTxt.setText("Destination: " + this.destinationBuilding.getName());
        }
    }

    /**
     * Starts the calculation process
     * @param v the View object that invoked this method
     */
    public void onCalculateBtnClick(View v)
    {
        GraphPCC pcc = GraphPCC.getInstance();
        if (this.startBuilding != null && this.destinationBuilding != null)
        {
            this.buildingIDs = pcc.getMinPathTo(this.startBuilding.getName(), this.destinationBuilding.getName());
            this.buildingList.removeAllViews();
        }
        ArrayList<Vertex> buildings = pcc.getVertexes();
        for (int i : this.buildingIDs)
        {
            TextView buildingEntry = new TextView(this);
            buildingEntry.setText(buildings.get(i).getName());
            this.buildingList.addView(buildingEntry);
        }
    }
}