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

public class AuthorActivity extends AppCompatActivity {

    DBHelper dbHelper;
    GridView gridView;
    EditText ed_id,ed_name,ed_address,ed_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);

        ed_id=findViewById(R.id.ed_id);
        ed_name=findViewById(R.id.ed_name);
        ed_address=findViewById(R.id.ed_address);
        ed_email=findViewById(R.id.ed_email);
        gridView=findViewById(R.id.grid_ListAuthor);
        dbHelper=new DBHelper(this);
        select();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Integer ma=Integer.parseInt(gridView.getItemAtPosition(i).toString());
                Author author= dbHelper.getAuhtorById(ma);
                ed_id.setText(String.valueOf(author.getIdAuthor()));
                ed_name.setText(author.getAddress().toString());
                ed_address.setText(author.getAddress().toString());
                ed_email.setText(author.getEmail().toString());
            }
        });
        Button btn_save = findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ed_id.getText().toString().equals("")||ed_name.getText().toString().equals("")||
                        ed_address.getText().toString().equals("")||ed_email.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "thong tin khong day du", Toast.LENGTH_SHORT).show();
                }else {
                    Author author= new Author();
                    author.setIdAuthor(Integer.parseInt(ed_id.getText().toString()));
                    author.setName(ed_name.getText().toString());
                    author.setAddress(ed_address.getText().toString());
                    author.setEmail(ed_email.getText().toString());

                    if (dbHelper.insertAuthor(author)>0){
                        Toast.makeText(getApplication(),"da luu thanh cong",Toast.LENGTH_SHORT).show();
                        lamRong();
                        select();
                    }
                    else  Toast.makeText(getApplication()," luu khong thanh cong",Toast.LENGTH_SHORT).show();
                }

            }
        });
        Button btn_select=findViewById(R.id.btn_select);
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select();

            }
        });
        Button btn_delete=findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ed_id.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Hay chon 1 author can xoa", Toast.LENGTH_SHORT).show();
                }else {
                    if (dbHelper.deleteAuhtor(Integer.parseInt(ed_id.getText().toString()))>0){
                        Toast.makeText(getApplicationContext(), "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                        lamRong();
                        select();
                    }
                    else Toast.makeText(getApplicationContext(), "xoa that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button btn_sua=findViewById(R.id.btn_update);
        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ed_id.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Hay chon 1 author can xoa", Toast.LENGTH_SHORT).show();
                }else {
                    Author author=dbHelper.getAuhtorById(Integer.parseInt(ed_id.getText().toString()));
                    author.setName(ed_name.getText().toString());
                    author.setAddress(ed_address.getText().toString());
                    author.setEmail(ed_email.getText().toString());

                    if (dbHelper.updateAuthor(author)){
                        Toast.makeText(getApplicationContext(), "cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                        lamRong();
                        select();
                    }else Toast.makeText(getApplicationContext(), "cap nhat that bai", Toast.LENGTH_SHORT).show();
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

    }
    public  void select(){
        if (!ed_id.getText().toString().equals("")){
            Author author;
            ArrayList<String> stringArrayList= new ArrayList<>();
            author=dbHelper.getIdAuhtor(Integer.parseInt(ed_id.getText().toString()));
                stringArrayList.add(author.getIdAuthor()+"");
                stringArrayList.add(author.getName());
                stringArrayList.add(author.getAddress());
                stringArrayList.add(author.getEmail());
            ArrayAdapter<String>adapter= new ArrayAdapter<>(AuthorActivity.this,
                    android.R.layout.simple_list_item_1,stringArrayList);
            gridView.setAdapter(adapter);
        }else {
            ArrayList<Author> list;
            ArrayList<String> stringArrayList= new ArrayList<>();
            list=dbHelper.getAllAuhtor();
            for (Author author :list){
                stringArrayList.add(author.getIdAuthor()+"");
                stringArrayList.add(author.getName());
                stringArrayList.add(author.getAddress());
                stringArrayList.add(author.getEmail());
            }
            ArrayAdapter<String>adapter= new ArrayAdapter<>(AuthorActivity.this,
                    android.R.layout.simple_list_item_1,stringArrayList);
            gridView.setAdapter(adapter);
        }

        }
        public void lamRong(){
        ed_id.setText("");
        ed_name.setText("");
        ed_address.setText("");
        ed_email.setText("");

    }
}