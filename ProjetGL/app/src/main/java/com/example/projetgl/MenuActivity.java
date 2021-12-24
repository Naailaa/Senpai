package com.example.projetgl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MenuActivity extends AppCompatActivity {

    Button demoBtn;

    TextView name;
    FirebaseUser user;
    DatabaseReference ref;

    LinearLayout l,l2,l3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //recuperer le nom
        name=findViewById(R.id.etudiant);
        user= FirebaseAuth.getInstance().getCurrentUser();
        ref= FirebaseDatabase.getInstance().getReference().child("Users");
        ref.child(user.getUid())
                .get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    name.setText(task.getResult().child("name").getValue(String.class));
                }else{
                    Toast.makeText(MenuActivity.this,"Erreur",Toast.LENGTH_SHORT).show();
                }
            }
        });

        demoBtn=(Button) findViewById(R.id.demoBtn);
        demoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MenuActivity.this,LoginActivity.class));

            }
        });

        l=(LinearLayout) findViewById(R.id.Visio);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this,DashboardActivity.class));
            }
        });

        l2=(LinearLayout) findViewById(R.id.Cours);
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this,CoursActivity.class));
            }
        });

        l3=(LinearLayout) findViewById(R.id.Chat);
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,CommentActivity.class);
                intent.putExtra("user_id",FirebaseAuth.getInstance().getCurrentUser().getUid());
                intent.putExtra("user_name",name.getText().toString().trim());
                startActivity(intent);
            }
        });
    }
}