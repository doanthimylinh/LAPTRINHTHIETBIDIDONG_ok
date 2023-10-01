package vn.com.linh.appa_provider4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "provider4", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table LopHocs(" +
                "id integer primary key," +
                "name text)");
        sqLiteDatabase.execSQL("create table SinhViens(" +
                "id integer primary key," +
                "name text," +
                "id_LopHoc integer not null constraint id references " +
                "LopHocs(id) on delete cascade on update cascade  )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists LopHocs");
        sqLiteDatabase.execSQL("drop table if exists SinhViens");
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
        super.onConfigure(db);
    }
}
