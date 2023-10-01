package com.example.frament_tk3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity  implements Fragment1.ISendDateListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.listSp,new Fragment1());
        fragmentTransaction.add(R.id.thongtinFragment2,new Fragment2());
        fragmentTransaction.commit();
    }

//    @Override
//    public void sendData(int hinh, String sanpham, String gia, String thongtin) {
//        Fragment2 fragment2= (Fragment2) getSupportFragmentManager().findFragmentById(R.id.thongtinFragment2);
//        fragment2.receiveDataFromFragment1(hinh,sanpham,gia,thongtin);
//    }
    public void sendDataHinh(int hinh){
        Fragment2 fragment2= (Fragment2) getSupportFragmentManager().findFragmentById(R.id.thongtinFragment2);
        fragment2.receiveDataHinhFromFragment1(hinh);
    }
    public void sendDataSanPham(String sanpham){
        Fragment2 fragment2= (Fragment2) getSupportFragmentManager().findFragmentById(R.id.thongtinFragment2);
        fragment2.receiveDataSanPhamFromFragment1(sanpham);
    }public void sendDataGia(String gia){
        Fragment2 fragment2= (Fragment2) getSupportFragmentManager().findFragmentById(R.id.thongtinFragment2);
        fragment2.receiveDataGiaFromFragment1(gia);
    }public void sendDataThongTin(String thongtin){
        Fragment2 fragment2= (Fragment2) getSupportFragmentManager().findFragmentById(R.id.thongtinFragment2);
        fragment2.receiveDataThongTinFromFragment1(thongtin);
    }


}