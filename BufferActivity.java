package com.quizearn.rishap.quiz20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BufferActivity extends AppCompatActivity {

    TextView rule1,rule2,rule3,rule4,rule5,rule6,rule7,rule8;
    Button Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buffer);
        Btn = (Button)findViewById(R.id.btn);
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BufferActivity.this, MainActivity.class));
            }
        });

        rule1 = (TextView)findViewById(R.id.rule1);
        rule2 = (TextView)findViewById(R.id.rule2);
        rule3 = (TextView)findViewById(R.id.rule3);
        rule4 = (TextView)findViewById(R.id.rule4);
        rule5 = (TextView)findViewById(R.id.rule5);
        rule6 = (TextView)findViewById(R.id.rule6);
        rule7 = (TextView)findViewById(R.id.rule7);
        rule8 = (TextView)findViewById(R.id.rule8);

        /*rule1.setText("1) Every Question has 4 options of which 1 option is correct.");
        rule2.setText("2) Only after attempting first question second will appear.");
        rule3.setText("3) You can attempt as many questions as you want within the given time of 1 minute.");
        rule4.setText("4) For each correct answer you will get 3 points and 1 mark will be deduced if it is incorrect.");
        rule5.setText("5) Submit your GooglePay number at the end of the quiz and if you are in the top 40% of the total players by the end of the live quiz then you will get you reward according to your rank.");
        */rule7.setText("7) Anyone found playing by multiple accounts will not be eligible for prize money");
        rule8.setText("8) Check your rank on the leaderboard and don't forget to come back when the next quiz goes live");

        String str1 = "1) Every Question has <font color='blue'>4</font> options of which <font color='blue'>1</font> option is correct.";
        rule1.setText(Html.fromHtml(str1), TextView.BufferType.SPANNABLE);
        String str2 = "2) You can attempt the next question only after choosing an answer for the current question , there is <font color='blue'>no option of skipping</font> a question";
        rule2.setText(Html.fromHtml(str2), TextView.BufferType.SPANNABLE);
        String str3 = "3) You can attempt as many questions as you want within the given time of <font color='blue'>1 minute</font>.";
        rule3.setText(Html.fromHtml(str3), TextView.BufferType.SPANNABLE);
        String str4 = "4) For each correct answer you will get <font color='blue'>3</font> points and <font color='blue'>-1</font> if it is incorrect.";
        rule4.setText(Html.fromHtml(str4), TextView.BufferType.SPANNABLE);
        String str5 = "5) In case of a <font color='blue'>tie</font> in the score, the person who played earlier will be given advantage";
        rule5.setText(Html.fromHtml(str5), TextView.BufferType.SPANNABLE);
        String str6 = "6) Submit your GooglePay/Paytm number at the end of the quiz and if you are in the <font color='blue'>top 40%</font> of the total players by the end of the live quiz then you will get you reward according to your rank.";
        rule6.setText(Html.fromHtml(str6), TextView.BufferType.SPANNABLE);


    }

    @Override
    public void onBackPressed() {
        Toast.makeText(BufferActivity.this,"Sorry can't go back",Toast.LENGTH_LONG).show();
    }
}
