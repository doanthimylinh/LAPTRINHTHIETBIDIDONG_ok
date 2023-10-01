package vn.com.linh.ontaptk3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity {
    ArrayList<Book> books;
    BookCustom bookCustom;
    DBHelper dbHelper;
    GridView gridView;
    EditText ed_id, ed_name;
    Spinner spinnerAuthor;
    ArrayAdapter<String> authorNameAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        ed_id=findViewById(R.id.ed_idBook);
        ed_name=findViewById(R.id.ed_nameBook);
        spinnerAuthor=findViewById(R.id.id_author_book);
        gridView=findViewById(R.id.grid_Book);
        dbHelper= new DBHelper(this);
        loadAuthor();
        render();
        Button btn_them=findViewById(R.id.btn_ThemBook);
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=Integer.parseInt(ed_id.getText().toString());
                String name=ed_name.getText().toString();
                String authorName=spinnerAuthor.getSelectedItem().toString();
                Author author=dbHelper.getAuthorByName(authorName);
                if (id>0&&!name.equals("")){
                    Book book= new Book(id,name,author);
                    dbHelper.addBook(book);
                    Toast.makeText(BookActivity.this," luu thanh cong"+book.getId(),Toast.LENGTH_SHORT).show();
                    lamRong();
                    render();
                }
                else  Toast.makeText(BookActivity.this," luu khong thanh cong",Toast.LENGTH_SHORT).show();
            }
        });
        Button btn_Update=findViewById(R.id.btn_SuaBook);
        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Author author = dbHelper.getAuthorByName(spinnerAuthor.getSelectedItem().toString());
                Book book = new Book(Integer.parseInt(ed_id.getText().toString()),ed_name.getText().toString(),author);
                dbHelper.updateBook(book);
                render();
            }
        });
        Button btnDelete=findViewById(R.id.btn_XoaBook);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(BookActivity.this);
                alert.setTitle("Xác nhận");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHelper.deleteBook(Integer.parseInt(ed_id.getText().toString()));
                        render();
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                alert.show();
            }
        });
        Button btn_thoat=findViewById( R.id.btn_author_thoat);
        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Book book= books.get(i);

                ed_name.setText(book.getName());
                ed_id.setText(book.getId()+"");
            }
        });
    }
    private void render(){
        books=dbHelper.getAllBooks();
        bookCustom= new BookCustom(BookActivity.this, R.layout.book_custom,books);
        gridView.setAdapter(bookCustom);
        bookCustom.notifyDataSetChanged();
    }
    private void lamRong(){
        ed_id.setText("");
        ed_name.setText("");
    }
    private void loadAuthor(){
        ArrayList<Author> authors= dbHelper.getAllAuthors();
        List<String> authorlist= new ArrayList<>();
        for (Author author: authors){
            authorlist.add(author.getName());
        }
        authorNameAdapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,authorlist);
        authorNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAuthor.setAdapter(authorNameAdapter);
    }
}