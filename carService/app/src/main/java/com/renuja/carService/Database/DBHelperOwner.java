package com.renuja.carService.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.renuja.carService.models.Owner;

import java.util.ArrayList;
import java.util.List;

public class DBHelperOwner extends SQLiteOpenHelper {

    private static final String DB_NAME = "vehicle_manager.db";
    private static final int DB_VERSION = 1;

    public DBHelperOwner(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE owners (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "role TEXT," +
                "name TEXT," +
                "email TEXT," +
                "phone TEXT," +
                "password TEXT," +
                "business_reg_no TEXT," +
                "business_name TEXT," +
                "business_type TEXT," +
                "business_description TEXT," +
                "business_address TEXT," +
                "business_phone TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS owners");
        onCreate(db);
    }

    public boolean registerOwner(String role, String Name, String email, String phone,
                                 String password, String regNo, String businessName, String type,
                                 String description, String address, String businessPhone) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("role", role);
        values.put("name", Name);
        values.put("email", email);
        values.put("phone", phone);
        values.put("password", password);
        values.put("business_reg_no", regNo);
        values.put("business_name", businessName);
        values.put("business_type", type);
        values.put("business_description", description);
        values.put("business_address", address);
        values.put("business_phone", businessPhone);

        long result = db.insert("owners", null, values);
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

    public List<Owner> getAllBusinesses() {
        List<Owner> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM owners", null);

        if (cursor.moveToFirst()) {
            do {
                list.add(new Owner(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getFloat(7)
                ));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return list;
    }

    public Owner getOwnerByRegId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM owners WHERE id = ?",
                new String[]{String.valueOf(id)}
        );

        if (cursor.moveToFirst()) {
            Owner owner = new Owner();
            owner.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            owner.setBusinessName(cursor.getString(cursor.getColumnIndexOrThrow("business_name")));
            owner.setBusinessType(cursor.getString(cursor.getColumnIndexOrThrow("business_type")));
            owner.setAddress(cursor.getString(cursor.getColumnIndexOrThrow("address")));
            owner.setPhone(cursor.getString(cursor.getColumnIndexOrThrow("phone")));
            owner.setDescription(cursor.getString(cursor.getColumnIndexOrThrow("description")));
            owner.setRating(cursor.getFloat(cursor.getColumnIndexOrThrow("rating")));
            cursor.close();
            return owner;
        }

        cursor.close();
        return null;
    }


}
