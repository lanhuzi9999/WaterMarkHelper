package com.example.watermarkhelper.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.watermarkhelper.R;

public class MainActivity extends AppCompatActivity {
    Button start_tv;
    TextView result_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
