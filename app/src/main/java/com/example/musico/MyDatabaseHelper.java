package com.example.musico;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.File;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "songs.db";
    private static final int DATABASE_VERSION = 1;
    private String TABLE_NAME;
    private static final String COL_ID = "_id";
    private static final String COL_NAME = "song_name";
    private static final String COL_SINGER = "singer_name";
    private static final String COL_SONG_PATH = "song_path";
    private static final String COL_IMAGE_PATH = "song_image";

    public MyDatabaseHelper(@Nullable Context context, @Nullable String tableName) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.TABLE_NAME = tableName;
        this.context = context;

        File dbFile = context.getDatabasePath(DATABASE_NAME);
        if (dbFile.exists()) {
            dbFile.setReadable(true, false);
            dbFile.setWritable(true, false);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COL_NAME + " TEXT, " +
                        COL_SINGER + " TEXT, " +
                        COL_SONG_PATH + " TEXT, " +
                        COL_IMAGE_PATH + " TEXT);";
        db.execSQL(query);
    }

    void createTableIfNotExists() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                        " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COL_NAME + " TEXT, " +
                        COL_SINGER + " TEXT, " +
                        COL_SONG_PATH + " INTEGER, " +
                        COL_IMAGE_PATH + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addSong(String name, String singer, String Songpath, String Imgpath) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, name);
        cv.put(COL_SINGER, singer);
        cv.put(COL_SONG_PATH, Songpath);
        cv.put(COL_IMAGE_PATH, Imgpath);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(String table_name) {
        String query = "SELECT * from " + table_name;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void deleteSong(String tableName, int songId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(tableName, COL_ID + "=?", new String[]{String.valueOf(songId)});
        if (result == -1) {
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Song deleted successfully", Toast.LENGTH_SHORT).show();
        }
    }

    void updateSong(String tableName, String id, String songPath, String imgPath) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_SONG_PATH, songPath);
        cv.put(COL_IMAGE_PATH, imgPath);

        int result = db.update(tableName, cv, COL_ID + "=?", new String[]{id});
        if (result == -1) {
            Toast.makeText(context, "Failed to update song", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Song updated successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
