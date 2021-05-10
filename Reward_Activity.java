package com.quizearn.rishap.quiz20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Reward_Activity extends AppCompatActivity {

    EditText Name, Number;
    Button Submit;

    String name,number,email;

    FirebaseDatabase database;
    private DatabaseReference ref;
    @Override
    public void onBackPressed(){
        Toast.makeText(Reward_Activity.this,"Sorry can't go back",Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward_);

        Name = (EditText)findViewById(R.id.nam);
        Number = (EditText)findViewById(R.id.numb);
        Submit = (Button)findViewById(R.id.submiBtn);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null)
        {
            email = user.getEmail();
        }
        //user3 = new User3();


        //User3 user3 = new User3(name,number);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
                //name = Name.getText().toString();
                number = Number.getText().toString();

                if(number.length()==10) {
                    String id = ref.push().getKey();
                    User3 user3 = new User3(email, number);
                    ref.child("Winners").child(id).setValue(user3);
                    startActivity(new Intent(Reward_Activity.this, LeaderBoard2.class));
                    Reward_Activity.this.finish();
                }
                else
                {
                    Toast.makeText(Reward_Activity.this,"Please enter your 10 digit number",Toast.LENGTH_LONG).show();
                }
            }
        });


    }

}
