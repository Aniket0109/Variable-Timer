package com.example.variabletimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;

    Button startStop;

    TextView minutes;

    TextView seconds;

    Boolean counterIsActive = false;

    CountDownTimer cTimer = null;

    public void buttonClicked(View view){

        if(counterIsActive){

            resetTimer();

        } else{

            counterIsActive = true;

            seekBar.setEnabled(false);

            startStop.setText("Stop");

            //start timer function
            cTimer = new CountDownTimer(seekBar.getProgress()*1000+100, 1000) {
                public void onTick(long millisUntilFinished) {

                    updateTimer((int) millisUntilFinished/1000);

                }
                public void onFinish() {

                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.airhorn);

                    mediaPlayer.start();

                    resetTimer();

                }
            };

            cTimer.start();

        }
    }

    public void resetTimer(){

        seekBar.setProgress(30);
        updateTimer(30);
        seekBar.setEnabled(true);
        cTimer.cancel();
        startStop.setText("Go");
        counterIsActive = false;

    }

    public void updateTimer(int secondsLeft){
        int min = secondsLeft/60;

        int sec = secondsLeft-(min*60);

        String secondString = Integer.toString(sec);

        if(sec<=9){
            secondString = "0"+secondString;
        }

        minutes.setText(Integer.toString(min));

        seconds.setText(secondString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        seekBar = (SeekBar) findViewById(R.id.seekBar);

        startStop = (Button) findViewById(R.id.button);

        minutes = (TextView) findViewById(R.id.minuteTextView);

        seconds = (TextView) findViewById(R.id.secondTextView);

        seekBar.setMax(600);

        seekBar.setProgress(30);

        seconds.setText(Integer.toString(30));

        minutes.setText(Integer.toString(0));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
    }