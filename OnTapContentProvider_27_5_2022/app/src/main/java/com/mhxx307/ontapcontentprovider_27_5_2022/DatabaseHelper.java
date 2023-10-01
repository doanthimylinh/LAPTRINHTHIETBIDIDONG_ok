package com.mhxx307.ontapcontentprovider_27_5_2022;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "SanPhamDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS loai_san_pham(id INTEGER PRIMARY KEY, ten_loai TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS san_pham(id INTEGER PRIMARY KEY, ten_san_pham TEXT, gia REAL, chat_luong TEXT," +
                " loai_san_pham_id INTEGER NOT NULL CONSTRAINT id references loai_san_pham(id) ON DELETE CASCADE ON UPDATE CASCADE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS loai_san_pham");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS san_pham");
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
        super.onConfigure(db);
    }
}
