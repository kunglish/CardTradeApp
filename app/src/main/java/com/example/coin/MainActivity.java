package com.example.coin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_login;
    EditText ed_name, ed_password;
    TextView tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_login = findViewById(R.id.btn_login);
        ed_name = findViewById(R.id.ed_name);
        ed_password = findViewById(R.id.ed_password);
        tv3 = findViewById(R.id.tv3);

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent1);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
            @Override
            public void onClick(View view) {
                UserModel userModel;
                String name = ed_name.getText().toString();
                String password = ed_password.getText().toString();
                String realpwd = dataBaseHelper.getpwd(name);
                if(realpwd.equals("Fail")){
                    Toast.makeText(MainActivity.this,"Undefined User!",Toast.LENGTH_LONG).show();
                } else {
                    //Toast.makeText(MainActivity.this, realpwd, Toast.LENGTH_LONG).show();
                    if (password.equals(realpwd)) {
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
                        Intent intent3 = new Intent(MainActivity.this, MpActivity.class);
                        startActivity(intent3);
                    } else {
                        Toast.makeText(MainActivity.this, "Wrong Password!", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }
}