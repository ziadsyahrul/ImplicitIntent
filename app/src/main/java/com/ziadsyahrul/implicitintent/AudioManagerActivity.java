package com.ziadsyahrul.implicitintent;

import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AudioManagerActivity extends AppCompatActivity {

    @BindView(R.id.btn_ring)
    Button btnRing;
    @BindView(R.id.btn_silent)
    Button btnSilent;
    @BindView(R.id.btn_vibrate)
    Button btnVibrate;

    // TODO 1, Panggil class audio manager
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_manager);
        ButterKnife.bind(this);

        // TODO 2, atur audio manager
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
    }

    @OnClick({R.id.btn_ring, R.id.btn_silent, R.id.btn_vibrate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_ring:
                // TODO 3, atur mode ring, ada perbedaan versi android M (marhsmello) keatas
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    if (audioManager.getMode() == AudioManager.RINGER_MODE_NORMAL) {
                        Toast.makeText(this, "Sudah dalam mode normal", Toast.LENGTH_SHORT).show();


                    } else {
                        audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                        Toast.makeText(this, "Mode Normal", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    Toast.makeText(this, "Mode Normal", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_silent:
                // TODO 4, atur mode ring, ada perbedaan versi android M (marhsmello) keatas
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    if (audioManager.getMode() == AudioManager.RINGER_MODE_SILENT) {
                        Toast.makeText(this, "Sudah dalam mdoe silent", Toast.LENGTH_SHORT).show();


                    }
                    else {
                        audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                        Toast.makeText(this, "Mode Silent", Toast.LENGTH_SHORT).show();
                    }
                    audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                    Toast.makeText(this, "Mode Silent", Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.btn_vibrate:
                // TODO 5, atur mode ring, ada perbedaan versi android M (marhsmello) keatas
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    if (audioManager.getMode() == AudioManager.RINGER_MODE_VIBRATE) {
                        Toast.makeText(this, "Sudah dalam mode vibrate", Toast.LENGTH_SHORT).show();


                    } else {
                        audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                        Toast.makeText(this, "Mode Getar", Toast.LENGTH_SHORT).show();
                    }
                    audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                    Toast.makeText(this, "Mode Getar", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
