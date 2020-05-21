package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    TextView loginLink;
    EditText name,username,password,con_password;
    Button register;
    private FirebaseAuth firebaseAuth;
    ProgressBar pb;
    private DatabaseReference db = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        loginLink=findViewById(R.id.register_login);
        name=findViewById(R.id.register_username);
        username=findViewById(R.id.register_email);
        password=findViewById(R.id.register_password);
        con_password=findViewById(R.id.register_con_password);
        register=findViewById(R.id.register_register);
        pb=findViewById(R.id.register_progressBar);
        firebaseAuth=FirebaseAuth.getInstance();
        final FirebaseUser user =firebaseAuth.getCurrentUser();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String uname=name.getText().toString().trim();
                final String email = username.getText().toString().trim();
                final String upassword=password.getText().toString().trim();
                final String ucon_password=con_password.getText().toString().trim();
                if(!uname.isEmpty() && !email.isEmpty() && !upassword.isEmpty() && !ucon_password.isEmpty()){
                    if(upassword.equals(ucon_password)) {
                        firebaseAuth.createUserWithEmailAndPassword(email, upassword).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    pb.setVisibility(View.VISIBLE);
                                    //db.push().setValue(user.getEmail());
                                    //db.child(user.getEmail()).setValue(uname);
                                    Toast.makeText(RegisterActivity.this,"sucess",Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(RegisterActivity.this,MainActivity.class));

                                }
                                else {
                                    Toast.makeText(RegisterActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    else{
                        con_password.setError("Does not match");
                        con_password.requestFocus();
                    }
                }
                else{
                    Toast.makeText(RegisterActivity.this,"Every Field is required",Toast.LENGTH_SHORT).show();
                }
            }
        });
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,MainActivity.class) );
            }
        });
    }

}
