package org.plugin.launcher;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;

import java.lang.reflect.Method;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * LauncherPlugin
 */
public class LauncherPlugin implements FlutterPlugin, MethodCallHandler {
    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        final MethodChannel channel = new MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "launcher");
        channel.setMethodCallHandler(new LauncherPlugin());
    }

    // This static function is optional and equivalent to onAttachedToEngine. It supports the old
    // pre-Flutter-1.12 Android projects. You are encouraged to continue supporting
    // plugin registration via this function while apps migrate to use the new Android APIs
    // post-flutter-1.12 via https://flutter.dev/go/android-project-migration.
    //
    // It is encouraged to share logic between onAttachedToEngine and registerWith to keep
    // them functionally equivalent. Only one of onAttachedToEngine or registerWith will be called
    // depending on the user's project. onAttachedToEngine or registerWith must both be defined
    // in the same class.
    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "launcher");
        channel.setMethodCallHandler(new LauncherPlugin());
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        if (call.method.equals("launcherQQ")) {
            Context context = getApplication();
            PackageManager manager = context.getPackageManager();
            String packageName = "com.tencent.mobileqq";
            Intent intent = manager.getLaunchIntentForPackage(packageName);
            if (intent == null || intent.resolveActivity(manager) == null) {
                result.success("0");
            } else {
                context.startActivity(intent);
                result.success("1");
            }
        } else if (call.method.equals("launcherWX")) {
            Context context = getApplication();
            PackageManager manager = context.getPackageManager();
            String packageName = "com.tencent.mm";
            Intent intent = manager.getLaunchIntentForPackage(packageName);
            if (intent == null || intent.resolveActivity(manager) == null) {
                result.success("0");
            } else {
                context.startActivity(intent);
                result.success("1");
            }
        } else if (call.method.equals("isInstallJD")) {
            PackageManager manager = getApplication().getPackageManager();
            try {
                String packageName = "com.jingdong.app.mall";
                PackageInfo info = manager.getPackageInfo(packageName, PackageManager.GET_GIDS);
                if (info != null) {
                    result.success("1");
                    return;
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            result.success("0");
        } else {
            result.notImplemented();
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    }

    private Application getApplication() {
        try {
            Class cls = Class.forName("android.app.ActivityThread");
            Method method = cls.getMethod("currentApplication");
            return (Application) method.invoke(null, (Object[]) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
