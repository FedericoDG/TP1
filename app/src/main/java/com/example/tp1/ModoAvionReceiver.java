package com.example.tp1;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class ModoAvionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String accion = intent.getAction();

        // "android.intent.action.AIRPLANE_MODE"
        if (Intent.ACTION_AIRPLANE_MODE_CHANGED.equals(accion)) {
            boolean modoAvionActivo = intent.getBooleanExtra("state", false);

            if (modoAvionActivo) {
                Intent newIntent = new Intent(Intent.ACTION_CALL);
                newIntent.setData(Uri.parse("tel:2664553747"));

                if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(newIntent);
                }
            } else {
                Toast.makeText(context, "Modo avión desactivado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}