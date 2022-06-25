import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'alarmas_platform_interface.dart';

/// An implementation of [AlarmasPlatform] that uses method channels.
class MethodChannelAlarmas extends AlarmasPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('alarmas');

  @override
  Future<String?> getPlatformVersion() async {
    final version =
        await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }

  @override
  Future<bool?> showAlarma() async {
    try {
      return await methodChannel.invokeMethod<bool>("alarma");
    } on PlatformException catch (e) {
      return false;
    }
  }

  @override
  Future<bool?> stropAlarma() async {
    try {
      return await methodChannel.invokeMethod<bool>("stopAlarma");
    } on PlatformException catch (e) {
      return false;
    }
  }
}
