package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class GeometryOptionActivity extends AppCompatActivity {
    Button btnCircle;
    Button btnTrapezoid;
    Button btnParallelogram;
    Button btnTriangle;
    Button btnRhombus;
    ImageView imgMollu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geometry_option);
        btnCircle = findViewById(R.id.btnCircle);
        btnRhombus = findViewById(R.id.btnRhombus);
        btnTrapezoid = findViewById(R.id.btnTrapezoid);
        btnTriangle = findViewById(R.id.btnTriangle);
        btnParallelogram = findViewById(R.id.btnParallelogram);
        imgMollu = findViewById(R.id.imageView2);
    }
}