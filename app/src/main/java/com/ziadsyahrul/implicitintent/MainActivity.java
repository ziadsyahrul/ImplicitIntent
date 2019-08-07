package com.ziadsyahrul.implicitintent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_audio)
    Button btnAudio;
    @BindView(R.id.btn_notification)
    Button btnNotification;
    @BindView(R.id.btn_wifi)
    Button btnWifi;
    @BindView(R.id.btn_email)
    Button btnEmail;
    @BindView(R.id.btn_sms)
    Button btnSms;
    @BindView(R.id.btn_telpon)
    Button btnTelpon;
    @BindView(R.id.btn_camera)
    Button btnCamera;
    @BindView(R.id.btn_browser)
    Button btnBrowser;
    @BindView(R.id.btn_alarm)
    Button btnAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_audio, R.id.btn_notification, R.id.btn_wifi, R.id.btn_email, R.id.btn_sms, R.id.btn_telpon, R.id.btn_camera, R.id.btn_browser, R.id.btn_alarm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_audio:
                startActivity(new Intent(MainActivity.this, AudioManagerActivity.class));
                break;
            case R.id.btn_notification:
                startActivity(new Intent(MainActivity.this, NotificationActivity.class));
                break;
            case R.id.btn_wifi:
                startActivity(new Intent(MainActivity.this, WifiActivity.class));
                break;
            case R.id.btn_email:
                startActivity(new Intent(MainActivity.this, EmailActivity.class));
                break;
            case R.id.btn_sms:
                startActivity(new Intent(MainActivity.this, SMSActivity.class));
                break;
            case R.id.btn_telpon:
                startActivity(new Intent(MainActivity.this, TelephoneActivity.class));
                break;
            case R.id.btn_camera:
                startActivity(new Intent(MainActivity.this, CameraActivity.class));
                break;
            case R.id.btn_browser:
                Uri link = Uri.parse("https://imastudio.co.id");
                Intent intentBrowser = new Intent(Intent.ACTION_VIEW, link);
                startActivity(intentBrowser);

                break;
            case R.id.btn_alarm:
                startActivity(new Intent(MainActivity.this, AlarmActivity.class));
                break;
        }
    }
}
