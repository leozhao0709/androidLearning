package com.example.lzhao.smssender;

import CommonActivity.PermissionsCallback;
import android.Manifest;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import CommonActivity.AppCompactPermissionActivity;

public class MainActivity extends AppCompactPermissionActivity {

    private EditText messageEditText;
    private EditText phoneNumberEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageEditText = (EditText) findViewById(R.id.messgaeEditText);
        phoneNumberEditText = (EditText) findViewById(R.id.phoneNumberEditText);
        Button sendMsgBtn = (Button) findViewById(R.id.sendMsgBtn);

        sendMsgBtn.setOnClickListener(new MyClickListener());
    }

    private class MyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            MainActivity.this.getPermissions(new PermissionsCallback() {
                @Override
                public void grantPermissionsCallback(@NonNull String... permissions) {
                    MainActivity.this.sendMsg();
                }

                @Override
                public void deniedPermissionsCallback(@NonNull String... permissions) {
                    Toast.makeText(MainActivity.this, "send sms permission need be granted!", Toast.LENGTH_SHORT).show();
                }
            }, Manifest.permission.SEND_SMS);

        }
    }

    private void sendMsg() {
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
