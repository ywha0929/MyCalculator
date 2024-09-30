package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class GeometryOptionActivity extends AppCompatActivity {
    Button btnCircle;
    Button btnTrapezoid;
    Button btnParallelogram;
    Button btnTriangle;
    Button btnRhombus;
    ImageView imgMollu;

    Boolean isPlay = false;
    AudioTrack mAudioTrack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geometry_option);
        btnCircle = findViewById(R.id.btnCircle);
        btnRhombus = findViewById(R.id.btnRhombus);
        btnTrapezoid = findViewById(R.id.btnTrapezoid);
        btnTriangle = findViewById(R.id.btnTriangle);
        btnParallelogram = findViewById(R.id.btnParallelogram);
        imgMollu = findViewById(R.id.imageView2);int mBufferSize = AudioRecord.getMinBufferSize(44100, AudioFormat.CHANNEL_IN_STEREO, AudioFormat.ENCODING_PCM_16BIT);
        mAudioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, 44100, AudioFormat.CHANNEL_IN_STEREO, AudioFormat.ENCODING_PCM_16BIT, mBufferSize, AudioTrack.MODE_STREAM);
        imgMollu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPlay == true) {
                    isPlay = false;

                }
                else {
                    isPlay = true;


                    Thread mPlayThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            byte[] writeData = new byte[mBufferSize];
                            InputStream inputStream = null;
                            try {

                                inputStream = getResources().openRawResource(R.raw.morlu);
                            }catch (Exception e) {
                                e.printStackTrace();
                            }

                            DataInputStream dis = new DataInputStream(inputStream);
                            mAudioTrack.play();  // write 하기 전에 play 를 먼저 수행해 주어야 함

                            while(isPlay) {
                                try {
                                    int ret = dis.read(writeData, 0, mBufferSize);
                                    if (ret <= 0) {
                                        (GeometryOptionActivity.this).runOnUiThread(new Runnable() { // UI 컨트롤을 위해
                                            @Override
                                            public void run() {
                                                isPlay = false;

                                            }
                                        });
                                        break;
                                    }
                                    mAudioTrack.write(writeData, 0, ret); // AudioTrack 에 write 를 하면 스피커로 송출됨
                                }catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                            mAudioTrack.stop();
                            mAudioTrack.release();
                            mAudioTrack = null;

                            try {
                                dis.close();
                                inputStream.close();
                            }catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    if(mAudioTrack == null) {
                        int mBufferSize = AudioRecord.getMinBufferSize(44100, AudioFormat.CHANNEL_IN_STEREO, AudioFormat.ENCODING_PCM_16BIT);
                        mAudioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, 44100, AudioFormat.CHANNEL_IN_STEREO, AudioFormat.ENCODING_PCM_16BIT, mBufferSize, AudioTrack.MODE_STREAM);
                    }
                    mPlayThread.start();
                }

            }
        });
        btnCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),GeometryCircleActivity.class));
            }
        });
        btnTrapezoid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),GeometryTrapezoidActivity.class));
            }
        });
        btnParallelogram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),GeometryParallelogramActivity.class));
            }
        });
        btnRhombus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),GeometryRhombusActivity.class));
            }
        });
        btnTriangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),GeometryTriangleActivity.class));
            }
        });

    }
}