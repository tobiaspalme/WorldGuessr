package de.phtp.worldguessr.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import de.phtp.worldguessr.R;

public class StartScreenActivity extends AppCompatActivity {

    Button startButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        startButton = findViewById(R.id.button);

        registerStartButton();
    }

    public void registerStartButton() {
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(StartScreenActivity.this, GameScreenActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
