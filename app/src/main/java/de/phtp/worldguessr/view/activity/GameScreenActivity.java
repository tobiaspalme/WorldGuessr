package de.phtp.worldguessr.view.activity;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import org.osmdroid.api.IGeoPoint;
import org.osmdroid.util.GeoPoint;

import de.phtp.worldguessr.R;
import de.phtp.worldguessr.databinding.ActivityGameScreenBinding;


public class GameScreenActivity extends AppCompatActivity {


    private ActivityGameScreenBinding binding;

    private IGeoPoint currMapCenter = new GeoPoint(0.0,0.0);
    private double currZoomLevel = 4.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGameScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setUpBottomNavBar();
    }

    private void setUpBottomNavBar(){
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_picture, R.id.navigation_map)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.fragment_game_screen);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);
    }

    public IGeoPoint getCurrMapCenter() {
        return currMapCenter;
    }

    public void setCurrMapCenter(IGeoPoint currMapCenter) {
        this.currMapCenter = currMapCenter;
    }

    public double getCurrZoomLevel() {
        return currZoomLevel;
    }

    public void setCurrZoomLevel(double currZoomLevel) {
        this.currZoomLevel = currZoomLevel;
    }
}
