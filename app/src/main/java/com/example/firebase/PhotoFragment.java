package com.example.firebase;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

//import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
//import android.widget.EditText;
import android.widget.ImageView;
//import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

//import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class PhotoFragment extends Fragment {
    private ImageView photo;
    private Uri filepath;
    private final int PICK_IMAGE_REQUEST=22;
    private StorageReference storageReference;
   // EditText photoName;
    //TextView uploads;
    private Context context = Objects.requireNonNull(getActivity()).getApplicationContext();

    public PhotoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_photo, container, false);
        {
            ActionBar actionBar;
            actionBar=((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
            ColorDrawable colorDrawable=new ColorDrawable(Color.parseColor("#0F9D58"));
            assert actionBar != null;
            actionBar.setBackgroundDrawable(colorDrawable);
        }
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageReference= storage.getReference();
        //private static final int RESULT_OK = ;
        Button upload = view.findViewById(R.id.btnUpload);
        Button choose = view.findViewById(R.id.btnChoose);
        photo=view.findViewById(R.id.imageView4);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        return view;

    }
    private void chooseImage(){
        Intent intent =new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
            Intent.createChooser(intent,"Select Image from here"),PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST && resultCode== Activity.RESULT_OK && data!=null && data.getData()!=null){
            filepath=data.getData();
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(context.getContentResolver(),filepath);
                photo.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void uploadImage(){
        if(filepath!=null){
            StorageReference ref =storageReference.child("image/*"+ UUID.randomUUID().toString());
            ref.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getActivity(),"uploaded",Toast.LENGTH_LONG).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(),"not uploaded",Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
