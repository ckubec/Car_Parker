package ckubec.tacoma.uw.edu.carparker.model;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ckubec.tacoma.uw.edu.carparker.FindActivity;
import ckubec.tacoma.uw.edu.carparker.Manifest;
import ckubec.tacoma.uw.edu.carparker.R;

public class SendTextActivity extends Activity {

    Button sendText;
    EditText destination;
    EditText msgText;
    FindActivity find = new FindActivity();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //asking for permissions
        ActivityCompat.requestPermissions(this,new String[]{"android.permission.SEND_SMS"},1);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        sendText = (Button) findViewById(R.id.sendTextBtn);
        destination = (EditText) findViewById(R.id.toPhoneNumberET);
        msgText = (EditText) findViewById(R.id.smsMessageET);
        sendText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendSMS();
            }
        });
    }

    protected void sendSMS() {

        String toPhoneNumber = destination.getText().toString();
        String smsMessage = "These are the coordinates I've parked in using the Car Parker App: " + find.getLocationX() + ", " + find.getLocationY();
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(toPhoneNumber, null, smsMessage, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "Sending SMS failed." + e.getMessage(),
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}