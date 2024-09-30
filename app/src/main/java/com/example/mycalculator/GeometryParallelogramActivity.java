package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GeometryParallelogramActivity extends AppCompatActivity {
    EditText editParallelogramA;
    EditText editParallelogramB;
    TextView textParallelogramResult;
    Button btnParallelogramResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geometry_parallelogram);
        editParallelogramA = findViewById(R.id.editParallelogramA);
        editParallelogramB = findViewById(R.id.editParallelogramB);
        textParallelogramResult = findViewById(R.id.textParallelogramResult);
        btnParallelogramResult = findViewById(R.id.btnParallelogramResult);
        btnParallelogramResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double A = Double.parseDouble(editParallelogramA.getText().toString());
                double B = Double.parseDouble(editParallelogramB.getText().toString());
                double result = A*B/2;
                textParallelogramResult.setText(""+result);
            }
        });
    }
}