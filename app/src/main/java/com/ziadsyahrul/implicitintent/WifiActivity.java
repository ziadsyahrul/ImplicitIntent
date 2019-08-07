package com.ziadsyahrul.implicitintent;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WifiActivity extends AppCompatActivity {

    @BindView(R.id.swith_wifi)
    Switch swithWifi;
    @BindView(R.id.txt_status)
    TextView txtStatus;

    // TODO panggil class wifi manager
    WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        ButterKnife.bind(this);

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if (wifiManager.isWifiEnabled()) {
            swithWifi.setChecked(true);
            txtStatus.setText("Wifi Sudah Nyala");

        }else {

            swithWifi.setChecked(false);
            txtStatus.setText("Wifi belum nyala");
        }

        swithWifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    wifiManager.setWifiEnabled(true);
                    txtStatus.setText("wifi dinyalakan");
                }
                else if (isChecked == false) {
                    wifiManager.setWifiEnabled(false);
                    txtStatus.setText("wifi dimatikan");

                }
            }
        });
    }
}
