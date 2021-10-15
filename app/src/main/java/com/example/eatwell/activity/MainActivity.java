package com.example.eatwell.activity;

import android.content.Intent;
import android.os.Bundle;
import com.example.eatwell.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    public static String stringEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FirebaseAuth.getInstance().addAuthStateListener(firebaseAuth -> {
            FirebaseUser currentUser= firebaseAuth.getCurrentUser();
            if (currentUser == null){
                Intent intent= new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();

            }else {
                Toast.makeText(this, "hello, "+currentUser.getEmail(), Toast.LENGTH_SHORT).show();

              //the name email without "."
                String s = currentUser.getEmail();
                int point = s.indexOf(".");
                stringEmail= s.substring(0,point);

                System.out.println(stringEmail);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_out) {
            FirebaseAuth.getInstance().signOut();
            Intent intent= new Intent(this,LoginActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}