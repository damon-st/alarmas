package com.damon.alarmas;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public  class AlarmaService extends Service {

  static Ringtone ringtone;
  private final IBinder mBinder = new LocalBinder();

  @Override
  public void onCreate() {
    super.onCreate();

  }

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    return mBinder;
  }
  @Override
  public int onStartCommand(Intent intent, int flags, int startId)
  {
    Uri alarmSound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + getApplicationContext().getPackageName() + "/raw/megatron");
    ringtone= RingtoneManager.getRingtone(getApplicationContext(), alarmSound);
    if(!ringtone.isPlaying()){
      ringtone.play();
    }
    return START_NOT_STICKY;
  }
  @Override
  public void onDestroy() {
    ringtone.stop();
    super.onDestroy();
  }

  public class LocalBinder extends Binder {
    AlarmaService getService() {
      return AlarmaService.this;
    }
  }
}
