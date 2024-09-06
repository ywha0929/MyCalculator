package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FractionConverterActivity extends AppCompatActivity {
    EditText numerator1;
    EditText numerator2;
    EditText numerator3;
    EditText numerator4;
    EditText denominator1;
    EditText denominator2;
    EditText denominator3;
    EditText denominator4;
    Button btnConvert1;
    Button btnConvert2;
    ImageView imageView;

    Boolean isPlay = false;
    AudioTrack mAudioTrack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fraction_converter);

        numerator1 = findViewById(R.id.numerator1);
        numerator2 = findViewById(R.id.numerator2);
        numerator3 = findViewById(R.id.numerator3);
        numerator4 = findViewById(R.id.numerator4);

        denominator1 = findViewById(R.id.denominator1);
        denominator2 = findViewById(R.id.denominator2);
        denominator3 = findViewById(R.id.denominator3);
        denominator4 = findViewById(R.id.denominator4);

        btnConvert1 = findViewById(R.id.btnConvert1);
        btnConvert2 = findViewById(R.id.btnConvert2);
        imageView = findViewById(R.id.imageView1);

        btnConvert1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(numerator1.getText().toString().compareTo("")!=0 && denominator1.getText().toString().compareTo("")!=0) {
                    int gcd = GCD( Integer.parseInt((numerator1.getText().toString())), Integer.parseInt(denominator1.getText().toString()) );
                    numerator3.setText(""+(Integer.parseInt(numerator1.getText().toString())/gcd));
                    denominator3.setText(""+(Integer.parseInt(denominator1.getText().toString())/gcd));
                }
                if(numerator2.getText().toString().compareTo("")!=0 && denominator2.getText().toString().compareTo("")!=0) {
                    int gcd = GCD( Integer.parseInt((numerator2.getText().toString())), Integer.parseInt(denominator2.getText().toString()) );
                    numerator4.setText(""+(Integer.parseInt(numerator2.getText().toString())/gcd));
                    denominator4.setText(""+(Integer.parseInt(denominator2.getText().toString())/gcd));
                }
            }
        });
        btnConvert2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(numerator1.getText().toString().compareTo("")!=0 &&
                    numerator2.getText().toString().compareTo("")!=0 &&
                    denominator1.getText().toString().compareTo("")!=0 &&
                    denominator2.getText().toString().compareTo("")!=0 ) {
                    int denom1 = Integer.parseInt(denominator1.getText().toString());
                    int denom2 = Integer.parseInt(denominator2.getText().toString());
                    int numer1 = Integer.parseInt(numerator1.getText().toString());
                    int numer2 = Integer.parseInt(numerator2.getText().toString());
                    int lcm = LCM(denom1,denom2);
                    numer1 *= (int) lcm / denom1;
                    numer2 *= (int) lcm / denom2;
                    denominator3.setText(""+lcm);
                    denominator4.setText(""+lcm);
                    numerator3.setText(""+numer1);
                    numerator4.setText(""+numer2);
                }
                else {
                    Toast.makeText(getApplicationContext(),"There is empty section",Toast.LENGTH_SHORT).show();
                }
            }
        });


        int mBufferSize = AudioRecord.getMinBufferSize(44100, AudioFormat.CHANNEL_IN_STEREO, AudioFormat.ENCODING_PCM_16BIT);
        mAudioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, 44100, AudioFormat.CHANNEL_IN_STEREO, AudioFormat.ENCODING_PCM_16BIT, mBufferSize, AudioTrack.MODE_STREAM);
        imageView.setOnClickListener(new View.OnClickListener() {
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

                                inputStream = getResources().openRawResource(R.raw.siu);
                            }catch (Exception e) {
                                e.printStackTrace();
                            }

                            DataInputStream dis = new DataInputStream(inputStream);
                            mAudioTrack.play();  // write 하기 전에 play 를 먼저 수행해 주어야 함

                            while(isPlay) {
                                try {
                                    int ret = dis.read(writeData, 0, mBufferSize);
                                    if (ret <= 0) {
                                        (FractionConverterActivity.this).runOnUiThread(new Runnable() { // UI 컨트롤을 위해
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
    }
    int GCD(int a, int b){
        int min = 0;
        if(a > b)
            min = b;
        else
            min = a;
        for(int i = min; i >=1; i--) {
            if(a % i == 0 && b % i ==0){
                return i;
            }
        }
        return 1;
    }
    int LCM(int a, int b) {
        int gcd = GCD(a,b);
        return a*b / gcd;
    }
}