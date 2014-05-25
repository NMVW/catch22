package com.example.fishfishfish;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.media.MediaRecorder.AudioSource;
import android.media.audiofx.Visualizer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import java.lang.Math;
import com.example.fishfishfish.Recorder;




public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		//playFreq(20000);
		
//		 int recorderBufferSize = AudioRecord.getMinBufferSize(44100,
//	                AudioFormat.CHANNEL_CONFIGURATION_MONO,
//	                AudioFormat.ENCODING_PCM_16BIT) * 2;
//
//	        AudioRecord recorder = new AudioRecord(AudioSource.DEFAULT, 44100,
//	                AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT,
//	                recorderBufferSize);
//	        
//		recorder.startRecording();
//		short[] samples = new short[44100];
//		int sample_num = 85;
//
//		while(sample_num<95)
//		{
//			double sum = 0;
//			int num_samples = 0;
//			for (int i = 0; i < 10; i++)
//			{
//				recorder.read(samples, 0, samples.length);
//				
//				DoubleFFT_1D fft = new DoubleFFT_1D(200);
//				
//				double[] samplesD= new double[samples.length];
//				for (int j=0;j<samples.length;j++) {
//				    samplesD[j] = (double)samples[j];
//				}
//
//				fft.realForward(samplesD);
//				if(samplesD[sample_num%1100]>0)
//				{
//					sum+=samplesD[sample_num%1100];
//					num_samples++;
//				}
//			}
//			
//			Log.d("sample" + Integer.toString(sample_num%1100), Double.toString(sum/(double)num_samples));
//			sample_num++;    
//		}
		
//		int RECORDER_SAMPLERATE = 44100;
//		int MAX_FREQ = RECORDER_SAMPLERATE / 2;
//		final int RECORDER_CHANNELS = AudioFormat.CHANNEL_IN_MONO;
//		final int RECORDER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;
//		
//		int bufferSize = AudioRecord.getMinBufferSize(RECORDER_SAMPLERATE,
//		                RECORDER_CHANNELS, RECORDER_AUDIO_ENCODING);
//		short[] buffer = new short[bufferSize];
//		
//		AudioRecord recorder = new AudioRecord(MediaRecorder.AudioSource.MIC,
//		                RECORDER_SAMPLERATE, RECORDER_CHANNELS,
//		                RECORDER_AUDIO_ENCODING, bufferSize);
//		int FFT_SIZE = bufferSize / 2;
//		DoubleFFT_1D mFFT = new DoubleFFT_1D(FFT_SIZE);
//		int bufferReadResult = recorder.read(buffer, 0, bufferSize);
//
//		double[] fftRealArray = new double[bufferReadResult];
//		for (int i = 0; i < bufferReadResult; i++) {
//		    fftRealArray[i] = (double) buffer[i] / 32768;
//
//		}
//
//		mFFT.realForward(fftRealArray);
//		
//		float[]
//		decibel[i] = Math.pow(re[i], 2) + Math.pow(im[i], 2);
//		decibel[i] = 10 * Math.log10(decibel[i]);
		
		int channel_config = AudioFormat.CHANNEL_CONFIGURATION_MONO;
		int format = AudioFormat.ENCODING_PCM_16BIT;
		int sampleSize = 44100;
		int bufferSize = AudioRecord.getMinBufferSize(sampleSize, channel_config, format);
		AudioRecord audioInput = new AudioRecord(AudioSource.MIC, sampleSize, channel_config, format, bufferSize);
		
		short[] audioBuffer = new short[bufferSize];
		
		while(true)
		{
			double sum20 = 0;
			double sum19 = 0;
			for (int i = 0; i < 1; i++)
			{
				audioInput.startRecording();
				audioInput.read(audioBuffer, 0, bufferSize);
				
				double[] micBufferData = new double[bufferSize];
			    final int bytesPerSample = 2; // As it is 16bit PCM
			    final double amplification = 100.0; // choose a number as you like


			    for (int index = 0, floatIndex = 0; index < bufferSize - bytesPerSample + 1; index += bytesPerSample, floatIndex++) {
			        double sample = 0;
			        for (int b = 0; b < bytesPerSample; b++) {
			            int v = (int) audioBuffer[index + b];
			            if (b < bytesPerSample - 1 || bytesPerSample == 1) {
			                v &= 0xFF;
			            }
			            sample += v << (b * 8);
			        }
			        double sample32 = amplification * (sample / 32768.0);
			        micBufferData[floatIndex] = sample;
			    }
			    
			    Complex[] fftTempArray = new Complex[bufferSize];
			    for (int i1=0; i1<bufferSize; i1++)
			    {
			        fftTempArray[i1] = new Complex(micBufferData[i1], 0);
			    }
			    Complex[] fftArray = FFT.fft(fftTempArray);
			
			    sum20 += Math.log10(Math.sqrt(fftArray[3715].re()*fftArray[3715].re()+fftArray[3715].im()*fftArray[3715].im()));
			    sum20 += Math.log10(Math.sqrt(fftArray[3714].re()*fftArray[3714].re()+fftArray[3714].im()*fftArray[3714].im()));
			    sum20 += Math.log10(Math.sqrt(fftArray[3716].re()*fftArray[3716].re()+fftArray[3716].im()*fftArray[3716].im()));

			    
			    sum19 += Math.log10(Math.sqrt(fftArray[3530].re()*fftArray[3530].re()+fftArray[3530].im()*fftArray[3530].im()));
			    sum19 += Math.log10(Math.sqrt(fftArray[3529].re()*fftArray[3529].re()+fftArray[3529].im()*fftArray[3529].im()));
			    sum19 += Math.log10(Math.sqrt(fftArray[3531].re()*fftArray[3531].re()+fftArray[3531].im()*fftArray[3531].im()));
			
//			Log.d("Hz: " + Double.toString((( (1.0 * 44100) / (1.0 * bufferSize) ) * 3715/2)), Double.toString(sum20/1.0));
//			Log.d("Hz: " + Double.toString((( (1.0 * 44100) / (1.0 * bufferSize) ) * 3530/2)), Double.toString(sum19/1.0));
			
			//Log.d("Hz: 20000" , Double.toString(sum20/3.0));
			//Log.d("Hz: 19000", Double.toString(sum19/3.0));
			
		
	    
	  for(int i1 = 3712; i1 < 3718; i1++)
	    {
	    	//Log.d("Hz: " + Double.toString((( (1.0 * 44100) / (1.0 * bufferSize) ) * 3715/2)) +  " Index: " + Integer.toString(3715), Double.toString(Math.sqrt(fftArray[3715].re()*fftArray[3715].re()+fftArray[3715].im()*fftArray[3715].im())) );
	    	//Log.d("Hz: " + Double.toString((( (1.0 * 44100) / (1.0 * bufferSize) ) * 3530/2)) +  " Index: " + Integer.toString(3530), Double.toString(Math.sqrt(fftArray[3530].re()*fftArray[3530].re()+fftArray[3530].im()*fftArray[3530].im())) );
		  Log.d("Hz: " + Double.toString((( (1.0 * 44100) / (1.0 * bufferSize) ) * i1/2)) +  " Index: " + Integer.toString(i1), Double.toString(Math.log10(Math.sqrt(fftArray[i1].re()*fftArray[i1].re()+fftArray[i1].im()*fftArray[i1].im()))) );
	    	
	    				
	    }
		
	}
			
		}
		
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
