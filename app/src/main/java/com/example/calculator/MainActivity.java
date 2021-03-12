package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText enteredAmount;
    private SeekBar seekBar;
    private Button calculateButton;
    private TextView resultTextView;
    private TextView textViewSeekBar;
    private int seekBarPercentage;
    private  TextView totalBillTextView;
    private float enteredBillFloat;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate fields
        enteredAmount = (EditText) findViewById(R.id.billAmountID);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        calculateButton = (Button) findViewById(R.id.calculateButton);
        resultTextView = (TextView) findViewById(R.id.resultID);
        textViewSeekBar = (TextView) findViewById(R.id.textViewSeekBar);
        totalBillTextView = (TextView) findViewById(R.id.textView4);

        seekBar.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
        seekBar.getThumb().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
        calculateButton.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textViewSeekBar.setText(String.valueOf(seekBar.getProgress())+"%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekBarPercentage = seekBar.getProgress();

            }
        });
    }

    @Override
    public void onClick(View view) {
        calculate();
    }

    public void calculate(){
        float result = 0.0f;
        try {
            totalBillTextView.setText("");
            totalBillTextView.setTextColor(Color.WHITE);
            enteredBillFloat = Float.parseFloat(enteredAmount.getText().toString());
            if (!enteredAmount.getText().toString().equals("")) {
                enteredBillFloat = Float.parseFloat(enteredAmount.getText().toString());
                result = enteredBillFloat * seekBarPercentage / 100;
                resultTextView.setText("Your tip will be $" + String.valueOf(result));

                Log.d(TAG, "Hello from Calculate method " + result);

            } else {
                Toast.makeText(MainActivity.this, "Please enter a bill amount", Toast.LENGTH_LONG).show();
            }
            totalBillTextView.setText("Your total bill $" + (enteredBillFloat + result));
        } catch(Exception ex){
           totalBillTextView.setTextColor(Color.RED);
           totalBillTextView.setText("Invalid input");
            }
    }
}