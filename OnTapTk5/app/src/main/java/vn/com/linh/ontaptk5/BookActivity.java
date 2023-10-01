package vn.com.linh.ontaptk5;

import androidx.appcompat.app.AppCompatActivity;

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

    EditText ed_id, ed_title;
    Spinner spinnerAuthor;
    ArrayAdapter<String> authorNameAdapter;
    GridView gridView;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        ed_id=findViewById(R.id.ed_idbook);
        ed_title=findViewById(R.id.ed_titleBook);
        spinnerAuthor=findViewById(R.id.spinnerAuthor);
        gridView=findViewById(R.id.grid_ListBook);
        dbHelper=new DBHelper(this);
        select();
        spinnerAuthor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String authorName=spinnerAuthor.getSelectedItem().toString();
                Author author=dbHelper.getAuthorByName(authorName);
                Toast.makeText(getApplicationContext(), ""+author, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        loadAuhor();
        select();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Integer id=Integer.parseInt(gridView.getItemAtPosition(i).toString());
                Book book=dbHelper.getBookById(id);
                ed_id.setText(String.valueOf(book.getId()));
                ed_title.setText(book.getTitle().toString());
            }
        });
        Button btn_them=findViewById(R.id.btn_savebook);
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ed_id.getText().toString().equals("")||
                        ed_title.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Nhap du thong tin", Toast.LENGTH_SHORT).show();
                }else {
                    int id=Integer.parseInt(ed_id.getText().toString());
                    String title=ed_title.getText().toString();
                    String authorName=spinnerAuthor.getSelectedItem().toString();
                    Author author=dbHelper.getAuthorByName(authorName);
                    if (id>0&&!title.equals("")){
                        Book book= new Book(id,title,author);
                        dbHelper.insertBook(book);
                        Toast.makeText(BookActivity.this," luu thanh cong"+book.getId(),Toast.LENGTH_SHORT).show();
                        lamRong();
                        select();
                    }
                    else  Toast.makeText(BookActivity.this," luu khong thanh cong",Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button btn_xoa=findViewById(R.id.btn_deletebook);
        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ed_id.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Chon book can xoa", Toast.LENGTH_SHORT).show();
                }else {
                    if (dbHelper.deleteBook(Integer.parseInt(ed_id.getText().toString()))>0){
                        Toast.makeText(getApplicationContext(), "xoa thanh cong author", Toast.LENGTH_SHORT).show();
                        lamRong();
                        select();
                    }
                }
            }
        });
        Button btn_sua=findViewById(R.id.btn_updatebook);
        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ed_id.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Chon book can sua", Toast.LENGTH_SHORT).show();
                }else {
                    Book book=dbHelper.getBookById(Integer.parseInt(ed_id.getText().toString()));
                    book.setTitle(ed_title.getText().toString());
                    String authorName=spinnerAuthor.getSelectedItem().toString();
                    Author author=dbHelper.getAuthorByName(authorName);
                    book.setAuthor(author);
                    if (dbHelper.updateBook(book)){
                        Toast.makeText(getApplicationContext(), "Cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                        lamRong();
                        select();
                    }else Toast.makeText(getApplicationContext(), "Cap nhat that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button btn_select=findViewById(R.id.btn_selectbook);
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select();
            }
        });
        Button btn_thoat=findViewById(R.id.btn_thoat);
        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void select(){
        if (!ed_id.getText().toString().equals("")) {
            Book book;
            ArrayList<String> stringArrayList= new ArrayList<>();
            book=dbHelper.getBookById(Integer.parseInt(ed_id.getText().toString()));
            stringArrayList.add(book.getId()+"");
            stringArrayList.add(book.getTitle());
            stringArrayList.add(book.getAuthor().getId()+"");
            ArrayAdapter<String> adapter= new ArrayAdapter<>(BookActivity.this,
                    android.R.layout.simple_list_item_1,stringArrayList);
            gridView.setAdapter(adapter);
        }else {
            ArrayList<Book> list=dbHelper.getAllBooks();
            ArrayList<String> stringArrayList= new ArrayList<>();
            for(Book book:list){
                stringArrayList.add(book.getId()+"");
                stringArrayList.add(book.getTitle());
                stringArrayList.add(book.getAuthor().getId()+"");
            }
            ArrayAdapter<String> adapter= new ArrayAdapter<>(BookActivity.this,
                    android.R.layout.simple_list_item_1,stringArrayList);
            gridView.setAdapter(adapter);
        }
    }
    private void lamRong(){
        ed_id.setText("");
        ed_title.setText("");
    }
    private void loadAuhor(){
        ArrayList<Author> authors=dbHelper.getAllAuthors();
        List<String> list=new ArrayList<>();
        for (Author author:authors){
            list.add(author.getName());
        }
        authorNameAdapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,list);
        authorNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAuthor.setAdapter(authorNameAdapter);
    }
}