package vn.com.linh.onthi2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context){
        super(context,"authors_demo",null,1);
    }
    public void QueryData(String sql){
        SQLiteDatabase database=this.getWritableDatabase();
        database.execSQL(sql);
    }
    public Cursor getData(String sql){
        SQLiteDatabase database=this.getReadableDatabase();
        return database.rawQuery(sql,null);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists authors"+
                "(id integer primary key,"+
                "author_name text,"+
                "author_address text,"+
                "author_email text)");
        sqLiteDatabase.execSQL("create table if not exists books"+
                "(id integer primary key," +
                "book_name text," +
                "author_id integer not null," +
                "constraint fk_authors foreign key (author_id) references authors(id) on delete cascade)");
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
    //Author
    public ArrayList<Author> getAllAuthors(){
        ArrayList<Author> list= new ArrayList<>();
        Cursor cursor=getData("select * from authors");
        if (cursor!=null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String address = cursor.getString(2);
                String email = cursor.getString(3);

                Author author = new Author(id, name, address, email);
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
                    author.getAddress()+"','"+
                    author.getEmail()+"')");
        }
    }
    public void deleteAuthor(int id){
        QueryData("delete from authors where id="+id+"");
    }
    public  void updateAuthor(Author author){
        if (author!=null){
            String sql="update authors set author_name='"+author.getName()
                    +"', author_address ='"+author.getAddress()
                    +"', author_email = '"+author.getEmail()
                    +"'where id='"+author.getId()+"'";
            QueryData(sql);
        }
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
    public Author getAuthorByName(String name){
        Author author=null;
        String sql="select * from authors where auhtor_name='"+name+"'";
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
    //book
    public ArrayList<Book> getAllBooks(){
        ArrayList<Book> list= new ArrayList<>();
        Cursor cursor=getData("select * from books");
        if (cursor!=null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int id_author=cursor.getInt(2);

                Book book=new Book(id,name,id_author);
                list.add(book);
            }
            cursor.close();
        }
        return list;
    }
    public void addBook(Book book){
        if (book!=null){
            QueryData("insert into books values('" +
                    book.getId()+"','" +
                    book.getName()+"','" +
                    book.getIdAuthor()+"')");
        }
    }
    public void deleteBook(int id){
        QueryData("delete from books where id="+id+"");
    }
    public  void updateBook(Book book){
        if (book!=null){
            String sql="update books set book_name='"+book.getName()
                    +"', author_id ='"+book.getIdAuthor()
                    +"'where id='"+book.getId()+"'";
            QueryData(sql);
        }
    }
    public Book getBookById(int id){
        Book book=null;
        String sql="select * from books where id='"+id+"'";
        Cursor cursor=getData(sql);
        if (cursor!=null){
            while (cursor.moveToNext()){
                String name = cursor.getString(1);
                int id_author=cursor.getInt(2);

                book = new Book(id,name,id_author);
            }
            cursor.close();
        }
        return book;
    }
    public Book getBookByName(String name){
        Book book=null;
        String sql="select * from books where book_name='"+name+"'";
        Cursor cursor=getData(sql);
        if (cursor!=null){
            while (cursor.moveToNext()){
                int id=cursor.getInt(0);
                int id_author=cursor.getInt(2);

                book = new Book(id, name, id_author);
            }
            cursor.close();
        }
        return book;
    }
}
