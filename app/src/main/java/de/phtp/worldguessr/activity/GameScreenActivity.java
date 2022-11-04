package de.phtp.worldguessr.activity;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import de.phtp.worldguessr.R;
import de.phtp.worldguessr.databinding.ActivityGameScreenBinding;


public class GameScreenActivity extends AppCompatActivity {


    private ActivityGameScreenBinding binding;


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
}
