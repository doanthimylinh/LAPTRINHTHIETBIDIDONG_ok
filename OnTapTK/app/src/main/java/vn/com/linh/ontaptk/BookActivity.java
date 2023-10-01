package vn.com.linh.ontaptk;

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

import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity {

    EditText edtTitle;
    Spinner spinnerAuthorName;
    Button btnAdd, btnDelete, btnUpdate;
    GridView gridViewBooks;
    ArrayAdapter<String> authorNameAdapter;
    BookArrayAdapter bookArrayAdapter;
    ArrayList<Book> books;
    DbHelper databaseHelper;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        anhXa();

        render();

        loadSpinnerData();

//        spinnerAuthorName.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) BookActivity.this);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = edtTitle.getText().toString().trim();
                String authorName = spinnerAuthorName.getSelectedItem().toString();
                Author author = databaseHelper.getAuthorByName(authorName);
                if (!title.equals("")) {
                    Book book = new Book(title, author);
                    databaseHelper.addBook(book);
                    render();
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Author author = databaseHelper.getAuthorByName(spinnerAuthorName.getSelectedItem().toString());
                Book book = new Book(id, edtTitle.getText().toString(), author);
                databaseHelper.updateBook(book);
                render();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(BookActivity.this);
                alert.setTitle("Xác nhận");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        databaseHelper.deleteBook(id);
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

        gridViewBooks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Book book = books.get(i);
                edtTitle.setText(book.getTitle());
                id = book.getId();
            }
        });

    }

    private void loadSpinnerData() {
        ArrayList<Author> authors = databaseHelper.getAllAuthors();
        List<String> authorNames = new ArrayList<>();
        for (Author author : authors) {
            authorNames.add(author.getName());
        }

        authorNameAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, authorNames);

        authorNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerAuthorName.setAdapter(authorNameAdapter);
    }

    private void render() {
        // van chua xoa duoc book khi xoa author
        books = databaseHelper.getAllBooks();
        bookArrayAdapter = new BookArrayAdapter(BookActivity.this, R.layout.layout_book_info, books);
        gridViewBooks.setAdapter(bookArrayAdapter);
        bookArrayAdapter.notifyDataSetChanged();
    }

    private void anhXa() {
        edtTitle = findViewById(R.id.edt_title);
        spinnerAuthorName = findViewById(R.id.spinner_authorName);
        btnAdd = findViewById(R.id.btn_add);
        btnDelete = findViewById(R.id.btn_delete);
        btnUpdate = findViewById(R.id.btn_update);
        gridViewBooks = findViewById(R.id.gridview_books);

        databaseHelper = new DbHelper(this);
    }


}