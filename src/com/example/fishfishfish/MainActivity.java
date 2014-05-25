package com.example.fishfishfish;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private static String logtag = "Fish3"; //for use as the tag when logging

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         
        Button buttonLure = (Button)findViewById(R.id.buttonLure);        
        buttonLure.setOnClickListener(lureListener); // Register the onClick listener with the implementation above
       
        Button buttonTackle = (Button)findViewById(R.id.buttonTackle);        
        buttonTackle.setOnClickListener(tackleListener); // Register the onClick listener with the implementation above
        
        Button buttonPlay = (Button)findViewById(R.id.buttonPlay);        
        buttonPlay.setOnClickListener(playListener); // Register the onClick listener with the implementation above
    }
     
    //Create an anonymous implementation of OnClickListener
    private OnClickListener lureListener = new OnClickListener() {
        public void onClick(View v) {
          Log.d(logtag,"onClick() called - lure button");              
          Toast.makeText(MainActivity.this, "The Lure button was clicked.", Toast.LENGTH_LONG).show();
          Log.d(logtag,"onClick() ended - lure button");
        }
    };
     
    // Create an anonymous implementation of OnClickListener
    private OnClickListener tackleListener = new OnClickListener() {
        public void onClick(View v) {
         Log.d(logtag,"onClick() called - tackle button"); 
         Toast.makeText(MainActivity.this, "The Stop button was clicked.", Toast.LENGTH_LONG).show();
          Log.d(logtag,"onClick() ended - tackle button");
        } 
    };
     
 // Create an anonymous implementation of OnClickListener
    private OnClickListener playListener = new OnClickListener() {
        public void onClick(View v) {
         Log.d(logtag,"onClick() called - play button"); 
         Toast.makeText(MainActivity.this, "The Play button was clicked.", Toast.LENGTH_LONG).show();
          Log.d(logtag,"onClick() ended - play button");
        } 
    };
     
    @Override
 protected void onStart() {//activity is started and visible to the user
  Log.d(logtag,"onStart() called");
  super.onStart();  
 }
 @Override
 protected void onResume() {//activity was resumed and is visible again
  Log.d(logtag,"onResume() called");
  super.onResume();
   
 }
 @Override
 protected void onPause() { //device goes to sleep or another activity appears
  Log.d(logtag,"onPause() called");//another activity is currently running (or user has pressed Home)
  super.onPause();
   
 }
 @Override
 protected void onStop() { //the activity is not visible anymore
  Log.d(logtag,"onStop() called");
  super.onStop();
   
 }
 @Override
 protected void onDestroy() {//android has killed this activity
   Log.d(logtag,"onDestroy() called");
   super.onDestroy();
 }
}
