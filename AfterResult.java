package com.quizearn.rishap.quiz20;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AfterResult extends AppCompatActivity {

    TextView Score,res;
    Button Claim;
    String score,p,nam;
    Uri photo;

    String e;
    int s;

    private DatabaseReference mDatabaseReference, childref;
    FirebaseDatabase database;
    @Override
    public void onBackPressed(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_result);


        database = FirebaseDatabase.getInstance();
        mDatabaseReference = database.getReference("Users");

        //Home = (Button)findViewById(R.id.homebtn);

        Score = (TextView) findViewById(R.id.score);
        res = (TextView)findViewById(R.id.result);
        //LeaderBtn = (Button)findViewById(R.id.leaderBtn);
        Claim = (Button)findViewById(R.id.claimBtn);
        Claim.setEnabled(false);
        Intent intent = getIntent();

        score = intent.getStringExtra("Score");

        Score.setText(score);

        s = Integer.parseInt(score);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null)
        {
            e = user.getEmail();
            photo = user.getPhotoUrl();
            nam = user.getDisplayName();
        }
        p=photo.toString();


            res.setText("Well Played!");
            Claim.setEnabled(true);
            Claim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(!TextUtils.isEmpty(e)){
                        String id = mDatabaseReference.push().getKey();
                        User user = new User(id,e,score,p,nam);
                        mDatabaseReference.child(id).setValue(user);
                    }
                    startActivity(new Intent(AfterResult.this, Reward_Activity.class));
                    AfterResult.this.finish();
                }
            });

        }



}
