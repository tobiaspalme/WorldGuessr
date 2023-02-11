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
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;

import de.phtp.worldguessr.R;
import de.phtp.worldguessr.control.GameControl;
import de.phtp.worldguessr.control.IGameControl;
import de.phtp.worldguessr.databinding.FragmentMapBinding;
import de.phtp.worldguessr.model.Place;
import de.phtp.worldguessr.view.activity.GameScreenActivity;

public class MapFragment extends Fragment implements View.OnClickListener {

    private FragmentMapBinding binding;
    private View root;

    private MapView map = null;

    private GameScreenActivity gameScreenActivity;

    private IGameControl gameControl;

    private boolean gameFinished = false;

    private FloatingActionButton floatingActionButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMapBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        Context ctx = getActivity();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        map = binding.map;

        gameScreenActivity = (GameScreenActivity) getActivity();

        gameControl = new GameControl();

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

        mapController.setZoom(gameScreenActivity.getCurrZoomLevel());
        mapController.setCenter(gameScreenActivity.getCurrMapCenter());

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
                if(!gameFinished){
                    setMarker(p, false);
                    //refresh map
                    map.invalidate();
                    //change icon to checkmark
                    floatingActionButton.show();

                }
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
        if (view.getId() == R.id.fragment_map_fab) {
            if (gameFinished) {
                requireActivity().finish();
            } else {
                AsyncTask.execute(() -> {
                    //Set final marker
                    Place realPlace = gameScreenActivity.getDao().getPlace(gameScreenActivity.getCurrentImageId());
                    GeoPoint realGeoPoint = new GeoPoint(realPlace.latitude, realPlace.longitude);
                    setMarker(realGeoPoint, true);

                    //draw line
                    GeoPoint p = ((Marker) map.getOverlays().get(1)).getPosition();
                    drawLine(p, realGeoPoint);

                    double distance = gameControl.calculateDistance(realGeoPoint.getLatitude(), realGeoPoint.getLongitude(), p.getLatitude(), p.getLongitude());
                    distance = gameControl.round(distance);
                    gameControl.insertScoreIntoDB(distance, gameScreenActivity.getDao());

                    //Snackbar
                    Snackbar distanceSnackbar = Snackbar.make(binding.fragmentMapFab, gameControl.buildSnackbarString(distance), Snackbar.LENGTH_INDEFINITE);
                    distanceSnackbar.show();
                });

                map.invalidate();
                floatingActionButton.setImageResource(R.drawable.ic_baseline_home_24);
                //lock map
                gameFinished = true;
            }
        }
    }

    @Override
    public void onDestroyView() {
        gameScreenActivity.setCurrMapCenter(map.getMapCenter());
        gameScreenActivity.setCurrZoomLevel(map.getZoomLevelDouble());
        super.onDestroyView();
    }

    public void setMarker(GeoPoint p, boolean isFinal) {
        if(!isFinal) {
            if(map.getOverlays().size() > 1) {
                map.getOverlays().remove(1);
            }
        }

        Marker startMarker = new Marker(map);
        startMarker.setPosition(p);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        startMarker.setInfoWindow(null);
        map.getOverlays().add(startMarker);
    }

    public void drawLine(GeoPoint start, GeoPoint finish) {
        Polyline line = new Polyline();
        line.addPoint(start);
        line.addPoint(finish);
        line.getOutlinePaint().setStrokeWidth(3);
        map.getOverlays().add(line);
    }
}
