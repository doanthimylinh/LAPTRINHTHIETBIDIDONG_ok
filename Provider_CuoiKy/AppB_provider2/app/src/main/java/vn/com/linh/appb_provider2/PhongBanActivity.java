package vn.com.linh.appb_provider2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PhongBanActivity extends AppCompatActivity {

    static final String URL="content://vn.com.linh.appa_provider2/PhongBans";
    EditText ed_id, ed_name;
    Button btn_them, btn_xoa, btn_sua, btn_select, btn_thoat;
    GridView gridView;
    List<PhongBan> phongBanList= new ArrayList<>();
    ArrayAdapter<String> adapter;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phong_ban);

        ed_id=findViewById(R.id.ed_id);
        ed_name=findViewById(R.id.ed_name);
        gridView=findViewById(R.id.gridVew_PhongBan);
        btn_them=findViewById(R.id.btn_Them);

        select();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PhongBan phongBan= phongBanList.get(i);
                id=phongBan.getId();
                ed_id.setText(""+id);
                ed_name.setText(phongBan.getName());
            }
        });
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues contentValues= new ContentValues();
                contentValues.put("id",Integer.parseInt(ed_id.getText().toString().trim()));
                contentValues.put("name", ed_name.getText().toString().trim());
                Uri phongBanUri=Uri.parse(URL);
                Uri isInsert=getContentResolver().insert(phongBanUri,contentValues);
                if (isInsert!=null){
                    phongBanList.clear();
                    select();
                    lamRong();
                    Toast.makeText(getApplicationContext(), "Luu thanh cong", Toast.LENGTH_SHORT).show();
                }else Toast.makeText(getApplicationContext(), "Luu khong thanh cong", Toast.LENGTH_SHORT).show();
            }
        });
        btn_select=findViewById(R.id.btn_Select);
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phongBanList.clear();
                String phongBanIdUrl = URL + "/" + ed_id.getText().toString();
                Uri phongBanIdUri = Uri.parse(phongBanIdUrl);
                Cursor cursor = getContentResolver().query(phongBanIdUri, null, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    while (!cursor.isAfterLast()) {
                        int id = cursor.getInt(0);
                        String tenPhongBan = cursor.getString(1);
                        PhongBan phongBan = new PhongBan(id, tenPhongBan);
                        phongBanList.add(phongBan);
                        cursor.moveToNext();
                    }
                    cursor.close();
                }

                List<String> phongBanListString = new ArrayList<>();
                for (PhongBan tempPhongBan : phongBanList) {
                    phongBanListString.add("id : " + tempPhongBan.getId() + " - ten: " + tempPhongBan.getName());
                }

                adapter = new ArrayAdapter<>(PhongBanActivity.this, android.R.layout.simple_list_item_1, phongBanListString);
                gridView.setAdapter(adapter);

                adapter.notifyDataSetChanged();
            }
        });
        btn_xoa=findViewById(R.id.btn_Xoa);
        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phongBanIdUrl= URL+"/"+id;
                Uri phongbanIdUri=Uri.parse(phongBanIdUrl);
                int isDelete= getContentResolver().delete(phongbanIdUri,null, null);
                if (isDelete>0){
                    Toast.makeText(getApplicationContext(), "xoa thanh cong", Toast.LENGTH_SHORT).show();
                    phongBanList.clear();
                    select();
                    lamRong();
                }
                else Toast.makeText(getApplicationContext(), "xoa that bai", Toast.LENGTH_SHORT).show();
            }
        });
        btn_sua=findViewById(R.id.btn_Sua);
        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phongBanIdUrl= URL+"/"+id;
                Uri phongbanIdUri=Uri.parse(phongBanIdUrl);
                PhongBan phongBan= new PhongBan(Integer.parseInt(ed_id.getText().toString()), ed_name.getText().toString());

                ContentValues contentValues= new ContentValues();
                contentValues.put("id",phongBan.getId());
                contentValues.put("name", phongBan.getName());

                int isUpdate= getContentResolver().update(phongbanIdUri,contentValues, null, null);
                if (isUpdate>0){
                    Toast.makeText(getApplicationContext(), "cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                    phongBanList.clear();
                    select();
                    lamRong();
                }
                else Toast.makeText(getApplicationContext(), "cap nhat that bai", Toast.LENGTH_SHORT).show();
            }
        });
        btn_thoat=findViewById(R.id.btn_thoat);
        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void select(){
        Uri phongBanUri=Uri.parse(URL);
        Cursor cursor =getContentResolver().query(phongBanUri,null,null,null,null);
        if (cursor!=null &&cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                int id=cursor.getInt(0);
                String name=cursor.getString(1);
                PhongBan phongBan= new PhongBan(id, name);
                phongBanList.add(phongBan);
                cursor.moveToNext();
            }
            cursor.close();

        }
        List<String> phongBanListString= new ArrayList<>();
        for (PhongBan tenphongBan:phongBanList){
            phongBanListString.add("id"+tenphongBan.getId()+"-ten:"+tenphongBan.getName());
        }
        adapter= new ArrayAdapter<>(PhongBanActivity.this, android.R.layout.simple_list_item_1, phongBanListString);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    private void lamRong(){
        ed_id.setText("");
        ed_name.setText("");
    }
}