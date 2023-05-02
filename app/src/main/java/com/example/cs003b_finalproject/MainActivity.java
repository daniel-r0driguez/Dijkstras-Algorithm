package com.example.cs003b_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

// Every activity is a context
public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private BuildingView R_BUILDING;
    private BuildingView V_BUILDING;
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