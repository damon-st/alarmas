import 'package:flutter_test/flutter_test.dart';
import 'package:alarmas/alarmas.dart';
import 'package:alarmas/alarmas_platform_interface.dart';
import 'package:alarmas/alarmas_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockAlarmasPlatform 
    with MockPlatformInterfaceMixin
    implements AlarmasPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final AlarmasPlatform initialPlatform = AlarmasPlatform.instance;

  test('$MethodChannelAlarmas is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelAlarmas>());
  });

  test('getPlatformVersion', () async {
    Alarmas alarmasPlugin = Alarmas();
    MockAlarmasPlatform fakePlatform = MockAlarmasPlatform();
    AlarmasPlatform.instance = fakePlatform;
  
    expect(await alarmasPlugin.getPlatformVersion(), '42');
  });
}
