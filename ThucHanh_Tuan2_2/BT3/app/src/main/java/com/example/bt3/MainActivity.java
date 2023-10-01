package com.example.bt3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

//public class MainActivity extends AppCompatActivity {
//    private ArrayList<String> myList;
//    private ArrayAdapter<String> adapter;
//    private Button btnNhap;
//    private ListView list;
//    private TextView tvNhap;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        tvNhap=findViewById(R.id.ed_ten);
//
//        myList= new ArrayList<String>();
//        list=findViewById(R.id.lv_danhsach);
//        adapter= new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,myList);
//        list.setAdapter(adapter);
//        btnNhap=findViewById(R.id.bt_nhap);
//        btnNhap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                myList.add(tvNhap.getText()+"");
//                adapter.notifyDataSetChanged();
//            }
//        });
//        TextView tv_ketqua=findViewById(R.id.tv_ketqua);
//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String msg="Position : "+i;
//                msg+="; value : "+adapterView.getItemAtPosition(i).toString();
//                tv_ketqua.setText(msg);
//            }
//        });
//    }
//}
public class MainActivity extends ListActivity {
    private ArrayList<String> myList;
    private ArrayAdapter<String> adapter;
    private Button btnNhap;
    private TextView tv_ketqua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        EditText ed_ten=findViewById(R.id.ed_ten);
        tv_ketqua=findViewById(R.id.tv_ketqua);

        myList= new ArrayList<String>();
        adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myList);
        setListAdapter(adapter);

        btnNhap=findViewById(R.id.bt_nhap);
        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myList.add(ed_ten.getText()+"");
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item= (String) getListAdapter().getItem(position);
        tv_ketqua.setText("position: "+position + "; value : "+ item +"");

    }
}
