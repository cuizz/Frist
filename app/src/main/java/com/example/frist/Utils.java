package com.example.frist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/13.
 */

public class Utils {
    public static AlertDialog dialog;
    public static AlertDialog.Builder normalDialog;
    public static String URL = "http://manager.sdelsq.net:8088/";
    public static String IMG_URL = "http://7xqai4.com2.z0.glb.qiniucdn.com/2016-03-14_56e64f25126fe.jpg?imageMogr2/thumbnail/460x460";
    public static void creatDialog(Context context, String content) {
        normalDialog =
                new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog, null);
        normalDialog.setView(view);
        TextView pos = (TextView) view.findViewById(R.id.quxiao);
        TextView sure = (TextView) view.findViewById(R.id.sure);
        pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissDialog();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissDialog();
            }
        });
        dialog = normalDialog.show();
    }

    public static void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
    public static void startActivity(Context context,Class classs){
        Intent intent=new Intent(context,classs);
        context.startActivity(intent);
    }
    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(
                    string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    public static String sha1(String s) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();
            return toHexString(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String toHexString(byte[] keyData) {
        if (keyData == null) {
            return null;
        }
        int expectedStringLen = keyData.length * 2;
        StringBuilder sb = new StringBuilder(expectedStringLen);
        for (int i = 0; i < keyData.length; i++) {
            String hexStr = Integer.toString(keyData[i] & 0x00FF, 16);
            if (hexStr.length() == 1) {
                sb.append("0").append(hexStr);
            } else {
                sb.append(hexStr);
            }
        }
        return sb.toString();
    }

    public static String getDBPath() {
        String sdCardPath = getSDPath();
        if (TextUtils.isEmpty(sdCardPath)) {
            return "";
        } else {
            return sdCardPath + File.separator + "GreenDao"
                    + File.separator + "sqlite";
        }
    }

    public static String getSDPath() {
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            return Environment.getExternalStorageDirectory().toString();
        } else {
            return "";
        }
    }
    public static List<String> getlist(){
        List<String> images=new ArrayList<>();
        if(images==null||images.size()==0){
            images.add("http://img.my.csdn.net/uploads/201309/01/1378037235_3453.jpg");
            images.add("http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg");
            images.add("http://img.my.csdn.net/uploads/201309/01/1378037235_9280.jpg");
            images.add("http://img.my.csdn.net/uploads/201309/01/1378037234_3539.jpg");
            images.add("http://img.my.csdn.net/uploads/201309/01/1378037234_6318.jpg");
        }
        return images;
    }
}
