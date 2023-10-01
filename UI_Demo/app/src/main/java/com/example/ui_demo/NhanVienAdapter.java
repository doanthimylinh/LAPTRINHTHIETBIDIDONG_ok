package com.example.ui_demo;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class NhanVienAdapter extends ArrayAdapter<NhanVien> {

    Activity context = null;
    ArrayList<NhanVien> myArray = null;
    int layoutId;

    public NhanVienAdapter(Activity context, int textViewResourceId, ArrayList<NhanVien> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.layoutId = textViewResourceId;
        this.myArray = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutId, null);
        if (myArray.size() > 0 && position >= 0) {
            ImageView img = convertView.findViewById(R.id.imageView);
            TextView profile = convertView.findViewById(R.id.profile);

            NhanVien nhanVien = myArray.get(position);
            String uri = nhanVien.getHinh();
            img.setImageURI(Uri.parse(uri));

            profile.setText("  " + "Mã NV: " + nhanVien.getMaSo() + "\n  " + "Tên NV: " + nhanVien.getHoTen() + "\n  " + "Giới tính: " + nhanVien.getGioiTinh() + "\n  " +
                    "Đơn vị: " + nhanVien.getDonVi());
        }
        return convertView;

    }
}
