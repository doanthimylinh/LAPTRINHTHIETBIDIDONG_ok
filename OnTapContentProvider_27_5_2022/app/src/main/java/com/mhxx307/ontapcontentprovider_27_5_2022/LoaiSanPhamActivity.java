package com.mhxx307.ontapcontentprovider_27_5_2022;

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

import com.mhxx307.ontapcontentprovider_27_5_2022.entity.LoaiSanPham;

import java.util.ArrayList;
import java.util.List;

public class LoaiSanPhamActivity extends AppCompatActivity {

    EditText edtId, edtTenLoai;
    Button btnThem, btnXoa, btnTim, btnSua;
    GridView gridViewLoaiSP;

    String LOAI_SAN_PHAM_URL = "content://com.mhxx307.ontapcontentprovider_27_5_2022/loai_san_pham";

    List<LoaiSanPham> loaiSanPhamList = new ArrayList<>();
    ArrayAdapter<String> adapter;

    int loaiSanPhamId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_san_pham);

        mapping();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("id", edtId.getText().toString().trim());
                contentValues.put("ten_loai", edtTenLoai.getText().toString());
                Uri isInserted = getContentResolver().insert(Uri.parse(LOAI_SAN_PHAM_URL), contentValues);
                if (isInserted != null) {
                    loaiSanPhamList.clear();
                    rendering(LOAI_SAN_PHAM_URL);
                    Toast.makeText(LoaiSanPhamActivity.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoaiSanPhamActivity.this, "Them khong thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        gridViewLoaiSP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LoaiSanPham loaiSanPham = loaiSanPhamList.get(i);

                loaiSanPhamId = loaiSanPham.getId();

                edtId.setText(loaiSanPham.getId());
                edtTenLoai.setText(loaiSanPham.getTenLoaiSanPham());

            }
        });

        btnTim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String LOAI_SAN_PHAM_ID_URL = LOAI_SAN_PHAM_URL + "/" + edtId.getText().toString();
                rendering(LOAI_SAN_PHAM_ID_URL);
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String LOAI_SAN_PHAM_ID_URL = LOAI_SAN_PHAM_URL + "/" + edtId.getText().toString();
                getContentResolver().delete(Uri.parse(LOAI_SAN_PHAM_ID_URL), null, null);
                loaiSanPhamList.clear();
                rendering(LOAI_SAN_PHAM_URL);
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String LOAI_SAN_PHAM_ID_URL = LOAI_SAN_PHAM_URL + "/" + edtId.getText().toString();
                ContentValues contentValues = new ContentValues();
                contentValues.put("id", edtId.getText().toString().trim());
                contentValues.put("ten_loai", edtTenLoai.getText().toString());

                getContentResolver().update(Uri.parse(LOAI_SAN_PHAM_ID_URL), contentValues, null, null);

                loaiSanPhamList.clear();
                rendering(LOAI_SAN_PHAM_URL);
            }
        });
    }

    private void mapping() {
        edtId = findViewById(R.id.edtId);
        edtTenLoai = findViewById(R.id.edtTenLoaiSanPham);
        btnThem = findViewById(R.id.btnThem);
        btnSua = findViewById(R.id.btnSua);
        btnTim = findViewById(R.id.btnTim);
        btnXoa = findViewById(R.id.btnXoa);
        gridViewLoaiSP = findViewById(R.id.gridviewLoaiSanPham);

        rendering(LOAI_SAN_PHAM_URL);
    }

    private void rendering(String url) {
        Cursor cursor = getContentResolver().query(Uri.parse(url), null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(0);
                String tenLoai = cursor.getString(1);
                LoaiSanPham loaiSanPham = new LoaiSanPham(id, tenLoai);
                loaiSanPhamList.add(loaiSanPham);
            }
            cursor.close();
        }

        List<String> loaiSanPhamListString = new ArrayList<>();

        for (LoaiSanPham tempLoaiSanPham : loaiSanPhamList) {
            loaiSanPhamListString.add("id: " + tempLoaiSanPham.getId() + "\nten loai: " + tempLoaiSanPham.getTenLoaiSanPham());
        }

        adapter = new ArrayAdapter<>(LoaiSanPhamActivity.this, android.R.layout.simple_list_item_1, loaiSanPhamListString);
        gridViewLoaiSP.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}