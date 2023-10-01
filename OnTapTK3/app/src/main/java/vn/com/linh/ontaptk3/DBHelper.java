package vn.com.linh.ontaptk3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context,"TK2",null,1);

    }
    public  void QueryData(String sql){
        SQLiteDatabase database=this.getWritableDatabase();
        database.execSQL(sql);
    }
    public Cursor getData(String sql){
        SQLiteDatabase database=this.getReadableDatabase();
        return database.rawQuery(sql, null);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists authors" +
                "(id integer primary key," +
                "name text," +
                "address text," +
                "email text)");
        sqLiteDatabase.execSQL(" create table if not exists books" +
                "(id integer primary key," +
                "name text," +
                "id_author integer not null," +
                "constraint fk_authors foreign key (id_author) references authors(id) on delete cascade )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists authors");
        sqLiteDatabase.execSQL("drop table if exists books");
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
        super.onConfigure(db);
    }
    //Auhtors
    public ArrayList<Author> getAllAuthors(){
        ArrayList<Author> list= new ArrayList<>();
        Cursor cursor= getData("select * from authors ");
        if (cursor!=null){
            while (cursor.moveToNext()){
                int id= cursor.getInt(0);
                String name=cursor.getString(1);
                String address=cursor.getString(2);
                String email=cursor.getString(3);

                Author author= new Author(id,name,address,email);
                list.add(author);
            }
            cursor.close();
        }
        return list;
    }
    public void addAuthor(Author author){
        if (author!=null){
            QueryData("insert into authors values('" +
                    author.getId()+"','" +
                    author.getName()+"','" +
                    author.getAddress()+"','" +
                    author.getEmail()+"')");
        }
    }
    public void deleteAuthor(int id){
        QueryData("delete from authors where id="+id);
    }
    public void updateAuthor(Author author){
        if (author!=null){
            String sql="update authors set name='"+author.getName()
                    +"',address='"+author.getAddress()
                    +"', email='"+author.getEmail()
                    +"'where id='"+ author.getId()+"'";
            QueryData(sql);
        }
    }
    public Author getAuthorByName(String name){
        Author author=null;
        String sql="select * from authors where name='"+name+"'";
        Cursor cursor=getData(sql);
        if (cursor!=null){
            while (cursor.moveToNext()){
                int id=cursor.getInt(0);
                String address = cursor.getString(2);
                String email = cursor.getString(3);

                author = new Author(id, name, address, email);
            }
            cursor.close();
        }
        return author;
    }
    public Author getAuthorById(int id){
        Author author=null;
        String sql="select * from authors where id='"+id+"'";
        Cursor cursor=getData(sql);
        if (cursor!=null){
            while (cursor.moveToNext()){
                String name = cursor.getString(1);
                String address = cursor.getString(2);
                String email = cursor.getString(3);

                author = new Author(id, name, address, email);
            }
            cursor.close();
        }
        return author;
    }
    //book
    public ArrayList<Book> getAllBooks() {
        ArrayList<Book> books = new ArrayList<>();
        Cursor resultData = getData("SELECT * FROM books");

        if (resultData != null) {
            while (resultData.moveToNext()) {
                int id = resultData.getInt(0);
                String bookTitle = resultData.getString(1);
                int authorId = resultData.getInt(2);

                Author author = getAuthorById(authorId);

                Book book = new Book(id, bookTitle, author);

                books.add(book);
            }
            resultData.close();
        }

        return books;
    }
    public void addBook(Book book){
        if (book!=null){
            QueryData("insert into books values('" +
                    book.getId()+"','" +
                    book.getName()+"','" +
                    book.getAuthor().getId()+"')");
        }
    }
    public void deleteBook(int id){
        QueryData("delete from books where id="+id);
    }
    public void updateBook(Book book){
        if (book!=null){
            String sql="update books set name='"+book.getName()
                    +"',id_author='"+book.getAuthor()
                    +"'where id='"+ book.getId()+"'";
            QueryData(sql);
        }
    }
}
