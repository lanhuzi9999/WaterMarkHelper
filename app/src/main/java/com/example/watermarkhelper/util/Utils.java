package com.example.watermarkhelper.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Looper;

/**
 * @author: robin
 * @description:
 * @date: 2015/7/01
 **/
public class Utils {
    /**
     * 是否ui线程
     * @param context
     * @return
     */
    public static boolean isUIThread(Context context){
        Thread curthread = Thread.currentThread() ;
        Looper curloop = context.getMainLooper() ;
        Thread loopthread = curloop.getThread() ;
        return curthread.getId() == loopthread.getId();
    }

    public static Activity getRootActivity(Activity activity){
        Activity root = activity ;
        while(root != null && root.getParent() != null){
            root = root.getParent();
        }
        return root ;
    }

    public static PackageInfo getPackageInfo(PackageManager pm, String packageName, int flags){
        PackageInfo pkgInfo = null;
        try {
            pkgInfo = pm.getPackageInfo(packageName, flags);
            //2017.7.21 Mic:360Q5读取到的versionCode=2147482647=0x7FFFFC17，占大部分，显然是故意的，针对这个问题进行处理
            if ((pkgInfo.versionCode & 0x7FFF0000) == 0x7FFF0000){ //把它判为360Q5，用getPackageArchiveInfo
                ApplicationInfo ai = pkgInfo.applicationInfo;
                PackageInfo pkgsrc = pm.getPackageArchiveInfo(ai.publicSourceDir, PackageManager.GET_ACTIVITIES);
                if (pkgsrc != null){
                    pkgInfo.versionCode = pkgsrc.versionCode;
                }
            }

        } catch (PackageManager.NameNotFoundException e) {
            CustomLog.w("", "getPackageInfo fail, reason="+e);
        }
        return pkgInfo ;
    }
}
