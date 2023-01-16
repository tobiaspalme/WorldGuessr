package de.phtp.worldguessr.control;

import java.util.List;

public interface IHistoryControl {

    /**
     * Get list of scores
     * @return list of type string
     */
    List<String> getScoreList();
}
