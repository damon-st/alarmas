import 'alarmas_platform_interface.dart';

class Alarmas {
  Future<String?> getPlatformVersion() {
    return AlarmasPlatform.instance.getPlatformVersion();
  }

  Future<bool?> showALarma() {
    return AlarmasPlatform.instance.showAlarma();
  }

  Future<bool?> stopALarma() {
    return AlarmasPlatform.instance.stropAlarma();
  }
}
