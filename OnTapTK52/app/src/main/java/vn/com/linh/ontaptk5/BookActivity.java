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

    EditText ed_id, ed_name;
    Spinner spinnerAuthor;
    ArrayAdapter<String> authorNameAdapter;
    GridView gridView;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        ed_id= findViewById(R.id.ed_idBook);
        ed_name= findViewById(R.id.ed_nameBook);
        gridView=findViewById(R.id.gridViewBooks);
        spinnerAuthor=findViewById(R.id.spinnerAuthor);
        dbHelper= new DBHelper(this);
        spinnerAuthor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String authorName=spinnerAuthor.getSelectedItem().toString();
                Author author=dbHelper.getAuthorByName(authorName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        loadAuthor();
        select();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Integer id=Integer.parseInt(gridView.getItemAtPosition(i).toString());
                Book book=dbHelper.getBookById(id);
                ed_id.setText(String.valueOf(book.getId()));
                ed_name.setText(book.getName().toString());
            }
        });
        Button btn_them=findViewById(R.id.btn_themBook);
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ed_id.getText().toString().equals("")||
                        ed_name.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Nhap day du thong tin", Toast.LENGTH_SHORT).show();
                }else {
                   int id=Integer.parseInt(ed_id.getText().toString());
                   String name=ed_name.getText().toString();
                   String authorName=spinnerAuthor.getSelectedItem().toString();
                   Author author= dbHelper.getAuthorByName(authorName);

                    if (id>0&&!name.equals("")){
                        Book book=new Book(id,name,author);
                        dbHelper.insertBook(book);
                        Toast.makeText(getApplicationContext(), "Luu thanh cong", Toast.LENGTH_SHORT).show();
                        lamRong();
                        select();
                    }else Toast.makeText(getApplicationContext(), "Luu that bai", Toast.LENGTH_SHORT).show();
                }
            }

        });
        Button btn_xoa=findViewById(R.id.btn_xoaBook);
        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ed_id.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Chon book can xoa", Toast.LENGTH_SHORT).show();
                }else {
                    if (dbHelper.deleteBook(Integer.parseInt(ed_id.getText().toString()))>0){
                        Toast.makeText(getApplicationContext(), "xoa thanh cong book", Toast.LENGTH_SHORT).show();
                        lamRong();
                        select();
                    }else Toast.makeText(getApplicationContext(), "xoa that bai", Toast.LENGTH_SHORT).show();

                }
            }
        });
        Button btn_sua=findViewById(R.id.btn_suaBook);
        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ed_id.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Chon author can sua", Toast.LENGTH_SHORT).show();
                }else {
                    Book book = dbHelper.getBookById(Integer.parseInt(ed_id.getText().toString()));
                    book.setName(ed_name.getText().toString());

                    String authorName=spinnerAuthor.getSelectedItem().toString();
                    Author author= dbHelper.getAuthorByName(authorName);
                    book.setAuthor(author);
                    if (dbHelper.updateBook(book)) {
                        Toast.makeText(getApplicationContext(), "cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                        lamRong();
                        select();
                    } else
                        Toast.makeText(getApplicationContext(), "cap nhat that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button btn_select=findViewById(R.id.btn_selectBook);
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select();
            }
        });
        Button btn_thoat=findViewById(R.id.btn_Thoat);
        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void loadAuthor(){
        ArrayList<Author> authors=dbHelper.getAllAuthors();
        List<String> list= new ArrayList<>();
        for (Author author:authors){
            list.add(author.getName());
        }
        authorNameAdapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        authorNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAuthor.setAdapter(authorNameAdapter);
    }
    public void select(){
        if (!ed_id.getText().toString().equals("")){
            Book book;
            ArrayList<String> stringArrayList= new ArrayList<>();
            book=dbHelper.getBookById(Integer.parseInt(ed_id.getText().toString()));
            stringArrayList.add(book.getId()+"");
            stringArrayList.add(book.getName());
            stringArrayList.add(book.getAuthor().getId()+"");

            ArrayAdapter<String> adapter= new ArrayAdapter<>(BookActivity.this,
                    android.R.layout.simple_list_item_1, stringArrayList);
            gridView.setAdapter(adapter);
        }else {
            ArrayList<Book> list= dbHelper.getAllBooks();
            ArrayList<String> stringArrayList= new ArrayList<>();
            for (Book book:list){
                stringArrayList.add(book.getId()+"");
                stringArrayList.add(book.getName());
                stringArrayList.add(book.getAuthor().getId()+"");
            }
            ArrayAdapter<String> adapter= new ArrayAdapter<>(BookActivity.this,
                    android.R.layout.simple_list_item_1, stringArrayList);
            gridView.setAdapter(adapter);
        }
    }
    public void lamRong(){
        ed_id.setText("");
        ed_name.setText("");
    }
}