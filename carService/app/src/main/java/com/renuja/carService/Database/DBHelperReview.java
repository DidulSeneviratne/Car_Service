package com.renuja.carService.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.renuja.carService.models.Owner;

import java.util.ArrayList;
import java.util.List;

public class DBHelperReview extends SQLiteOpenHelper {

    private static final String DB_NAME = "vehicle_manager.db";
    private static final int DB_VERSION = 1;

    public DBHelperReview(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE reviews (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_id INTEGER," +
                "owner_id INTEGER," +
                "review TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS reviews");
        onCreate(db);
    }

    public boolean registerReview(String user_id, String owner_id, String review) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("user_id", user_id);
        values.put("owner_id", owner_id);
        values.put("review", review);

        long result = db.insert("reviews", null, values);
        return result != -1;
    }

}
