package com.sroblesdorado.apptaller;

import static android.app.DownloadManager.COLUMN_ID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "app_database.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_DIAS_OCUPADOS = "dias_ocupados";
    private static final String COLUM_ID = "id";
    private static final String COLUMN_DATE = "date";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_DIAS_OCUPADOS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_DATE + " INTEGER);";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DIAS_OCUPADOS);
        onCreate(db);
    }
    // Método para insertar un día ocupado
    public synchronized void insertDiaOcupado(long date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE, date);
        db.insert(TABLE_DIAS_OCUPADOS, null, values);
        db.close();
    }

    // Método para obtener todos los días ocupados
    public synchronized List<Long> getAllDiasOcupados() {
        List<Long> diasOcupados = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_DIAS_OCUPADOS, new String[]{COLUMN_DATE},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                diasOcupados.add(cursor.getLong(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return diasOcupados;
    }




}
