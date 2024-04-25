package com.example.hw_ticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements RadioGroup.OnCheckedChangeListener, TextWatcher {
    private TextView output;
    private EditText txt;
    private RadioGroup rgender;
    private RadioGroup rticket;

    private Button submit;
    private  String str = "0";
    private  String gender="";
    private String[] ticketType={"",""};
    private  String ticket="";
    private  int sum=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = (TextView) findViewById(R.id.lblOutput);
        rgender = (RadioGroup) findViewById(R.id.rgGender);
        rticket = (RadioGroup) findViewById(R.id.rgticket);
        rgender.setOnCheckedChangeListener(this);
        rticket.setOnCheckedChangeListener(this);
        txt = (EditText) findViewById(R.id.txtName);
        txt.addTextChangedListener(this);

    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }
    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        calculateSum();
    }
    @Override
    public void afterTextChanged(Editable editable) {
    }
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (radioGroup == rgender) {
            show(i, "rgender");
        } else {
            show(i, "rticket");
        }
        calculateSum();
    }
    private void calculateSum() {
        if (!txt.getText().toString().isEmpty()) {
            int ticketCount = Integer.parseInt(txt.getText().toString());
            int ticketPrice = Integer.parseInt(ticketType[1]);
            str = ticketCount + "張";
            sum = ticketCount * ticketPrice;
        } else {
            str = "0張";
            sum = 0;
        }
        output.setText("性別："+gender + "\n" +"票別(數量)："+ ticketType[0] + str + "\n" + "金額：" + sum);
    }
    public void show(int i, String g) {
        RadioButton selected = findViewById(i);
        if (g.equals("rgender")) {
            gender = selected.getText().toString();
        } else if (g.equals("rticket")) {
            ticket = selected.getText().toString();
            ticketType = ticket.split("\n");
        }
    }
    public void submit(View view){
        Intent intent=new Intent(this, result.class);
        intent.putExtra("result",output.getText());
        startActivity(intent);
    }
}