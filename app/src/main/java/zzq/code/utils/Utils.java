package zzq.code.utils;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.TypedValue;

/**
 * Created by 喜欢、陪你看风景 on 2017/9/21.
 */

public class Utils {
    //全局上下文环境
    private static Application sApplication;

    public static void init(@NonNull Application application){
        sApplication = NullUtil.checkNonNull(application,"初始化Utils的Application对象不可为Null!");
    }

    public static class DimenUtil{

        public static float dp2px(float dp){
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,sApplication.getResources().getDisplayMetrics());
        }

        public static float sp2px(float sp){
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,sp,sApplication.getResources().getDisplayMetrics());
        }


    }




    public static class NullUtil{

        public static  <T> T checkNonNull(T obj){
            if (obj == null){
                throw new NullPointerException();
            }

            return obj;
        }

        public static  <T> T checkNonNull(T obj,String errorTip){
            if (obj == null){
                throw new NullPointerException(errorTip);
            }

            return obj;
        }
    }
}
