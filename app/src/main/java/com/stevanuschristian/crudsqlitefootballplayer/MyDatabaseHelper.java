package com.stevanuschristian.crudsqlitefootballplayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context ctx;
    private static final String DATABASE_NAME = "db_football";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "tbl_player";
    private static final String FIELD_ID = "id";
    private static final String FIELD_NAMA = "nama";
    private static final String FIELD_NOMOR = "nomor";
    private static final String FIELD_KLUB = "klub";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
        this.ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*
        CREATE TABLE TABLE_NAME (
            FIELD_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            FIELD_NAMA VARCHAR(50),
            FIELD_NOMOR INTEGER,
            FIELD_KLUB TEXT
        );
         */
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                FIELD_NAMA + " VARCHAR(50)," +
                FIELD_NOMOR + " INTEGER," +
                FIELD_KLUB + " TEXT ); "
                ;

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long tambahPlayer(String nama, int nomor, String klub){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FIELD_NAMA, nama);
        cv.put(FIELD_NOMOR, nomor);
        cv.put(FIELD_KLUB, klub);

        long eksekusi = db.insert(TABLE_NAME, null, cv);
        return eksekusi;
    }

    public Cursor bacaDataPlayer(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    public long hapusPlayer(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        long eksekusi = db.delete(TABLE_NAME, "id = ?", new String[]{id});

        return eksekusi;
    }

    public long ubahPlayer(String id, String nama, String nomor, String klub){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FIELD_NAMA, nama);
        cv.put(FIELD_NOMOR, nomor);
        cv.put(FIELD_KLUB, klub);

        long eksekusi = db.update(TABLE_NAME, cv, "id = ?", new String[]{id});
        return eksekusi;
    }
}
