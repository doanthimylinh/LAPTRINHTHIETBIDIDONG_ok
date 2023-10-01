package com.mhxx307.ontapcontentprovider_27_5_2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnLoaiSanPham:
                startActivity(new Intent(MainActivity.this, LoaiSanPhamActivity.class));
                break;
            case R.id.mnSanPham:
                startActivity(new Intent(MainActivity.this, SanPhamActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}