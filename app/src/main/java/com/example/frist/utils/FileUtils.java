package com.example.frist.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by ASUS on 2015/3/18.
 */
public class FileUtils {
    public static File createFile(String sFile) throws IOException {
        File file = new File(sFile);
        File filePrt = file.getParentFile();
        if (filePrt != null &&!filePrt.exists()) {
            filePrt.mkdirs();
        }
        file.createNewFile();
        return file;
    }

    public static boolean deleteFile(String filePath){
        boolean result = false;
        if (StringUtils.isNotEmpty(filePath)){
            File targetFile = new File(filePath);
            if (targetFile.exists()){
                if (targetFile.isDirectory()){
                    File[] targetFiles = targetFile.listFiles();
                    for (File temp:targetFiles){
                        deleteFile(temp.getAbsolutePath());
                    }
                    DebugLog.e("delete:\t"+targetFile.getAbsolutePath());
                    targetFile.delete();
                }else if (targetFile.isFile()){
                    DebugLog.e("delete:\t"+targetFile.getAbsolutePath());
                    targetFile.delete();
                }
                result = true;
            }
        }
        return result;
    }
    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * 保存二进制流到指定路径
     *
     * @param instream
     * @param filepath
     */
    public void saveFile(InputStream instream, String filepath) {
        if (!isExternalStorageWritable()) {
            DebugLog.d( "SD卡不可用，保存失败");
            return;
        }

        File file = new File(filepath);

        try {
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int cnt = 0;

            while ((cnt = instream.read(buffer)) != -1) {
                fos.write(buffer, 0, cnt);
            }

            instream.close();
            fos.close();

        } catch (FileNotFoundException e) {
            DebugLog.d( e.getMessage());
        } catch (IOException e) {
            DebugLog.d( e.getMessage());
        }
    }

    /**
     * Copy file
     *
     * @param from
     *            origin file path
     * @param to
     *            target file path
     */
    public void copyFile(String from, String to) {
        if (!isExternalStorageWritable()) {
            DebugLog.d("SD卡不可用，保存失败");
            return;
        }

        File fileFrom = new File(from);
        File fileTo = new File(to);

        try {

            FileInputStream fis = new FileInputStream(fileFrom);
            FileOutputStream fos = new FileOutputStream(fileTo);
            byte[] buffer = new byte[1024];
            int cnt = 0;

            while ((cnt = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, cnt);
            }

            fis.close();
            fos.close();

        } catch (FileNotFoundException e) {
            DebugLog.d( e.getMessage());
        } catch (IOException e) {
            DebugLog.d( e.getMessage());
        }
    }

    /**
     * 保存 JSON 字符串到指定文件
     *
     * @param json
     * @param filepath
     */
    public boolean saveJson(String json, String filepath) {
        if (!isExternalStorageWritable()) {
            DebugLog.d( "SD卡不可用，保存失败");
            return false;
        }

        File file = new File(filepath);

        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(json.getBytes());
            fos.close();

        } catch (FileNotFoundException e) {
            DebugLog.d(e.getMessage());
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    /**
     * 删除指定的 JSON 文件
     *
     * @param filepath
     * @return
     */
    public boolean deleteJson(String filepath) {
        if (!isExternalStorageWritable()) {
            DebugLog.d("SD卡不可用，保存失败");
            return false;
        }

        File file = new File(filepath);

        if (file.exists()) {
            file.delete();
        }

        return false;
    }

    /**
     * 从指定文件读取 JSON 字符串
     *
     * @param filepath
     * @return
     */
    public String readJson(String filepath) {
        if (!isExternalStorageWritable()) {
            DebugLog.d("SD卡不可用，保存失败");
            return null;
        }

        File file = new File(filepath);
        StringBuilder sb = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file)));
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            reader.close();

        } catch (FileNotFoundException e) {
           // Logger.d(TAG, e.getMessage());
        } catch (IOException e) {
           // Logger.d(TAG, e.getMessage());
        }

        return sb.toString();
    }

    /**
     * 保存图片到制定路径
     *
     * @param filepath
     * @param bitmap
     */
    public void saveBitmap(String filepath, Bitmap bitmap) {
        if (!isExternalStorageWritable()) {
            DebugLog.d( "SD卡不可用，保存失败");
            return;
        }

        if (bitmap == null) {
            return;
        }

        try {
            File file = new File(filepath);
            FileOutputStream outputstream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputstream);
            outputstream.flush();
            outputstream.close();
        } catch (FileNotFoundException e) {
            //Logger.d(TAG, e.getMessage());
        } catch (IOException e) {
          //  Logger.d(TAG, e.getMessage());
        }
    }

    /**
     * 删除 Files 目录下所有的图片
     *
     * @return
     */
    public boolean cleanCache(Context context) {
        if (!isExternalStorageWritable()) {
            DebugLog.d("SD卡不可用，保存失败");
            return false;
        }

        File dir = context.getExternalFilesDir(null);

        if (dir != null) {
            for (File file : dir.listFiles()) {
                file.delete();
            }
        }

        return true;
    }

    /**
     * 计算 Files 目录下图片的大小
     *
     * @return
     */
    public String getCacheSize(Context context) {
        if (!isExternalStorageWritable()) {
            //Logger.d(TAG, "SD卡不可用，保存失败");
            return null;
        }

        long sum = 0;
        File dir = context.getExternalFilesDir(null);

        if (dir != null) {
            for (File file : dir.listFiles()) {
                sum += file.length();
            }
        }

        if (sum < 1024) {
            return sum + "字节";
        } else if (sum < 1024 * 1024) {
            return (sum / 1024) + "K";
        } else {
            return (sum / (1024 * 1024)) + "M";
        }
    }

    /**
     * 返回当前应用 SD 卡的绝对路径 like
     * /storage/sdcard0/Android/data/com.example.test/files
     */
    public String getAbsolutePath(Context context) {
        File root = context.getExternalFilesDir(null);

        if (root != null) {
            return root.getAbsolutePath();
        }

        return null;
    }

    /**
     * 返回当前应用的 SD卡缓存文件夹绝对路径 like
     * /storage/sdcard0/Android/data/com.example.test/cache
     */
    public String getCachePath(Context context) {
        File root = context.getExternalCacheDir();

        if (root != null) {
            return root.getAbsolutePath();
        }

        return null;
    }

    public boolean isBitmapExists(String filename,Context context) {
        File dir = context.getExternalFilesDir(null);
        File file = new File(dir, filename);

        return file.exists();
    }
    public boolean isFileExists(String filename) {

        File file = new File(filename);
        return file.exists();
    }


    /* 判断文件MimeType的method */
    public static String getMIMEType(File f) {
        String type = "";
        String fName = f.getName();
		/* 取得扩展名 */
        String end = fName
                .substring(fName.lastIndexOf(".") + 1, fName.length())
                .toLowerCase();

		/* 依扩展名的类型决定MimeType */
        if (end.equals("m4a") || end.equals("mp3") || end.equals("mid")
                || end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {
            type = "audio";
        } else if (end.equals("3gp") || end.equals("mp4")) {
            type = "video";
        } else if (end.equals("jpg") || end.equals("gif") || end.equals("png")
                || end.equals("jpeg") || end.equals("bmp")) {
            type = "image";
        } else if (end.equals("apk")) {
			/* android.permission.INSTALL_PACKAGES */
            type = "application";
        } else if (end.equals("ppt") || end.equals("pptx")) {
			/* ppt */
            type = "application";
        } else if (end.equals("xls") || end.equals("xlsx")) {
			/* excel */
            type = "application";
        } else if (end.equals("doc") || end.equals("docx")) {
			/* word */
            type = "application";
        } else if (end.equals("chm")) {
			/* android.permission.INSTALL_PACKAGES */
            type = "application";
        } else if (end.equals("pdf")) {
			/* android.permission.INSTALL_PACKAGES */
            type = "application";
        } else if (end.equals("txt") || end.equals("log")) {
			/* android.permission.INSTALL_PACKAGES */
            type = "text";
        } else {
            type = "*";
        }
		/* 如果无法直接打开，就跳出软件列表给用户选择 */
        if (end.equals("apk")) {
            type += "/vnd.android.package-archive";
        } else if (end.equals("ppt") || end.equals("pptx")) {
			/* ppt */
            type += "/vnd.ms-powerpoint";
        } else if (end.equals("xls") || end.equals("xlsx")) {
			/* excel */
            type += "/vnd.ms-excel";
        } else if (end.equals("doc") || end.equals("docx")) {
			/* word */
            type += "/msword";
        } else if (end.equals("chm")) {
			/* android.permission.INSTALL_PACKAGES */
            type += "/x-chm";
        } else if (end.equals("pdf")) {
			/* android.permission.INSTALL_PACKAGES */
            type += "/pdf";
        } else if (end.equals("txt") || end.equals("log")) {
            type += "/plain";
        } else {
            type += "/*";
        }
        return type;
    }
}
