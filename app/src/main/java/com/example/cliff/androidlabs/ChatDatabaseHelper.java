package com.example.cliff.androidlabs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by xman on 11/23/2017.
 */

public class ChatDatabaseHelper  extends SQLiteOpenHelper {

    private static final String LOGTAG = ChatDatabaseHelper.class.getSimpleName();

    public static final String DATABASE_NAME = "Messages.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "Messages";
    public static final String COLUMN_ID = "MessageID";
    public static final String COLUMN_CONTENT = "Message";

    private SQLiteDatabase database;

    private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
    COLUMN_CONTENT + " TEXT" +
            ")";

    public ChatDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(LOGTAG, "Calling onCreate");
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(LOGTAG, "Calling onUpgrade, oldVersion=" + oldVersion + " newVersion=" + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void openDatabase() {
        database = getWritableDatabase();
    }

    public void closeDatabase() {
        if (database != null && database.isOpen()) {
            database.close();
        }
    }

    public void insert(String content) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CONTENT, content);

        database.insert(TABLE_NAME, null, values);

    }

    public Cursor getRecords() {
        return database.query(TABLE_NAME, null, null, null, null, null, null);
    }
}
