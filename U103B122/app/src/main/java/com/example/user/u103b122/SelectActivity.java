package com.example.user.u103b122;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class SelectActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    static final String db_name = "friendDB";
    static final String tb_name = "friend";
    SQLiteDatabase db;
    Cursor cur;
    ArrayList<String> aMemo = new ArrayList<String>();
    ListView lv;
    ArrayAdapter<String> aa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        lv = (ListView) findViewById(R.id.lv);
        aa = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,aMemo);
        lv.setAdapter(aa);
        lv.setOnItemClickListener(this);

        db = openOrCreateDatabase(db_name, Context.MODE_PRIVATE, null);

        cur = db.rawQuery("SELECT * FROM " + tb_name, null);

        if (cur.moveToFirst()) {
            String str;
            do {
                str = "姓名:" + cur.getString(1);
                str += "  電話:" + cur.getString(2) + "\n";
                aMemo.add(str);
            } while (cur.moveToNext());

            aa.notifyDataSetChanged();
        }

        db.close();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        cur.moveToPosition(position);
        String uri = "tel:" + cur.getString(cur.getColumnIndex("tel"));
        Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(it);
    }
}
