package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GeometryRhombusActivity extends AppCompatActivity {
    EditText editRhombusA;
    EditText editRhombusB;
    TextView textRhombusResult;
    Button btnRhombusResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geometry_rhombus);
        editRhombusA = findViewById(R.id.editRhombusA);
        editRhombusB = findViewById(R.id.editRhombusB);
        textRhombusResult = findViewById(R.id.textRhombusResult);
        btnRhombusResult = findViewById(R.id.btnRhombusResult);
        btnRhombusResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double A = Double.parseDouble(editRhombusA.getText().toString());
                double B = Double.parseDouble(editRhombusB.getText().toString());
                double result = A*B/2;
                textRhombusResult.setText(""+result);
            }
        });
    }
}