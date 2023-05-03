package com.example.cs003b_finalproject;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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
    private Button calculateButton;
    private ArrayList<Integer> buildingIDs;

    @Override
    public void onClick(View v)
    {
        if (v instanceof BuildingView)
        {
            Toast.makeText(this, "Building clicked", Toast.LENGTH_SHORT).show();
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

        this.calculateButton = findViewById(R.id.calculateShortestPathBtn);
        this.calculateButton.setOnClickListener(this::onCalculateBtnClick);

        this.isSelectingBuilding = true;
        this.startBuilding = null;
        this.destinationBuilding = null;
    }

    public void onBuildingClick(View v)
    {
        if (this.isSelectingBuilding)
        {
            this.startBuilding = (BuildingView) v;
            this.isSelectingBuilding = false;
        }
        else
        {
            this.destinationBuilding = (BuildingView) v;
            this.isSelectingBuilding = true;
        }
    }

    public void onCalculateBtnClick(View v)
    {
        GraphPCC pcc = GraphPCC.getInstance();
        if (this.startBuilding != null && this.destinationBuilding != null)
        {
            this.buildingIDs = pcc.getMinPathTo(this.startBuilding.getName(), this.destinationBuilding.getName());
        }
        ArrayList<Vertex> buildings = pcc.getVertexes();
        for (int i : this.buildingIDs)
        {
            System.out.println(buildings.get(i).getName());
        }
    }
}