package com.ziadsyahrul.implicitintent;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TelephoneActivity extends AppCompatActivity {

    @BindView(R.id.edt_nomor_telpon)
    EditText edtNomorTelpon;
    @BindView(R.id.btn_panggil)
    Button btnPanggil;
    @BindView(R.id.btn_dial_phone)
    Button btnDialPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telephone);
        ButterKnife.bind(this);

        // TODO Cek permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                Toast.makeText(this, "Permission sms sudah di aktifkan", Toast.LENGTH_SHORT).show();
            }
            else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 2);
            }
        }
    }

    @SuppressLint("MissingPermission")
    @OnClick({R.id.edt_nomor_telpon, R.id.btn_panggil, R.id.btn_dial_phone})
    public void onViewClicked(View view) {

        String noTelp = edtNomorTelpon.getText().toString().trim();

        switch (view.getId()) {
            case R.id.edt_nomor_telpon:

                // TODO atur intent ke kontak
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);

                // TODO atur data kembali dari kontak
                startActivityForResult(intent, 1);

                break;
            case R.id.btn_panggil:

                if (TextUtils.isEmpty(noTelp)) {
                    Toast.makeText(this, "Tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intentCall = new Intent(Intent.ACTION_CALL);
                    intentCall.setData(Uri.parse("tel:" + noTelp));
                    startActivity(intentCall);
                }

                break;
            case R.id.btn_dial_phone:

                if (TextUtils.isEmpty(noTelp)) {
                    Toast.makeText(this, "Tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intentDial = new Intent(Intent.ACTION_DIAL);
                    intentDial.setData(Uri.parse("tel:" + noTelp));
                    startActivity(intentDial);
                }

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                Cursor cursor = null;
                Uri uri = data.getData();

                cursor = getContentResolver().query(uri, new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},
                        null,
                        null,
                        null);

                if (cursor != null && cursor.moveToNext()) {
                    String noTelpon = cursor.getString(0);
                    edtNomorTelpon.setText(noTelpon);
                }
            }
        }
    }
}
