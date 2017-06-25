package com.example.user.u103b122;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class InsertActivity extends AppCompatActivity {

    static final String db_name = "friendDB";
    static final String tb_name = "friend";
    SQLiteDatabase db;
    EditText etName,etTel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        etName = (EditText) findViewById(R.id.name);
        etTel = (EditText) findViewById(R.id.tel);

        db = openOrCreateDatabase(db_name, Context.MODE_PRIVATE, null);
    }

    public void OnSave(View view) {
        ContentValues cv = new ContentValues(2);
        cv.put("name", etName.getText().toString());
        cv.put("tel", etTel.getText().toString());
        db.insert(tb_name, null, cv);
    }
}
