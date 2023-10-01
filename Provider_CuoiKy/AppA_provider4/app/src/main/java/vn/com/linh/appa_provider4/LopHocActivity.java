package vn.com.linh.appa_provider4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LopHocActivity extends AppCompatActivity {

    EditText ed_id, ed_name;
    Button btn_theem, btn_xoa, btn_sua, btn_select, btn_thoat;
    GridView gridView;

    static final String URL="content://vn.com.linh.appa_provider4/LopHocs";
    List<LopHoc> lopHocList=new ArrayList<>();
    ArrayAdapter<String> adapter;
    int id_LopHoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lop_hoc);
        ed_id=findViewById(R.id.ed_id);
        ed_name=findViewById(R.id.ed_ten);

        gridView=findViewById(R.id.gridviewLopHoc);
        btn_theem=findViewById(R.id.btn_Them);

        select();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LopHoc lopHoc=lopHocList.get(i);
                id_LopHoc=lopHoc.getId();
                ed_id.setText(""+id_LopHoc);
                ed_name.setText(lopHoc.getTenLop());
            }
        });
        btn_theem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues contentValues= new ContentValues();
                contentValues.put("id", Integer.parseInt(ed_id.getText().toString()));
                contentValues.put("name", ed_name.getText().toString());
                Uri lopHocUri=Uri.parse(URL);
                Uri isInsert=getContentResolver().insert(lopHocUri,contentValues);
                if (isInsert!=null){
                    lopHocList.clear();
                    select();
                    lamRong();
                    Toast.makeText(getApplicationContext(), "thanh cong", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(getApplicationContext(), "that bai", Toast.LENGTH_SHORT).show();
            }
        });
        btn_sua=findViewById(R.id.btn_Sua);
        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lopHocIdUri=URL+"/"+id_LopHoc;
                Uri lopHocUri=Uri.parse(lopHocIdUri);
                LopHoc lopHoc= new LopHoc(Integer.parseInt(ed_id.getText().toString()), ed_name.getText().toString());

                ContentValues contentValues= new ContentValues();
                contentValues.put("id", Integer.parseInt(ed_id.getText().toString()));
                contentValues.put("name", ed_name.getText().toString());

                int isUpdate=getContentResolver().update(lopHocUri,contentValues, null, null);
                if (isUpdate>0){
                    lopHocList.clear();
                    select();
                    lamRong();
                    Toast.makeText(getApplicationContext(), "thanh cong", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(getApplicationContext(), "that bai", Toast.LENGTH_SHORT).show();
            }
        });
        btn_xoa=findViewById(R.id.btn_Xoa);
        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lopHocIdUri=URL+"/"+id_LopHoc;
                Uri lopHocUri=Uri.parse(lopHocIdUri);

                int isDelete=getContentResolver().delete(lopHocUri, null, null);
                if (isDelete>0){
                    lopHocList.clear();
                    select();
                    lamRong();
                    Toast.makeText(getApplicationContext(), "thanh cong", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(getApplicationContext(), "that bai", Toast.LENGTH_SHORT).show();
            }
        });
        btn_select=findViewById(R.id.btn_Select);
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopHocList.clear();
                String lopHocIdUrl=URL+"/"+ed_id.getText().toString();
                Uri lopHocIdUri= Uri.parse(lopHocIdUrl);
                Cursor cursor=getContentResolver().query(lopHocIdUri, null, null, null, null);
                if (cursor!=null && cursor.moveToFirst()){
                    while (!cursor.isAfterLast()){
                        int id=cursor.getInt(0);
                        String name=cursor.getString(1);
                        LopHoc lopHoc= new LopHoc(id,name);
                        lopHocList.add(lopHoc);
                        cursor.moveToNext();
                    }
                    cursor.close();
                }
                List<String> list=new ArrayList<>();
                for (LopHoc lopHoc:lopHocList){
                    list.add("id: "+lopHoc.getId()+"_ten: "+lopHoc.getTenLop());

                }
                adapter= new ArrayAdapter<>(LopHocActivity.this, android.R.layout.simple_list_item_1,list);
                gridView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
        btn_thoat=findViewById(R.id.btn_thoat);
        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    private void select(){
        Uri lopHocUri= Uri.parse(URL);
        Cursor cursor=getContentResolver().query(lopHocUri, null, null, null, null);
        if (cursor!=null && cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                int id=cursor.getInt(0);
                String name=cursor.getString(1);
                LopHoc lopHoc= new LopHoc(id,name);
                lopHocList.add(lopHoc);
                cursor.moveToNext();
            }
            cursor.close();
        }
        List<String> list=new ArrayList<>();
        for (LopHoc lopHoc:lopHocList){
            list.add("id: "+lopHoc.getId()+"_ten: "+lopHoc.getTenLop());

        }
        adapter= new ArrayAdapter<>(LopHocActivity.this, android.R.layout.simple_list_item_1,list);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    private void lamRong(){
        ed_name.setText("");
        ed_id.setText("");
    }
}