package com.renuja.carService.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.renuja.carService.models.Appointment;
import com.renuja.carService.models.UserAppointment;

import java.util.ArrayList;
import java.util.List;

public class DBHelperAppointment extends SQLiteOpenHelper {

    private static final String DB_NAME = "vehicle_manager.db";
    private static final int DB_VERSION = 1;

    public DBHelperAppointment(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE appointments (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_id INTEGER," +
                "owner_id INTEGER," +
                "appointment_status TEXT DEFAULT 'pending'," +
                "problem TEXT," +
                "visit_type TEXT," +
                "location TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS appointments");
        onCreate(db);
    }

    public boolean registerAppointment(int user_id, int owner_id, String problem, String visit_type, String location) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("user_id", user_id);
        values.put("owner_id", owner_id);
        values.put("appointment_status", "pending");
        values.put("problem", problem);
        values.put("visit_type", visit_type);
        values.put("location", location);

        long result = db.insert("appointments", null, values);
        return result != -1;
    }

    public List<Appointment> getAppointmentsWithUser(int ownerId) {

        List<Appointment> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query =
                "SELECT a.*, u.name, u.phone " +
                        "FROM appointments a " +
                        "INNER JOIN users u ON a.user_id = u.id " +
                        "WHERE a.owner_id = ?";

        Cursor cursor = db.rawQuery(query,
                new String[]{String.valueOf(ownerId)});

        if (cursor.moveToFirst()) {
            do {
                Appointment model = new Appointment();

                model.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                model.setUserId(cursor.getInt(cursor.getColumnIndexOrThrow("user_id")));
                model.setOwnerId(cursor.getInt(cursor.getColumnIndexOrThrow("owner_id")));
                model.setProblem(cursor.getString(cursor.getColumnIndexOrThrow("problem")));
                model.setVisitType(cursor.getString(cursor.getColumnIndexOrThrow("visit_type")));
                model.setLocation(cursor.getString(cursor.getColumnIndexOrThrow("location")));
                model.setStatus(cursor.getString(cursor.getColumnIndexOrThrow("appointment_status")));

                // 🔥 USER DATA
                model.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
                model.setPhone(cursor.getString(cursor.getColumnIndexOrThrow("phone")));

                list.add(model);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return list;
    }

    public void updateAppointmentStatus(int id, String status) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("appointment_status", status);

        db.update("appointments", values, "id=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public List<UserAppointment> getUserAppointments(int userId) {

        List<UserAppointment> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT appointments.id, appointments.appointment_status, " +
                        "appointments.problem, appointments.visit_type, appointments.location, " +

                        "owners.business_name, owners.business_address, owners.business_phone, owners.business_type " +

                        "FROM appointments " +
                        "JOIN owners ON owners.id = appointments.owner_id " +
                        "WHERE appointments.user_id = ?",
                new String[]{String.valueOf(userId)}
        );

        if (cursor.moveToFirst()) {
            do {
                UserAppointment model = new UserAppointment();

                model.appointmentId = cursor.getInt(0);
                model.status = cursor.getString(1);
                model.problem = cursor.getString(2);
                model.visitType = cursor.getString(3);
                model.location = cursor.getString(4);

                model.businessName = cursor.getString(5);
                model.businessAddress = cursor.getString(6);
                model.businessPhone = cursor.getString(7);
                model.businessType = cursor.getString(8);

                list.add(model);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return list;
    }

}
