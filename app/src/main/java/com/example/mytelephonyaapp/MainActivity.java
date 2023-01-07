package com.example.mytelephonyaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

        Button btn_sms,btn_call;
        EditText et_number,et_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_sms = findViewById(R.id.btn_sms);
        btn_call = findViewById(R.id.btn_call);
        et_number =findViewById(R.id.et_mo_number);
        et_text = findViewById(R.id.et_text);

        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //use intent for calling function

                Intent intent=new Intent();
                intent.setAction(intent.ACTION_CALL);
                intent.setData(Uri.parse("tel: "+et_number.getText().toString()));
                startActivity(intent);

            }
        });

        btn_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               SmsManager smsManager=SmsManager.getDefault();
               String[] mobile_numbers= et_number.getText().toString().split("#");// sent the multiple number (split "#")
               String text = et_text.getText().toString();
               for(String mobile_number:mobile_numbers) {

                   // show the massage sent notification
                   Intent si_intent = new Intent(MainActivity.this, sentActivity.class);
                   PendingIntent si = PendingIntent.getActivity(MainActivity.this, 0, si_intent, 0);

                   //show the massage deliver notification
                   Intent dl_intent = new Intent(MainActivity.this, DeliverActivity.class);
                   PendingIntent dl = PendingIntent.getActivity(MainActivity.this, 0, dl_intent, 0);


                   smsManager.sendTextMessage(mobile_number, null, text, si, dl);


                }
            }
        });
    }
}