package com.example.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "BookData", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Authors(id integer primary Key,name text, address text, email text)");
        sqLiteDatabase.execSQL("CREATE TABLE Bookss(" +
                "id_book integer primary Key," +
                "title text, " +
                "id_author integer not null constraint id references " +
                "Authors(id) On delete Cascade ON UPDATE CASCADE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Books");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Authors");
        onCreate(sqLiteDatabase);
    }
    //Them, xoa,sua, truy van du lieu
    public int insertAuthor(Author author){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("id",author.getIdAuthor());
        values.put("name",author.getName());
        values.put("address",author.getAddress());
        values.put("email",author.getEmail());
        int re =(int)db.insert("Authors",null,values);
        db.close();
        return  re;
    }

    //get tất cả tác giả
    public ArrayList<Author> getAllAuhtor(){
        ArrayList<Author> list=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from authors", null);
        if (cursor!=null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                list.add(new Author(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }
        return  list;

    }
    //get 1 tác giả
    public Author getIdAuhtor(int id){
        Author author=new Author();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from authors where id="+id, null);
        if (cursor!=null){
            cursor.moveToFirst();

            author.setIdAuthor(cursor.getInt(0));
            author.setName(cursor.getString(1));
            author.setAddress(cursor.getString(2));
            author.setEmail(cursor.getString(3));
                cursor.moveToNext();
            cursor.close();
            db.close();
            }
        return  author;
        }
    //xóa 1 tác giả
    public Integer deleteAuhtor(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        Integer r= db.delete("Authors" ,"id=?", new String[]{String.valueOf(id)});
        return r;
    }
    //sua tac gia
    public boolean updateAuthor(Author author){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("id", author.getIdAuthor());
        values.put("name",author.getName());
        values.put("address", author.getAddress());
        values.put("email", author.getEmail());
        db.update("Authors", values, "id=?", new String[]{String.valueOf(author.getIdAuthor())});
        return true;
    }
    //lau author by id
    public Author getAuhtorById(int id){
        Author author=new Author();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from Authors where id="+id, null);
        if (cursor!=null){
            cursor.moveToFirst();
            author.setIdAuthor(cursor.getInt(0));
            author.setName(cursor.getString(1));
            author.setAddress(cursor.getString(2));
            author.setEmail(cursor.getString(3));
            cursor.close();
            db.close();
        }
        return author;
    }
    public int insertBook(Book book){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("id_book",book.getId_book());
        values.put("title",book.getTitle());
        values.put("id_author",book.getAuthor().getIdAuthor());
        int re =(int)db.insert("Bookss",null,values);
        db.close();
        return  re;
    }
    public ArrayList<Book> getAllBooks(){
        ArrayList<Book> list=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from Bookss", null);
        if (cursor!=null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                int authorId=cursor.getInt(2);
                Author author=getAuhtorById(authorId);
                list.add(new Book(cursor.getInt(0),cursor.getString(1),author));
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }
        return  list;

    }
    public Book getBookById(int id){
        Book book=null;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from Bookss where id_book="+id, null);
        if (cursor!=null){
            cursor.moveToFirst();

            String name=cursor.getString(1);
            int authorId=cursor.getInt(2);
            Author author=getAuhtorById(authorId);
            book= new Book(id,name,author);
            cursor.close();
            db.close();
        }
        return  book;
    }
    public Integer deleteBook(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        Integer r= db.delete("Bookss" ,"id_book=?", new String[]{String.valueOf(id)});
        return r;
    }
    //sua tac gia
    public boolean updateBook(Book book){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("id_book", book.getId_book());
        values.put("title",book.getTitle());
        values.put("id_author",book.getAuthor().getIdAuthor());
        db.update("Bookss", values, "id_book=?", new String[]{String.valueOf(book.getId_book())});
        return true;
    }
    }


