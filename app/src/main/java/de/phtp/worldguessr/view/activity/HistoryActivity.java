package de.phtp.worldguessr.view.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.stream.Collectors;

import de.phtp.worldguessr.R;
import de.phtp.worldguessr.model.AppDB;
import de.phtp.worldguessr.model.DAO;
import de.phtp.worldguessr.model.Score;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_game_history_screen);
        super.onCreate(savedInstanceState);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        AsyncTask.execute(() -> {
            DAO dao = AppDB.getInstance(this).dao();
            List<Score> scores = dao.getAllScores();
            List<String> entries = scores.stream().map(Score::toString).collect(Collectors.toList());

            ListAdapter myListAdapter =
                    new ArrayAdapter<>(
                            this, // Die aktuelle Umgebung (diese Activity)
                            R.layout.my_list, // Die ID des Zeilenlayouts (der XML-Layout Datei)
                            R.id.my_list_text_view,   // Die ID eines TextView-Elements im Zeilenlayout
                            entries);  // Beispieldaten in einer ArrayList

            ListView myListView = findViewById(R.id.list_view);
            myListView.setAdapter(myListAdapter);
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(myIntent);
        return true;
    }
}
