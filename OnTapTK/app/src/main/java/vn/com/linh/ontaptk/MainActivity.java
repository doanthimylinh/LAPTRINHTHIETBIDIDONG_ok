package vn.com.linh.ontaptk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
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
    DbHelper databaseHelper;
    ArrayList<Author> authors;
    AuthorArrayAdapter adapter;

    Button btnAdd, btnDelete, btnUpdate, btnExit, btnBooks;
    EditText edtAuthorName, edtAuthorAddress, edtAuthorEmail;
    GridView gridview_author;

    int id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapping();

        render();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtAuthorName.getText().toString();
                String address = edtAuthorAddress.getText().toString();
                String email = edtAuthorEmail.getText().toString();

                if (!name.equals("") && !address.equals("") && !email.equals("")) {
                    Author author = new Author(name, address, email);
                    databaseHelper.addAuthor(author);
                    refreshEditText();
                    render();
                } else {
                    Toast.makeText(MainActivity.this, "Not empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Xac nhan");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        databaseHelper.deleteAuthor(id);
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

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Author author = new Author(id, edtAuthorName.getText().toString(), edtAuthorAddress.getText().toString(), edtAuthorEmail.getText().toString());
                databaseHelper.updateAuhtor(author);
                render();
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        gridview_author.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Author author = authors.get(i);
                edtAuthorName.setText(author.getName());
                edtAuthorAddress.setText(author.getAddress());
                edtAuthorEmail.setText(author.getEmail());

                id = author.getId();
            }
        });

        btnBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BookActivity.class);
                startActivity(intent);
            }
        });
    }

    private void refreshEditText() {
        edtAuthorEmail.setText("");
        edtAuthorAddress.setText("");
        edtAuthorName.setText("");
    }

    private void render() {
        authors = databaseHelper.getAllAuthors();
        adapter = new AuthorArrayAdapter(this, R.layout.layout_author_info, authors);
        gridview_author.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void mapping() {
        btnAdd = findViewById(R.id.btn_add_author);
        btnDelete = findViewById(R.id.btn_delete_author);
        btnUpdate = findViewById(R.id.btn_update_author);
        btnExit = findViewById(R.id.btnExit);
        edtAuthorName = findViewById(R.id.edt_author_name);
        edtAuthorAddress = findViewById(R.id.edt_author_address);
        edtAuthorEmail = findViewById(R.id.edt_author_email);
        gridview_author = findViewById(R.id.gridview_author);
        btnBooks = findViewById(R.id.btn_books);

        databaseHelper = new DbHelper(this);
    }


}