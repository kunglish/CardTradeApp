package com.example.coin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText rname, remail, rpassword, rpasswordr;
    Button btn_register;
    TextView tv5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn_register = findViewById(R.id.btn_register);
        rname = findViewById(R.id.ed_rname);
        remail = findViewById(R.id.ed_remail);
        rpassword = findViewById(R.id.ed_rpassword);
        rpasswordr = findViewById(R.id.ed_password_re);
        tv5 = findViewById(R.id.tv5);

        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent2);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserModel userModel;
                DataBaseHelper dataBaseHelper;
                String pwd = rpassword.getText().toString();
                String rpwd = rpasswordr.getText().toString();
                String username = rname.getText().toString();
                String email = remail.getText().toString();
                if(username==null){
                    Toast.makeText(RegisterActivity.this,"Empty Username!",Toast.LENGTH_LONG).show();
                }
                else if(email==null){
                    Toast.makeText(RegisterActivity.this,"Empty Email!",Toast.LENGTH_LONG).show();
                }
                else if(pwd==null){
                    Toast.makeText(RegisterActivity.this,"Empty Password!",Toast.LENGTH_LONG).show();
                }
                else if(email==null){
                    Toast.makeText(RegisterActivity.this,"Empty Email!",Toast.LENGTH_LONG).show();
                }
                if(pwd.equals(rpwd)){
                    try{
                        userModel = new UserModel(-1,rname.getText().toString(),remail.getText().toString(),rpassword.getText().toString());
                        //Toast.makeText(RegisterActivity.this, userModel.toString(), Toast.LENGTH_LONG).show();
                    }catch(Exception e) {
                        Toast.makeText(RegisterActivity.this, "Error Creating User", Toast.LENGTH_LONG).show();
                        userModel = new UserModel(-1, "error", "error", "error");
                    }
                    dataBaseHelper = new DataBaseHelper(RegisterActivity.this);
                    boolean success = dataBaseHelper.addOne(userModel);
                    Toast.makeText(RegisterActivity.this, "Success = " + success, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(RegisterActivity.this,"Different Password!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}