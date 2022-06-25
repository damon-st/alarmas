#import "AlarmasPlugin.h"
#if __has_include(<alarmas/alarmas-Swift.h>)
#import <alarmas/alarmas-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "alarmas-Swift.h"
#endif

@implementation AlarmasPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftAlarmasPlugin registerWithRegistrar:registrar];
}
@end
