package threadlifecycle.moi.android.fr.project.Database;

import java.util.List;

import io.reactivex.Flowable;
import threadlifecycle.moi.android.fr.project.Model.Match;

public interface IMatchDataSource {
    Flowable<Match> getMatchById(int matchId);
    Flowable<List<Match>> getAllMatches();
    //Flowable<List<Match>> getSummary();
    void insertMatch(Match... matches);
    void updateMatch(Match... matches);
    void deleteMatch(Match match);
    void deleteAllmatches();
    //Flowable<List<Match>> statLet();
    //Flowable<List<Match>> statFaute();
    //Flowable<List<Match>> statDuree();
    Flowable<List<Match>> getPlayerMatches(String playerName);
}
