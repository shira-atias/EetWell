package com.example.eatwell.viewmodel;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.eatwell.DialogProgress;
import com.example.eatwell.activity.MainActivity;
import com.example.eatwell.models.MyRecipe;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Timer;
import java.util.TimerTask;

public class MyRecipeViewModel extends ViewModel {
    public MutableLiveData<Boolean> isDone = new MutableLiveData<>();
    public MutableLiveData<Boolean> isImageDone = new MutableLiveData<>();
    private  String imageNameUri;

    public void addMyMeal(String title, String instructions, String ingredient){

        String stringEmail = MainActivity.stringEmail;
       //create a table with the name of the email
        DatabaseReference dbRef= FirebaseDatabase.getInstance().getReference(stringEmail);
        DatabaseReference newRowRef= dbRef.child(title);
        String id= newRowRef.push().getKey();
        MyRecipe myRecipe= new MyRecipe(title,instructions,ingredient,id,imageNameUri);
        newRowRef.setValue(myRecipe).addOnSuccessListener(command -> {
            isDone.postValue(true);
        });
    }

    public void saveImageToStorage(Uri uri, String nameImage, Context context, ImageView ivImage){
        StorageReference storageReference = FirebaseStorage.getInstance().getReference("uploads").child(nameImage);
        storageReference.putFile(uri).addOnSuccessListener(taskSnapshot -> {
         //   Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
            isImageDone.postValue(true);
        })
                .addOnFailureListener(e -> {
            Toast.makeText(context, "error"+ e.getMessage(), Toast.LENGTH_SHORT).show();
        })
                .addOnProgressListener(snapshot -> DialogProgress.getDialogProgress(true,context)).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                TimerTask progress =new TimerTask() {
                    @Override
                    public void run() {
                        DialogProgress.getDialogProgress(false,context);
                        loadImageFromStorage(nameImage,context,ivImage);
                    }
                };
                Timer timer = new Timer();
                timer.schedule(progress, 1000);
            }
        });

    }

    public void loadImageFromStorage(String ImageName, Context context, ImageView ivImage) {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference("uploads/").child(ImageName);
        storageReference.getDownloadUrl().addOnSuccessListener(uri -> {
            imageNameUri= uri.toString();
            Picasso.get().load(uri).into(ivImage);

        }).addOnFailureListener(e -> Toast.makeText(context, "error "+ e.getMessage(), Toast.LENGTH_SHORT).show());

    }

}
