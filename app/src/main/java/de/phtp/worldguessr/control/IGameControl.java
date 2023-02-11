package de.phtp.worldguessr.control;

import de.phtp.worldguessr.model.DAO;

public interface IGameControl {

    /**
     * calculates the distance between two points
     * @param lat1 latitude of point 1
     * @param lon1 longitude of point 1
     * @param lat2 latitude of point 2
     * @param lon2 longitude of point 2
     * @return distance between two points
     */
    double calculateDistance(double lat1, double lon1, double lat2, double lon2);

    /**
     * rounds a double value with two decimal places
     * @param value value to be rounded
     * @return the rounded value
     */
    double round(double value);

    /**
     * inserts score (distance + timestamp)
     * @param distance double distance
     * @param dao dao
     */
    void insertScoreIntoDB(double distance, DAO dao);

    /**
     * builds the string containing the score
     * @param distance double distance
     * @return the string containing the score
     */
    String buildSnackbarString(double distance);

}
