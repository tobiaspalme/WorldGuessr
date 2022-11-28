package de.phtp.worldguessr.view.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import de.phtp.worldguessr.R;
import de.phtp.worldguessr.control.HistoryControl;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_game_history_screen);
        super.onCreate(savedInstanceState);

        AsyncTask.execute(() -> {
            ListAdapter myListAdapter =
                    new ArrayAdapter<String>(
                        this, // Die aktuelle Umgebung (diese Activity)
                        R.layout.my_list, // Die ID des Zeilenlayouts (der XML-Layout Datei)
                        R.id.my_list_text_view,   // Die ID eines TextView-Elements im Zeilenlayout
                        HistoryControl.getScoreList(getApplicationContext()));  // Beispieldaten in einer ArrayList

            ListView myListView = (ListView) findViewById(R.id.list_view);
            myListView.setAdapter(myListAdapter);
        });
    }
}
