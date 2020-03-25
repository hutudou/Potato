package com.example.administrator.potato.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.potato.R;
import com.example.administrator.potato.model.StudentModel;
import com.example.administrator.potato.util.DataBaseUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 使用Google官方的数据库框架 room
 *
 * @author awei
 * @date 2020/3/24
 */
public class UseRoomActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.buttonInsert)
    Button buttonInsert;
    @Bind(R.id.buttonDelete)
    Button buttonDelete;
    @Bind(R.id.buttonModify)
    Button buttonModify;
    @Bind(R.id.buttonQuery)
    Button buttonQuery;
    private List<StudentModel> studentModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_room);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText("使用数据库框架room");
    }

    @Override
    protected void initData() {
        if (true) {
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                StudentModel studentModel = new StudentModel();
                studentModel.setStudentAddress("四川");
                studentModel.setStudentAge(24);
                studentModel.setStudentName("阿伟");
                DataBaseUtil.getDataBaseUtil(getApplicationContext()).studentDao().insertAll(studentModel);
                DataBaseUtil.getDataBaseUtil(getApplicationContext()).studentDao().getAllStudents();
                Log.d("awei", "sss");
                Log.d("awei", "sss");
                Log.d("awei", "sss");
            }
        }).start();

    }

    @OnClick({R.id.buttonInsert, R.id.buttonDelete, R.id.buttonModify, R.id.buttonQuery})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //增
            case R.id.buttonInsert:
                //room框架是同步操作 因此只能运行在子线程中 使用rxJava来进行进程调度
                DataBaseUtil.getDataBaseUtil(getApplicationContext()).studentDao().getAllStudents()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<StudentModel>>() {
                            @Override
                            public void accept(List<StudentModel> studentModels) throws Exception {
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        });
                break;
            //删
            case R.id.buttonDelete:
                break;
            //改
            case R.id.buttonModify:
                break;
            //查
            case R.id.buttonQuery:
                break;
            default:
                break;
        }
    }
}
