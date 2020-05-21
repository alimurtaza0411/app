package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, NavigationView.OnNavigationItemSelectedListener {
    FirebaseAuth firebaseAuth =FirebaseAuth.getInstance();
    TextView username;
    private DatabaseReference db= FirebaseDatabase.getInstance().getReference("update");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawer_layout);
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_bar);
        NavigationView navigationView =findViewById(R.id.navigation_view);
        username=findViewById(R.id.header_username);
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        navigationView.setNavigationItemSelectedListener(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        String value ="ali";
       // db.child("user").child("1").setValue(value);

        HomeFragment dhfragment = new HomeFragment();
        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, dhfragment).commit();
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id) {
            case R.id.nav_home:
                HomeFragment hfragment = new HomeFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, hfragment).addToBackStack(null).commit();
                Toast.makeText(HomeActivity.this, "Home", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_photo:
               // PhotoFragment pfragment = new PhotoFragment();
               // getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, pfragment).addToBackStack(null).commit();

                Toast.makeText(HomeActivity.this, "Photo", Toast.LENGTH_LONG).show();
                startActivity(new Intent(HomeActivity.this,PhotoActivity.class));
                break;
            case R.id.nav_exit:
            case R.id.nav_logout:
                //FirebaseUser user =firebaseAuth.getCurrentUser();
                firebaseAuth.signOut();
                Intent intent =new Intent(HomeActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                Toast.makeText(HomeActivity.this, "exit", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_setting:
                SettingFragment sfragment = new SettingFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, sfragment).addToBackStack(null).commit();
                Toast.makeText(HomeActivity.this, "setting", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_profile:
                ProfileFragment pffragment = new ProfileFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, pffragment).addToBackStack(null).commit();
                Toast.makeText(HomeActivity.this, "Profile", Toast.LENGTH_LONG).show();
                break;


        }
        return true;
    }

}