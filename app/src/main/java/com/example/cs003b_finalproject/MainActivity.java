package com.example.cs003b_finalproject;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

// Every activity is a context
public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private BuildingView R_BUILDING;
    private BuildingView V_BUILDING;

    private LinearLayout LIST;
    private ScrollView scrollViewList;
    @Override
    public void onClick(View v)
    {
        if (v instanceof BuildingView)
        {
            Toast.makeText(this, "Building clicked", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        this.R_BUILDING = findViewById(R.id.r_building);
        this.V_BUILDING = findViewById(R.id.v_building);
        this.R_BUILDING.setOnClickListener(this::onBuildingClick);
        this.V_BUILDING.setOnClickListener(this::onBuildingClick);
        this.LIST = findViewById(R.id.buildingListContainer);
        this.scrollViewList = findViewById(R.id.scrollViewList);

        GraphPCC pcc = GraphPCC.getInstance();
        pcc.getMinPathTo("Construction Area", "C Building");
        System.out.println(pcc.getMinPathTo("Construction Area", "L Building"));
    }

    protected void onBuildingClick(View v)
    {
        if (!(v instanceof BuildingView))
        {
            return;
        }
        BuildingView buildingView = (BuildingView) v;
        Toast.makeText(this, "Building " + buildingView.getName(), Toast.LENGTH_SHORT).show();
    }
}