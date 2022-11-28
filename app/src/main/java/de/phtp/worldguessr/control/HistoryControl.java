package de.phtp.worldguessr.control;

import android.content.Context;

import java.util.List;
import java.util.stream.Collectors;

import de.phtp.worldguessr.model.AppDB;
import de.phtp.worldguessr.model.DAO;
import de.phtp.worldguessr.model.Score;

public class HistoryControl {
    public static List<String> getScoreList(Context context) {
        DAO dao = AppDB.getInstance(context).dao();
        List<Score> scores = dao.getAllScores();
        return scores.stream().map(Score::toString).collect(Collectors.toList());
    }
}
