package com.example.menu_demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_xemdanhsach:
            Toast.makeText(this,"Xem danh sách",Toast.LENGTH_LONG).show();
            return true;
            case R.id.mnxemdsSV:
                Toast.makeText(this,"Xem danh sách sinh viên",Toast.LENGTH_LONG).show();
                return true;
            case R.id.mnxemdsLH:
                Toast.makeText(this,"Xem danh sách lớp học",Toast.LENGTH_LONG).show();
                return true;
            case R.id.suadanhsach:
                return true;
            case R.id.xoadanhsach:
                return true;
            case R.id.indanhsach:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}