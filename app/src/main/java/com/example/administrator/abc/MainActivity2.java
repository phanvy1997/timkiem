package com.example.administrator.abc;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Administrator on 04/11/2017.
 */

public class MainActivity2 extends MainActivity{
    final String DATABASE_NAME = "MonNgonViet.sqlite";
    SQLiteDatabase database;
    public ListView Lv;
    ArrayList<MonAn> list;
    AdapterMonAn adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        readData();
        Lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                intent.putExtra("Name", list.get(i).getClass().toString());
                startActivity(intent);
            }
        });
    }
    private void addControls() {
        Lv = (ListView) findViewById(R.id.LvDanhSach);
        list = new ArrayList<>();
        adapter = new AdapterMonAn(this, list);
        Lv.setAdapter(adapter);
    }

    private void readData(){
        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM MonAn",null);
        list.clear();
        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            String TenMonAn = cursor.getString(0);
            String GioiThieu = cursor.getString(1);
            byte[] Anh = cursor.getBlob(2);
            list.add(new MonAn(TenMonAn,GioiThieu,Anh ));
        }
        adapter.notifyDataSetChanged();
    }
}


