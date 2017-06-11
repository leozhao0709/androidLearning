package com.example.lzhao.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText enteredAmountText;
    private SeekBar seekBar;
    private Button calculateBtn;
    private TextView totalResultTextView;
    private TextView textViewSeekbar;
    private int seekbarPercentage;
    private float enteredBillFloat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enteredAmountText = (EditText) findViewById(R.id.enteredAmountText);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        calculateBtn = (Button) findViewById(R.id.calculateBtn);
        totalResultTextView = (TextView) findViewById(R.id.resultTextView);
        textViewSeekbar = (TextView) findViewById(R.id.textViewSeekbar);

        calculateBtn.setOnClickListener(this);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                textViewSeekbar.setText(String.valueOf(progress) + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekbarPercentage = seekBar.getProgress();
            }
        });
    }

    @Override
    public void onClick(View v) {
        this.calculate();
    }

    public void calculate() {
        float result = 0.0f;

        if(enteredAmountText.getText().toString().equals(""))
        {
            Toast.makeText(this, R.string.error_remind, Toast.LENGTH_SHORT).show();
        }
        else {
            enteredBillFloat = Float.parseFloat(enteredAmountText.getText().toString());
            result = enteredBillFloat * (seekbarPercentage / 100f);
            totalResultTextView.setText("Your tip will be " + "$" + String.valueOf(result));
        }
    }
}
