package com.example.ui_demo;

import static android.R.layout.simple_list_item_1;

import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.Manifest;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import gun0912.tedbottompicker.TedBottomPicker;


public class MainActivity extends AppCompatActivity {

    ArrayList<NhanVien> nhanViens=new ArrayList<>();
    String[] dv_List;
    String donvi;
    private static final int PICK_IMAGE = 222;
    private ImageView imgPhoto;
    int position = 0;
    String path;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText ed_ma=findViewById(R.id.ed_ma);
        EditText ed_ten=findViewById(R.id.ed_ten);
        ListView lv_NhanVien=findViewById(R.id.lv_danhsach);
        RadioGroup radioGroup=findViewById(R.id.radio);
        RadioButton rb_Nam=findViewById(R.id.nam);
        RadioButton rb_Nu=findViewById(R.id.nu);
        Button chonAnh=findViewById(R.id.chonAnh);

        imgPhoto=findViewById(R.id.image);

        Spinner sp_donvi=findViewById(R.id.donvi);
        dv_List= getResources().getStringArray(R.array.donvi_list);
        ArrayAdapter<String> adapter= new ArrayAdapter<>(this, simple_list_item_1,dv_List);
        sp_donvi.setAdapter(adapter);
        sharedPreferences = getSharedPreferences("DanhSachNhanVien", MODE_PRIVATE);


        sp_donvi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                donvi=dv_List[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Button bt_them=findViewById(R.id.bt_them);
        bt_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nhanViens.add(new NhanVien(path, Integer.parseInt(ed_ma.getText().toString()), ed_ten.getText().toString(),
                        ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString(), donvi));

                NhanVienAdapter nhanVienAdapter=new NhanVienAdapter(MainActivity.this, R.layout.custom_listview,nhanViens);

                lv_NhanVien.setAdapter(nhanVienAdapter);


            }
        });
        Button bt_xoa=findViewById(R.id.bt_xoa);
        bt_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = lv_NhanVien.getChildCount() - 1; i >= 0; i--) {
                    View v = lv_NhanVien.getChildAt(i);
                    CheckBox chk = (CheckBox) v.findViewById(R.id.chk_item);
                    if (chk.isChecked()) {
                        nhanViens.remove(i);
                    }
                }
                NhanVienAdapter nhanVienAdapter=new NhanVienAdapter(MainActivity.this, R.layout.custom_listview,nhanViens);

                lv_NhanVien.setAdapter(nhanVienAdapter);

            }
        });
        Button bt_thoat=findViewById(R.id.thoat);
        bt_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Thoát khỏi chương trình", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        Button bt_sua=findViewById(R.id.bt_sua);
        bt_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NhanVien nv = nhanViens.get(position);

                int maSo = Integer.parseInt(ed_ma.getText().toString());
                String hoTen = ed_ten.getText().toString();
                String gioiTinh = ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
//                Bitmap bitmap = ((BitmapDrawable)imgPhoto.getDrawable()).getBitmap();
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                byte[] byteArray = stream.toByteArray();

                nv.setMaSo(maSo);
                nv.setHoTen(hoTen);
                nv.setGioiTinh(gioiTinh);
                nv.setDonVi(donvi);
                nv.setHinh(path);

                NhanVienAdapter nhanVienAdapter=new NhanVienAdapter(MainActivity.this, R.layout.custom_listview,nhanViens);

                lv_NhanVien.setAdapter(nhanVienAdapter);
            }
        });
        Button bt_Luu=findViewById(R.id.Luu);
        bt_Luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(nhanViens);
                editor.putString("listNhanVien", json);
                editor.commit();
            }
        });
        lv_NhanVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                NhanVien nv= nhanViens.get(i);
                ed_ma.setText(nv.getMaSo()+"");
                ed_ten.setText(nv.getHoTen());
//                gioi tinh
                if(nv.getGioiTinh().equals("Nam"))
                    rb_Nam.setChecked(true);
                else rb_Nu.setChecked(true);
//                xu li don vi
                for (int j=0; j<dv_List.length; j++)
                    if(dv_List[j].equals(nv.getDonVi()))
                        sp_donvi.setSelection(j);
                String uri = nv.getHinh();

                imgPhoto.setImageURI(Uri.parse(uri));

            }
        });
        chonAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermission();
            }
        });
    }

    private void requestPermission(){

        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                openImagePicker();
            }

            @Override
            public void onPermissionDenied( List<String> deniedPermissions) {
                Toast.makeText(MainActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }


        };
        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                .check();
    }
    private  void openImagePicker(){
        TedBottomPicker.OnImageSelectedListener listener=new TedBottomPicker.OnImageSelectedListener() {
            @Override
            public void onImageSelected(Uri uri) {
                path=uri+"";
                try {
                    Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                    imgPhoto.setImageBitmap(bitmap);
                } catch (IOException e){
                    e.printStackTrace();
                }

            }
        };
        TedBottomPicker tedBottomPicker= new TedBottomPicker.Builder(MainActivity.this)
                .setOnImageSelectedListener(listener)
                .create();
        tedBottomPicker.show(getSupportFragmentManager());
    }


}