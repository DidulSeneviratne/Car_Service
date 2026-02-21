package com.renuja.carService.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelperUser extends SQLiteOpenHelper {

    private static final String DB_NAME = "vehicle_manager.db";
    private static final int DB_VERSION = 1;

    public DBHelperUser(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "email TEXT," +
                "phone TEXT," +
                "password TEXT," +
                "vehicle_model TEXT," +
                "vehicle_type TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    public boolean registerUser(String role, String Name, String email, String phone,
                                String password, String vehicleModel, String vehicleType) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("role", role);
        values.put("name", Name);
        values.put("email", email);
        values.put("phone", phone);
        values.put("password", password);
        values.put("vehicle_model", vehicleModel);
        values.put("vehicle_type", vehicleType);

        long result = db.insert("users", null, values);
        return result != -1;
    }

    public int checkLogin(String name, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT role FROM users WHERE name=? AND password=?";
        Cursor cursor = db.rawQuery(query, new String[]{name, password});

        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            cursor.close();
            return id;   // "user" or "owner"
        }

        cursor.close();
        return -1;  // login failed
    }

}
