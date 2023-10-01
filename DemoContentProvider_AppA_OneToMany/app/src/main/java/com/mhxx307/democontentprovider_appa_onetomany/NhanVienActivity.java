package com.mhxx307.democontentprovider_appa_onetomany;

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

import com.mhxx307.democontentprovider_appa_onetomany.entity.NhanVien;
import com.mhxx307.democontentprovider_appa_onetomany.entity.PhongBan;

import java.util.ArrayList;
import java.util.List;

public class NhanVienActivity extends AppCompatActivity {
    EditText edtId, edtTenNhanVien;
    Button btnTim, btnThem, btnSua, btnXoa;
    GridView gridViewNhanVien;
    Spinner spinnerPhongBan;
    public static final String URL_PHONGBAN = "content://com.mhxx307.democontentprovider_appa_onetomany.provider/phong_ban";
    public static final String URL_NHANVIEN= "content://com.mhxx307.democontentprovider_appa_onetomany.provider/nhan_vien";

    List<PhongBan> phongBanList = new ArrayList<>();
    List<NhanVien> nhanVienList = new ArrayList<>();

    ArrayAdapter<String> nhanvienAdapter;

    int phongBanId;
    int nhanVienId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_vien);

        mapping();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues contentValues = setContentValuesForNhanVien();
                Uri nhanVienUri = Uri.parse(URL_NHANVIEN);
                Uri isInsert = getContentResolver().insert(nhanVienUri, contentValues);
                if (isInsert != null) {
                    Toast.makeText(NhanVienActivity.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
                    nhanVienList.clear();
                    rendering();
                } else {
                    Toast.makeText(NhanVienActivity.this, "Them that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlNhanVienId = URL_NHANVIEN + "/" + nhanVienId;
                int isDelete = getContentResolver().delete(Uri.parse(urlNhanVienId), null, null);
                if (isDelete > 0) {
                    Toast.makeText(NhanVienActivity.this, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                    nhanVienList.clear();
                    rendering();
                } else {
                    Toast.makeText(NhanVienActivity.this, "Xoa khong thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlNhanVienId = URL_NHANVIEN + "/" + nhanVienId;

                ContentValues values = setContentValuesForNhanVien();

                int isUpdate = getContentResolver().update(Uri.parse(urlNhanVienId), values, null, null);
                if (isUpdate > 0) {
                    Toast.makeText(NhanVienActivity.this, "Cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                    nhanVienList.clear();
                    rendering();
                } else {
                    Toast.makeText(NhanVienActivity.this, "Cap nhat khong thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        gridViewNhanVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NhanVien nhanVien = nhanVienList.get(i);

                nhanVienId = nhanVien.getId();

                edtId.setText(nhanVienId + "");
                edtTenNhanVien.setText(nhanVien.getTenNhanVien());

            }
        });
    }

    private void loadSpinner() {
        Cursor cursor = getContentResolver().query(Uri.parse(URL_PHONGBAN), null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(0);
                String tenPhongBan = cursor.getString(1);
                PhongBan phongBan = new PhongBan(id, tenPhongBan);
                phongBanList.add(phongBan);
                cursor.moveToNext();
            }
            cursor.close();
        }

        List<String> tenPhongBanList = new ArrayList<>();
        for (PhongBan tempPhongBan : phongBanList) {
            tenPhongBanList.add(tempPhongBan.getTenPhongBan());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tenPhongBanList);
        spinnerPhongBan.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void mapping() {
        edtId = findViewById(R.id.edtId);
        edtTenNhanVien = findViewById(R.id.edtTenNhanVien);
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
        btnTim = findViewById(R.id.btnTim);
        gridViewNhanVien = findViewById(R.id.gridviewNhanVien);
        spinnerPhongBan = findViewById(R.id.spinnerPhongBan);

        loadSpinner();

        rendering();

    }

    private void rendering() {
        Uri nhanVienUri = Uri.parse(URL_NHANVIEN);
        Cursor cursor = getContentResolver().query(nhanVienUri, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {

            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    int id = cursor.getInt(0);
                    String tenNhanVien = cursor.getString(1);
                    Toast.makeText(NhanVienActivity.this, "" + tenNhanVien, Toast.LENGTH_SHORT).show();
                    int phongBan_Id = cursor.getInt(2);

                    Toast.makeText(NhanVienActivity.this, "" + phongBan_Id, Toast.LENGTH_SHORT).show();

                    for(PhongBan phongBan : phongBanList) {
                        if (phongBan.getId() == phongBan_Id) {
                            NhanVien nhanVien = new NhanVien(id, tenNhanVien, phongBan);
                            nhanVienList.add(nhanVien);
                        }
                    }

                    cursor.moveToNext();
                }
            }

            cursor.close();
        }

        List<String> nhanVienListString = new ArrayList<>();
        for (NhanVien tempNhanVien : nhanVienList) {
            nhanVienListString.add("id : " + tempNhanVien.getId() + " - ten: " + tempNhanVien.getTenNhanVien() + " - phong ban: " + tempNhanVien.getPhongBan().getTenPhongBan());
        }

        nhanvienAdapter = new ArrayAdapter<>(NhanVienActivity.this, android.R.layout.simple_list_item_1, nhanVienListString);
        gridViewNhanVien.setAdapter(nhanvienAdapter);

        nhanvienAdapter.notifyDataSetChanged();
    }

    private ContentValues setContentValuesForNhanVien() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Integer.parseInt(edtId.getText().toString().trim()));
        contentValues.put("name", edtTenNhanVien.getText().toString());

        String tenPhongBan = spinnerPhongBan.getSelectedItem().toString();

        for(PhongBan phongBan : phongBanList) {
            if (phongBan.getTenPhongBan() == tenPhongBan) {
                phongBanId = phongBan.getId();
            }
        }

        contentValues.put("phong_ban_id", phongBanId);

        return contentValues;
    }
}