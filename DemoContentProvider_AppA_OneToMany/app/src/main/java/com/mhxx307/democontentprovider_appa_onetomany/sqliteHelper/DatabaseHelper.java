package com.mhxx307.democontentprovider_appa_onetomany.sqliteHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "phongbannhanvien.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS phong_ban(id INTEGER PRIMARY KEY, name TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS nhan_vien(id INTEGER PRIMARY KEY, name TEXT, phong_ban_id INTEGER NOT NULL," +
                " CONSTRAINT fk_phong_ban FOREIGN KEY(phong_ban_id) REFERENCES phong_ban(id) ON DELETE CASCADE ON UPDATE CASCADE)");
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
        super.onConfigure(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS phong_ban");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS nhan_vien");
        onCreate(sqLiteDatabase);
    }
}
