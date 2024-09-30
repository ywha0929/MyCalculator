package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GeometryCircleActivity extends AppCompatActivity {
    Button btnCalculate;
    EditText editRadius;
    TextView textResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geometry_circle);
        btnCalculate = findViewById(R.id.btnCircleResult);
        editRadius = findViewById(R.id.editCircleR);
        textResult = findViewById(R.id.textCircleResult);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double R = Double.parseDouble(editRadius.getText().toString());
                double result = R*R*3.141592;
                textResult.setText(""+result);
            }
        });
    }
}