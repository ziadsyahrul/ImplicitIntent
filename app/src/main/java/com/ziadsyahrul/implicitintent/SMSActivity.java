package com.ziadsyahrul.implicitintent;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
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

public class SMSActivity extends AppCompatActivity {

    @BindView(R.id.edt_nomor_tujuan)
    EditText edtNomorTujuan;
    @BindView(R.id.edt_body_sms)
    EditText edtBodySms;
    @BindView(R.id.btn_sms_langsung)
    Button btnSmsLangsung;
    @BindView(R.id.btn_sms_aplikasi)
    Button btnSmsAplikasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        ButterKnife.bind(this);

        // TODO Cek permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
                Toast.makeText(this, "Permission sms sudah di aktifkan", Toast.LENGTH_SHORT).show();
            }
            else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 2);
            }
        }
    }

    @OnClick({R.id.edt_nomor_tujuan, R.id.btn_sms_langsung, R.id.btn_sms_aplikasi})
    public void onViewClicked(View view) {

        // TODO ambil inputan dari user
        String noTelp = edtNomorTujuan.getText().toString().trim();
        String bodySms = edtBodySms.getText().toString().trim();

        switch (view.getId()) {
            case R.id.edt_nomor_tujuan:

                // TODO atur intent ke kontak
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);

                // TODO atur data kembali dari kontak
                startActivityForResult(intent, 1);

                break;
            case R.id.btn_sms_langsung:

                if (TextUtils.isEmpty(noTelp) || TextUtils.isEmpty(bodySms)){
                    Toast.makeText(this, "Tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
                else {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(noTelp, null, bodySms, null, null);
                    Toast.makeText(this, "Berhasil kirim sms", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_sms_aplikasi:

                if (TextUtils.isEmpty(noTelp) || TextUtils.isEmpty(bodySms)){
                    Toast.makeText(this, "Tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent2 = new Intent(Intent.ACTION_SENDTO);
                    intent2.setData(Uri.parse("smsto:" + Uri.encode(noTelp)));
                    intent2.putExtra("sms_body", bodySms);
                    startActivity(intent2);
                }

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // TODO ambil data dari kontak
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
                    edtNomorTujuan.setText(noTelpon);
                }
            }
        }

        if (requestCode == 2) {
            if (resultCode == RESULT_OK){
                Toast.makeText(this, "permission sms diaktifkan", Toast.LENGTH_SHORT).show();
            }

            else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "permission batal", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
