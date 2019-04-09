package com.example.hellosensor;

import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener {
    Vibrator vibrator;
    TextView txt_accelerometer_x;
    TextView txt_accelerometer_y;
    TextView txt_accelerometer_z;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private float[] mLastAccelerometer = new float[3];
    static final float ALPHA = 0.25f; // if ALPHA = 1 OR 0, no filter applies.
    final Context c = this;
    boolean differenceChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        differenceChange = true;
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        setContentView(R.layout.activity_accelerometer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        txt_accelerometer_x = (TextView) findViewById(R.id.txt_accelerometer_x);
        txt_accelerometer_y = (TextView) findViewById(R.id.txt_accelerometer_y);
        txt_accelerometer_z = (TextView) findViewById(R.id.txt_accelerometer_z);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        start();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int round_x = Math.round(mLastAccelerometer[0]);
        int round_y = Math.round(mLastAccelerometer[1]);
        int round_z = Math.round(mLastAccelerometer[2]);
        double difference_x = Math.sqrt((Math.pow((0 - round_x), 2)));
        double difference_y = Math.sqrt((Math.pow((8 - round_y), 2)));
        double difference_z = Math.sqrt((Math.pow((6 - round_z), 2)));

        mLastAccelerometer= lowPass(event.values.clone(), mLastAccelerometer);
        txt_accelerometer_x.setText("X-Axis: " + round_x + " m/s^2");
        txt_accelerometer_y.setText("Y-Axis: " + round_y + " m/s^2");
        txt_accelerometer_z.setText("Z-Axis: " + round_z + " m/s^2");


        if((difference_x <= 2 && difference_y <= 2 && difference_z <= 2) && differenceChange) {
            differenceChange = false;
            if (Build.VERSION.SDK_INT >= 26) {
                vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                vibrator.vibrate(100);
            }
            Snackbar.make(findViewById(R.id.cardView5), "Oh hello there!", Snackbar.LENGTH_LONG)
                    .setAction("Hmm, seems like something went wrong.", null).show();
        }else if(difference_x > 2 || difference_y > 2 || difference_z > 2){
            differenceChange = true;
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void start() {
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) == null) {
            noSensorsAlert();
        } else {
            mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
            mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
    }

    private void noSensorsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Your device doesn't support the Accerelometer.")
                .setCancelable(false)
                .setNegativeButton("Close",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
        alertDialog.show();
    }

    public void stop() {
        mSensorManager.unregisterListener(this,mAccelerometer);
    }

    @Override
    protected void onPause() {
        super.onPause();
        stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        start();
    }

    protected float[] lowPass( float[] input, float[] output ) {
        if (output == null) return input;
        for (int i = 0; i < input.length; i++) {
            output[i] = output[i] + ALPHA * (input[i] - output[i]);
        }
        return output;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
