package com.example.hellosensor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToCompassActivity (View view){
        Intent intent = new Intent (this, CompassActivity.class);
        startActivity(intent);
    }

    public void goToAccelerometerActivity (View view){
        Intent intent = new Intent (this, AccelerometerActivity.class);
        startActivity(intent);
    }
}
