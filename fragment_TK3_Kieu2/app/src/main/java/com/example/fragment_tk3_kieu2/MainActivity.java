package com.example.fragment_tk3_kieu2;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity  implements SendDataSanPham{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    @Override
    public void sendDataSanPham(SanPham sanPham) {
        Fragment2 fragment2 = (Fragment2) getFragmentManager().findFragmentById(R.id.fragment_2);

        Configuration configuration = getResources().getConfiguration();


        if (fragment2 != null && configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            fragment2.receiveDataFromFragment1(sanPham);
        }else {

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("sanpham", sanPham);
            startActivity(intent);
        }
    }
}