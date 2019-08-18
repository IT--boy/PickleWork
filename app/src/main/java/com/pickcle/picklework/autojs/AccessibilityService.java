package com.pickcle.picklework.autojs;

import android.accessibilityservice.AccessibilityServiceInfo;

import com.pickcle.picklework.Pref;


/**
 * Created by Stardust on 2017/12/8.
 */

public class AccessibilityService extends com.stardust.view.accessibility.AccessibilityService {


    @Override
    protected void onServiceConnected() {
        AccessibilityServiceInfo serviceInfo = getServiceInfo();
        if (Pref.isStableModeEnabled()) {
            serviceInfo.flags &= ~AccessibilityServiceInfo.FLAG_INCLUDE_NOT_IMPORTANT_VIEWS;
        } else {
            serviceInfo.flags |= AccessibilityServiceInfo.FLAG_INCLUDE_NOT_IMPORTANT_VIEWS;
        }
        setServiceInfo(serviceInfo);
        super.onServiceConnected();

    }
}
