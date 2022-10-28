package de.phtp.worldguessr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openStartScreen();
    }

    public void openStartScreen(){
        Intent myIntent = new Intent(MainActivity.this, StartScreenActivity.class);
        MainActivity.this.startActivity(myIntent);
    }
}