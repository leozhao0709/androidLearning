package com.example.lzhao.meterstoinches;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    /*
        1m = 39.3701in
     */

    private EditText metersEditText;
    private Button convertBtn;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        metersEditText = (EditText) findViewById(R.id.metersEditText);
        convertBtn = (Button) findViewById(R.id.convertBtn);
        resultTextView = (TextView) findViewById(R.id.resultTextView);

        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double multipler = 39.3701;
                double result = 0.0;

                if (metersEditText.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this, R.string.error_message, Toast.LENGTH_SHORT).show();
                } else
                {
                    double meterValue = Double.parseDouble(metersEditText.getText().toString());

                    result = meterValue * multipler;

                    resultTextView.setText(String.format(getResources().getString(R.string.inches_value), result));
                }
            }
        });
    }
}
