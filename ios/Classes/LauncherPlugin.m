#import "LauncherPlugin.h"

@implementation LauncherPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  FlutterMethodChannel* channel = [FlutterMethodChannel
      methodChannelWithName:@"launcher"
            binaryMessenger:[registrar messenger]];
  LauncherPlugin* instance = [[LauncherPlugin alloc] init];
  [registrar addMethodCallDelegate:instance channel:channel];
}

- (void)handleMethodCall:(FlutterMethodCall*)call result:(FlutterResult)result {
  if ([@"launcherQQ" isEqualToString:call.method]) {
      NSString * value = @"0";
      NSURL * url = [NSURL URLWithString:@"mqq://"];
      if ([[UIApplication sharedApplication] canOpenURL:url]) {
          [[UIApplication sharedApplication] openURL:url];
          value = @"1";
      }
      result(value);
  } else if ([@"launcherWX" isEqualToString:call.method]) {
      NSString * value = @"0";
      NSURL * url = [NSURL URLWithString:@"weixin://"];
      if ([[UIApplication sharedApplication] canOpenURL:url]) {
          [[UIApplication sharedApplication] openURL:url];
          value = @"1";
      }
      result(value);
  } else if ([@"isInstallJD" isEqualToString:call.method]) {
    NSString * value = @"0";
    if ([[UIApplication sharedApplication] canOpenURL:[NSURL URLWithString:@"openApp.jdMobile://"]]) {
        value = @"1";
    }
    result(value);
  } else {
    result(FlutterMethodNotImplemented);
  }
}

@end
