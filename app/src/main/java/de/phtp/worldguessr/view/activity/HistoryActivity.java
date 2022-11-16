package de.phtp.worldguessr.view.activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import de.phtp.worldguessr.R;

public class HistoryActivity extends AppCompatActivity {

    String[] testArray = {
            "hallo",
            "das",
            "ist",
            "ein",
            "Test",
            "hallo",
            "das",
            "ist",
            "ein",
            "Test",
            "hallo",
            "das",
            "ist",
            "ein",
            "Test",
            "hallo",
            "das",
            "ist",
            "ein",
            "Test",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_game_history_screen);
        super.onCreate(savedInstanceState);

        ArrayAdapter<String> myArrayAdapter =
                new ArrayAdapter<String>(
                        this, // Die aktuelle Umgebung (diese Activity)
                        R.layout.my_list, // Die ID des Zeilenlayouts (der XML-Layout Datei)
                        R.id.my_list_text_view,   // Die ID eines TextView-Elements im Zeilenlayout
                        testArray);  // Beispieldaten in einer ArrayList

        ListView myListView = (ListView) findViewById(R.id.list_view);
        myListView.setAdapter(myArrayAdapter);

    }
}
