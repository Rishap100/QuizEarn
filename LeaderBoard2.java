package com.quizearn.rishap.quiz20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LeaderBoard2 extends AppCompatActivity {

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<NewProfile>list;
    MyAdapter adapter;
    Button homebtm;
    int max=-99;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board2);

        homebtm = (Button)findViewById(R.id.home);
        homebtm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LeaderBoard2.this, NavActivity.class));
            }
        });

        reference = FirebaseDatabase.getInstance().getReference("Users");
        recyclerView = (RecyclerView)findViewById(R.id.myRecycler);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<NewProfile>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    NewProfile p = dataSnapshot1.getValue(NewProfile.class);
                    list.add(p);
                }
                class listComparator implements Comparator<NewProfile>{
                    public int compare(NewProfile l,NewProfile r)
                    {
                        int ls = Integer.parseInt(l.getScore());
                        int rs = Integer.parseInt(r.getScore());
                        if(ls<=rs)
                            return -1;
                        return 1;
                        //return l.getScore().compareTo(r.getScore());
                    }
                }
                Collections.sort(list, new listComparator());
                Collections.reverse(list);
                adapter = new MyAdapter(LeaderBoard2.this,list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(LeaderBoard2.this, "Oops...Something went wrong", Toast.LENGTH_LONG).show();
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
