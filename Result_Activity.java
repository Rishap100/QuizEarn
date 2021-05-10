package com.quizearn.rishap.quiz20;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Result_Activity extends AppCompatActivity {

    TextView t1,t2,t3,t4;
    Button Btn;
    int a;

    String e,nam,p,score;
    Uri photo;

    private DatabaseReference mDatabaseReference, childref;
    FirebaseDatabase database;

    @Override
    public void onBackPressed(){
        Toast.makeText(Result_Activity.this,"Sorry can't go back",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_);

        t1=(TextView)findViewById(R.id.totques);
        t2=(TextView)findViewById(R.id.correct);
        t3=(TextView)findViewById(R.id.wrong);
        t4=(TextView)findViewById(R.id.nothing);
        Btn = (Button)findViewById(R.id.btn);
        database = FirebaseDatabase.getInstance();
        mDatabaseReference = database.getReference("Users");


        Intent i = getIntent();

        String ques = i.getStringExtra("Total");
        final String correct = i.getStringExtra("Correct");
        String wrong = i.getStringExtra("Incorrect");

        int t = Integer.parseInt(ques);
        t = t-1;

        int c = Integer.parseInt(correct);
        int w = Integer.parseInt(wrong);
        c = t-w;

        if(c<0)
        {
            c=0;
        }
        a = (3*c)-w;

        String tot = Integer.toString(t);
        String co = Integer.toString(c);
        String wr = Integer.toString(w);
        String sc = Integer.toString(a);
        t1.setText(tot);
        t2.setText(co);
        t3.setText(wr);
        t4.setText(sc);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null)
        {
            e = user.getEmail();
            photo = user.getPhotoUrl();
            nam = user.getDisplayName();
        }
        p = photo.toString();
        if(a<10 && a>=0)
        {
            score = "0"+Integer.toString(a);
        }
        else {
            score = Integer.toString(a);
        }
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!TextUtils.isEmpty(e)){
                    String id = mDatabaseReference.push().getKey();
                    User user = new User(id,e,score,p,nam);
                    mDatabaseReference.child(id).setValue(user);
                }
                startActivity(new Intent(Result_Activity.this, Reward_Activity.class));
                Result_Activity.this.finish();
            }
        });

    }
}
