package vn.com.linh.appa_provider3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PhongBanActivity extends AppCompatActivity {
    EditText ed_id, ed_name;
    GridView gridView;
    Button btn_them ,btn_xoa, btn_sua, btn_tim, btn_thoat;
    static final String URL="content://vn.com.linh.appa_provider3/PhongBans";
    List<PhongBan> phongBanList=new ArrayList<>();
    ArrayAdapter<String> adapter;
    int id_PhongBan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phong_ban);
        ed_id=findViewById(R.id.ed_id);
        ed_name=findViewById(R.id.ed_name);
        gridView=findViewById(R.id.gridViewPhongBan);
        btn_them=findViewById(R.id.btn_Them);

        select();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PhongBan phongBan= phongBanList.get(i);
                id_PhongBan=phongBan.getId();
                ed_id.setText(""+id_PhongBan);
                ed_name.setText(phongBan.getName());
            }
        });
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues contentValues= new ContentValues();
                contentValues.put("id", Integer.parseInt(ed_id.getText().toString()));
                contentValues.put("name", ed_name.getText().toString());
                Uri phongBanUri=Uri.parse(URL);
                Uri isInsert=getContentResolver().insert(phongBanUri,contentValues);
                if (isInsert!=null){
                    phongBanList.clear();
                    select();
                    lamrong();
                    Toast.makeText(getApplicationContext(), "Them thanh cong", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(getApplicationContext(), "them that bai", Toast.LENGTH_SHORT).show();
            }
        });
        btn_sua=findViewById(R.id.btn_CapNhat);
        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phongBanIdUrl=URL+"/"+id_PhongBan;
                Uri phongBanUri=Uri.parse(phongBanIdUrl);
                PhongBan phongBan=new PhongBan(Integer.parseInt(ed_id.getText().toString()), ed_name.getText().toString());

                ContentValues contentValues= new ContentValues();
                contentValues.put("id", phongBan.getId());
                contentValues.put("name", phongBan.getName());

                int isUpdate=getContentResolver().update(phongBanUri,contentValues,null,null);
                if (isUpdate>0){
                    phongBanList.clear();
                    select();
                    lamrong();
                    Toast.makeText(getApplicationContext(), "Cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(getApplicationContext(), "Cap nhat that bai", Toast.LENGTH_SHORT).show();
            }
        });
        btn_xoa=findViewById(R.id.btn_Xoa);
        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phongBanIdUrl=URL+"/"+id_PhongBan;
                Uri phongBanUri=Uri.parse(phongBanIdUrl);

                int isDelete=getContentResolver().delete(phongBanUri,null,null);
                if (isDelete>0){
                    phongBanList.clear();
                    select();
                    lamrong();
                    Toast.makeText(getApplicationContext(), "xoa thanh cong", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(getApplicationContext(), "xoa that bai", Toast.LENGTH_SHORT).show();
            }
        });
        btn_tim=findViewById(R.id.btn_Tim);
        btn_tim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phongBanList.clear();
                String phongBanIdUrl=URL+"/"+ed_id.getText().toString().trim();
                Uri phongBanIdUri=Uri.parse(phongBanIdUrl);

                Cursor cursor=getContentResolver().query(phongBanIdUri, null, null, null, null);
                if (cursor!=null && cursor.moveToFirst()){
                    while (!cursor.isAfterLast()){
                        int id=cursor.getInt(0);
                        String name=cursor.getString(1);
                        PhongBan phongBan= new PhongBan(id,name);
                        phongBanList.add(phongBan);
                        cursor.moveToNext();
                    }
                    cursor.close();
                }
                List<String> list= new ArrayList<>();
                for (PhongBan phongBan:phongBanList){
                    list.add("id: "+phongBan.getId()+" -ten: "+phongBan.getName());

                }
                adapter= new ArrayAdapter<>(PhongBanActivity.this, android.R.layout.simple_list_item_1,list);
                gridView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
        btn_thoat=findViewById(R.id.btn_Thoat);
        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void select(){
        Uri phongBanUri=Uri.parse(URL);
        Cursor cursor=getContentResolver().query(phongBanUri, null, null, null, null);
        if (cursor!=null && cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                int id=cursor.getInt(0);
                String name=cursor.getString(1);
                PhongBan phongBan= new PhongBan(id,name);
                phongBanList.add(phongBan);
                cursor.moveToNext();
            }
            cursor.close();
        }
        List<String> list= new ArrayList<>();
        for (PhongBan phongBan:phongBanList){
            list.add("id: "+phongBan.getId()+" -ten: "+phongBan.getName());

        }
        adapter= new ArrayAdapter<>(PhongBanActivity.this, android.R.layout.simple_list_item_1,list);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    private void lamrong(){
        ed_name.setText("");
        ed_id.setText("");
    }
}