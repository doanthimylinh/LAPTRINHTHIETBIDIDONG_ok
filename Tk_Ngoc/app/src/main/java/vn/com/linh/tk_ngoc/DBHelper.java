package vn.com.linh.tk_ngoc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "NhanVien", null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE NhanVien(id Interger primary key, hoten text,sdt Interger, diachi text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS NhanVien");
        onCreate(sqLiteDatabase);
    }

    //Thêm nhân viên
    public int insertNhanVien(NhanVien nhanVien){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("id", nhanVien.getId());
        values.put("hoten", nhanVien.getHoten());
        values.put("sdt", nhanVien.getSdt());
        values.put("diachi", nhanVien.getDiachi());
        int r= (int) db.insert("NhanVien",null,values);
        close();
        return r;
    }

    //Lấy danh sách nhân viên
    public ArrayList<NhanVien> getAllNhanVien(){
        ArrayList<NhanVien> list= new ArrayList<>();
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor= db.rawQuery("select * from NhanVien", null);
        if(cursor!=null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                list.add(new NhanVien(cursor.getInt(0), cursor.getString(1),
                        cursor.getInt(2), cursor.getString(3)));
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }
        return list;
    }

    //Lấy nhân viên theo id
    public NhanVien getNhanVientheoId(int id){
        NhanVien nv= new NhanVien();
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from NhanVien where id="+id, null);
        if(cursor!=null){
            cursor.moveToFirst();
                nv.setId(cursor.getInt(0));
                nv.setHoten(cursor.getString(1));
                nv.setSdt(cursor.getInt(2));
                nv.setDiachi(cursor.getString(3));
                cursor.close();
                db.close();

        }

        return  nv;
    }

    public Integer deleteNhanVien(int id){
        SQLiteDatabase db= this.getWritableDatabase();
        Integer r= (Integer) db.delete("NhanVien","id = ?",new String[]{String.valueOf(id)});
        return r;
    }

    public boolean updateNhanVien(NhanVien nhanVien){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("id", nhanVien.getId());
        values.put("hoten", nhanVien.getHoten());
        values.put("sdt", nhanVien.getSdt());
        values.put("diachi", nhanVien.getDiachi());
        db.update("NhanVien",values,"id = ?",new String[]{String.valueOf(nhanVien.getId())});
        return  true;
    }


}
