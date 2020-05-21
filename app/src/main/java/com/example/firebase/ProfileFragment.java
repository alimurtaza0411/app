package com.example.firebase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    private TextView email;
    private EditText name,date,number;
    private Button save;
    private FirebaseDatabase database;
    private DatabaseReference mRef;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_profile, container, false);
        final DatabaseReference db =FirebaseDatabase.getInstance().getReference();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        email=view.findViewById(R.id.profile_email);
        name=view.findViewById(R.id.profile_name);
        date=view.findViewById(R.id.profile_date);
        save=view.findViewById(R.id.profile_save);
        number=view.findViewById(R.id.profile_number);
        database=FirebaseDatabase.getInstance();
        mRef=database.getReference();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        String userEmail = user.getEmail();
        email.setText(userEmail);
        final String userName = name.getText().toString();
        final String userDate = date.getText().toString();
        final String userNumber = number.getText().toString();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!userName.isEmpty() && !userDate.isEmpty() && !userNumber.isEmpty()) {
                    Toast.makeText(getActivity(), "Every field is required", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getActivity(), "Every field is required", Toast.LENGTH_LONG).show();
                }
            }
        });


        return view;
    }
}

