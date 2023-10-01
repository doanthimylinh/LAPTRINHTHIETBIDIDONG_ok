package vn.com.linh.appa_provider5;

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

public class LoaiSanPhamActivity extends AppCompatActivity {

    EditText ed_id, ed_name;
    Button btn_them, btn_xoa, btn_sua, btn_select, btn_thoat;
    GridView gridView;

    static final String URL="content://vn.com.linh.appa_provider5/LoaiSanPhams";
    List<LoaiSanPham> loaiSanPhamList= new ArrayList<>();
    ArrayAdapter<String> adapter;
    int id_LoaiSP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_san_pham);
        ed_id=findViewById(R.id.ed_ma_lsp);
        ed_name=findViewById(R.id.ed_ten_lsp);
        gridView=findViewById(R.id.gridViewLoaiSP);
        select();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LoaiSanPham loaiSanPham=loaiSanPhamList.get(i);
                id_LoaiSP=loaiSanPham.getId();
                ed_id.setText(""+id_LoaiSP);
                ed_name.setText(loaiSanPham.getName());
            }
        });
        btn_them=findViewById(R.id.btn_Them);
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues contentValues= new ContentValues();
                contentValues.put("id", Integer.parseInt(ed_id.getText().toString()));
                contentValues.put("name", ed_name.getText().toString());
                Uri uri=Uri.parse(URL);
                Uri isInsert=getContentResolver().insert(uri, contentValues);
                if (isInsert!=null){
                    loaiSanPhamList.clear();
                    select();
                    lamRong();
                    Toast.makeText(getApplicationContext(), "thanh cong", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(getApplicationContext(), "that bai", Toast.LENGTH_SHORT).show();
            }
        });
        btn_xoa=findViewById(R.id.btn_Xoa_lsp);
        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idUri=URL+"/"+id_LoaiSP;
                Uri uri=Uri.parse(idUri);

                int isDelete=getContentResolver().delete(uri, null,null);
                if (isDelete>0){
                    loaiSanPhamList.clear();
                    select();
                    lamRong();
                    Toast.makeText(getApplicationContext(), "thanh cong", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(getApplicationContext(), "that bai", Toast.LENGTH_SHORT).show();
            }
        });
        btn_sua=findViewById(R.id.btn_Sua_lsp);
        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String IdUri=URL+"/"+id_LoaiSP;
                Uri uri=Uri.parse(IdUri);
                LoaiSanPham loaiSanPham= new LoaiSanPham(Integer.parseInt(ed_id.getText().toString()), ed_name.getText().toString());

                ContentValues contentValues= new ContentValues();
                contentValues.put("id", Integer.parseInt(ed_id.getText().toString()));
                contentValues.put("name", ed_name.getText().toString());

                int isUpdate=getContentResolver().update(uri,contentValues, null, null);
                if (isUpdate>0){
                    loaiSanPhamList.clear();
                    select();
                    lamRong();
                    Toast.makeText(getApplicationContext(), "thanh cong", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(getApplicationContext(), "that bai", Toast.LENGTH_SHORT).show();
            }
        });
        btn_select=findViewById(R.id.btn_Tim);
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loaiSanPhamList.clear();
                String idUri=URL+"/"+ed_id.getText().toString();
                Uri uri=Uri.parse(idUri);
                Cursor cursor=getContentResolver().query(uri, null,null,null, null);
                if (cursor!=null && cursor.moveToFirst()){
                    while (!cursor.isAfterLast()){
                        int id=cursor.getInt(0);
                        String name=cursor.getString(1);
                        LoaiSanPham loaiSanPham= new LoaiSanPham(id,name);
                        loaiSanPhamList.add(loaiSanPham);
                        cursor.moveToNext();
                    }
                    cursor.close();
                }
                List<String> list= new ArrayList<>();
                for (LoaiSanPham loaiSanPham:loaiSanPhamList){
                    list.add("id: "+loaiSanPham.getId()+"-ten: "+loaiSanPham.getName());

                }
                adapter= new ArrayAdapter<>(LoaiSanPhamActivity.this, android.R.layout.simple_list_item_1, list);
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
        Uri loaiSpUri=Uri.parse(URL);
        Cursor cursor=getContentResolver().query(loaiSpUri, null,null,null, null);
        if (cursor!=null && cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                int id=cursor.getInt(0);
                String name=cursor.getString(1);
                LoaiSanPham loaiSanPham= new LoaiSanPham(id,name);
                loaiSanPhamList.add(loaiSanPham);
                cursor.moveToNext();
            }
            cursor.close();
        }
        List<String> list= new ArrayList<>();
        for (LoaiSanPham loaiSanPham:loaiSanPhamList){
            list.add("id: "+loaiSanPham.getId()+"-ten: "+loaiSanPham.getName());

        }
        adapter= new ArrayAdapter<>(LoaiSanPhamActivity.this, android.R.layout.simple_list_item_1, list);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    private void lamRong(){
        ed_name.setText("");
        ed_id.setText("");
    }
}