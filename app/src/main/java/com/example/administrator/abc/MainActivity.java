package com.example.administrator.abc;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    final String DATABASE_NAME = "MonNgonViet.sqlite";
    SQLiteDatabase database;
    public ListView Lv;
    ArrayList<DanhMuc> list;
   AdapterDanhMuc adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        readData();
        Lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("Name", list.get(i).getClass().toString());
                startActivity(intent);
            }
        });
    }
    private void addControls() {
        Lv = (ListView) findViewById(R.id.LvDanhSach);
        list = new ArrayList<>();
        adapter = new AdapterDanhMuc(this, list);
        Lv.setAdapter(adapter);
    }

    private void readData(){
        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM DanhMuc",null);
        list.clear();
        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            String TenDanhMuc = cursor.getString(0);
            list.add(new DanhMuc(TenDanhMuc));
        }
        adapter.notifyDataSetChanged();
    }


}
