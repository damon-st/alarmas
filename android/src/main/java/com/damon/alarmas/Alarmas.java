package com.damon.alarmas;

import static android.content.Context.BIND_AUTO_CREATE;
import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public  class Alarmas  extends Worker {

  private final NotificationManager mNotificationManager;

  public Alarmas(@NonNull Context context, @NonNull WorkerParameters workerParams) {
    super(context, workerParams);
    mNotificationManager = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

  }

  @NonNull
  @Override
  public Result doWork() {
   String r =  getInputData().getString("play");
    showNotification(r);
    return Result.success();
  }

  void showNotification(String tipo) {
    try
    {
      if(tipo.equals("true")){
//        Uri alarmSound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + getApplicationContext().getPackageName() + "/raw/megatron");
//        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), alarmSound);
//        r.play();
        Intent i = new Intent(getApplicationContext(), AlarmaService.class);
        getApplicationContext().stopService(i);
        getApplicationContext().startService(i);
      }else{
//        Uri alarmSound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + getApplicationContext().getPackageName() + "/raw/megatron");
//        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), alarmSound);
//        r.stop();
        Intent i = new Intent(getApplicationContext(), AlarmaService.class);
        getApplicationContext().stopService(i);
      }


    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
