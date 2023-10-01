package vn.com.linh.appa_provider4;

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
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.lop:
                Intent intent= new Intent(MainActivity.this, LopHocActivity.class);
                startActivity(intent);
                break;
            case R.id.Sinhvien:
                Intent intent1= new Intent(MainActivity.this,HocSinhActivity.class);
                startActivity(intent1);
                break;
            default:
                finish();        }
        return super.onOptionsItemSelected(item);
    }
}