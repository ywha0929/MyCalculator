package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnNormal;
    Button btnFraction;
    Button btnGeometry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNormal = findViewById(R.id.btnNormal);
        btnFraction = findViewById(R.id.btnFraction);
        btnGeometry = findViewById(R.id.btnGeometry);
        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),NormalCalculatorActivity.class));
            }
        });
        btnFraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FractionOptionActivity.class));
            }
        });
        btnGeometry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), GeometryOptionActivity.class));
            }
        });
    }
}