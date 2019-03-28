package threadlifecycle.moi.android.fr.project.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "matches")
public class Match {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "winnerName")
    private String winnerName;

    @ColumnInfo(name = "loserName")
    private String loserName;

    @ColumnInfo(name = "latitude")
    private int latitude;

    @ColumnInfo(name = "longitude")
    private int longitude;

    @ColumnInfo(name = "typeMatch")
    private String typeMatch;

    @ColumnInfo(name = "dureeMatch")
    private String dureeMatch;

    @ColumnInfo(name = "classement")
    private String classement;

    @ColumnInfo(name = "letWinner")
    private int letWinner;

    @ColumnInfo(name = "letLoser")
    private int letLoser;

    @ColumnInfo(name = "fauteWinner")
    private int fauteWinner;

    @ColumnInfo(name = "fauteLoser")
    private int fauteLoser;

    public Match() {

    }

    @Ignore
    public Match(String winnerName, String loserName, int latitude, int longitude,
                 String typeMatch, String dureeMatch, String classement, int letWinner,
                 int letLoser, int fauteWinner, int fauteLoser) {
        this.winnerName = winnerName;
        this.loserName = loserName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.typeMatch = typeMatch;
        this.dureeMatch = dureeMatch;
        this.classement = classement;
        this.letWinner = letWinner;
        this.letLoser = letLoser;
        this.fauteWinner = fauteWinner;
        this.fauteLoser = fauteLoser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    public String getLoserName() {
        return loserName;
    }

    public void setLoserName(String loserName) {
        this.loserName = loserName;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public String getTypeMatch() {
        return typeMatch;
    }

    public void setTypeMatch(String typeMatch) {
        this.typeMatch = typeMatch;
    }

    public String getDureeMatch() {
        return dureeMatch;
    }

    public void setDureeMatch(String dureeMatch) {
        this.dureeMatch = dureeMatch;
    }

    public String getClassement() {
        return classement;
    }

    public void setClassement(String classement) {
        this.classement = classement;
    }

    public int getLetWinner() {
        return letWinner;
    }

    public void setLetWinner(int letWinner) {
        this.letWinner = letWinner;
    }

    public int getLetLoser() {
        return letLoser;
    }

    public void setLetLoser(int letLoser) {
        this.letLoser = letLoser;
    }

    public int getFauteWinner() {
        return fauteWinner;
    }

    public void setFauteWinner(int fauteWinner) {
        this.fauteWinner = fauteWinner;
    }

    public int getFauteLoser() {
        return fauteLoser;
    }

    public void setFauteLoser(int fauteLoser) {
        this.fauteLoser = fauteLoser;
    }

    @Override
    public String toString() {
        return new StringBuilder(winnerName).append(dureeMatch).append("\n").toString();
    }
}
