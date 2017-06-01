package com.example.lzhao.callPhone;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int MY_PERMISSION_REQUEST_CALL_PHONE = 1;
    private EditText phoneEditText;
    private Button callBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.callBtn = (Button) findViewById(R.id.callBtn);
        this.phoneEditText = (EditText) findViewById(R.id.phoneEditText);

        this.callBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSION_REQUEST_CALL_PHONE);
        }
        else {
            callPhone();
        }
    }

    public void callPhone() {

        String phoneNumber = this.phoneEditText.getText().toString().trim();

        if("".equals(phoneNumber))
        {
            System.out.println("空号码");
        }
        else
        {
            // 打电话
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel://" + phoneNumber));

            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        System.out.println(grantResults.toString());

        if(requestCode == MY_PERMISSION_REQUEST_CALL_PHONE)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                callPhone();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
