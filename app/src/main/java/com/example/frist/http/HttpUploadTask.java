package com.example.frist.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.SparseBooleanArray;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.phrase.Phrase;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ASUS on 2015/2/9.
 */
public class HttpUploadTask  extends AsyncTask<Void,Integer,String>  {

    ProgressDialog mpDialog;
    Context mContent;

    int mTag;
    String tempID;
    String mDescribe;
    ArrayList<String> selectedFiles;
    int fileSize;
    SparseBooleanArray mResults;
    int mUploadType;

    public HttpUploadTask(Context mContent, int mTag, String describe, ArrayList<String> selectedFiles, int uploadtype) {
        this.mContent = mContent;
        this.mTag = mTag;
        this.mDescribe = describe;
        this.selectedFiles = selectedFiles;
        fileSize = selectedFiles==null?0:selectedFiles.size();
        mResults = new SparseBooleanArray(fileSize);
        tempID = null;
        mUploadType = uploadtype;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        mpDialog = new ProgressDialog(mContent, ProgressDialog.THEME_HOLO_LIGHT);
//        mpDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);//设置风格为圆形进度条
        mpDialog.setMessage("开始上传...");
        mpDialog.setCancelable(true);

        mpDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
                cancel(true);
            }
        });
        mpDialog.show();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        mpDialog.setMessage(String.format("正在上传第%d个，共%d个", values[0], fileSize));
    }

    @Override
    protected String doInBackground(Void... params) {
        OkHttpClient okHttpClient = new OkHttpClient();
        String url = "";
        String postTemplate = "idcard={idcard}&selfdescribe={selfdescribe}&uploadtype={uploadtype}";
        String postData = Phrase.from(postTemplate)
                .put("idcard","")
                .put("selfdescribe",mDescribe)
                .put("uploadtype",mUploadType)
                .format().toString();
        RequestBody formBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"),postData);

//        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
//        formEncodingBuilder.add("idcard", BaseApplication.getCurrentUser().getIdcard());
//        formEncodingBuilder.add("orgcode", BaseApplication.getCurrentUser().getOrgCode());
//        formEncodingBuilder.add("selfdescribe", mDescribe);
//        RequestBody formBody = formEncodingBuilder.build();

        Request request = new Request.Builder().tag(mTag)
                .url(url+ "")
                .post(formBody)
                .build();
        mCall = okHttpClient.newCall(request);
        Response response = null;
        try {
            response = mCall.execute();
            if(response.isSuccessful()){
                tempID = response.body().string();

                if (StringUtils.isNotEmpty(tempID)&&!StringUtils.equals("failed",tempID)){
                    for (int i = 0;i<fileSize && !isCancelled();i++) {
                        publishProgress(i+1);
                        MultipartBuilder f = new MultipartBuilder().type(MultipartBuilder.FORM);
                        f.addFormDataPart("idcard", "");
                        f.addFormDataPart("serialnum", tempID);
                        f.addFormDataPart("uploadtype", ""+mUploadType);
                        File fileTemp = new File(selectedFiles.get(i));
                        if (fileTemp.getName().endsWith(".png")) {
                            f.addFormDataPart("file", fileTemp.getName(),
                                    RequestBody.create(MediaType.parse("image/png"), fileTemp));
                        } else if (fileTemp.getName().endsWith(".jpg") || fileTemp.getName().endsWith(".jpeg")) {
                            f.addFormDataPart("file", fileTemp.getName(),
                                    RequestBody.create(MediaType.parse("image/jpeg"), fileTemp));
                        }

                        formBody = f.build();
                        request = new Request.Builder().tag(mTag)
                                .url(url+ "")
                                .post(formBody)
                                .build();
                        if (request != null) {
                            try {
                                mCall = okHttpClient.newCall(request);
                                response = mCall.execute();
                                if (response.isSuccessful()) {
                                    String temp = response.body().string();
                                    mResults.put(i,StringUtils.equals(temp,"success"));
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        mpDialog.dismiss();
        if (StringUtils.isNotEmpty(tempID)) {
           // BusProvider.getInstance().post(new HttpEvent(mTag, new Gson().toJson(mResults)));
        }else {
           // BusProvider.getInstance().post(new HttpEvent(mTag, null));
        }
    }

    Call mCall;
    @Override
    protected void onCancelled() {
        super.onCancelled();
        if (mCall!=null){
            mCall.cancel();
        }
    }
}

//public class HttpUploadTask  extends AsyncTask<Void,Integer,String> implements GlobalData {
//
//    ProgressDialog mpDialog;
//    Context mContent;
//
//    int mTag;
//    String mUrl;
//    HashMap<String,String> mBody;
//    LinkedList<File> mFiles;
//    int fileSize;
//    SparseBooleanArray mResults;
//
//    public HttpUploadTask(Context mContent, int mTag,String mUrl, HashMap<String,String> mBody,LinkedList<File> files) {
//        this.mContent = mContent;
//        this.mTag = mTag;
//        this.mBody = mBody;
//        this.mUrl = "http://"+
//                PreferenceManager.getDefaultSharedPreferences(mContent).getString(serverUrl, defaultServerUrl)
//                + mUrl;
//        this.mFiles = files;
//        fileSize = mFiles==null?0:mFiles.size();
//        mResults = new SparseBooleanArray();
//    }
//
//    @Override
//    protected void onPreExecute(){
//        super.onPreExecute();
//        mpDialog = new ProgressDialog(mContent, ProgressDialog.THEME_HOLO_LIGHT);
////        mpDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);//设置风格为圆形进度条
//        mpDialog.setMessage("开始上传...");
//        mpDialog.setCancelable(true);
//
//        mpDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//            @Override
//            public void onCancel(DialogInterface dialog) {
//                dialog.dismiss();
//                cancel(true);
//            }
//        });
//        mpDialog.show();
//    }
//
//    @Override
//    protected void onProgressUpdate(Integer... values) {
//        super.onProgressUpdate(values);
//        mpDialog.setMessage(String.format("正在上传第%d个，共%d个", values[0], fileSize));
//    }
//
//    @Override
//    protected String doInBackground(Void... params) {
//        OkHttpClient okHttpClient = new OkHttpClient();
//        for (int i = 0;i<fileSize && !isCancelled();i++) {
//            publishProgress(i+1);
//            MultipartBuilder f = new MultipartBuilder().type(MultipartBuilder.FORM);
//            if (mBody != null) {
//                Set<String> keys = mBody.keySet();
//                for (String key : keys) {
//                    String value = mBody.get(key);
//                    if (StringUtils.isNotEmpty(value)) {
//                        f.addFormDataPart(key, value);
//                    }
//                }
//            }
//            if (mFiles.get(i).getName().endsWith(".png")) {
//                f.addFormDataPart("file", mFiles.get(i).getName(),
//                        RequestBody.create(MediaType.parse("image/png"), mFiles.get(i)));
//            } else if (mFiles.get(i).getName().endsWith(".jpg") || mFiles.get(i).getName().endsWith(".jpeg")) {
//                f.addFormDataPart("file", mFiles.get(i).getName(),
//                        RequestBody.create(MediaType.parse("image/jpeg"), mFiles.get(i)));
//            }
//
//            RequestBody formBody = f.build();
//            Request request = new Request.Builder().tag(mTag)
//                    .url(mUrl)
//                    .post(formBody)
//                    .build();
//            DebugLog.e(mUrl);
//            if (request != null) {
//                try {
//                    mCall = okHttpClient.newCall(request);
//                    Response response = mCall.execute();
//                    if (response.isSuccessful()) {
//                        String temp = response.body().string();
//                        DebugLog.e(temp);
//                        mResults.put(i,true);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(String result) {
//        super.onPostExecute(result);
//        mpDialog.dismiss();
//        BusProvider.getInstance().post(new HttpEvent(mTag,result));
//    }
//
//    Call mCall;
//    @Override
//    protected void onCancelled() {
//        super.onCancelled();
//        DebugLog.e("onCancelled");
//        if (mCall!=null){
//            mCall.cancel();
//        }
//    }
//}
