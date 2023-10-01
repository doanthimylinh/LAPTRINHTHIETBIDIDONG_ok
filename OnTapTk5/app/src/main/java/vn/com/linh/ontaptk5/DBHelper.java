package vn.com.linh.ontaptk5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, "KTTK5", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table Authors(" +
                "id integer primary key," +
                "name text," +
                "address text," +
                "email text)");
        sqLiteDatabase.execSQL("create table Books(" +
                "id integer primary key," +
                "title text," +
                "id_author integer not null constraint id references " +
                "Authors(id) on delete cascade on update cascade)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists Books");
        sqLiteDatabase.execSQL("drop table if exists Authors");
        onCreate(sqLiteDatabase);
    }
    //Author
    public int insertAuthor(Author author){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("id",author.getId());
        values.put("name",author.getName());
        values.put("address", author.getAddress());
        values.put("email", author.getEmail());
        int re=(int)db.insert("Authors", null, values);
        db.close();
        return  re;
    }
    public ArrayList<Author> getAllAuthors(){
        ArrayList<Author> list=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from authors",null);
        if(cursor!=null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                list.add(new Author(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)));
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }
        return list;
    }
    public  Author getAuthorById(int id){
        Author author= new Author();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from authors where id="+id, null);
        if (cursor!=null){
            cursor.moveToFirst();
            author.setId(cursor.getInt(0));
            author.setName(cursor.getString(1));
            author.setAddress(cursor.getString(2));
            author.setEmail(cursor.getString(3));
            cursor.close();
            db.close();
        }
        return author;
    }
    public  Author getAuthorByName(String name){
        Author author=new Author();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from authors where name='"+name+"'", null);
        if (cursor!=null){
            cursor.moveToFirst();
            author.setId(cursor.getInt(0));
            author.setName(cursor.getString(1));
            author.setAddress(cursor.getString(2));
            author.setEmail(cursor.getString(3));
            cursor.close();
            db.close();
        }
        return author;
    }
    public Integer deleteAuthor(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        Integer re=db.delete("Authors", "id=?",new String[]{String.valueOf(id)});
        return  re;
    }
    public boolean updateAuthor(Author author){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("id",author.getId());
        values.put("name",author.getName());
        values.put("address", author.getAddress());
        values.put("email", author.getEmail());
        db.update("Authors",values, "id=?", new String[]{String.valueOf(author.getId())});
        return  true;
    }

    //Book
    public int insertBook(Book book){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("id",book.getId());
        values.put("title",book.getTitle());
        values.put("id_author", book.getAuthor().getId());
        int re=(int)db.insert("Books", null, values);
        db.close();
        return  re;
    }
    public ArrayList<Book> getAllBooks(){
        ArrayList<Book> list=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from books",null);
        if(cursor!=null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                int id_Author=cursor.getInt(2);
                Author author=getAuthorById(id_Author);
                list.add(new Book(cursor.getInt(0),
                        cursor.getString(1),
                        author));
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }
        return list;
    }
    public  Book getBookById(int id){
        Book book= new Book();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from books where id="+id, null);
        if (cursor!=null){
            cursor.moveToFirst();
            book.setId(cursor.getInt(0));
            book.setTitle(cursor.getString(1));

            int id_Author=cursor.getInt(2);
            Author author=getAuthorById(id_Author);
            book.setAuthor(author);
            cursor.close();
            db.close();
        }
        return book;
    }
    public Integer deleteBook(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        Integer re=db.delete("Books", "id=?",new String[]{String.valueOf(id)});
        return  re;
    }
    public boolean updateBook(Book book){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("id",book.getId());
        values.put("title",book.getTitle());
        values.put("id_author", book.getAuthor().getId());
        db.update("books",values, "id=?", new String[]{String.valueOf(book.getId())});
        return  true;
    }
}
