package com.ziadsyahrul.implicitintent;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotificationActivity extends AppCompatActivity {

    @BindView(R.id.btn_notification)
    Button btnNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.btn_notification)
    public void onViewClicked() {

        // atur notification manager
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        String channelId = "1";
        String channelName = "notif";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(mChannel);


            // TODO 6, atur tampilan notifikasi
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle("Notifikasi boss")
                    .setContentText("Hello brother")
                    .setAutoCancel(true);

            // TODO 7, atur lama getar dan suara notifikasi
            long[] getar = {500, 500, 500, 500, 500, 500};
            Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            // TODO 8, event ketika notifikasi diklik
            Intent resulIntent = new Intent(this, NotificationActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, resulIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            // TODO 9, eksekusi pending intent
            builder.setContentIntent(pendingIntent);
            builder.setSound(sound);
            builder.setVibrate(getar);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            notificationManager.notify(1, builder.build());
//        }

            // TODO 10, action notification
            notificationManager.notify(1, builder.build());


        }
    }
}
