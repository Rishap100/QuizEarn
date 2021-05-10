package com.quizearn.rishap.quiz20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Payment2 extends AppCompatActivity {

    public static final String GPAY_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";
    public String name, upiId, amount, note;
    TextView msg;
    Button pay,next;
    Uri uri;
    String r;
    EditText ref;
    TextView text1,text2,text3;

    DatabaseReference reference;

    public static String payerName, UpiId, msgNote, sendAmount, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment2);

        text1 = (TextView)findViewById(R.id.textView14);
        text2 = (TextView)findViewById(R.id.textView26);
        text3 = (TextView)findViewById(R.id.textView25);

        String text = "<font color='black'>Pay </font><font color='blue'>\u20B95</font><font color='black'> through <font color='blue'>GooglePay by clicking Pay button and you will be directed to GooglePay App.</font>";
        text1.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);

        msg = findViewById(R.id.status);
        pay = findViewById(R.id.pay);

        reference = FirebaseDatabase.getInstance().getReference();

        //ref = (EditText)findViewById(R.id.referral);
        //next = (Button)findViewById(R.id.next);
        /*next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String r = ref.getText().toString();
                if(r.equals("quizearn123"))
                {
                    next.setEnabled(true);
                    startActivity(new Intent(Payment2.this, BufferActivity.class));
                }
                else
                {
                    Toast.makeText(Payment2.this,"Wrong referral", Toast.LENGTH_LONG).show();
                }
            }
        });*/


        //initialising default value
        name="Souvik";
        upiId="777souvik@oksbi";
        note="QuizEarn";
        amount="5";

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                payerName = name;
                UpiId = upiId;
                msgNote = note;
                sendAmount = amount;

                if(!payerName.equals("") && !upiId.equals("") && !msgNote.equals("") && !sendAmount.equals("")){

                    uri = getUpiPaymentUri(payerName, UpiId, msgNote, sendAmount);
                    payWithGpay(GPAY_PACKAGE_NAME);

                }
                else {
                    Toast.makeText(Payment2.this,"Fill all above details and try again.", Toast.LENGTH_SHORT).show();


                }



            }
        });

    }

    private static Uri getUpiPaymentUri(String name, String upiId, String note, String amount){
        return  new Uri.Builder()
                .scheme("upi")
                .authority("pay")
                .appendQueryParameter("pa",upiId)
                .appendQueryParameter("pn",name)
                .appendQueryParameter("tn",note)
                .appendQueryParameter("am",amount)
                .appendQueryParameter("cu","INR")
                .build();
    }

    private void payWithGpay(String packageName){

        if(isAppInstalled(this,packageName)){

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            intent.setPackage(packageName);
            startActivityForResult(intent,0);


        }
        else{
            Toast.makeText(Payment2.this,"Google pay is not installed. Please istall and try again.", Toast.LENGTH_SHORT).show();
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            status = data.getStringExtra("Status").toLowerCase();
        }
        if ((RESULT_OK == resultCode) && status.equals("success")) {
            Toast.makeText(Payment2.this, "Transaction successful.", Toast.LENGTH_SHORT).show();
            msg.setText("Transaction successful of ₹" + sendAmount);
            msg.setTextColor(Color.GREEN);
            startActivity(new Intent(Payment2.this , BufferActivity.class));

        }

        else{
            Toast.makeText(Payment2.this, "Transaction cancelled or failed please try again.", Toast.LENGTH_SHORT).show();
            msg.setText("Transaction Failed of ₹" + sendAmount);
            msg.setTextColor(Color.RED);
        }

    }


    public static boolean isAppInstalled(Context context, String packageName){
        try{
            context.getPackageManager().getApplicationInfo(packageName,0);
            return true;
        }catch (PackageManager.NameNotFoundException e){
            return false;
        }
    }
}
