package com.example.tp1;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private ModoAvionReceiver modoAvionReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        modoAvionReceiver = new ModoAvionReceiver();

        obtenerPermisos();

;    }

    @Override
    protected void onResume() {
        super.onResume();
        // "android.intent.action.AIRPLANE_MODE"
        registerReceiver(modoAvionReceiver, new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED));

    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(modoAvionReceiver);
    }

    private void obtenerPermisos() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE}, 1);
        }
    }
}