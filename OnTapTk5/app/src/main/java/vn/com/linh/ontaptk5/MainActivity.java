package vn.com.linh.ontaptk5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.material.badge.BadgeUtils;

import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    EditText ed_id, ed_name, ed_address, ed_email;
    GridView gridView;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_id=findViewById(R.id.ed_idAuthor);
        ed_name=findViewById(R.id.ed_nameAuthor);
        ed_address=findViewById(R.id.ed_addressAuthor);
        ed_email=findViewById(R.id.ed_emailAuthor);
        gridView=findViewById(R.id.grid_ListAuthor);
        dbHelper=new DBHelper(this);
        select();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Integer id=Integer.parseInt(gridView.getItemAtPosition(i).toString());
                Author author=dbHelper.getAuthorById(id);
                ed_id.setText(String.valueOf(author.getId()));
                ed_name.setText(author.getName().toString());
                ed_address.setText(author.getAddress().toString());
                ed_email.setText(author.getEmail().toString());
            }
        });
        Button btn_them=findViewById(R.id.btn_saveAuthor);
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ed_id.getText().toString().equals("")||
                   ed_name.getText().toString().equals("")||
                   ed_address.getText().toString().equals("")||
                   ed_email.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Nhap du thong tin", Toast.LENGTH_SHORT).show();
                }else {
                    Author author= new Author();
                    author.setId(Integer.parseInt(ed_id.getText().toString()));
                    author.setName(ed_name.getText().toString());
                    author.setAddress(ed_address.getText().toString());
                    author.setEmail(ed_email.getText().toString());

                    if (dbHelper.insertAuthor(author)>0){
                        Toast.makeText(getApplicationContext(), "Luu thanh cong", Toast.LENGTH_SHORT).show();
                        lamRong();
                        select();
                    }else Toast.makeText(getApplicationContext(), "Luu that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button btn_xoa=findViewById(R.id.btn_deleteAuthor);
        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ed_id.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Chon author can xoa", Toast.LENGTH_SHORT).show();
                }else {
                    if (dbHelper.deleteAuthor(Integer.parseInt(ed_id.getText().toString()))>0){
                        Toast.makeText(getApplicationContext(), "xoa thanh cong author", Toast.LENGTH_SHORT).show();
                        lamRong();
                        select();
                    }
                }
            }
        });
        Button btn_sua=findViewById(R.id.btn_updateAuthor);
        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ed_id.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Chon author can xoa", Toast.LENGTH_SHORT).show();
                }else {
                    Author author=dbHelper.getAuthorById(Integer.parseInt(ed_id.getText().toString()));
                    author.setName(ed_name.getText().toString());
                    author.setAddress(ed_address.getText().toString());
                    author.setEmail(ed_email.getText().toString());
                    if (dbHelper.updateAuthor(author)){
                        Toast.makeText(getApplicationContext(), "Cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                        lamRong();
                        select();
                    }else Toast.makeText(getApplicationContext(), "Cap nhat that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button btn_select=findViewById(R.id.btn_selectAuthor);
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
        Button btn_book=findViewById(R.id.btn_book);
        btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, BookActivity.class);
                startActivity(intent);
            }
        });
    }
    private void select(){
        if (!ed_id.getText().toString().equals("")) {
            Author author;
            ArrayList<String> stringArrayList= new ArrayList<>();
            author=dbHelper.getAuthorById(Integer.parseInt(ed_id.getText().toString()));
            stringArrayList.add(author.getId()+"");
            stringArrayList.add(author.getName());
            stringArrayList.add(author.getAddress());
            stringArrayList.add(author.getEmail());
            ArrayAdapter<String> adapter= new ArrayAdapter<>(MainActivity.this,
                    android.R.layout.simple_list_item_1,stringArrayList);
            gridView.setAdapter(adapter);
        }else {
            ArrayList<Author> list=dbHelper.getAllAuthors();
            ArrayList<String> stringArrayList= new ArrayList<>();
            for(Author author:list){
                stringArrayList.add(author.getId()+"");
                stringArrayList.add(author.getName());
                stringArrayList.add(author.getAddress());
                stringArrayList.add(author.getEmail());
            }
            ArrayAdapter<String> adapter= new ArrayAdapter<>(MainActivity.this,
                    android.R.layout.simple_list_item_1,stringArrayList);
            gridView.setAdapter(adapter);
        }
    }
    private void lamRong(){
        ed_id.setText("");
        ed_email.setText("");
        ed_address.setText("");
        ed_name.setText("");
    }

}