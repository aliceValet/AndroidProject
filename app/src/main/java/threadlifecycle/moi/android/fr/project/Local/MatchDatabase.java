package threadlifecycle.moi.android.fr.project.Local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import threadlifecycle.moi.android.fr.project.Model.Match;

import static threadlifecycle.moi.android.fr.project.Local.MatchDatabase.DATABASE_VERSION;

@Database(entities = Match.class, version = DATABASE_VERSION)
public abstract class MatchDatabase extends RoomDatabase {
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME = "Match-Database-Room";

    public abstract MatchDAO matchDAO();

    private static MatchDatabase mInstance;

    public static MatchDatabase getInstance(Context context) {
        if (mInstance == null) {
            mInstance = Room.databaseBuilder(context, MatchDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return mInstance;
    }
}
