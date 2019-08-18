package com.pickcle.picklework.util;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Method;



/**
 * Created by James on 2016/7/28.
 * Note:Sd卡操作工具类
 */
public class SdcardUtils {
    private static String FILE_NAME = "justcan";
    public static final String NO_FIND_EXT_SDCARD_ROOT_PATH = "noFindExtSdcardRootPath";
    /**
     * 闹钟路径
     */
    public static String alarmPath;
    /**
     * 缓存路径
     */
    public static String cachePath;
    /**
     * 根路径
     */
    public static String extSdcardRootPath;
    /**
     * 图片路径
     */
    public static String imagePath;
    public static String haPath;
    /**
     * 音频路径
     */
    public static String musicPath;
    /**
     * 包路径
     */
    public static String packageHaPath;
    /**
     * 提供者路径
     */
    public static String preferencesPath;
    /**
     * sd卡路径
     */
    public static String sdPath;
    /**
     * sd卡根路径
     */
    public static String sdcardRootPath;
    public static String stickerPath;
    /**
     * 视频路径
     */
    public static String videoPath;

    /**
     * 初始化SD卡的文件夹
     */
    public static void initSdcardFolders( Context context) {
        File file;
        File musicFile;
        File picFile;
        File movieFile;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            file = new File(Environment.getExternalStorageDirectory(), "");
            movieFile = context.getExternalFilesDir(Environment.DIRECTORY_MOVIES);
            picFile = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            musicFile = context.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        } else if (Build.MODEL.equals("ZTE U930HD")) {
            file = new File("/mnt/sdcard2");
            musicFile = null;
            picFile = null;
            movieFile = null;
        } else {
            file = context.getFilesDir();
            musicFile = null;
            picFile = null;
            movieFile = null;
        }
        SdcardUtils.packageHaPath = context.getFilesDir() + "/" + FILE_NAME + File.separator;
        SdcardUtils.haPath = file.toString() + "/" + FILE_NAME + "/";
        File shmsFile = new File(SdcardUtils.haPath);
        if (!shmsFile.exists()) {
            shmsFile.mkdirs();
        }
        SdcardUtils.sdPath = file.toString() + "/";
        String string = musicFile == null ? SdcardUtils.haPath + ".music/" : musicFile.toString() + "/";
        SdcardUtils.musicPath = string;
        SdcardUtils.cachePath = SdcardUtils.haPath + "cache/";
        SdcardUtils.stickerPath = SdcardUtils.haPath + ".stickerSvg/";
        SdcardUtils.alarmPath = SdcardUtils.haPath + "alarm/";
        SdcardUtils.preferencesPath = SdcardUtils.haPath + ".preferences/";


        SdcardUtils.mkDirs(context);
    }


    /**
     * 获取到内存卡的路径
     */
    public static String getStoragePath(Context context, boolean flag) {

        StorageManager storageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
        try {
            Class storageClass = Class.forName("android.os.storage.StorageVolume");
            Method method1 = storageManager.getClass().getMethod("getVolumeList", new Class[0]);

            Method method2 = storageClass.getMethod("getPath", new Class[0]);
            Method method3 = storageClass.getMethod("isRemovable", new Class[0]);
            Object object = method1.invoke(storageManager, new Object[0]);

            int n = Array.getLength(object);

            for (int i = 0; i < n; ++i) {
                Object object2 = Array.get(object, i);
                String path = (String) method2.invoke(object2, new Object[0]);

                boolean flagTemp = (Boolean) method3.invoke(object2, new Object[0]);
                if (flag == flagTemp)
                    return path;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return NO_FIND_EXT_SDCARD_ROOT_PATH;
        }

        return NO_FIND_EXT_SDCARD_ROOT_PATH;
    }

    /**
     * 获取内存卡状态
     */
    public static String getStorageState(Context context, String path) {
        try {
            StorageManager storageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
            return (String) StorageManager.class.getMethod("getVolumeState", String.class).invoke(storageManager, path);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static void mkDirs(Context context) {
        mkImageAndVideoDirs();
        if (!new File(cachePath).exists()) {
            new File(cachePath).mkdirs();
        }
        if (!new File(stickerPath).exists()) {
            new File(stickerPath).mkdirs();
        }
        if (!new File(alarmPath).exists()) {
            new File(alarmPath).mkdirs();
        }
        if (!new File(preferencesPath).exists()) {
            new File(preferencesPath).mkdirs();
        }
        if (!new File(musicPath).exists()) {
            new File(musicPath).mkdirs();
        }
        if (!new File(packageHaPath).exists()) {
            new File(packageHaPath).mkdirs();
            moveCacheFile(context);
        }
    }

    /**
     * 判断内存卡的状态
     */
    public static boolean isSdCardState() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 创建目录
     */
    public static void mkImageAndVideoDirs() {
        if (!new File(imagePath).exists()) {
            new File(imagePath).mkdirs();
        }
        if (!new File(videoPath).exists()) {
            new File(videoPath).mkdirs();
        }
    }

    /**
     * 删除文件
     */
    private static void moveCacheFile(Context context) {
        File file = new File(context.getFilesDir().toString());
        if (!file.exists() || !file.isDirectory()) return;
        for (File tempFile : file.listFiles()) {
            if (!tempFile.isFile() || tempFile.getName().startsWith("jpush") || tempFile.getName().startsWith("mob") || tempFile.getName().startsWith("td") || tempFile.getName().startsWith("TDtcagent") || tempFile.getName().startsWith("umeng") || tempFile.getName().startsWith("weibo") || tempFile.getName().startsWith("com"))
                continue;
            tempFile.renameTo(new File(SdcardUtils.packageHaPath + tempFile.getName()));
        }
    }

}
