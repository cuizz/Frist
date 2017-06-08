package com.example.frist.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.frist.R;

/**
 * Created by Administrator on 2017/6/8.
 */

public class CustomDialog extends ProgressDialog {
    String content;

    public CustomDialog(Context context) {
        super(context);
    }

    public CustomDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init(getContext());
    }

    private void init(Context context) {
        setContentView(R.layout.load_dialog);
        TextView textView = (TextView) findViewById(R.id.tv_load_dialog);
        textView.setText(content);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
    }

    public void setContent(String str) {
        content = str;
    }

    public void setDisable(boolean disable) {
        //设置不可取消，点击其他区域不能取消，实际中可以抽出去封装供外包设置
        setCancelable(disable);
        setCanceledOnTouchOutside(disable);
    }

    @Override
    public void show() {
        super.show();
    }
}
