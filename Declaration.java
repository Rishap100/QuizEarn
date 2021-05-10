package com.quizearn.rishap.quiz20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Declaration extends AppCompatActivity {

    TextView Rules1, Rules2, Rules3;
    Button Ready;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declaration);

        Rules1 = (TextView)findViewById(R.id.rules1);
        Rules2 = (TextView)findViewById(R.id.rules2);
        Rules3 = (TextView)findViewById(R.id.rules3);
        Ready = (Button)findViewById(R.id.readybtnn);

        Rules1.setText("1. The entry fees of the live quiz is 2 rupees.");
        Rules2.setText("2. A part of your fees submitted will be donated to PMCare for the cause of COvid-19.");
        Rules3.setText("3. Most importantly have fun, learn and earn");
        Ready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Declaration.this, NavActivity.class));
            }
        });
    }

}
