package com.example.frist.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.frist.MainActivity;
import com.example.frist.R;
import com.example.frist.Utils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Matthew_Chen on 2017/4/14.
 */

public class AdActivity extends AppCompatActivity {
    TextView textView;
    Long currenttime;
    Timer timer;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    long currenttimes = System.currentTimeMillis();
                    if (currenttimes - currenttime <= 5000) {
                        long time = (currenttimes - currenttime) / 1000;
                        textView.setText("跳过  " + String.valueOf(5L - time));
                    } else {
                        Utils.startActivity(AdActivity.this,MainActivity.class);
                        finish();
                        timer.cancel();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.ad_activity);
        textView = (TextView) findViewById(R.id.textView);
        timer = new Timer();
        timer.schedule(new TimeRask(), 0, 1000);
        currenttime = System.currentTimeMillis();
    }

    class TimeRask extends TimerTask {
        @Override
        public void run() {
            Message message = new Message();
            message.what = 0;
            message.arg1 = 5;
            handler.sendMessage(message);
        }
    }
}





