package vn.com.linh.tk_ngoc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText id, hoten, sdt, diachi;
    Button thoat, them, xoa, sua, select, trang;
    GridView dsNhanVien;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id = findViewById(R.id.et_id);
        hoten = findViewById(R.id.et_hoten);
        sdt = findViewById(R.id.et_sdt);
        diachi = findViewById(R.id.et_dichi);
        thoat = findViewById(R.id.btn_thoat);
        them = findViewById(R.id.btn_them);
        xoa = findViewById(R.id.btn_xoa);
        sua = findViewById(R.id.btn_sua);
        trang = findViewById(R.id.btn_trang);
        select = findViewById(R.id.btn_select);
        dsNhanVien = findViewById(R.id.dsNhanVien);
        dbHelper = new DBHelper(this);
        select();


        trang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id.setText("");
                hoten.setText("");
                sdt.setText("");
                diachi.setText("");
                id.findFocus();
            }
        });
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                them();
            }
        });
        sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sua();
            }
        });
        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xoa();
            }
        });
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select();
            }
        });
        dsNhanVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Integer ma = Integer.parseInt(dsNhanVien.getItemAtPosition(i).toString());
                NhanVien nv = dbHelper.getNhanVientheoId(ma);
                id.setText(String.valueOf(nv.getId()));
                hoten.setText(nv.getHoten().toString());
                sdt.setText(String.valueOf(nv.getSdt()));
                diachi.setText(nv.getDiachi().toString());
                Toast.makeText(getApplicationContext(), nv.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void them() {
        if (id.getText().toString().equals("") ||
                hoten.getText().toString().equals("") ||
                sdt.getText().toString().equals("") ||
                diachi.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Thông tin chưa đầy đủ", Toast.LENGTH_SHORT).show();
        } else {
            int ma = Integer.parseInt(id.getText().toString());
            int sdt1 = Integer.parseInt(sdt.getText().toString());
            NhanVien nv = new NhanVien(ma,
                    hoten.getText().toString(),
                    sdt1,
                    diachi.getText().toString());
            if (dbHelper.insertNhanVien(nv) > 0) {
                Toast.makeText(getApplicationContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();
                select();
            } else {
                Toast.makeText(getApplicationContext(), "Lưu Thất bại", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void select() {
        ArrayList<NhanVien> list = dbHelper.getAllNhanVien();
        ArrayList<String> stringArrayList = new ArrayList<>();
        for (NhanVien nv : list) {
            stringArrayList.add(nv.getId() + "");
            stringArrayList.add(nv.getHoten());
            stringArrayList.add(nv.getSdt() + "");
            stringArrayList.add(nv.getDiachi());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stringArrayList);
        dsNhanVien.setAdapter(adapter);
    }

    public void xoa() {
        if (id.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Hãy chọn 1 nhân viên cần xóa", Toast.LENGTH_SHORT).show();
        } else {
            if (dbHelper.deleteNhanVien(Integer.parseInt(id.getText().toString())) > 0) {
                Toast.makeText(getApplicationContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                select();
            } else {
                Toast.makeText(getApplicationContext(), "Xóa thất bại", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void sua() {
        if (id.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Chọn 1 nhân viên cần sửa", Toast.LENGTH_SHORT).show();
        }else {
//            NhanVien nhanVien=new NhanVien(Integer.parseInt(id.getText().toString()),
//                    hoten.getText().toString(),
//                    Integer.parseInt(sdt.getText().toString()),
//                    diachi.getText().toString());
            NhanVien nhanVien=dbHelper.getNhanVientheoId(Integer.parseInt(id.getText().toString()));
            nhanVien.setHoten(hoten.getText().toString());
            nhanVien.setSdt(Integer.parseInt(sdt.getText().toString()));
            nhanVien.setDiachi(diachi.getText().toString());

            if(dbHelper.updateNhanVien(nhanVien)){
                Toast.makeText(getApplicationContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                select();
            }
            else {
                Toast.makeText(getApplicationContext(), "Sửa thất bại", Toast.LENGTH_SHORT).show();
            }
        }

    }
}