package com.example.timerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView textViewTimer;
    MediaPlayer mediaPlayer;
    SeekBar seekBar;
    Boolean counterIsActive = false;
    CountDownTimer countDownTimer;

    public void reset(){


        countDownTimer.cancel();
        textViewTimer.setText("00:30");
        seekBar.setProgress(30);
        goButton.setText("Go");
        counterIsActive = false;
        seekBar.setEnabled(true);

    }

    public void hi(int i){

        int minutes = i / 60;
        int seconds = i - minutes * 60;

        if (minutes < 10) {

            if (seconds < 10) {

                textViewTimer.setText("0" + minutes + ":" + "0" + seconds);

            } else {
                textViewTimer.setText("0" + minutes + ":" + seconds);
            }

        } else if (seconds < 10) {
            if (minutes >= 10) {
                textViewTimer.setText(minutes + ":" + "0" + seconds);
            }
        }
       }

       Button goButton;
       Button stop;

        public void timerController(View view) {

            if (counterIsActive == false) {


                counterIsActive = true;
                seekBar.setEnabled(false);

                goButton.setText("Stop");


                countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {

                    @Override
                    public void onTick(long millisUntilFinished) {

                        hi((int) millisUntilFinished / 1000);

                    }

                    @Override
                    public void onFinish() {

                        mediaPlayer.start();
                        reset();


                        Log.i("INFO", "Time is up!");

                    }
                }.start();


            }
            else{

                reset();


            }
        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = (Button) findViewById(R.id.go);

          mediaPlayer = MediaPlayer.create(this, R.raw.laugh);


       seekBar= (SeekBar) findViewById(R.id.seekB);
        seekBar.setMax(600);
        seekBar.setProgress(30);

        textViewTimer= (TextView) findViewById(R.id.timerTextView);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            hi(progress);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }


        });
    }}