package com.geronimo.hnapp.util;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by 5013003250 on 12/23/2014.
 */
public class CommonUtil {
    private static String LOGTAG = "HNAPI";
    private static boolean toLogOrNotToLog = true;

    public static void logger(String s, Class clazz) {
        if (toLogOrNotToLog)
            Log.d(LOGTAG, s + "\n" + " :Class is:" + clazz.getName());

    }

    public static void logger(Throwable t, Class getClass) {
        if (toLogOrNotToLog)
            Log.d(LOGTAG,
                    Log.getStackTraceString(t) + "\nclass is:"
                            + getClass.getName());

    }

    public static void logger(Bundle bundle, Class clazz) {
        for (String key : bundle.keySet()) {
            CommonUtil.logger("key is: " + key + "  value: "
                    + bundle.get(key).toString(), clazz);
        }
    }

    public static boolean isEmpty(String inputText) {
        return TextUtils.isEmpty(inputText);
    }

}
