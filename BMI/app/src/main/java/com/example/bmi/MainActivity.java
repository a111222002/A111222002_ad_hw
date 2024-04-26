package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txvShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txvShow=(TextView) findViewById(R.id.txvShow);
        txvShow.setTextSize(36);
        Button btnCals=(Button) findViewById(R.id.btncal);
        Button btnClears=(Button) findViewById(R.id.btnclear);
        btnCals.setOnClickListener(this);
        btnClears.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText edtHeight = (EditText) findViewById(R.id.edtheight);
        EditText edtWeight=(EditText) findViewById(R.id.edtweight);

        if(v.getId() == R.id.btncal){
            String heightStr = edtHeight.getText().toString();
            String weightStr = edtWeight.getText().toString();

            if (heightStr.isEmpty() || weightStr.isEmpty()) {
                Toast.makeText(this, "Please enter both height and weight", Toast.LENGTH_SHORT).show();
                return;
            }

            double height = Double.parseDouble(heightStr);
            double weight = Double.parseDouble(weightStr);

            if (height == 0) {
                Toast.makeText(this, "Height cannot be zero", Toast.LENGTH_SHORT).show();
                return;
            }
            double BMI =weight/Math.pow(height/100.0,2);
            if (BMI >= 24)
                txvShow.setTextColor(Color.RED);
            else if (BMI < 18.5)
                txvShow.setTextColor(Color.BLUE);
            else
                txvShow.setTextColor(Color.GREEN);
            txvShow.setText(String.format("%.2f",BMI));
        }
        else{
            edtWeight.setText("");
            edtHeight.setText("");
            txvShow.setText("");
        }
    }
}