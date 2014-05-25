package com.example.fishfishfish;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.audiofx.Visualizer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		//playFreq(20000);
		Visualizer visualizer = null;
		visualizer.setEnabled(true);
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	void playFreq(float frequency)
	{
		
		short[] buffer = new short[1024];
		@SuppressWarnings("deprecation")
		AudioTrack track = new AudioTrack(AudioManager.STREAM_MUSIC, 44100, AudioFormat.CHANNEL_CONFIGURATION_MONO, AudioFormat.ENCODING_PCM_16BIT, 1024, AudioTrack.MODE_STREAM);
		float increment = (float)(2*Math.PI) * frequency / 44100; // angular increment for each sample
		float angle = 0;
		float samples[] = new float[1024];
		track.play();

		while (true) 
		{
			for (int i = 0; i < samples.length; i++) 
			{
			  samples[i] = (float) Math.sin(angle);   //the part that makes this a sine wave....
			  buffer[i] = (short) (samples[i] * Short.MAX_VALUE);
			  angle += increment;
			}
			track.write( buffer, 0, samples.length );  //write to the audio buffer.... and start all over again!         
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
