package com.mhxx307.democontentprovider_appa_onetomany;

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
            case R.id.mnPhongBan:
                startActivity(new Intent(MainActivity.this, PhongBanActivity.class));
                break;
            case R.id.mnNhanVien:
                startActivity(new Intent(MainActivity.this, NhanVienActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}