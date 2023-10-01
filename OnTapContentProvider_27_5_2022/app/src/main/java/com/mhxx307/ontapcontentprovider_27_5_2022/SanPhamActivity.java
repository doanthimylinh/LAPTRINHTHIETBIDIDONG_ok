package com.mhxx307.ontapcontentprovider_27_5_2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.mhxx307.ontapcontentprovider_27_5_2022.entity.LoaiSanPham;
import com.mhxx307.ontapcontentprovider_27_5_2022.entity.SanPham;

import java.util.ArrayList;
import java.util.List;

public class SanPhamActivity extends AppCompatActivity {

    EditText edtId, edtTenSanPham, edtGia;
    RadioGroup radioGroupChatLuong;
    Spinner spinnerLoaiSanPham;
    Button btnThem, btnXoa, btnTim, btnSua;
    GridView gridViewSP;

    String LOAI_SAN_PHAM_URL = "content://com.mhxx307.ontapcontentprovider_27_5_2022/loai_san_pham";
    String SAN_PHAM_URL = "content://com.mhxx307.ontapcontentprovider_27_5_2022/san_pham";

    List<LoaiSanPham> loaiSanPhamList = new ArrayList<>();
    List<SanPham> sanPhamList = new ArrayList<>();

    ArrayAdapter<String> adapter;

    String chatLuong = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham);

        mapping();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("id", edtId.getText().toString());
                contentValues.put("ten_san_pham", edtTenSanPham.getText().toString());
                contentValues.put("gia", Double.parseDouble(edtGia.getText().toString()));
//                if (radioGroupChatLuong.getCheckedRadioButtonId() == R.id.radioCLT) {
//                    chatLuong = "chat luong thap";
//                } else if (radioGroupChatLuong.getCheckedRadioButtonId() == R.id.radioCLTB) {
//                    chatLuong = "chat luontrung binh";
//                } else if (radioGroupChatLuong.getCheckedRadioButtonId() == R.id.radioCLC) {
//                    chatLuong = "chat luong cao";
//                }

                radioGroupChatLuong.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        RadioButton radioButton = radioGroup.findViewById(i);
                        chatLuong = radioButton.getText().toString();
                    }
                });

                contentValues.put("chat_luong", chatLuong);

                for(LoaiSanPham loaiSanPham : loaiSanPhamList) {
                    if (spinnerLoaiSanPham.getSelectedItem().toString() == loaiSanPham.getTenLoaiSanPham()) {
                        contentValues.put("loai_san_pham_id", loaiSanPham.getId());
                    }
                }

                getContentResolver().insert(Uri.parse(SAN_PHAM_URL), contentValues);
            }
        });
    }

    private void mapping() {
        edtId = findViewById(R.id.edtId);
        edtTenSanPham = findViewById(R.id.edtTenSanPham);
        edtGia = findViewById(R.id.edtGiaSanPham);
        radioGroupChatLuong = findViewById(R.id.radioGroup_ChatLuong);
        spinnerLoaiSanPham = findViewById(R.id.spinnerLoaiSanPham);
        btnThem = findViewById(R.id.btnThem);
        btnSua = findViewById(R.id.btnSua);
        btnTim = findViewById(R.id.btnTim);
        btnXoa = findViewById(R.id.btnXoa);
        gridViewSP = findViewById(R.id.gridviewSanPham);

        loadDataSpinner();
        rendering(SAN_PHAM_URL);
    }

    private void loadDataSpinner() {
        Cursor cursor = getContentResolver().query(Uri.parse(LOAI_SAN_PHAM_URL), null, null, null, null);
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
            loaiSanPhamListString.add(tempLoaiSanPham.getTenLoaiSanPham());
        }

        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(SanPhamActivity.this, android.R.layout.simple_list_item_1, loaiSanPhamListString);
        spinnerLoaiSanPham.setAdapter(adapterSpinner);
        adapter.notifyDataSetChanged();
    }

    private void rendering(String url) {
        Cursor cursor = getContentResolver().query(Uri.parse(url), null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(0);
                String tenSanPham = cursor.getString(1);
                double gia = cursor.getDouble(2);
                String chatLuong = cursor.getString(3);
                int loaiSanPhamId = cursor.getInt(4);

                for (LoaiSanPham tempLoai : loaiSanPhamList) {
                    if (id == tempLoai.getId()) {
                        SanPham sanPham = new SanPham(id, tenSanPham, gia, chatLuong, tempLoai);
                        sanPhamList.add(sanPham);
                    }
                }
            }
            cursor.close();
        }

        List<String> sanPhamListString = new ArrayList<>();

        for (SanPham tempSP : sanPhamList) {
            sanPhamListString.add("id: " + tempSP.getId() + "\nTen sp: " + tempSP.getTenSanPham()
                    + "\nGia: " + tempSP.getGiaTien() + "\nChat luong: " + tempSP.getChatLuongSanPham()
                    + "\nLoai san pham : " + tempSP.getLoaiSanPham().getId() + " - " + tempSP.getLoaiSanPham().getTenLoaiSanPham());
        }


        adapter = new ArrayAdapter<>(SanPhamActivity.this, android.R.layout.simple_list_item_1, sanPhamListString);
        gridViewSP.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}