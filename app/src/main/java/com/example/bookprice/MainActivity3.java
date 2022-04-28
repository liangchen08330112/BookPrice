package com.example.bookprice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener {
    private Button btnset,btnback;//定义控件
    private EditText etname,etpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        btnset=(Button) findViewById(R.id.btn_set);//控件初始化
        btnback=(Button) findViewById(R.id.btn_back);
        etname=(EditText) findViewById(R.id.et_usernameR);
        etpassword=(EditText) findViewById(R.id.et_passwordR);
        btnset.setOnClickListener(this);//点击监听-接口点击方式
        btnback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_set://注册功能
                String username,password;
                Intent intent=new Intent(this,MainActivity.class);
                username = etname.getText().toString().trim();
                password = etpassword.getText().toString().trim();
                if(username.equals("")||password.equals("")){
                    Toast.makeText(this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    intent.putExtra("username", username);
                    intent.putExtra("password", password);
                    startActivity(intent);
                    Toast.makeText(this, "创建成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            case R.id.btn_back://返回功能
                Intent intent2=new Intent(this,MainActivity.class);
                startActivity(intent2);
                break;
        }
    }
}