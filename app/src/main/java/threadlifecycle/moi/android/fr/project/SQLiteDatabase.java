package threadlifecycle.moi.android.fr.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;

public class SQLiteDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Matches.db";

    public static final String TABLE_MATCHES = "matches_table";
    public static final String COL_ID = "id";
    public static final String COL_PLAYER1 = "player1";
    public static final String COL_PLAYER2 = "player2";
    public static final String COL_LET1 = "let1";
    public static final String COL_LET2 = "let2";
    public static final String COL_FAULT1 = "fault1";
    public static final String COL_FAULT2 = "fault2";
    public static final String COL_LAT = "latitude";
    public static final String COL_LONGI = "longitude";
    public static final String COL_TYPE = "typematch";
    public static final String COL_DUREE = "duree";
    public static final String COL_CLASSEMENT = "classement";


    public SQLiteDatabase(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(android.database.sqlite.SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_MATCHES + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "PLAYER1 TEXT, PLAYER2 TEXT, LET1 INT, LET2 INT, FAULT1 INT, FAULT2 INT, LAT INT," +
                " LONGI INT, TYPE TEXT, DUREE TEXT, CLASSEMENT TEXT) ");
    }

    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATCHES);
        onCreate(db);
    }

    public boolean InsertNewMatch(String player1, String player2, int let1, int let2, int fault1,
                                  int fault2, int lat, int longi, String typeMatch, String duree,
                                  String classement){

        android.database.sqlite.SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_PLAYER1, player1);
        contentValues.put(COL_PLAYER2, player2);
        contentValues.put(COL_LET1, let1);
        contentValues.put(COL_LET2, let2);
        contentValues.put(COL_FAULT1, fault1);
        contentValues.put(COL_FAULT2, fault2);
        contentValues.put(COL_LAT, lat);
        contentValues.put(COL_LONGI, longi);
        contentValues.put(COL_TYPE, typeMatch);
        contentValues.put(COL_DUREE, duree);
        contentValues.put(COL_CLASSEMENT, classement);

        long result = db.insert(TABLE_MATCHES, null, contentValues);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public void DeleteAllMatches(){

        android.database.sqlite.SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DROP TABLE " + TABLE_MATCHES);
        db.execSQL("CREATE TABLE " + TABLE_MATCHES + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "PLAYER1 TEXT, PLAYER2 TEXT, LET1 INT, LET2 INT, FAULT1 INT, FAULT2 INT, LAT INT, " +
                "LONGI INT, TYPE TEXT, DUREE TEXT, CLASSEMENT TEXT) ");
        db.close();
    }

    public Cursor GetAllMatches(){
        android.database.sqlite.SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + TABLE_MATCHES, null);
        return result;
    }
}
