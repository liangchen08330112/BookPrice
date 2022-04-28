package com.example.bookprice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{

    private EditText et_name,et_price,et_number;
    private Button add,delete,search,change;
    private TextView tv_show;
    MyHelper myHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add= findViewById(R.id.add);
        delete = findViewById(R.id.delete);
        search = findViewById(R.id.search);
        change = findViewById(R.id.change);
        et_name = findViewById(R.id.et_name);
        et_price = findViewById(R.id.et_price);
        et_number = findViewById(R.id.et_number);
        tv_show = findViewById(R.id.tv_show);

        add.setOnClickListener(MainActivity2.this);
        delete.setOnClickListener(MainActivity2.this);
        search.setOnClickListener(MainActivity2.this);
        change.setOnClickListener(MainActivity2.this);

        myHelper = new MyHelper(this);
    }
    @Override
    public void onClick(View v) {
        String name,price,number;
        SQLiteDatabase db;
        ContentValues values;
        switch (v.getId()){
            case R.id.add://点击添加数据，输入框中的数据暂存
                name = et_name.getText().toString();
                price = et_price.getText().toString();
                number = et_number.getText().toString();
                db = myHelper.getWritableDatabase();
                values = new ContentValues();
                values.put("name",name);
                values.put("price",price);
                values.put("number",number);
                db.insert("information",null,values);
                Toast.makeText(this,"添加成功",Toast.LENGTH_SHORT).show();
                db.close();
                break;
            case R.id.search:
                db = myHelper.getReadableDatabase();//可读的
                Cursor cursor = db.query("information",null,null,null,null,null,null);
                if (cursor.getCount() == 0){
                    tv_show.setText("");
                    Toast.makeText(this,"没有数据",Toast.LENGTH_SHORT).show();
                }
                else {
                    cursor.moveToFirst();
                    tv_show.setText("图书名称:  "+cursor.getString(1)
                            +"图书价格：    "+cursor.getString(2)
                            +"库存数量：    "+cursor.getString(3));
                }
                while (cursor.moveToNext()){
                    tv_show.append("图书名称:  "+cursor.getString(1)
                            +"图书价格：    "+cursor.getString(2)
                            +"库存数量：    "+cursor.getString(3));
                }
                cursor.close();
                db.close();
                break;
            case R.id.change:
                db = myHelper.getReadableDatabase();
                values = new ContentValues();
                values.put("price",price = et_price.getText().toString());
                values.put("number",number = et_number.getText().toString());
                db.update("information",values,"name = ?",
                        new String[]{et_name.getText().toString()});
                Toast.makeText(this,"信息已修改",Toast.LENGTH_SHORT).show();
                db.close();
                break;
            case R.id.delete:
                db = myHelper.getWritableDatabase();
                db.delete("information",null,null);
                tv_show.setText("  ");
                db.close();
                break;
        }
    }

    //数据库第一次创建时被调用
    private class MyHelper extends SQLiteOpenHelper {
        public MyHelper(Context context){super(context,"admin.db",null,1);}
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE information(_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "name VARCHAR(20),password VARCHAR(20))");
        }

        //进行修改
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //db.execSQL("ALTER TABLE person ADD phone VARCHAR(12)");
        }
    }
}