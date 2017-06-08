package com.example.frist.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;

import com.example.frist.R;
import com.example.frist.Utils;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.apache.commons.lang3.StringUtils;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by ASUS on 2014/12/4.
 */
public class HttpTask extends AsyncTask<Void, Void, String> {

    ProgressDialog mpDialog;
    Context mContent;
    String msgToShow;
    int httpTpye;
    int mTag;
    String mUrl;
    HashMap<String, String> mBody;

    public HttpTask(Context mContent, String msgToShow, int httpTpye, int mTag, String mUrl, HashMap<String, String> mBody) {
        this.mContent = mContent;
        this.msgToShow = msgToShow;
        this.httpTpye = httpTpye;
        this.mTag = mTag;
        this.mBody = mBody;
        this.mUrl = Utils.URL + mUrl;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mpDialog = new ProgressDialog(mContent, ProgressDialog.THEME_HOLO_LIGHT);
        mpDialog.setMessage("正在加载。。。");
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
    protected String doInBackground(Void... params) {
        Request request = null;
        switch (httpTpye) {
            case 0:
                request = new Request.Builder().tag(mTag)
                        .url(msgToShow)
                        .build();
                break;
            case 1:
                JSONObject object = new JSONObject();
                StringBuilder stringBuilder = new StringBuilder("");
                if (mBody != null) {
                    Set<String> keys = mBody.keySet();
                    for (String key : keys) {
                        String value = mBody.get(key);
                        if (StringUtils.isNotEmpty(value)) {
                            stringBuilder.append(key).append('=').append(value).append('&');
                            try {
                                object.put(key, value);
                            } catch (Exception e) {

                            }

                        }
                    }
                }
                String content = StringUtils.removeEnd(stringBuilder.toString(), "&");
                //DebugLog.e(content);
                RequestBody formBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), content);
                FormEncodingBuilder f = new FormEncodingBuilder();
                if (mBody != null) {
                    Set<String> keys = mBody.keySet();
                    for (String key : keys) {
                        String value = mBody.get(key);
                        if (StringUtils.isNotEmpty(value)) {
                            f.add(key, value);
                        }
                    }
                }

                RequestBody formBodys = f.build();
                request = new Request.Builder().tag(mTag)
                        .url(mUrl)
                        .post(formBody)
                        .build();
                break;
        }
        //DebugLog.e(mUrl);
        if (request != null) {
            OkHttpClient okHttpClient = new OkHttpClient();
            try {
                mCall = okHttpClient.newCall(request);
                Response response = mCall.execute();
                if (response.isSuccessful()) {
                    String temp = response.body().string();
                    //DebugLog.e(temp);
                    return temp;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        mpDialog.dismiss();
        // Log.i("TAF",result);
        EventBus.getDefault().post(new HttpEvent(result, mTag));
    }

    Call mCall;

    @Override
    protected void onCancelled() {
        super.onCancelled();
        // DebugLog.e("onCancelled");
        if (mCall != null) {
            mCall.cancel();
        }
    }
}
