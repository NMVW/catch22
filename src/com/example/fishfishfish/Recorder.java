package com.example.fishfishfish;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder.AudioSource;

public class Recorder {

    private short[] recordedAudioBuffer;
    private int bufferRead;

    public short[] startRecording() {

        int recorderBufferSize = AudioRecord.getMinBufferSize(44100,
                AudioFormat.CHANNEL_CONFIGURATION_MONO,
                AudioFormat.ENCODING_PCM_16BIT) * 2;

        AudioRecord recorder = new AudioRecord(AudioSource.DEFAULT, 44100,
                AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT,
                recorderBufferSize);

        recordedAudioBuffer = new short[recorderBufferSize];
        recorder.startRecording();

        bufferRead = recorder.read(recordedAudioBuffer, 0, recorderBufferSize);

        synchronized (this) {
            try {
                this.wait(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        recorder.stop();
        recorder.release();
        return recordedAudioBuffer;

    }

    public int getBufferRead() {
        return bufferRead;
    }
}