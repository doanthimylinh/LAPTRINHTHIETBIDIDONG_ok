package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {
    EditText ed_idbook,ed_title,ed_idauthor;
    GridView gridView;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        ed_idbook=findViewById(R.id.ed_idbook);
        ed_title=findViewById(R.id.ed_title);
        ed_idauthor=findViewById(R.id.ed_idauthor);

        gridView=findViewById(R.id.grid_ListBook);
        dbHelper=new DBHelper(this);
        select();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Integer ma=Integer.parseInt(gridView.getItemAtPosition(i).toString());
                Book book=dbHelper.getBookById(ma);
                ed_idbook.setText(String.valueOf(book.getId_book()));
                ed_title.setText(book.getTitle().toString());
                ed_idauthor.setText(String.valueOf(book.getAuthor().getIdAuthor()));
            }
        });

        Button btn_save=findViewById(R.id.btn_savebook);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book= new Book();
                book.setId_book(Integer.parseInt(ed_idbook.getText().toString()));
                book.setTitle(ed_title.getText().toString());
                book.setAuthor(dbHelper.getAuhtorById(Integer.parseInt(ed_idauthor.getText().toString())));

                if (dbHelper.insertBook(book)>0){
                    Toast.makeText(getApplication(),"da luu thanh cong",Toast.LENGTH_SHORT).show();
                    lamRong();
                    select();
                }
                else  Toast.makeText(getApplication()," luu khong thanh cong",Toast.LENGTH_SHORT).show();
            }
        });
        Button btn_select=findViewById(R.id.btn_selectbook);
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select();
            }
        });
    Button btn_xoa=findViewById(R.id.btn_deletebook);
    btn_xoa.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (ed_idbook.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(), "Hay chon 1 book can xoa", Toast.LENGTH_SHORT).show();
            }else {
                if (dbHelper.deleteBook(Integer.parseInt(ed_idbook.getText().toString()))>0){
                    Toast.makeText(getApplicationContext(), "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                    lamRong();
                    select();
                }
                else Toast.makeText(getApplicationContext(), "xoa that bai", Toast.LENGTH_SHORT).show();
            }
        }
    });
    Button btn_thoat=findViewById(R.id.btn_thoat);
    btn_thoat.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    });
    Button btn_sua=findViewById(R.id.btn_updatebook);
    btn_sua.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (ed_idbook.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(), "Hay chon 1 author can xoa", Toast.LENGTH_SHORT).show();
            }else {
                Book book=dbHelper.getBookById(Integer.parseInt(ed_idbook.getText().toString()));
                book.setTitle(ed_title.getText().toString());
                book.setAuthor(dbHelper.getAuhtorById(Integer.parseInt(ed_idauthor.getText().toString())));

                if (dbHelper.updateBook(book)){
                    Toast.makeText(getApplicationContext(), "cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                    lamRong();
                    select();
                }else Toast.makeText(getApplicationContext(), "cap nhat that bai", Toast.LENGTH_SHORT).show();
            }

        }
    });
    }

    public void select(){

        ArrayList<Book> bookArrayList;
        ArrayList<String> stringArrayList= new ArrayList<>();
        bookArrayList=dbHelper.getAllBooks();
        for (Book book :bookArrayList){
            stringArrayList.add(book.getId_book()+"");
            stringArrayList.add(book.getTitle());
            stringArrayList.add(book.getAuthor().getIdAuthor()+"");
        }
        ArrayAdapter<String>adapter= new ArrayAdapter<>(BookActivity.this,
                android.R.layout.simple_list_item_1,stringArrayList);
        gridView.setAdapter(adapter);
    }
    public void lamRong(){
        ed_idbook.setText("");
        ed_title.setText("");
        ed_idauthor.setText("");

    }

}