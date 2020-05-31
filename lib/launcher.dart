import 'dart:async';

import 'package:flutter/services.dart';

class Launcher {
  static const MethodChannel _channel = const MethodChannel('launcher');

  static Future<String> get launcherQQ async {
    return await _channel.invokeMethod('launcherQQ');
  }

  static Future<String> get launcherWX async {
    return await _channel.invokeMethod('launcherWX');
  }
}
