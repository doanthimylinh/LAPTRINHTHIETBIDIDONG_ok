package vn.com.linh.appb_provider2;

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

    EditText ed_id, ed_name, ed_address;
    Spinner spinner;
    Button btn_them, btn_xoa, btn_sua, btn_select, btn_thoat;
    GridView gridView;
    static final String URL_PHONGBAN="content://vn.com.linh.appa_provider2/PhongBans";
    static final String URL_NHANVIEN="content://vn.com.linh.appa_provider2/NhanViens";//phan biet chu hoa chu thuong
    List<NhanVien> nhanVienList= new ArrayList<>();
    List<PhongBan> phongBanList= new ArrayList<>();
    ArrayAdapter<String> adapter;
    int id_nhanVien;
    int phongBanId ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_vien);
        ed_id=findViewById(R.id.ed_id);
        ed_name=findViewById(R.id.ed_name);
        ed_address=findViewById(R.id.ed_diaChi);
        spinner =findViewById(R.id.spinner_PhongBan);
        gridView=findViewById(R.id.gridVew_NhanVien);
        btn_them=findViewById(R.id.btn_Them);
        Load();
        select();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NhanVien nhanVien= nhanVienList.get(i);
                id_nhanVien=nhanVien.getId();
                ed_id.setText(""+id_nhanVien);
                ed_name.setText(nhanVien.getName());
                ed_address.setText(nhanVien.getAddress());
            }
        });
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues contentValues= setConTentValuesFoNhanVien();
                Uri nhanVienUri=Uri.parse(URL_PHONGBAN);
                Uri isInsert=getContentResolver().insert(nhanVienUri,contentValues);
                if (isInsert!=null){
                    nhanVienList.clear();
                    Toast.makeText(getApplicationContext(), "Luu thanh cong", Toast.LENGTH_SHORT).show();
                    select();
                }else Toast.makeText(getApplicationContext(), "Luu khong thanh cong", Toast.LENGTH_SHORT).show();
            }
        });
        btn_xoa=findViewById(R.id.btn_Xoa);
        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nhanVienIdUrl= URL_NHANVIEN+"/"+id_nhanVien;
                Uri nhanVienUri=Uri.parse(nhanVienIdUrl);
                int isDelete= getContentResolver().delete(nhanVienUri,null, null);
                if (isDelete>0){
                    Toast.makeText(getApplicationContext(), "xoa thanh cong", Toast.LENGTH_SHORT).show();
                    nhanVienList.clear();
                    select();
                }
                else Toast.makeText(getApplicationContext(), "xoa that bai", Toast.LENGTH_SHORT).show();
            }
        });
        btn_select=findViewById(R.id.btn_Select);
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nhanVienList.clear();
                String nhanVienIdUrl = URL_NHANVIEN + "/" + ed_id.getText().toString();
                Uri nhanVienIdUri = Uri.parse(nhanVienIdUrl);
                Cursor cursor = getContentResolver().query(nhanVienIdUri, null, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    while (!cursor.isAfterLast()) {
                        int id=cursor.getInt(0);
                        String name=cursor.getString(1);
                        String address=cursor.getString(2);
                        int id_PhongBan=cursor.getInt(3);
                        for (PhongBan phongBan:phongBanList){
                            if (phongBan.getId()==id_PhongBan){
                                NhanVien nhanVien= new NhanVien(id, name, address, phongBan);
                                nhanVienList.add(nhanVien);
                            }
                        }
                        cursor.moveToNext();
                    }
                    cursor.close();
                }

                List<String> nhanVienListString = new ArrayList<>();
                for (NhanVien tempNhanVien : nhanVienList) {
                    nhanVienListString.add("id : " + tempNhanVien.getId() + " - ten: " +tempNhanVien.getName()+"-address"+ tempNhanVien.getAddress()+"-phong ban: "+tempNhanVien.getPhongBan().getName());
                }

                adapter = new ArrayAdapter<>(NhanVienActivity.this, android.R.layout.simple_list_item_1, nhanVienListString);
                gridView.setAdapter(adapter);

                adapter.notifyDataSetChanged();
            }
        });
        btn_sua=findViewById(R.id.btn_Sua);
        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nhanVienIdUrl= URL_NHANVIEN+"/"+id_nhanVien;
                ContentValues contentValues= setConTentValuesFoNhanVien();

                int isUpdate= getContentResolver().update(Uri.parse(nhanVienIdUrl),contentValues, null, null);
                if (isUpdate>0){
                    Toast.makeText(getApplicationContext(), "cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                    nhanVienList.clear();
                    select();
                }
                else Toast.makeText(getApplicationContext(), "cap nhat that bai", Toast.LENGTH_SHORT).show();
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
    private void Load(){
        Cursor cursor =getContentResolver().query(Uri.parse(URL_PHONGBAN),null,null,null,null);
        if (cursor!=null &&cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                int id=cursor.getInt(0);
                String name=cursor.getString(1);
                PhongBan phongBan= new PhongBan(id, name);
                phongBanList.add(phongBan);
                cursor.moveToNext();
            }
            cursor.close();
        }
        List<String > tenPhongBanList= new ArrayList<>();
        for (PhongBan phongBan: phongBanList){
            tenPhongBanList.add(phongBan.getName());
            Toast.makeText(getApplicationContext(), ""+phongBan.getName(), Toast.LENGTH_SHORT).show();

        }
        adapter= new ArrayAdapter<>(NhanVienActivity.this, android.R.layout.simple_list_item_1, tenPhongBanList);
        spinner.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    private void select(){
        Uri nhanVienUri=Uri.parse(URL_NHANVIEN);
        Cursor cursor =getContentResolver().query(nhanVienUri,null,null,null,null);
        if (cursor!=null &&cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                int id=cursor.getInt(0);
                String name=cursor.getString(1);
                String address=cursor.getString(2);
                int id_PhongBan=cursor.getInt(3);
                for (PhongBan phongBan:phongBanList){
                    if (phongBan.getId()==id_PhongBan){
                        NhanVien nhanVien= new NhanVien(id, name, address, phongBan);
                        nhanVienList.add(nhanVien);
                    }
                }
                cursor.moveToNext();
            }
            cursor.close();
        }
        List<String> nhanVienListString = new ArrayList<>();
        for (NhanVien tempNhanVien : nhanVienList) {
            nhanVienListString.add("id : " + tempNhanVien.getId() + " - ten: " +tempNhanVien.getName()+"-address"+ tempNhanVien.getAddress()+"-phong ban: "+tempNhanVien.getPhongBan().getName());
        }

        adapter = new ArrayAdapter<>(NhanVienActivity.this, android.R.layout.simple_list_item_1, nhanVienListString);
        gridView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }
    private ContentValues setConTentValuesFoNhanVien(){
        ContentValues contentValues= new ContentValues();
        contentValues.put("id",Integer.parseInt(ed_id.getText().toString().trim()));
        contentValues.put("name", ed_name.getText().toString().trim());
        contentValues.put("address", ed_address.getText().toString().trim());
        String tenPhongBan=spinner.getSelectedItem().toString();

        for (PhongBan phongBan:phongBanList){
            if (phongBan.getName()==tenPhongBan){
                phongBanId=phongBan.getId();
            }
        }
        contentValues.put("id_PhongBan", phongBanId);
        return contentValues;

    }
}