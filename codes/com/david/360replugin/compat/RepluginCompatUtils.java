
package com.david.360replugin.compat;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Build;

import java.lang.reflect.Field;

/**
 * 360 replugin 适配 8.0
 * 插件 activity 的 screenOrientation 默认是 ActivityInfo.SCREEN_ORIENTATION_PORTRAIT，
 * 我们无法在AndroidManifest.xml 中自定义，所以 当 我们指定activity的 <item name="windowIsTranslucent">true</item> 时
 * 在8.0的手机上会奔溃，
 * 这里我们的解决方案是 使用反射 修改 Activity 类中的成员变量 mActivityInfo 的 screenOrientation，
 * 将screenOrientation这个值改为 ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
 * 此操作 需要在 onCreate（）之前调用
 * description: java class function
 * author: liyawei
 * date: 2019/4/12 11:33 AM
 */
public class RepluginCompatUtils {

    /**
     * super.onCreate(savedInstanceState) 之前 调用;
     */
    public static void onCreate(Activity activity) {
        if (activity == null) {
            return;
        }
        int requestedOrientation = activity.getRequestedOrientation();
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) {
            if (requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                    || requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                modifyRequestedOrientation(activity);
            }
        }
    }

    private static void modifyRequestedOrientation(Activity activity) {
        if (activity == null){
            return;
        }
        try {
            Field mActivityInfoField = ReflectUtils.getField(activity, "mActivityInfo");
            mActivityInfoField.setAccessible(true);
            ActivityInfo activityInfo = (ActivityInfo) mActivityInfoField.get(activity);
            activityInfo.screenOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }
}
