package vn.com.ngoc.appbprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static final String URL = "content://vn.com.ngoc.myapplication/Products";
    static final Uri CONTENT_URI = Uri.parse(URL);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText id = findViewById(R.id.et_id);
        EditText name = findViewById(R.id.et_name);
        EditText unit = findViewById(R.id.et_unit);
        EditText madein = findViewById(R.id.et_madein);

        Button save = findViewById(R.id.btn_save);
        Button select = findViewById(R.id.btn_select);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("id", id.getText().toString());
                values.put("name", name.getText().toString());
                values.put("unit", unit.getText().toString());
                values.put("madein", madein.getText().toString());

//                Uri product= Uri.parse(URL);
                Uri insert_uri = getContentResolver().insert(CONTENT_URI, values);
                Toast.makeText(getApplicationContext(), "Lưu Thành Công", Toast.LENGTH_SHORT).show();
            }
        });
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> string_list= new ArrayList<>();
                Cursor cursor= getContentResolver().query(CONTENT_URI, null, null, null, null);
                if(cursor!=null && cursor.moveToFirst()){
                    while(!cursor.isAfterLast()){
                        string_list.add(cursor.getInt(0)+"");
                        string_list.add(cursor.getString(1));
                        string_list.add(cursor.getString(2));
                        string_list.add(cursor.getString(3));
                        cursor.moveToNext();
                    }
                    ArrayAdapter<String> adapter= new ArrayAdapter<>(MainActivity.this,
                            android.R.layout.simple_list_item_1, string_list);
                    GridView gridView= findViewById(R.id.gridview);
                    gridView.setAdapter(adapter);
                }
                else Toast.makeText(getApplicationContext(), "không có kết quả", Toast.LENGTH_SHORT).show();
            }
        });


    }
}