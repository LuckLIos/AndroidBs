package com.example.lucklios.zuihoudebiyesheji;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button buttonLog;
    private EditText editTextName;
    private  EditText editTextPassWord;
    private  TextView textViewXie;
    private MyDataBaseHelper dbHelper;//--------------------------------------------------------
    private Button buttondengluo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       editTextName=(EditText)findViewById(R.id.logName);
       editTextPassWord=(EditText)findViewById(R.id.logPassword);
       buttondengluo=(Button)findViewById(R.id.denglu);
       buttonLog=(Button)findViewById(R.id.log);
       textViewXie=(TextView)findViewById(R.id.XieRu);
       dbHelper=new MyDataBaseHelper(this,"Persion.db",null,1);//-------------------------------------
       buttonLog.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(editTextName.getText().toString().equals("")||editTextPassWord.getText().toString().equals("")){
                   Toast.makeText(MainActivity.this,"输入不能为空！",Toast.LENGTH_SHORT).show();
               }else{
                   textViewXie.setText(editTextName.getText().toString());
                   SQLiteDatabase db=dbHelper.getWritableDatabase();//-------------------------------------------------------
                   ContentValues values=new ContentValues();
                   values.put("name",editTextName.getText().toString());
                   values.put("password",editTextPassWord.getText().toString());
                   db.insert("person",null,values);
                   editTextPassWord.setText("");
                   editTextName.setText("");
               }

           }
       });
       buttondengluo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               SQLiteDatabase db=dbHelper.getWritableDatabase();
               String name;
               String password;
               Boolean ok=false;
               Cursor cursor=db.query("person",null,null,null,null,null,null);
               if(cursor.moveToFirst()){
                   do{
                       name=cursor.getString(cursor.getColumnIndex("name"));
                       password=cursor.getString(cursor.getColumnIndex("password"));
                       if(editTextName.getText().toString().equals(name)&&editTextPassWord.getText().toString().equals(password)){
                           ok=true;
                       }

                   }while (cursor.moveToNext());
               }
               cursor.close();
               if(ok){
                   Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                   startActivity(intent);
               }else {
                   Toast.makeText(MainActivity.this,"请确认用户和密码是否正确",Toast.LENGTH_SHORT).show();
                   editTextName.setText("");
                   editTextPassWord.setText("");
               }
           }
       });
    }
}
