package threadlifecycle.moi.android.fr.project.Database;

import java.util.List;

import io.reactivex.Flowable;
import threadlifecycle.moi.android.fr.project.Local.MatchDataSource;
import threadlifecycle.moi.android.fr.project.Model.Match;

//manipule les donnees dnas la base de donnees
public class MatchRepository implements IMatchDataSource{

    private IMatchDataSource mLocalDataSource;
    private static MatchRepository mInstance;

    public MatchRepository(IMatchDataSource mLocalDataSource) { this.mLocalDataSource = mLocalDataSource; }

    public static MatchRepository getInstance(IMatchDataSource mLocalDataSource) {
        if (mInstance == null) {
            mInstance = new MatchRepository(mLocalDataSource);
        }
        return mInstance;
    }

    @Override
    public Flowable<Match> getMatchById(int matchId) {
        return mLocalDataSource.getMatchById(matchId);
    }

    @Override
    public Flowable<List<Match>> getAllMatches() {
        return mLocalDataSource.getAllMatches();
    }
    /*
    @Override
    public Flowable<List<Match>> getSummary() {
        return mLocalDataSource.getSummary();
    }
    */
    @Override
    public void insertMatch(Match... matches) {
        mLocalDataSource.insertMatch(matches);
    }

    @Override
    public void updateMatch(Match... matches) {
        mLocalDataSource.updateMatch(matches);
    }

    @Override
    public void deleteMatch(Match match) {
        mLocalDataSource.deleteMatch(match);
    }

    @Override
    public void deleteAllmatches() {
        mLocalDataSource.deleteAllmatches();
    }
    /*
    @Override
    public Flowable<List<Match>> statLet() {
        return mLocalDataSource.statLet();
    }

    @Override
    public Flowable<List<Match>> statFaute() {
        return mLocalDataSource.statFaute();
    }

    @Override
    public Flowable<List<Match>> statDuree() {
        return mLocalDataSource.statDuree();
    }
    */
    @Override
    public Flowable<List<Match>> getPlayerMatches(String playerName) {
        return mLocalDataSource.getPlayerMatches(playerName);
    }
}
