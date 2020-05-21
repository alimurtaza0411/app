package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

//import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
//import java.util.Objects;
import java.util.Objects;
import java.util.UUID;

public class PhotoActivity extends AppCompatActivity {
    private ImageView photo;
    private Uri filepath,resltUri;
    private final int PICK_IMAGE_REQUEST=1;
    private StorageReference storageReference;
    TextView uploads;
    EditText photoName;
    DatabaseReference databaseReference;
    ProgressBar progressBar;
    StorageTask uploadtask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        {
            ActionBar actionBar;
            actionBar=getSupportActionBar();
            ColorDrawable colorDrawable=new ColorDrawable(Color.parseColor("#0F9D58"));
            assert actionBar != null;
            actionBar.setBackgroundDrawable(colorDrawable);
        }
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageReference= storage.getReference();
        //private static final int RESULT_OK = ;
        Button upload = findViewById(R.id.btnUpload);
        Button choose = findViewById(R.id.btnChoose);
        photo=findViewById(R.id.imageView4);
        uploads = findViewById(R.id.Uploads);
        photoName = findViewById(R.id.photoName);
        progressBar=findViewById(R.id.progressBar);
        databaseReference= FirebaseDatabase.getInstance().getReference("uploads");
        upload.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if(uploadtask!=null && uploadtask.isInProgress()){
                    Toast.makeText(PhotoActivity.this,"upload is in progress",Toast.LENGTH_LONG).show();
                }
                else {
                    uploadImage();
                }
            }
        });
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
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
        if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            filepath=data.getData();
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
                photo.setImageBitmap(bitmap);
                photo.setImageURI(filepath);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private String getFileExtension(Uri uri){
        ContentResolver CR =getContentResolver();
        MimeTypeMap mime =MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(CR.getType(uri));
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void uploadImage() {
        if (filepath != null) {
        }
    }
}
