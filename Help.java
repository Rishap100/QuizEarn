package com.quizearn.rishap.quiz20;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Help extends AppCompatActivity {

    Button hbtn,btnh;
    String mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

       // mail = "rishapverma100@gmail.com";
        btnh = (Button)findViewById(R.id.btnhome);
        btnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Help.this, NavActivity.class));
            }
        });

        hbtn = (Button)findViewById(R.id.homebtn);
        hbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emialIntent = new Intent(Intent.ACTION_SEND);
                emialIntent.setData(Uri.parse("mailto:"));
                emialIntent.setType("text/plain");
                emialIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"quizearnrsv@gmail.com"});

                try {
                    startActivityForResult(Intent.createChooser(emialIntent,"Choose Gmail"),800);
                }
                catch(Exception e){
                    Toast.makeText(Help.this,e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 800) {
            Toast.makeText(Help.this,"Mail Sent! Thank you for the feedback",Toast.LENGTH_LONG).show();
        }
    }
}
