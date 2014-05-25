package com.example.fishfishfish;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SplashActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		
		
		// TODO Auto-generated method stub
	}
	
	 public void startGame(View view) {
	    	Intent intent = new Intent(this, MainActivity.class);
	    	startActivity(intent);
	    }

}
