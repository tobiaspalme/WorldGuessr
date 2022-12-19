package de.phtp.worldguessr.view.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import de.phtp.worldguessr.control.GameControl;
import de.phtp.worldguessr.control.IMapControl;
import de.phtp.worldguessr.control.MapControl;
import de.phtp.worldguessr.databinding.FragmentMapBinding;

public class MapFragment extends Fragment implements View.OnClickListener {

    private FragmentMapBinding binding;
    private View root;

    private MapView map = null;

    private IMapControl mapControl;

    private FloatingActionButton floatingActionButton;

    private boolean gameFinished = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMapBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        Context ctx = getActivity();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        map = binding.map;

        mapControl = MapControl.getInstance();
        mapControl.updateMap(map);

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

        if(mapControl != null) {
            mapController.setZoom(mapControl.getCurrZoomLevel());
            mapController.setCenter(mapControl.getCurrMapCenter());
        }

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
                mapControl.setMarker(p, false);
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
                    MapControl.deleteInstance();
                    requireActivity().finish();
                } else {
                    AsyncTask.execute(() -> {
                        String text = GameControl.getInstance().finalizeGame(map,mapControl);
                        Snackbar snackbar = Snackbar
                                .make(binding.fragmentMapFab, text, Snackbar.LENGTH_INDEFINITE);
                        snackbar.show();});
                    map.invalidate();
                    floatingActionButton.setImageResource(R.drawable.ic_baseline_home_24);
                    gameFinished = true;
                }
                break;
        }
    }

    @Override
    public void onDestroyView() {
        if(mapControl != null){
            mapControl.setCurrMapCenter(map.getMapCenter());
            mapControl.setCurrZoomLevel(map.getZoomLevelDouble());
        }
        super.onDestroyView();
    }


}
