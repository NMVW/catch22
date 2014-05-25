//package com.example.fishfishfish;
//
//import android.app.Activity;
//import android.app.ActionBar;
//import android.app.Fragment;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.os.Build;
//
//public class MainActivity extends Activity {
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
//
//		if (savedInstanceState == null) {
//			//getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
//		}
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
//
//}
//
//


package com.example.fishfishfish;



import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.*;
import android.view.View;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;





public class MainActivity extends Activity implements SensorEventListener {

private String input="Hey bitch, I caught you. I'm gonna eat yo' fin tonight. Holler at your fish";

private SensorManager senSensorManager;

    private Sensor senAccelerometer;

    private long lastUpdate;

    private float last_x, last_y, last_z;

    private float speed;

    private static final int SHAKE_THRESHOLD = 50;

    private ArrayList<Integer> lotteryNumbers;

    private MediaPlayer mpAudio1;

    private MediaPlayer mpAudio2;

    private MediaPlayer mpAudio3;



    /** Called when the activity is first created. */

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);

//        for (int i = 0; i < lotteryNumbers.size(); i++) {

//             Log.i("LOG", " @@@@@@@@" + lotteryNumbers.get(i));

//        }

        mpAudio1 = MediaPlayer.create(this, R.raw.reel);

        mpAudio2 = MediaPlayer.create(this, R.raw.meow);

        mpAudio3 = MediaPlayer.create(this, R.raw.splash);

    }



    protected void onResume() {
    	EditText number = (EditText) findViewById(R.id.EditText01);
    	EditText message = (EditText) findViewById(R.id.editText1);
    	
    	number.setHint("Enter Phone Number");
    	message.setHint("Enter Message");

        super.onResume();

        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    }



    protected void onPause() {

        super.onPause();

        senSensorManager.unregisterListener(this);

    }



    @Override

    public void onSensorChanged(SensorEvent sensorEvent) {

        //To change body of implemented methods use File | Settings | File Templates.

        Sensor mySensor = sensorEvent.sensor;



        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            float x = sensorEvent.values[0];

            float y = sensorEvent.values[1];

            float z = sensorEvent.values[2];



            long curTime = System.currentTimeMillis();

            // only allow one update every 100ms.

            if ((curTime - lastUpdate) > 100) {

                long diffTime = (curTime - lastUpdate);

                lastUpdate = curTime;



                speed = Math.abs(x+y+z - last_x - last_y - last_z)/ diffTime * 10000;



                

                last_x = x;

                last_y = y;

                last_z = z;

            }

        }

    }



    @Override

public void onAccuracyChanged(Sensor sensor, int accuracy) {

// can be safely ignored for this demo

}





    private void setValues(float x, float y, float z) {

        
//
//        TextView text = (TextView)findViewById(R.id.number_1);
//
//        text.setText(""+x);
//
//        text = (TextView)findViewById(R.id.number_2);
//
//        text.setText(""+y);
//
//        text = (TextView)findViewById(R.id.number_3);
//
//        text.setText(""+z);
//
//
//
//        FrameLayout ball1 = (FrameLayout) findViewById(R.id.ball_1);
//
//        ball1.setVisibility(View.VISIBLE);
//
//        FrameLayout ball2 = (FrameLayout) findViewById(R.id.ball_2);
//
//        ball2.setVisibility(View.VISIBLE);
//
//        FrameLayout ball3 = (FrameLayout) findViewById(R.id.ball_3);
//
//        ball3.setVisibility(View.VISIBLE);

    



    }

    @Override

    public boolean dispatchKeyEvent(KeyEvent event) {

        int action = event.getAction();

        int keyCode = event.getKeyCode();

            switch (keyCode) {

            case KeyEvent.KEYCODE_VOLUME_UP:

                if (action == KeyEvent.ACTION_UP) {

                if (speed > SHAKE_THRESHOLD) {

                        Toast.makeText(this, "shake detected w/ speed: " + speed, Toast.LENGTH_SHORT).show();

                       EditText inputText = (EditText) findViewById(R.id.editText1);
                       input = inputText.toString();

                       //setValues(last_x,last_y,last_z);
                        
                        
                       
                       stringManipulation();

                       mpAudio1.seekTo(2000);

                    }

                }

                return true;

            case KeyEvent.KEYCODE_VOLUME_DOWN:

                if (action == KeyEvent.ACTION_DOWN) {

                    //TODO

                }

                return true;

            default:

                return super.dispatchKeyEvent(event);

            }

    }

    public String stringManipulation(){

    String output = input;

    int abs_x = (int)Math.abs(last_x);

    int abs_z = (int)Math.abs(last_z);

    if(abs_x>13){

    for(int i =0; i<abs_x-13; i++){

    output += "!";

    }

    }

    else if(abs_x<10){

    float norm =((float)abs_x)/10;

    int remainder = Math.round(norm*output.length());

    output = output.substring(0,remainder);

    }

    float accuracy = ((float)abs_z)/20;

    output = scrambler(output, accuracy);

    Log.d("test",output);

    return output;

    }

    public String scrambler(String input, float accuracy){

    int length = input.length();

    char[] c = input.toCharArray();

    for(int i=0;i<Math.floor((length*accuracy)/5);i++){

    int rando = randomizer(0,length-1);

        char temp = c[rando];

        int rando2 = randomizer(0,length-1);

        c[rando] = c[rando2];

        c[rando2] = temp;

    }

    String output = new String(c);

    return output;

    }

    public int randomizer(int min, int max){

    return (min + (int)(Math.random() * ((max - min) + 1)));

    }

}
