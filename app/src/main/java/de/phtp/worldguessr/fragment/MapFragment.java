package de.phtp.worldguessr.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;

import de.phtp.worldguessr.activity.MainActivity;
import de.phtp.worldguessr.activity.StartScreenActivity;
import de.phtp.worldguessr.databinding.FragmentMapBinding;

public class MapFragment extends Fragment {

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

        map.setTileSource(TileSourceFactory.MAPNIK);

        IMapController mapController = map.getController();
        mapController.setZoom(9.5);
        GeoPoint startPoint = new GeoPoint(48.8583, 2.2944);
        mapController.setCenter(startPoint);



        //remove - and + buttons
        map.setBuiltInZoomControls(false);
        //use touch gestures to zoom
        map.setMultiTouchControls(true);


        updateTouchPosition();
        registerHomeButton();


        return root;
    }

    private void registerHomeButton() {
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getActivity(), StartScreenActivity.class);
                startActivity(myIntent);
            }
        });
    }

    private void updateTouchPosition(){

        MapEventsReceiver mReceive = new MapEventsReceiver() {
            @Override
            public boolean singleTapConfirmedHelper(GeoPoint p) {
                if(map.getOverlays().size() > 1) {
                    map.getOverlays().remove(1);
                }

                GeoPoint geoPoint = new GeoPoint(p.getLatitude(),p.getLongitude());
                Marker startMarker = new Marker(map);
                startMarker.setPosition(geoPoint);
                startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                map.getOverlays().add(startMarker);
                map.invalidate();

                return false;
            }

            @Override
            public boolean longPressHelper(GeoPoint p) {
                return false;
            }
        };


        MapEventsOverlay OverlayEvents = new MapEventsOverlay(getActivity(), mReceive);
        map.getOverlays().add(OverlayEvents);
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
