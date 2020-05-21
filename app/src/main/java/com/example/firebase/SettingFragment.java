package com.example.firebase;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {
    private TextView changePassword,deleteAccount;
    FirebaseAuth firebaseAuth;
    // Fragment fragment;

    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_setting, container, false);
        changePassword=view.findViewById(R.id.change_password);
        deleteAccount=view.findViewById(R.id.delete_account);
        firebaseAuth=FirebaseAuth.getInstance();
        final FirebaseUser user =firebaseAuth.getCurrentUser();
        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.delete();
                firebaseAuth.signOut();
                startActivity(new Intent(getActivity(),MainActivity.class));
            }
        });
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = user.getEmail();
                firebaseAuth.sendPasswordResetEmail(username);
                Toast.makeText(getActivity(), "Email as been sent to change password", Toast.LENGTH_LONG).show();

            }
        });
        return view;

    }
}
