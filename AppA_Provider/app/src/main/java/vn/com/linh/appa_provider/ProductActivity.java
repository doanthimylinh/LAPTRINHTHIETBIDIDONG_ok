package vn.com.linh.appa_provider;

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

public class ProductActivity extends AppCompatActivity {

    static final String URI="content://vn.com.linh.appa_provider";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        EditText ed_id=findViewById(R.id.ed_id);
        EditText ed_name=findViewById(R.id.ed_name);
        EditText ed_unit=findViewById(R.id.ed_unit);
        EditText ed_madein=findViewById(R.id.ed_madein);

        GridView gridView=findViewById(R.id.grid_productlist);

        Button btn_save=findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values=new ContentValues();
                values.put("id",ed_id.getText().toString());
                values.put("name",ed_name.getText().toString());
                values.put("unit",ed_unit.getText().toString());
                values.put("madein",ed_madein.getText().toString());
                Uri product= Uri.parse(URI);
                Uri insert_Uri=getContentResolver().insert(product,values);
                Toast.makeText(getApplicationContext(),"Lưu thành công",Toast.LENGTH_SHORT).show();
            }
        });

        Button btn_select=findViewById(R.id.btn_select);
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> arrayList=new ArrayList<>();
                Uri product=Uri.parse(URI);
                Cursor cursor=getContentResolver().query(product,null,null,null,null);
                if (cursor!=null){
                    cursor.moveToFirst();
                    do{
                        arrayList.add(cursor.getInt(0)+"");
                        arrayList.add(cursor.getString(1));
                        arrayList.add(cursor.getString(2));
                        arrayList.add(cursor.getString(3));
                    }while (cursor.moveToNext());
                    ArrayAdapter<String> adapter= new ArrayAdapter<>(ProductActivity.this, android.R.layout.simple_list_item_1,arrayList);
                    gridView.setAdapter(adapter);
                }else
                    Toast.makeText(getApplicationContext(), "Không có kế quả", Toast.LENGTH_SHORT).show();
            }
        });

    }
}