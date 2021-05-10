package com.quizearn.rishap.quiz20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DemoResult extends AppCompatActivity {

    Button hbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_result);

        hbtn = (Button)findViewById(R.id.homebtn);
        hbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DemoResult.this,NavActivity.class));
            }
        });
    }
}
