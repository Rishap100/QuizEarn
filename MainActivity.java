package com.quizearn.rishap.quiz20;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    Button b1,b2,b3,b4;
    TextView question_text,timer_text;
    ArrayList<Integer> list;
    int total=0,q;
    int index=-1;
    int correct=0;
    int wrong=0;
    String ans;
    Context context;

    final int min = 1;
    final int max = 36;
     int random=0 ;

     MediaPlayer sound,mysound;


    DatabaseReference reference;
    @Override
    public void onBackPressed(){
        Toast.makeText(MainActivity.this,"Sorry can't go back",Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sound = MediaPlayer.create(this,R.raw.quiz_tone_new);
        sound.start();
        sound.setLooping(true);

        mysound = MediaPlayer.create(this,R.raw.short_chime);




        b1=(Button)findViewById(R.id.op1);
        b1.setBackgroundColor(Color.WHITE);
        b2=(Button)findViewById(R.id.op2);
        b2.setBackgroundColor(Color.WHITE);
        b3=(Button)findViewById(R.id.op3);
        b3.setBackgroundColor(Color.WHITE);
        b4=(Button)findViewById(R.id.op4);
        b4.setBackgroundColor(Color.WHITE);


        question_text=(TextView)findViewById(R.id.ques_text);
        timer_text=(TextView)findViewById(R.id.timer_text);

        list = new ArrayList<Integer>();
        for (int i=1; i<=23; i++) {
            list.add(i);
        }
        Collections.shuffle(list);

        updateQuestions();

        reversetimer(60, timer_text);


    }

    private void updateQuestions()
    {
        b1.setEnabled(true);
        b2.setEnabled(true);
        b3.setEnabled(true);
        b4.setEnabled(true);

        index++;
        q = list.get(index);

        total++;
        if(total>=23)
        {
            /*Intent i =new Intent(MainActivity.this, Result_Activity.class);
            i.putExtra("Total", String.valueOf(total));
            i.putExtra("Correct", String.valueOf(correct));
            i.putExtra("Incorrect", String.valueOf(wrong));*/
            b1.setEnabled(false);
            b2.setEnabled(false);
            b3.setEnabled(false);
            b4.setEnabled(false);
            //startActivity(i);
            Toast.makeText(MainActivity.this,"Please wait for the timer to end",Toast.LENGTH_LONG).show();
            //MainActivity.this.finish();
        }
        else
        {
            reference= FirebaseDatabase.getInstance().getReference().child("Questions").child(String.valueOf(q));
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    String ques = dataSnapshot.child("Question").getValue().toString();
                    String opt1 = dataSnapshot.child("Option1").getValue().toString();
                    String opt2 = dataSnapshot.child("Option2").getValue().toString();
                    String opt3 = dataSnapshot.child("Option3").getValue().toString();
                    String opt4 = dataSnapshot.child("Option4").getValue().toString();
                    ans = dataSnapshot.child("answer").getValue().toString();

                    question_text.setText(ques);
                    b1.setText(opt1);
                    b2.setText(opt2);
                    b3.setText(opt3);
                    b4.setText(opt4);


                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            b1.setEnabled(false);
                            b2.setEnabled(false);
                            b3.setEnabled(false);
                            b4.setEnabled(false);
                            if(b1.getText().toString().equals(ans))
                            {
                                /*CountDownTimer cntr_aCounter = new CountDownTimer(1000, 1000) {
                                    public void onTick(long millisUntilFinished) {

                                        mysound.start();
                                    }

                                    public void onFinish() {
                                        //code fire after finish
                                        mysound.stop();
                                    }
                                };cntr_aCounter.start();*/
                                mysound.start();

                                b1.setBackgroundColor(Color.GREEN);
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b1.setBackgroundColor(Color.WHITE);
                                        updateQuestions();
                                    }
                                },1000);
                            }
                            else
                            {
                                wrong++;
                                b1.setBackgroundColor(Color.RED);

                                if(b2.getText().toString().equals(ans))
                                {
                                    b2.setBackgroundColor(Color.GREEN);
                                }
                                if(b3.getText().toString().equals(ans))
                                {
                                    b3.setBackgroundColor(Color.GREEN);
                                }
                                if(b4.getText().toString().equals(ans))
                                {
                                    b4.setBackgroundColor(Color.GREEN);
                                }

                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b1.setBackgroundColor(Color.WHITE);
                                        b2.setBackgroundColor(Color.WHITE);
                                        b3.setBackgroundColor(Color.WHITE);
                                        b4.setBackgroundColor(Color.WHITE);
                                        updateQuestions();
                                    }
                                },1000);
                            }
                        }
                    });

                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            b1.setEnabled(false);
                            b2.setEnabled(false);
                            b3.setEnabled(false);
                            b4.setEnabled(false);
                            if(b2.getText().toString().equals(ans))
                            {

                                /*CountDownTimer cntr_aCounter = new CountDownTimer(1000, 1000) {
                                    public void onTick(long millisUntilFinished) {

                                        mysound.start();
                                    }

                                    public void onFinish() {
                                        //code fire after finish
                                        mysound.stop();
                                    }
                                };cntr_aCounter.start();*/
                                mysound.start();
                                b2.setBackgroundColor(Color.GREEN);
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b2.setBackgroundColor(Color.WHITE);
                                        updateQuestions();
                                    }
                                },1000);
                            }
                            else
                            {
                                wrong++;
                                b2.setBackgroundColor(Color.RED);

                                if(b1.getText().toString().equals(ans))
                                {
                                    b1.setBackgroundColor(Color.GREEN);
                                }
                                if(b3.getText().toString().equals(ans))
                                {
                                    b3.setBackgroundColor(Color.GREEN);
                                }
                                if(b4.getText().toString().equals(ans))
                                {
                                    b4.setBackgroundColor(Color.GREEN);
                                }

                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b1.setBackgroundColor(Color.WHITE);
                                        b2.setBackgroundColor(Color.WHITE);
                                        b3.setBackgroundColor(Color.WHITE);
                                        b4.setBackgroundColor(Color.WHITE);
                                        updateQuestions();
                                    }
                                },1000);
                            }
                        }
                    });

                    b3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            b1.setEnabled(false);
                            b2.setEnabled(false);
                            b3.setEnabled(false);
                            b4.setEnabled(false);
                            if(b3.getText().toString().equals(ans))
                            {

                                mysound.start();
                                /*CountDownTimer cntr_aCounter = new CountDownTimer(1000, 1000) {
                                    public void onTick(long millisUntilFinished) {

                                        mysound.start();
                                    }

                                    public void onFinish() {
                                        //code fire after finish
                                        mysound.stop();
                                    }
                                };cntr_aCounter.start();*/
                                b3.setBackgroundColor(Color.GREEN);
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b3.setBackgroundColor(Color.WHITE);
                                        updateQuestions();
                                    }
                                },1000);
                            }
                            else
                            {
                                wrong++;
                                b3.setBackgroundColor(Color.RED);

                                if(b2.getText().toString().equals(ans))
                                {
                                    b2.setBackgroundColor(Color.GREEN);
                                }
                                if(b1.getText().toString().equals(ans))
                                {
                                    b1.setBackgroundColor(Color.GREEN);
                                }
                                if(b4.getText().toString().equals(ans))
                                {
                                    b4.setBackgroundColor(Color.GREEN);
                                }

                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b1.setBackgroundColor(Color.WHITE);
                                        b2.setBackgroundColor(Color.WHITE);
                                        b3.setBackgroundColor(Color.WHITE);
                                        b4.setBackgroundColor(Color.WHITE);
                                        updateQuestions();
                                    }
                                },1000);
                            }
                        }
                    });

                    b4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            b1.setEnabled(false);
                            b2.setEnabled(false);
                            b3.setEnabled(false);
                            b4.setEnabled(false);
                            if(b4.getText().toString().equals(ans))
                            {
                                mysound.start();
                                /*CountDownTimer cntr_aCounter = new CountDownTimer(1000, 1000) {
                                    public void onTick(long millisUntilFinished) {

                                        mysound.start();
                                    }

                                    public void onFinish() {
                                        //code fire after finish
                                        mysound.stop();
                                    }
                                };cntr_aCounter.start();*/
                                b4.setBackgroundColor(Color.GREEN);
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b4.setBackgroundColor(Color.WHITE);
                                        updateQuestions();
                                    }
                                },1000);
                            }
                            else
                            {
                                wrong++;
                                b4.setBackgroundColor(Color.RED);

                                if(b2.getText().toString().equals(ans))
                                {
                                    b2.setBackgroundColor(Color.GREEN);
                                }
                                if(b3.getText().toString().equals(ans))
                                {
                                    b3.setBackgroundColor(Color.GREEN);
                                }
                                if(b1.getText().toString().equals(ans))
                                {
                                    b1.setBackgroundColor(Color.GREEN);
                                }

                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b1.setBackgroundColor(Color.WHITE);
                                        b2.setBackgroundColor(Color.WHITE);
                                        b3.setBackgroundColor(Color.WHITE);
                                        b4.setBackgroundColor(Color.WHITE);
                                        updateQuestions();
                                    }
                                },1000);
                            }
                        }
                    });
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    public void reversetimer(int seconds,final TextView tv) {
        new CountDownTimer(seconds * 1000 + 1000, 1000) {
            @Override
            public void onTick(long millsUntilFinised) {
                int seconds = (int) (millsUntilFinised / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                tv.setText(String.format("%02d", minutes) + ":" + String.format("%02d", seconds));


            }

            @Override
            public void onFinish() {
                tv.setText("Completed");
                Intent intent = new Intent(MainActivity.this, Result_Activity.class);
                intent.putExtra("Total", String.valueOf(total));
                intent.putExtra("Correct", String.valueOf(correct));
                intent.putExtra("Incorrect", String.valueOf(wrong));
                b1.setEnabled(false);
                b2.setEnabled(false);
                b3.setEnabled(false);
                b4.setEnabled(false);
                startActivity(intent);
                MainActivity.this.finish();

            }

        }.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sound.release();
        //finish();
    }
}
