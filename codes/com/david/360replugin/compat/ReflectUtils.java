
package com.david.360replugin.compat;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * description: java class function
 * author: liyawei
 * date: 2019/4/12 11:26 AM
 */
public class ReflectUtils {

    /**
     *  从 当前 类 及 父类 中获取 指定 方法
     */
    public static Field getField(Object object, String name) {
        if (object == null) {
            return null;
        }
        Class clazz = object.getClass();
        while (clazz != null){
            LogUtil.e(" clazz ==== " + clazz.getName());
            Field field = null;
            try {
                field = clazz.getDeclaredField(name);
                if (field != null) {
                    LogUtil.e("==== field found" + clazz.getName());
                    return field;
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            clazz = clazz.getSuperclass();
        }
        return null;
    }

}
