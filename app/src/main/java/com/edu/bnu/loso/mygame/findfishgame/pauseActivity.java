package com.edu.bnu.loso.mygame.findfishgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.edu.bnu.loso.mygame.R;

import java.util.ArrayList;

public class pauseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pause);

        final Button button = (Button) findViewById(R.id.buttonExit);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                finish();
            }
        });

        final Button button1 = (Button) findViewById(R.id.buttonGoOn);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                finish();
            }
        });
    }
}
