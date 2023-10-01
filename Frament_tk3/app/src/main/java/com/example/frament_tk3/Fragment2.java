package com.example.frament_tk3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment2 extends Fragment {

    private TextView tensp,giasp,thongtinsp;
    private ImageView img;
    private Button btn_mua;
    private  View view;
    public Fragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_2, container, false);
        TextView tensp=view.findViewById(R.id.tenSP);
        ImageView img=view.findViewById(R.id.img);
        TextView giasp=view.findViewById(R.id.gia);
        TextView thongtinsp=view.findViewById(R.id.chitietsanpham);
        Button btn_mua=view.findViewById(R.id.mua);
        // Inflate the layout for this fragment


        return view;
    }
//    public void receiveDataFromFragment1(int hinh, String sanpham,String gia, String thongtin){
//        img.setImageResource(hinh);
//        tensp.setText(sanpham);
//        giasp.setText(gia);
//        thongtinsp.setText(thongtin);
//    }

    public void receiveDataHinhFromFragment1(int hinh){
        img.setImageResource(hinh);

    }
    public void receiveDataSanPhamFromFragment1(String sanpham){
        tensp.setText(sanpham);

    }public void receiveDataGiaFromFragment1(String gia){
        giasp.setText(gia);

    }public void receiveDataThongTinFromFragment1(String thongtin){
        thongtinsp.setText(thongtin);

    }


}