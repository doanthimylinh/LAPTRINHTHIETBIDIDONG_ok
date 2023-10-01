package vn.com.linh.appa_provider_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_Class:
                Intent intent=new Intent(MainActivity.this, ClassActivity.class);
                startActivity(intent);
                return  true;
            case R.id.item_student:
                Intent intent2=new Intent(MainActivity.this, StudentActivity.class);
                startActivity(intent2);
                return  true;
            default:
                finish();
                return  super.onOptionsItemSelected(item);
        }
    }
}