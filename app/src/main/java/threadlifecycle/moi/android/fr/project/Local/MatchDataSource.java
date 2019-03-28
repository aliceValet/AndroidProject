package threadlifecycle.moi.android.fr.project.Local;

import java.util.List;

import io.reactivex.Flowable;
import threadlifecycle.moi.android.fr.project.Database.IMatchDataSource;
import threadlifecycle.moi.android.fr.project.Model.Match;

public class MatchDataSource implements IMatchDataSource {

    private MatchDAO matchDAO;
    private static MatchDataSource mInstance;

    public MatchDataSource(MatchDAO matchDAO) {this.matchDAO = matchDAO;}

    public static MatchDataSource getInstance(MatchDAO matchDAO) {
        if (mInstance == null) {
            mInstance = new MatchDataSource(matchDAO);
        }
        return mInstance;
    }

    @Override
    public Flowable<Match> getMatchById(int matchId) {
        return matchDAO.getMatchById(matchId);
    }

    @Override
    public Flowable<List<Match>> getAllMatches() {
        return matchDAO.getAllMatches();
    }
    /*
    @Override
    public Flowable<List<Match>> getSummary() {
        return matchDAO.getSummary();
    }
    */
    @Override
    public void insertMatch(Match... matches) {
        matchDAO.insertMatch(matches);
    }

    @Override
    public void updateMatch(Match... matches) {
        matchDAO.updateMatch(matches);
    }

    @Override
    public void deleteMatch(Match match) {
        matchDAO.deleteMatch(match);
    }

    @Override
    public void deleteAllmatches() {
        matchDAO.deleteAllmatches();
    }
    /*
    @Override
    public Flowable<List<Match>> statLet() {
        return matchDAO.statLet();
    }

    @Override
    public Flowable<List<Match>> statFaute() {
        return matchDAO.statFaute();
    }

    @Override
    public Flowable<List<Match>> statDuree() {
        return matchDAO.statDuree();
    }
    */
    @Override
    public Flowable<List<Match>> getPlayerMatches(String playerName) {
        return matchDAO.getPlayerMatches(playerName);
    }
}
