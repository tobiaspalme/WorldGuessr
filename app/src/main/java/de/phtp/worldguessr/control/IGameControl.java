package de.phtp.worldguessr.control;

import org.osmdroid.views.MapView;

import de.phtp.worldguessr.view.fragment.MapFragment;

public interface IGameControl {

    /**
     * returns the picture id of the current picture
     * @return the picture id of the current picture
     */
    int getPictureId();

    /**
     * sets the final marker, draws the line between the points, calculate the distance and inserts it as a score into db
     * @param map the current map
     * @param fragment the current MapFragment
     * @return snack-bar string
     */
    String finalizeGame(MapView map, MapFragment fragment);


}
