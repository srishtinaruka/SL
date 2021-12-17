package com.example.schedulelawyer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class add_new_evidence extends AppCompatActivity {
    EditText name;
    EditText date;
    TextView upload;
    Button add;
    StorageReference storageReference;
    FirebaseFirestore db;
    final HashMap<Object , Object> data=new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_evidence);
        name=findViewById(R.id.editName);
        date=findViewById(R.id.editDate);
        upload=findViewById(R.id.upload);
        add=findViewById(R.id.addEvidence);
        db=FirebaseFirestore.getInstance();
        storageReference= FirebaseStorage.getInstance().getReference("evidence");
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.activity().setAllowRotation(true).setAllowFlipping(true)
                        .setAspectRatio(1 , 1).setFixAspectRatio(true)
                        .setRequestedSize(300 , 300).setCropShape(CropImageView.CropShape.OVAL)
                        .start(add_new_evidence.this);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.put("name" , name.getText().toString());
                data.put("date" , date.getText().toString());
                Log.i("rectify" , data+"");
                db.collection("cases").document(getIntent().getStringExtra("clientEmail"))
                        .collection("case").document(getIntent().getStringExtra("caseId"))
                                    .collection("evidences").add(data).addOnSuccessListener(
                        new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(@NonNull DocumentReference documentReference) {
                                    Intent intent=new Intent(add_new_evidence.this , all_evidence_page.class);
                                    startActivity(intent);
                            }
                        }
                );
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode== CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode==RESULT_OK){
            CropImage.ActivityResult result=CropImage.getActivityResult(intent);
            if(resultCode==CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                Toast.makeText(add_new_evidence.this , ""+result.getError() , Toast.LENGTH_SHORT).show();
            }else{
                Uri imageUri=result.getUri();
                String ename=getIntent().getStringExtra("clientEmail")+name.getText().toString();
                StorageReference imgRef=storageReference.child(ename);
                UploadTask uploadTask=imgRef.putFile(imageUri);
                uploadTask.continueWithTask(task -> {
                    if(!task.isSuccessful()){
                        throw task.getException();
                    }
                    return imgRef.getDownloadUrl();
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Uri> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(add_new_evidence.this , "evidence uploaded successfully" , Toast.LENGTH_SHORT).show();
                            upload.setText(name.getText().toString());
                            data.put("link" , task.getResult().toString());
                    }}
                });
            }
        }
    }
}