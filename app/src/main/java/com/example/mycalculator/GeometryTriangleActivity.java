package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GeometryTriangleActivity extends AppCompatActivity {

    EditText editTriangleA;
    EditText editTriangleB;
    TextView textTriangleResult;
    Button btnTriangleResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geometry_triangle);
        editTriangleA = findViewById(R.id.editTriangleA);
        editTriangleB = findViewById(R.id.editTriangleB);
        textTriangleResult = findViewById(R.id.textTriangleResult);
        btnTriangleResult = findViewById(R.id.btnTriangleResult);
        btnTriangleResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double A = Double.parseDouble(editTriangleA.getText().toString());
                double B = Double.parseDouble(editTriangleB.getText().toString());
                double result = A*B/2;
                textTriangleResult.setText(""+result);
            }
        });
    }
}