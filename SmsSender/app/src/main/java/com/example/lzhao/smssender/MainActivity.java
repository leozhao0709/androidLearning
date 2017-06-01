package com.example.lzhao.smssender;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText messageEditText;
    private EditText phoneNumberEditText;
    private Button sendMsgBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageEditText = (EditText) findViewById(R.id.messgaeEditText);
        phoneNumberEditText = (EditText) findViewById(R.id.phoneNumberEditText);
        sendMsgBtn = (Button) findViewById(R.id.sendMsgBtn);

        sendMsgBtn.setOnClickListener(new MyClickListener());
    }

    private class MyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String phoneNumber = phoneNumberEditText.getText().toString().trim();
            String messgae = MainActivity.this.messageEditText.getText().toString();

            if("".equals(phoneNumber) || "".equals(messgae))
            {
                Toast.makeText(MainActivity.this, "号码和内容不能为空", Toast.LENGTH_SHORT).show();
                return;
            }

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, messgae, null, null);
        }
    }
}
