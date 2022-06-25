import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'alarmas_method_channel.dart';

abstract class AlarmasPlatform extends PlatformInterface {
  /// Constructs a AlarmasPlatform.
  AlarmasPlatform() : super(token: _token);

  static final Object _token = Object();

  static AlarmasPlatform _instance = MethodChannelAlarmas();

  /// The default instance of [AlarmasPlatform] to use.
  ///
  /// Defaults to [MethodChannelAlarmas].
  static AlarmasPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [AlarmasPlatform] when
  /// they register themselves.
  static set instance(AlarmasPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }

  Future<bool?> showAlarma() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }

  Future<bool?> stropAlarma() {
    throw UnimplementedError('stropAlarma() has not been implemented.');
  }
}
