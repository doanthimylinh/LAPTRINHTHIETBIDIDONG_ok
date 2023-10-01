package vn.com.linh.appa_provider_1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context,"providerLan1",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table Classs(" +
                "id integer primary key," +
                "ten text)");
        sqLiteDatabase.execSQL("create table Students(" +
                "id integer primary key," +
                "ten text," +
                "diachi text ," +
                "id_class integer not null constraint id references " +
                "Classs(id) on delete cascade on update cascade)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists students");
        sqLiteDatabase.execSQL("drop table if exists classs");
        onCreate(sqLiteDatabase);
    }
}
