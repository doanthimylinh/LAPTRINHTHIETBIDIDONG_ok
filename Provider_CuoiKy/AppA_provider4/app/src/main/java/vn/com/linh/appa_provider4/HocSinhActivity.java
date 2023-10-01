package vn.com.linh.appa_provider4;

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

public class HocSinhActivity extends AppCompatActivity {

    EditText ed_id, ed_name;
    Button btn_theem, btn_xoa, btn_sua, btn_select, btn_thoat;
    GridView gridView;
    Spinner spinner;

    static final String URL_LOPHOC="content://vn.com.linh.appa_provider4/LopHocs";
    static final String URL_SINHVIEN="content://vn.com.linh.appa_provider4/SinhViens";
    List<LopHoc> lopHocList=new ArrayList<>();
    List<SinhVien> sinhVienList=new ArrayList<>();
    ArrayAdapter<String> adapter;
    int id_LopHoc;
    int id_SinhVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoc_sinh);

        ed_id=findViewById(R.id.ed_id);
        ed_name=findViewById(R.id.ed_ten);
        spinner=findViewById(R.id.spinner_LopHoc);
        gridView=findViewById(R.id.gridViewSinhVien);
        btn_theem=findViewById(R.id.btn_Them);

        select();
        load();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SinhVien sinhVien=sinhVienList.get(i);
                id_SinhVien=sinhVien.getId();
                ed_id.setText(""+id_SinhVien);
                ed_name.setText(sinhVien.getTenHS());
            }
        });
        btn_theem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues contentValues= setContentValueForHocSinh();
                Uri sinhVienUri=Uri.parse(URL_SINHVIEN);
                Uri isInsert=getContentResolver().insert(sinhVienUri,contentValues);
                if (isInsert!=null){
                    sinhVienList.clear();
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
                String sinhVienIdUrl=URL_SINHVIEN+"/"+id_SinhVien;
                ContentValues contentValues= setContentValueForHocSinh();

                int isUpdate=getContentResolver().update(Uri.parse(sinhVienIdUrl),contentValues, null, null);
                if (isUpdate>0){
                    sinhVienList.clear();
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
                String sinhVienIdUrl=URL_SINHVIEN+"/"+id_SinhVien;
                Uri sinhVienUri=Uri.parse(sinhVienIdUrl);

                int isDelete=getContentResolver().delete(sinhVienUri, null, null);
                if (isDelete>0){
                    sinhVienList.clear();
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
                sinhVienList.clear();
                String sinhVienIdUrl=URL_SINHVIEN+"/"+ed_id.getText().toString();
                Uri sinhVienIdUri= Uri.parse(sinhVienIdUrl);
                Cursor cursor=getContentResolver().query(sinhVienIdUri, null, null, null, null);
                if (cursor!=null && cursor.moveToFirst()){
                    while (!cursor.isAfterLast()){
                        int id=cursor.getInt(0);
                        String name=cursor.getString(1);
                        int id_lopHoc=cursor.getInt(2);
                        for (LopHoc lopHoc:lopHocList){
                            if (lopHoc.getId()==id_lopHoc){
                                SinhVien sinhVien= new SinhVien(id,name,lopHoc);
                                sinhVienList.add(sinhVien);
                            }
                        }

                        cursor.moveToNext();
                    }
                    cursor.close();
                }
                List<String> list=new ArrayList<>();
                for (SinhVien sinhVien:sinhVienList){
                    list.add("id: "+sinhVien.getId()+"_ten: "+sinhVien.getTenHS()+"Lop: "+sinhVien.getLopHoc().getTenLop());

                }
                adapter= new ArrayAdapter<>(HocSinhActivity.this, android.R.layout.simple_list_item_1,list);
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
        Uri sinhVienUri= Uri.parse(URL_SINHVIEN);
        Cursor cursor=getContentResolver().query(sinhVienUri, null, null, null, null);
        if (cursor!=null && cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                int id=cursor.getInt(0);
                String name=cursor.getString(1);
                int id_lopHoc=cursor.getInt(2);
                for (LopHoc lopHoc:lopHocList){
                    if (lopHoc.getId()==id_lopHoc){
                        SinhVien sinhVien= new SinhVien(id,name,lopHoc);
                        sinhVienList.add(sinhVien);
                    }
                }

                cursor.moveToNext();
            }
            cursor.close();
        }
        List<String> list=new ArrayList<>();
        for (SinhVien sinhVien:sinhVienList){
            list.add("id: "+sinhVien.getId()+"_ten: "+sinhVien.getTenHS()+"Lop: "+sinhVien.getLopHoc().getTenLop());

        }
        adapter= new ArrayAdapter<>(HocSinhActivity.this, android.R.layout.simple_list_item_1,list);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    private void lamRong(){
        ed_name.setText("");
        ed_id.setText("");
    }
    private void load(){
        Cursor cursor=getContentResolver().query(Uri.parse(URL_LOPHOC), null, null, null, null);

        if(cursor!=null && cursor.moveToFirst()){
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
            list.add(lopHoc.getTenLop());

        }
        adapter= new ArrayAdapter<>(HocSinhActivity.this, android.R.layout.simple_list_item_1,list);
        spinner.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    private ContentValues setContentValueForHocSinh(){
        ContentValues contentValues= new ContentValues();

        contentValues.put("id", Integer.parseInt(ed_id.getText().toString()));
        contentValues.put("name", ed_name.getText().toString());
        String tenLopHoc=spinner.getSelectedItem().toString();
        for (LopHoc lopHoc:lopHocList){
            if (lopHoc.getTenLop()==tenLopHoc)
                id_LopHoc=lopHoc.getId();
        }
        contentValues.put("id_LopHoc", id_LopHoc);
        return  contentValues;

    }
}