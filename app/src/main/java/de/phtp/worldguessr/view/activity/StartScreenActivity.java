package de.phtp.worldguessr.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import de.phtp.worldguessr.R;

public class StartScreenActivity extends AppCompatActivity implements Button.OnClickListener {

    Button startButton;
    Button historyButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        startButton = findViewById(R.id.button);
        historyButton = findViewById(R.id.button2);

        startButton.setOnClickListener(this);
        historyButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                Intent myIntent = new Intent(StartScreenActivity.this, GameScreenActivity.class);
                startActivity(myIntent);
                Log.d("StartClick","button pressed");
                break;
            case R.id.button2:
                Intent myIntent2 = new Intent(StartScreenActivity.this, HistoryActivity.class);
                startActivity(myIntent2);
                Log.d("StartClick","button2 pressed");
                break;
        }
    }
}
