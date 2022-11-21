package de.phtp.worldguessr.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import de.phtp.worldguessr.R;
import de.phtp.worldguessr.model.AppDB;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {

    Button startButton;
    Button historyButton;
    AppDB db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.activity_main_start_game_button);
        historyButton = findViewById(R.id.activity_main_history_button);

        startButton.setOnClickListener(this);
        historyButton.setOnClickListener(this);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "AppDatabase").allowMainThreadQueries().build();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_main_start_game_button:
                Intent myIntent = new Intent(MainActivity.this, GameScreenActivity.class);
                startActivity(myIntent);
                Log.d("StartClick","button pressed");
                //GameControl.createInstance(db);
                break;
            case R.id.activity_main_history_button:
                Intent myIntent2 = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(myIntent2);
                Log.d("StartClick","button2 pressed");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        db.close();
    }
}
