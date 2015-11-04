package com.abhyaas.app.testbackgroundservice;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

public class BackgroundService extends Service {
    public BackgroundService() {
        CountDownTimer cdt = setupTimer();
        cdt.start();
    }

    private CountDownTimer setupTimer() {
        CountDownTimer cdt;

        cdt = new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d("tick",""+ (int) (millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                Log.d("tick","finished");
            }
        };

        return cdt;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
