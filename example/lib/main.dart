import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:launcher/launcher.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: const Text('Plugin example app')),
        body: Center(
          child: Column(
            children: <Widget>[
              OutlineButton(
                onPressed: () {
                  Launcher.launcherQQ.then((value) {
                    print('launcherQQ -> $value');
                  });
                },
                child: Text('launcherQQ'),
              ),
              OutlineButton(
                onPressed: () {
                  Launcher.launcherWX.then((value) {
                    print('launcherWX -> $value');
                  });
                },
                child: Text('launcherWX'),
              )
            ],
            mainAxisAlignment: MainAxisAlignment.center,
          ),
        ),
      ),
    );
  }
}
