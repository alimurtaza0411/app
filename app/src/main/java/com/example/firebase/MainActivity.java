package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText username,password;
    Button login;
    TextView registerLink;
    ImageButton eye;
    FirebaseAuth firebaseAuth;
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=findViewById(R.id.login_username);
        password=findViewById(R.id.login_password);
        login=findViewById(R.id.login_login);
        registerLink=findViewById(R.id.login_register);
        eye=findViewById(R.id.login_eye);
        pb=findViewById(R.id.login_pb);
        firebaseAuth=FirebaseAuth.getInstance();
        pb.setVisibility(View.INVISIBLE);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uusername=username.getText().toString().trim();
                String upassword=password.getText().toString().trim();
                if(uusername.isEmpty()){
                    username.requestFocus();
                    username.setError("Please Enter");
                }
                else{
                    if(upassword.isEmpty()){
                        password.requestFocus();
                        password.setError("Please Enter");
                    }
                    else{
                        //pb.setVisibility(View.VISIBLE);
                        firebaseAuth.signInWithEmailAndPassword(uusername,upassword).addOnCompleteListener(MainActivity.this,new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    pb.setVisibility(View.VISIBLE);
                                    Toast.makeText(MainActivity.this,"Login successful",Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                                    startActivity(intent);
                                    // finish();
                                }
                                else{
                                    Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }) ;
                    }
                }
            }
        });
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransformationMethod tfm = password.getTransformationMethod();
                if(tfm!=null){
                    password.setTransformationMethod(null);
                }
                else{
                    password.setTransformationMethod(new PasswordTransformationMethod());
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user =firebaseAuth.getCurrentUser();
        if(user!=null){
            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }


}
