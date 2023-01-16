package de.phtp.worldguessr.control;

import org.osmdroid.views.MapView;

import de.phtp.worldguessr.view.fragment.MapFragment;

public interface IGameControl {

    int getPictureId();

    String finalizeGame(MapView map, MapFragment fragment);


}
