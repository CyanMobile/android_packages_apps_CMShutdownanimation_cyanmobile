package com.cyanogenmod.cmshutdownanimation;

import android.os.Bundle;
import android.os.Environment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import android.util.Log;

import com.cyanogenmod.cmshutdownanimation.R;


public class mvsduiReceiver extends BroadcastReceiver {

    private static final String TAG = "CMShut-Move";
    public static final String mvSdUi = "com.cyanogenmod.cmshutdownanimation.MOVE_SHUTDOWNANIMATION";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v(TAG, "Action: " + intent.getAction());
        if (intent.getAction().equals(mvSdUi)) {
            String fileName = intent.getStringExtra("fileName");
            File fromFile = new File(fileName);
            Log.v(TAG, "file: " + fileName);
            if (fromFile.exists()) {
                try {
                    FileInputStream infile = new FileInputStream(fromFile);
                    FileOutputStream outfile = context.openFileOutput("shutdownanimation.zip", Context.MODE_WORLD_READABLE);

                    byte[] buf = new byte[100000];
                    int i = 0;
                    while ((i = infile.read(buf)) != -1) {
                        outfile.write(buf, 0, i);
                    }
                    if (infile != null) infile.close();
                    if (outfile != null) outfile.close();
                } catch (Exception e) {
                    Log.e(TAG, "Error: " + e);
                }
                Toast.makeText(context, R.string.move_ui, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
