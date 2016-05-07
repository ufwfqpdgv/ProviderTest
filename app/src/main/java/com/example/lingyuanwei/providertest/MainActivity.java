package com.example.lingyuanwei.providertest;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_addToBook;
    private Button btn_quaryFromBook;
    private Button btn_updateBook;
    private Button btn_deleteBook;
    private String newId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn_addToBook = (Button) findViewById(R.id.btn_addToBook);
        btn_quaryFromBook = (Button) findViewById(R.id.btn_quaryFromBook);
        btn_updateBook = (Button) findViewById(R.id.btn_updateBook);
        btn_deleteBook = (Button) findViewById(R.id.btn_deleteBook);

        btn_addToBook.setOnClickListener(this);
        btn_quaryFromBook.setOnClickListener(this);
        btn_updateBook.setOnClickListener(this);
        btn_deleteBook.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_addToBook:
                Uri uriAdd = Uri.parse("content://com.example.lingyuanwei.sqlitetest.provider/book");
                ContentValues values = new ContentValues();
                values.put("name", "outName");
                values.put("author", "outAuthor");
                values.put("pages", "132");
                values.put("price", "55.2");
                Uri uriNew = getContentResolver().insert(uriAdd, values);
                newId = uriNew.getPathSegments().get(1);
                break;
            case R.id.btn_quaryFromBook:
                Uri uriQuary = Uri.parse("content://com.example.lingyuanwei.sqlitetest.provider/book");
                Cursor cursor = getContentResolver().query(uriQuary, null, null, null, null);
                Log.d("lyw", "name author pages price");
                while (cursor.moveToNext()) {
                    Log.d("lyw", cursor.getString(cursor.getColumnIndex("name"))
                            + " " + cursor.getString(cursor.getColumnIndex("author"))
                            + " " + cursor.getString(cursor.getColumnIndex("pages"))
                            + " " + cursor.getString(cursor.getColumnIndex("price")));
                }
                cursor.close();
                break;
            case R.id.btn_updateBook:
                Uri uriUpdate = Uri.parse("content://com.example.lingyuanwei.sqlitetest.provider/book/" + newId);
                ContentValues valuesUpdate = new ContentValues();
                valuesUpdate.put("name", "outName2222");
                getContentResolver().update(uriUpdate, valuesUpdate, null, null);
                break;
            case R.id.btn_deleteBook:
                Uri uriDelete = Uri.parse("content://com.example.lingyuanwei.sqlitetest.provider/book/" + newId);
                getContentResolver().delete(uriDelete, null, null);
                break;
        }
    }
}
