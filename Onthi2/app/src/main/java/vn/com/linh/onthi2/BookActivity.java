package vn.com.linh.onthi2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    GridView gridViewBook;
    ArrayList<Book> books;
    BookCustom bookCustom;
    DBHelper dbHelper;
    EditText ed_id, ed_name, ed_idAuthor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        gridViewBook=findViewById(R.id.gridViewBook);
        ed_id=findViewById(R.id.ed_idBook);
        ed_name=findViewById(R.id.ed_nameBook);
        ed_idAuthor=findViewById(R.id.ed_idAuhtor_book);
        dbHelper=new DBHelper(this);
        render();

        Button btn_them=findViewById(R.id.btn_ThemBook);
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=Integer.parseInt(ed_id.getText().toString());
                String name=ed_name.getText().toString();
                int id_author=Integer.parseInt(ed_idAuthor.getText().toString());
                if (id>0){
                    Book book= new Book(id,name,id_author);
                    dbHelper.addBook(book);
                    Toast.makeText(BookActivity.this," luu thanh cong",Toast.LENGTH_SHORT).show();
                    lamtrong();
                    render();
                }
                else  Toast.makeText(BookActivity.this," luu khong thanh cong",Toast.LENGTH_SHORT).show();
            }
        });
        Button btn_xoa=findViewById(R.id.btn_XoaBook);
        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert= new AlertDialog.Builder(BookActivity.this);
                alert.setTitle("xac nhan");
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
        Button btn_update=findViewById(R.id.btn_SuaBook);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book= new Book(Integer.parseInt(ed_id.getText().toString()),ed_name.getText().toString(),Integer.parseInt(ed_idAuthor.getText().toString()));
                dbHelper.updateBook(book);
                render();
            }
        });
        Button btn_thoat=findViewById(R.id.btn_thoat_book);
        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        gridViewBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Book book=books.get(i);
                ed_id.setText(book.getId()+"");
                ed_name.setText(book.getName());
                ed_idAuthor.setText(book.getIdAuthor()+"");
            }
        });
    }
    private void render(){
        books=dbHelper.getAllBooks();
        bookCustom=new BookCustom(BookActivity.this,R.layout.book_custom,books);
        gridViewBook.setAdapter(bookCustom);
        bookCustom.notifyDataSetChanged();
    }
    private void lamtrong(){
        ed_id.setText("");
        ed_name.setText("");
        ed_idAuthor.setText("");
    }
}