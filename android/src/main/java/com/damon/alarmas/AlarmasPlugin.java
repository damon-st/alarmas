package com.damon.alarmas;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/** AlarmasPlugin */
public class AlarmasPlugin implements FlutterPlugin, MethodCallHandler, ActivityAware {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;

  Activity activity;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "alarmas");
    channel.setMethodCallHandler(this);
  }

  @SuppressLint("RestrictedApi")
  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    } else if(call.method.equals("alarma")) {
      try{
//        Intent i = new Intent(activity,AlarmaService.class);
//        activity.startService(i);
        Data.Builder data = new Data.Builder();
        data.put("play","true");
        Constraints constraints = new Constraints.Builder()
                .setRequiresBatteryNotLow(false)
                .setRequiresCharging(false)
                .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                .build();
        WorkRequest
                workRequest = new OneTimeWorkRequest.Builder(Alarmas.class)
                .addTag("ConductorCancelacion")
                .setConstraints(constraints)
                .setInputData(data.build())
                .build();

        WorkManager.getInstance(activity).enqueue(workRequest);
        result.success(true);
      }catch (Exception e){
        Log.i("DAMON", "error "+e.getMessage());
        result.success(true);
      }
    }else if(call.method.equals("stopAlarma")){
      try{
//        Intent i = new Intent(activity,AlarmaService.class);
//        activity.stopService(i);
        Data.Builder data = new Data.Builder();
        data.put("play","false");
        Constraints constraints = new Constraints.Builder()
                .setRequiresBatteryNotLow(false)
                .setRequiresCharging(false)
                .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                .build();
        WorkRequest
                workRequest = new OneTimeWorkRequest.Builder(Alarmas.class)
                .addTag("ConductorCancelacion")
                .setConstraints(constraints)
                .setInputData(data.build())
                .build();

        WorkManager.getInstance(activity).enqueue(workRequest);
        result.success(true);

      }catch (Exception e){
        Log.i("DAMON", "error "+e.getMessage());
        result.success(true);
      }
    }else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }

  @Override
  public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
  activity = binding.getActivity();
  }

  @Override
  public void onDetachedFromActivityForConfigChanges() {

  }

  @Override
  public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {
    activity =binding.getActivity();
  }

  @Override
  public void onDetachedFromActivity() {

  }
}
