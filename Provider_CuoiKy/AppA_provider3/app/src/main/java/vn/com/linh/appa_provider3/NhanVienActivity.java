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
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NhanVienActivity extends AppCompatActivity {
    EditText ed_id, ed_name, ed_diaChi;
    Spinner spinner;
    GridView gridView;
    Button btn_them ,btn_xoa, btn_sua, btn_tim, btn_thoat;
    static final String URL_NHANVIEN="content://vn.com.linh.appa_provider3/NhanViens";
    static final String URL_PHONGBAN="content://vn.com.linh.appa_provider3/PhongBans";
    List<NhanVien> nhanVienList=new ArrayList<>();
    List<PhongBan> phongBanList=new ArrayList<>();
    ArrayAdapter<String> adapter;
    int id_PhongBan;
    int id_NhanVien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_vien);

        ed_id=findViewById(R.id.ed_id);
        ed_name=findViewById(R.id.ed_name);
        ed_diaChi=findViewById(R.id.ed_diachi);
        spinner=findViewById(R.id.spinner_PhongBan);
        gridView=findViewById(R.id.gridViewNhanVien);
        load();
        select();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NhanVien nhanVien= nhanVienList.get(i);
                id_NhanVien=nhanVien.getId();
                ed_id.setText(""+id_NhanVien);
                ed_name.setText(nhanVien.getName());
                ed_diaChi.setText(nhanVien.getAddress());
            }
        });
        btn_them=findViewById(R.id.btn_Them);
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues contentValues=setContentValuesForNhanVien();
                Uri nhanVienUri=Uri.parse(URL_NHANVIEN);
                Uri isInsert=getContentResolver().insert(nhanVienUri,contentValues);
                if (isInsert!=null){
                    nhanVienList.clear();
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
                String nhanVienIdUrl=URL_NHANVIEN+"/"+id_NhanVien;
                ContentValues contentValues=setContentValuesForNhanVien();

                int isUpdate=getContentResolver().update(Uri.parse(nhanVienIdUrl),contentValues,null,null);
                if (isUpdate>0){
                    nhanVienList.clear();
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
                String nhanVienIdUrl=URL_NHANVIEN+"/"+id_NhanVien;
                Uri nhanVienUri=Uri.parse(nhanVienIdUrl);

                int isDelete=getContentResolver().delete(nhanVienUri,null,null);
                if (isDelete>0){
                    nhanVienList.clear();
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
                nhanVienList.clear();
                String nhanVienIdUrl=URL_NHANVIEN+"/"+ed_id.getText().toString().trim();
                Uri nhanVienIdUri=Uri.parse(nhanVienIdUrl);

                Cursor cursor=getContentResolver().query(nhanVienIdUri, null, null, null, null);
                if (cursor!=null && cursor.moveToFirst()){
                    while (!cursor.isAfterLast()){
                        int id=cursor.getInt(0);
                        String name=cursor.getString(1);
                        String diaChi=cursor.getString(2);
                        int id_phongBan=cursor.getInt(3);
                        for (PhongBan phongBan:phongBanList){
                            if (phongBan.getId()==id_phongBan){
                                NhanVien nhanVien= new NhanVien(id, name,diaChi,phongBan);
                                nhanVienList.add(nhanVien);
                            }
                        }
                        cursor.moveToNext();
                    }
                    cursor.close();
                }
                List<String> list= new ArrayList<>();
                for (NhanVien nhanVien:nhanVienList){
                    list.add("id: "+nhanVien.getId()+" -ten: "+nhanVien.getName()+" -Dia chi: "+nhanVien.getAddress()+ "-Phong ban: "+nhanVien.getPhongBan().getName());

                }
                adapter= new ArrayAdapter<>(NhanVienActivity.this, android.R.layout.simple_list_item_1,list);
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
        Uri nhanVienUri=Uri.parse(URL_NHANVIEN);
        Cursor cursor=getContentResolver().query(nhanVienUri, null, null, null, null);
        if (cursor!=null && cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                int id=cursor.getInt(0);
                String name=cursor.getString(1);
                String diaChi=cursor.getString(2);
                int id_phongBan=cursor.getInt(3);
                for (PhongBan phongBan:phongBanList){
                    if (phongBan.getId()==id_phongBan){
                        NhanVien nhanVien= new NhanVien(id, name,diaChi,phongBan);
                        nhanVienList.add(nhanVien);
                    }
                }
                cursor.moveToNext();
            }
            cursor.close();
        }
        List<String> list= new ArrayList<>();
        for (NhanVien nhanVien:nhanVienList){
            list.add("id: "+nhanVien.getId()+" -ten: "+nhanVien.getName()+" -Dia chi: "+nhanVien.getAddress()+ "-Phong ban: "+nhanVien.getPhongBan().getName());

        }
        adapter= new ArrayAdapter<>(NhanVienActivity.this, android.R.layout.simple_list_item_1,list);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    private void lamrong(){
        ed_name.setText("");
        ed_id.setText("");
        ed_diaChi.setText("");
    }
    private void load(){
        Cursor cursor=getContentResolver().query(Uri.parse(URL_PHONGBAN),null,null, null,null);
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
            list.add(phongBan.getName());
        }
        adapter= new ArrayAdapter<>(NhanVienActivity.this, android.R.layout.simple_list_item_1,list);
        spinner.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    private  ContentValues setContentValuesForNhanVien(){
        ContentValues contentValues= new ContentValues();
        contentValues.put("id", Integer.parseInt(ed_id.getText().toString().trim()));
        contentValues.put("name", ed_name.getText().toString().trim());
        contentValues.put("address", ed_diaChi.getText().toString().trim());
        String temPhongBan=spinner.getSelectedItem().toString();

        for (PhongBan phongBan:phongBanList){
            if (phongBan.getName()==temPhongBan)
                id_PhongBan=phongBan.getId();
        }
        contentValues.put("id_PhongBan",id_PhongBan);
        return  contentValues;
    }

}