package com.example.fragment_tk3_kieu2;

import android.app.Fragment;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Fragment2 extends Fragment {

    TextView tensp,giasp,thongtinsp;
    ImageView img;
    Button btn_mua;
    View view;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_2, container, false);
        tensp=view.findViewById(R.id.tenSP);
        img=view.findViewById(R.id.img);
        giasp=view.findViewById(R.id.gia);
        thongtinsp=view.findViewById(R.id.chitietsanpham);
        btn_mua=view.findViewById(R.id.mua);
        // Inflate the layout for this fragment


        return view;
    }
    public void receiveDataFromFragment1(SanPham sanpham){
        img.setImageResource(sanpham.getHinh());
        tensp.setText(sanpham.getSanpham());
        giasp.setText(sanpham.getGia());
        thongtinsp.setText(sanpham.getThongtin());
    }



}