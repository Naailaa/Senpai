package com.example.projetgl;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CommentAdapter commentAdapter;
    private ArrayList<Comment> commentList;

    private EditText addComment;
    private TextView post;

    private DatabaseReference chatReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        addComment = findViewById(R.id.add_comment);
        post = findViewById(R.id.post);
        recyclerView = findViewById(R.id.recycler_view);
        commentList = new ArrayList<>();
        commentAdapter = new CommentAdapter(this);

        chatReference = FirebaseDatabase.getInstance().getReference().child("Chat");
        loadMessages();
        post.setOnClickListener(v->{
            String message = addComment.getText().toString().trim();
            if(!message.isEmpty()){
                String commentid = chatReference.push().getKey();
                HashMap<String,String> map = new HashMap<>();
                map.put("id",commentid);
                map.put("comment",message);
                map.put("publisherid",getIntent().getStringExtra("user_id"));
                map.put("publisher",getIntent().getStringExtra("user_name"));

                chatReference.child(commentid).setValue(map).addOnSuccessListener(unused ->{

                }).addOnFailureListener(e ->{
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                });

                addComment.setText("");
            }
        });
    }

    private void loadMessages(){
        chatReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                commentList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Comment comment = dataSnapshot.getValue(Comment.class);
                    commentList.add(comment);
                }
                commentAdapter.setmComments(commentList);
                recyclerView.setAdapter(commentAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(CommentActivity.this));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

