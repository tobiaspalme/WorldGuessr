package de.phtp.worldguessr.control;

import android.content.Context;

import java.util.List;
import java.util.stream.Collectors;

import de.phtp.worldguessr.model.AppDB;
import de.phtp.worldguessr.model.DAO;
import de.phtp.worldguessr.model.Score;

public class HistoryControl implements IHistoryControl{

    private Context context;

    public HistoryControl(Context context) {
        this.context = context;
    }



    public List<String> getScoreList() {
        DAO dao = AppDB.getInstance(context).dao();
        List<Score> scores = dao.getAllScores();
        return scores.stream().map(Score::toString).collect(Collectors.toList());
    }
}
