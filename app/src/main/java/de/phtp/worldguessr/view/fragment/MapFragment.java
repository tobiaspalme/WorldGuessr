package de.phtp.worldguessr.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;

import de.phtp.worldguessr.R;
import de.phtp.worldguessr.control.GameControl;
import de.phtp.worldguessr.control.MapControl;
import de.phtp.worldguessr.databinding.FragmentMapBinding;
import de.phtp.worldguessr.view.activity.MainActivity;

public class MapFragment extends Fragment implements View.OnClickListener {

    private FragmentMapBinding binding;
    private View root;

    private MapView map = null;

    private FloatingActionButton floatingActionButton;

    private boolean gameFinished = false;

    private final GeoPoint START_POINT = new GeoPoint(48.8583, 2.2944);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMapBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        Context ctx = getActivity();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        map = binding.map;

        mapSetUp();

        floatingActionButton = binding.fragmentMapFab;
        floatingActionButton.setOnClickListener(this);
        floatingActionButton.hide();


        updateTouchPosition();

        return root;
    }

    private void mapSetUp() {
        map.setTileSource(TileSourceFactory.MAPNIK);

        IMapController mapController = map.getController();
        mapController.setZoom(4.0);
        mapController.setCenter(START_POINT);

        map.setHorizontalMapRepetitionEnabled(false);
        map.setVerticalMapRepetitionEnabled(false);

        //remove - and + buttons
        map.setBuiltInZoomControls(false);
        //use touch gestures to zoom
        map.setMultiTouchControls(true);
    }

    private void updateTouchPosition() {

        MapEventsReceiver mReceive = new MapEventsReceiver() {
            @Override
            public boolean singleTapConfirmedHelper(GeoPoint p) {
                MapControl.setMarker(map, p);
                //refresh map
                map.invalidate();
                //change icon to checkmark
                floatingActionButton.show();
                return false;
            }

            @Override
            public boolean longPressHelper(GeoPoint p) {
                return false;
            }
        };

        MapEventsOverlay OverlayEvents = new MapEventsOverlay(mReceive);
        map.getOverlays().add(OverlayEvents);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_map_fab:
                if (gameFinished) {
                    GameControl.deleteInstance();
                    Intent myIntent = new Intent(getActivity(), MainActivity.class);
                    startActivity(myIntent);
                } else {
                    AsyncTask.execute(() -> {
                        String text = GameControl.getInstance().finalizeGame(((Marker)map.getOverlays().get(1)).getPosition());
                        Snackbar snackbar = Snackbar
                                .make(binding.fragmentMapFab, text, Snackbar.LENGTH_INDEFINITE);
                        snackbar.show();});
                    floatingActionButton.setImageResource(R.drawable.ic_baseline_home_24);
                    gameFinished = true;
                }
                break;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
        map.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        map.onResume();
    }

}
