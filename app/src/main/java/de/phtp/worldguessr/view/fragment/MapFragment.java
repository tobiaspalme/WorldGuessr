package de.phtp.worldguessr.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import de.phtp.worldguessr.R;
import de.phtp.worldguessr.control.MapControl;
import de.phtp.worldguessr.databinding.FragmentMapBinding;
import de.phtp.worldguessr.view.activity.MainActivity;

public class MapFragment extends Fragment implements View.OnClickListener {

    private FragmentMapBinding binding;
    private View root;

    private MapView map = null;

    private FloatingActionButton homeButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMapBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        Log.d("M1", "MapFragment Created");


        Context ctx = getActivity();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));


        map = binding.map;

        homeButton = binding.fragmentMapFab;
        homeButton.setOnClickListener(this);

        map.setTileSource(TileSourceFactory.MAPNIK);

        IMapController mapController = map.getController();
        mapController.setZoom(4);
        GeoPoint startPoint = new GeoPoint(48.8583, 2.2944);
        mapController.setCenter(startPoint);

        map.setHorizontalMapRepetitionEnabled(false);
        map.setVerticalMapRepetitionEnabled(false);

        //remove - and + buttons
        map.setBuiltInZoomControls(false);
        //use touch gestures to zoom
        map.setMultiTouchControls(true);

        updateTouchPosition();

        return root;
    }

    private void updateTouchPosition() {

        MapEventsReceiver mReceive = new MapEventsReceiver() {
            @Override
            public boolean singleTapConfirmedHelper(GeoPoint p) {
                MapControl.setMarker(map, p);
                //refresh map
                map.invalidate();
                //change icon to checkmark
                homeButton.setImageResource(R.drawable.ic_check);

                //Snackbar for testing purpose
                Snackbar snackbar = Snackbar
                        .make(binding.fragmentMapFab, "test123", Snackbar.LENGTH_INDEFINITE);
                snackbar.show();
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
                Intent myIntent = new Intent(getActivity(), MainActivity.class);
                startActivity(myIntent);
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
