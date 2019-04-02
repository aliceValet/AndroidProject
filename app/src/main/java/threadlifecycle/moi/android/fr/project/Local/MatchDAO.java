package threadlifecycle.moi.android.fr.project.Local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import threadlifecycle.moi.android.fr.project.Model.Match;

//Data access object. Interface permettant d'acceder a l'objet match cree dans Match.
//Definit interactions avec la database
@Dao
public interface MatchDAO {
    //tag pour generer une requete SQL
    @Query("SELECT * FROM matches WHERE id=:matchId")
    Flowable<Match> getMatchById(int matchId); //retourne un objet de type Flowable (liste de classes)

    @Query("SELECT * FROM matches")
    Flowable<List<Match>> getAllMatches();

    /*
    @Query("SELECT winnerName AND dureeMatch FROM matches")
    Flowable<List<Match>> getSummary();
    */

    @Insert
    void insertMatch(Match... matches);

    @Update
    void updateMatch(Match... matches);

    @Delete
    void deleteMatch(Match match);

    @Query("DELETE FROM matches")
    void deleteAllmatches();

    /*
    @Query("SELECT letWinner AND letLoser FROM matches")
    Flowable<List<Match>> statLet();

    @Query("SELECT fauteWinner AND fauteLoser FROM matches")
    Flowable<List<Match>> statFaute();

    @Query("SELECT dureeMatch FROM matches")
    Flowable<List<Match>> statDuree();
    */

    @Query("SELECT * FROM matches WHERE winnerName=:playerName OR loserName=:playerName")
    Flowable<List<Match>> getPlayerMatches(String playerName);

}
