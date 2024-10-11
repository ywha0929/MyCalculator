package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GeometryTrapezoidActivity extends AppCompatActivity {

    EditText editTrapezoidA;
    EditText editTrapezoidB;
    EditText editTrapezoidH;
    TextView textTrapezoidResult;
    Button btnTrapezoidResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geometry_trapezoid);
        editTrapezoidA = findViewById(R.id.editTrapezoidA);
        editTrapezoidB = findViewById(R.id.editTrapezoidB);
        editTrapezoidH = findViewById(R.id.editTrapezoidH);
        textTrapezoidResult = findViewById(R.id.textTrapezoidResult);
        btnTrapezoidResult = findViewById(R.id.btnTrapezoidResult);
        btnTrapezoidResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double A = Double.parseDouble(editTrapezoidA.getText().toString());
                double B = Double.parseDouble(editTrapezoidB.getText().toString());
                double H = Double.parseDouble(editTrapezoidH.getText().toString());
                double result = (A+B)*H/2;
                textTrapezoidResult.setText(""+result);
            }
        });
    }
}