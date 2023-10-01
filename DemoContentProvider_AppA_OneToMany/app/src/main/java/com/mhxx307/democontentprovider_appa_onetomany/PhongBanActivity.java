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
import android.widget.Toast;

import com.mhxx307.democontentprovider_appa_onetomany.entity.PhongBan;

import java.util.ArrayList;
import java.util.List;

public class PhongBanActivity extends AppCompatActivity {
    EditText edtId, edtTenPhongBan;
    Button btnTim, btnThem, btnSua, btnXoa;
    GridView gridViewPhongBan;
    public static final String URL = "content://com.mhxx307.democontentprovider_appa_onetomany.provider/phong_ban";
    List<PhongBan> phongBanList = new ArrayList<>();
    ArrayAdapter<String> adapter;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phong_ban);

        mapping();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("id", Integer.parseInt(edtId.getText().toString().trim()));
                contentValues.put("name", edtTenPhongBan.getText().toString());
                Uri phongBanUri = Uri.parse(URL);
                Uri isInsert = getContentResolver().insert(phongBanUri, contentValues);
                if (isInsert != null) {
                    Toast.makeText(PhongBanActivity.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
                    phongBanList.clear();
                    rendering();
                } else {
                    Toast.makeText(PhongBanActivity.this, "Them that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnTim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phongBanList.clear();
                String phongBanIdUrl = URL + "/" + edtId.getText().toString();
                Uri phongBanIdUri = Uri.parse(phongBanIdUrl);
                Cursor cursor = getContentResolver().query(phongBanIdUri, null, null, null, null);
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

                List<String> phongBanListString = new ArrayList<>();
                for (PhongBan tempPhongBan : phongBanList) {
                    phongBanListString.add("id : " + tempPhongBan.getId() + " - ten: " + tempPhongBan.getTenPhongBan());
                }

                adapter = new ArrayAdapter<>(PhongBanActivity.this, android.R.layout.simple_list_item_1, phongBanListString);
                gridViewPhongBan.setAdapter(adapter);

                adapter.notifyDataSetChanged();
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phongBanIdUrl = URL + "/" + id;
                Uri phongBanIdUri = Uri.parse(phongBanIdUrl);
                int isDelete = getContentResolver().delete(phongBanIdUri, null, null);
                if (isDelete > 0) {
                    Toast.makeText(PhongBanActivity.this, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                    phongBanList.clear();
                    rendering();
                } else {
                    Toast.makeText(PhongBanActivity.this, "Xoa khong thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phongBanIdUrl = URL + "/" + id;
                Uri phongBanIdUri = Uri.parse(phongBanIdUrl);

                PhongBan phongban = new PhongBan(Integer.parseInt(edtId.getText().toString()), edtTenPhongBan.getText().toString());

                ContentValues contentValues = new ContentValues();
                contentValues.put("id", phongban.getId());
                contentValues.put("name", phongban.getTenPhongBan());

                int isUpdate = getContentResolver().update(phongBanIdUri, contentValues, null, null);
                if (isUpdate > 0) {
                    Toast.makeText(PhongBanActivity.this, "cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                    phongBanList.clear();
                    rendering();
                } else {
                    Toast.makeText(PhongBanActivity.this, "cap nhat khong thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        gridViewPhongBan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PhongBan phongBan = phongBanList.get(i);

                id = phongBan.getId();

                edtId.setText("" + id);
                edtTenPhongBan.setText(phongBan.getTenPhongBan());
            }
        });

    }

    private void rendering() {
        Uri phongBanUri = Uri.parse(URL);
        Cursor cursor = getContentResolver().query(phongBanUri, null, null, null, null);
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

        List<String> phongBanListString = new ArrayList<>();
        for (PhongBan tempPhongBan : phongBanList) {
            phongBanListString.add("id : " + tempPhongBan.getId() + " - ten: " + tempPhongBan.getTenPhongBan());
        }

        adapter = new ArrayAdapter<>(PhongBanActivity.this, android.R.layout.simple_list_item_1, phongBanListString);
        gridViewPhongBan.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }

    private void mapping() {
        edtId = findViewById(R.id.edtId);
        edtTenPhongBan = findViewById(R.id.edtTenPhongBan);
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
        btnTim = findViewById(R.id.btnTim);
        gridViewPhongBan = findViewById(R.id.gridviewPhongBan);

        rendering();
    }
}