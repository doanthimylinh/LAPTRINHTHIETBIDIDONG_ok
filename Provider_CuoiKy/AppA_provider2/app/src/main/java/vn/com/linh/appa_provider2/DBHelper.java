package vn.com.linh.appa_provider2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "AppA_provider2", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table PhongBans(" +
                "id integer primary key," +
                "name text)");
        sqLiteDatabase.execSQL("create table NhanViens(" +
                "id integer primary key," +
                "name text," +
                "address text," +
                "id_PhongBan integer not null constraint id references " +
                "PhongBans(id)  on delete cascade  on update cascade )");
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
        super.onConfigure(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists Nhanviens");
        sqLiteDatabase.execSQL("Drop table if exists PhongBans");
        onCreate(sqLiteDatabase);
    }
}
