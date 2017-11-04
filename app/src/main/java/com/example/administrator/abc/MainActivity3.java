package com.example.administrator.abc;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;

/**
 * Created by Administrator on 04/11/2017.
 */

public class MainActivity3 extends AppCompatActivity {
    final String DATABASE_NAME = "MonNgonViet.sqlite";
    SQLiteDatabase database;
    ArrayList<ChiTietMonAn> list;
    AdapterCTMonAn adapter;
    TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ctmonan);
        TabHost host = (TabHost) findViewById(R.id.tab);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("TomTat");
        spec.setContent(R.id.Tab1);
        spec.setIndicator("Tóm Tắt");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("NguyenLieu");
        spec.setContent(R.id.Tab2);
        spec.setIndicator("Nguyên Liệu");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("CachLam");
        spec.setContent(R.id.Tab3);
        spec.setIndicator("Cách Làm");
        host.addTab(spec);


        addControls();
        readData();
    }
    private void addControls() {
        tabHost  = (TabHost) findViewById(R.id.tab);
        list = new ArrayList<>();
        adapter = new AdapterCTMonAn(this, list);
        tabHost.setTag(adapter);
    }

    private void readData(){
        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM ChiTietMonAn",null);
        list.clear();
        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            String TenMonAn = cursor.getString(0);
            String TomTat = cursor.getString(1);
            String NguyenLieu = cursor.getString(2);
            String CachLam = cursor.getString(3);
            byte[] AnhMA = cursor.getBlob(4);
            list.add(new ChiTietMonAn(TenMonAn,TomTat,NguyenLieu,CachLam,AnhMA ));
        }
        adapter.notifyDataSetChanged();
    }
}
