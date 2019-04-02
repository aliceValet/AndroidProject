package threadlifecycle.moi.android.fr.project.Local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import threadlifecycle.moi.android.fr.project.Model.Match;

import static threadlifecycle.moi.android.fr.project.Local.MatchDatabase.DATABASE_VERSION;

//tag pour base de donnees. entities=Match.class transforme Match en classe.
//Recupere l'entite dans match (instructions pour la mise enforme de la table
@Database(entities = Match.class, version = DATABASE_VERSION)
public abstract class MatchDatabase extends RoomDatabase {
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME = "Match-Database-Room";

    public abstract MatchDAO matchDAO();

    private static MatchDatabase mInstance;

    public static MatchDatabase getInstance(Context context) {
        //si base de donnees n'existe pas.
        if (mInstance == null) {
            mInstance = Room.databaseBuilder(context, MatchDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration() //si les migrations ne correspondent pas aux caracteristiques de la database
                    //Allows Room to destructively recreate database tables if Migrations that would migrate old database schemas
                    // to the latest schema version are not found.
                    .build();
        }
        return mInstance;
    }
}
