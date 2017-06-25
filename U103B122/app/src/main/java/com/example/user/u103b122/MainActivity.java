package com.example.user.u103b122;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    static final String db_name = "friendDB";
    static final String tb_name = "friend";
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase(db_name, Context.MODE_PRIVATE, null);

        String createTable = "CREATE TABLE IF NOT EXISTS " + tb_name +
                " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR(32), " +
                "tel VARCHAR(16))";
        db.execSQL(createTable);

        Cursor cur = db.rawQuery("SELECT * FROM " + tb_name, null);

        if (cur.getCount() == 0) {
            addData("張三","0955555555");
            addData("葉修","0933223322");
        }

        db.close();
    }

    private void addData(String name, String tel) {
        ContentValues cv = new ContentValues(2);
        cv.put("name", name);
        cv.put("tel", tel);
        db.insert(tb_name, null, cv);
    }

    public void OnInsert(View view) {
        Intent it = new Intent(this,InsertActivity.class);
        startActivity(it);
    }

    public void OnSelect(View view) {
        Intent it = new Intent(this,SelectActivity.class);
        startActivity(it);
    }
}
