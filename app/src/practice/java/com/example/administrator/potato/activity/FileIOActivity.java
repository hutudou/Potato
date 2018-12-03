package com.example.administrator.potato.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.potato.AppConstant;
import com.example.administrator.potato.R;
import com.example.administrator.potato.utils.DateUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FileIOActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_io);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, "使用文件IO", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }

    //写入文件
    private void writeFile() {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        String content = "Hello Word";
        String time = DateUtils.getLocalDate("YYYY-MM-dd hh:mm:ss");
        String fileName = "Temp-" + time + ".txt";
        try {
            //判断当前SD卡状态 为正常时才可以写入文件
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File dir = new File(AppConstant.TEMP_FILE);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File file = new File(AppConstant.TEMP_FILE, fileName);
                if (file.exists()) {
                    file.delete();
                }
                try {
                    fos = new FileOutputStream(file);
                    bos = new BufferedOutputStream(fos);
                    bos.write(content.getBytes());
                    Toast.makeText(mContext, "保存成功", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(mContext, "文件保存失败,请重试", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            Log.d("awei", e.getLocalizedMessage());
        } finally {
            //关闭流
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @OnClick(R.id.buttonInput)
    public void onViewClicked() {
        writeFile();
    }
}
