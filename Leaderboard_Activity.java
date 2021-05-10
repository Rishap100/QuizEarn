package com.quizearn.rishap.quiz20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class Leaderboard_Activity extends AppCompatActivity {

    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String>list;
    ArrayAdapter<String>adapter;
    User2 user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard_);

        user = new User2();
        listView = (ListView)findViewById(R.id.listview);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Users");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.user_list, R.id.info, list);
        Query query = ref.orderByChild("score").limitToFirst(100);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    user = ds.getValue(User2.class);
                    list.add(user.getEmail().toString()+"     Score  "+user.getScore().toString());
                    //list.add("Score   "+user.getScore().toString());
                }
                Collections.sort(list);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
