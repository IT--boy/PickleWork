package com.pickcle.picklework.autojs;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.text.TextUtils;

import com.stardust.app.GlobalAppContext;
import com.stardust.autojs.core.util.ProcessShell;

import java.util.Locale;

/**
 * Created by Stardust on 2017/7/1.
 */

public class AccessibilityServiceTool {

    private static final String cmd = "enabled=$(settings get secure enabled_accessibility_services)\n" +
            "pkg=%s\n" +
            "if [[ $enabled == *$pkg* ]]\n" +
            "then\n" +
            "echo already_enabled\n" +
            "else\n" +
            "enabled=$pkg:$enabled\n" +
            "settings put secure enabled_accessibility_services $enabled\n" +
            "fi";

    public static boolean enableAccessibilityServiceByRoot(Context context, Class<? extends AccessibilityService> accessibilityService) {
        String serviceName = context.getPackageName() + "/" + accessibilityService.getName();
        try {
            return TextUtils.isEmpty(ProcessShell.execCommand(String.format(Locale.getDefault(), cmd, serviceName), true).error);
        } catch (Exception ignored) {
            return false;
        }
    }

    public static boolean enableAccessibilityServiceByRootAndWaitFor(Context context, long timeOut) {
        if (enableAccessibilityServiceByRoot(context, com.stardust.view.accessibility.AccessibilityService.class)) {
            com.stardust.view.accessibility.AccessibilityService.waitForEnabled(timeOut);
            return true;
        }
        return false;
    }

    public static void goToAccessibilitySetting() {
        GlobalAppContext.get().startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

}
