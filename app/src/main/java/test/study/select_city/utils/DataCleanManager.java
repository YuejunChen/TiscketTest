package test.study.select_city.utils;

import android.content.Context;

import java.io.File;


/**
 * Created by Mr.Chen on 2017/8/31.
 */
public class DataCleanManager {
    public static void cleanInternalCache(Context context) {
        deleteFilesByDirectory(context.getCacheDir());
    }

    private static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }
}
