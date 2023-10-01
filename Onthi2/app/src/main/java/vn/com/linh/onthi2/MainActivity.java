package vn.com.linh.onthi2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    ArrayList<Author> authors;
    AuthorCustom authorCustom;
    GridView gridViewAuthor;
    EditText ed_id,ed_name,ed_address,ed_email;
    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_id=findViewById(R.id.ed_idAuhtor);
        ed_name=findViewById(R.id.ed_nameAuthor);
        ed_address=findViewById(R.id.ed_AddressAuthor);
        ed_email=findViewById(R.id.ed_emailAuthor);
        gridViewAuthor=findViewById(R.id.gridViewAuthor);
        dbHelper= new DBHelper(this);
        render();
        Button btn_save=findViewById(R.id.btn_Them);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=Integer.parseInt(ed_id.getText().toString());
                String name=ed_name.getText().toString();
                String address=ed_address.getText().toString();
                String email=ed_email.getText().toString();
                if (id>0 && !name.equals("")&& !address.equals("") && !email.equals("")){
                    Author author= new Author(id,name,address,email);
                    dbHelper.addAuthor(author);
                    Toast.makeText(MainActivity.this," luu thanh cong",Toast.LENGTH_SHORT).show();
                    lamTrong();
                    render();
                }
                else  Toast.makeText(MainActivity.this," luu khong thanh cong",Toast.LENGTH_SHORT).show();
            }
        });
        Button btn_xoa=findViewById(R.id.btn_Xoa);
        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert= new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("xac nhan");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHelper.deleteAuthor(Integer.parseInt(ed_id.getText().toString()));
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
        Button btn_update=findViewById(R.id.btn_Sua);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Author author= new Author(Integer.parseInt(ed_id.getText().toString()),ed_name.getText().toString(),ed_address.getText().toString(),ed_email.getText().toString());
                dbHelper.updateAuthor(author);
                render();
            }
        });
        Button btn_thoat=findViewById(R.id.btn_thoat);
        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Button btn_book=findViewById(R.id.btn_Book);
        btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, BookActivity.class);
                startActivity(intent);
            }
        });

        gridViewAuthor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Author author=authors.get(i);

                ed_name.setText(author.getName());
                ed_address.setText(author.getAddress());
                ed_email.setText(author.getEmail());
                ed_id.setText(author.getId()+"");
            }
        });
    }

    private void lamTrong(){
        ed_id.setText("");
        ed_email.setText("");
        ed_name.setText("");
        ed_address.setText("");

    }
    private void render(){
        authors=dbHelper.getAllAuthors();
        authorCustom= new AuthorCustom(this,R.layout.author_custom,authors);
        gridViewAuthor.setAdapter(authorCustom);
        authorCustom.notifyDataSetChanged();
    }
}