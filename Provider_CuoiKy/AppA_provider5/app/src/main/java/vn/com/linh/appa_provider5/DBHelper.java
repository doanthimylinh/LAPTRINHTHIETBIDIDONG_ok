package vn.com.linh.appa_provider5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "provider5", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table LoaiSanPhams(" +
                "id integer primary key," +
                "name text)");
        sqLiteDatabase.execSQL("create table SanPhams(" +
                "id integer primary key," +
                "name text," +
                "giaTien real," +
                "chatLuongSanPham text," +
                "id_LoaiSanPham integer not null constraint id references " +
                "LoaiSanPhams(id) on delete cascade on update cascade)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists LoaiSanPhams");
        sqLiteDatabase.execSQL("drop table if exists SanPhams");
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
        super.onConfigure(db);
    }
}
