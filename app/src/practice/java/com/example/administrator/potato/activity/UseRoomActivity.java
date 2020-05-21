package com.example.administrator.potato.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.potato.R;
import com.example.administrator.potato.holder.BaseRecyclerViewHolder;
import com.example.administrator.potato.model.StudentModel;
import com.example.administrator.potato.recycler.adapter.CommonRecyclerViewAdapter;
import com.example.administrator.potato.util.BaseDataBaseUtil;
import com.example.administrator.potato.utils.ToastMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 使用Google官方的数据库框架 room
 *
 * @author potato
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
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private List<StudentModel> studentModelList = new ArrayList<>();
    /**
     * 适配器
     */
    private CommonRecyclerViewAdapter<StudentModel> adapter;

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
        adapter = new CommonRecyclerViewAdapter<StudentModel>(mContext, R.layout.recyler_student_data_item) {
            @Override
            public void convert(BaseRecyclerViewHolder holder, StudentModel item, int position) {
                TextView textView = holder.getView(R.id.textStudentId);
                textView.setText(String.valueOf(item.getStudentId()));

                textView = holder.getView(R.id.textStudentName);
                textView.setText(item.getStudentName());

                textView = holder.getView(R.id.textStudentName);
                textView.setText(item.getStudentName());

                textView = holder.getView(R.id.textStudentAge);
                textView.setText(String.valueOf(item.getStudentAge()));

                textView = holder.getView(R.id.textStudentAddress);
                textView.setText(item.getStudentAddress());
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
//        queryInMainThread();
        //进入时自动查询一次
        BaseDataBaseUtil.instance(getApplicationContext()).studentDao().getAllStudentsByRxjava()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<StudentModel>>() {
                    @Override
                    public void accept(List<StudentModel> studentModels) throws Exception {
                        Log.d("potato", "aa");
                        ToastMessage.toastSuccess("查询成功...");
                        studentModelList = new ArrayList<>();
                        studentModelList.addAll(studentModels);
                        adapter.fillList(studentModels);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("potato", "查询出错");
                    }
                });
    }

    /**
     * 在主线程中查询数据
     */
    private void queryInMainThread() {
        try {
            List<StudentModel> list = BaseDataBaseUtil.instance(getApplicationContext()).studentDao().getAllStudentsByNormal();
            if (list.size() > 0) {
                Log.d("potato", "查询成功");
            }
        } catch (Exception e) {
            Log.d("potato", "程序发生异常，原因是:" + e.getLocalizedMessage());
        }

    }

    /**
     * 在子线程中查询数据
     */
    private void queryInOtherThread() {
        //为了防止堵塞主线程  操纵数据库需要自己手动切换到子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<StudentModel> list = new ArrayList<>();
                list = BaseDataBaseUtil.instance(getApplicationContext()).studentDao().getAllStudentsByNormal();
                if (list.size() > 0) {
                    Log.d("potato", "成功查询到数据");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            /*
                             * 更改UI需要手动切换到主线程
                             */
                        }
                    });
                }
            }
        }).start();
    }

    @OnClick({R.id.buttonInsert, R.id.buttonDelete, R.id.buttonModify, R.id.buttonQuery})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //增
            case R.id.buttonInsert:
                Single.fromCallable(new Callable<List<Long>>() {
                    @Override
                    public List<Long> call() throws Exception {
                        StudentModel studentModel = new StudentModel();
                        studentModel.setStudentName("土豆");
                        studentModel.setStudentAge(18);
                        studentModel.setStudentAddress("地球");
                        //这里返回的 其实是插入之后的数据的行id 因此需要使用一个list来存储
                        return BaseDataBaseUtil.instance(getApplicationContext()).studentDao().insertAll(studentModel);
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<List<Long>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                Log.d("awei", "awei");
                            }

                            @Override
                            public void onSuccess(List<Long> longs) {
                                //只要longs的大小大于0 就表示这次的插入操作是成功的
                                Log.d("awei", "" + longs.size());
                                if (longs.size() > 0) {
                                    ToastMessage.toastSuccess("插入成功...");
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("awei", "awei");
                                ToastMessage.toastError("插入失败...");

                            }
                        });
                break;
            //删
            case R.id.buttonDelete:
                Single.fromCallable(new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        //简化操作 每次都删除数据中的第一个数据项
                        if (studentModelList.size() > 0) {
                            return BaseDataBaseUtil.instance(getApplicationContext()).studentDao().delete(studentModelList.get(0));
                        } else {
                            return -1;
                        }
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                if (integer > 0) {
                                    Log.d("awei", "success");
                                } else {
                                    Log.d("awei", "删除失败，数据库中不存在该值");
                                }
                            }

                        });
                break;
            //改
            case R.id.buttonModify:
                Single.fromCallable(new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        //修改前先判断数据库是否有值
                        if (studentModelList.size() > 0) {
                            studentModelList.get(0).setStudentName("花花花");
                            return BaseDataBaseUtil.instance(getApplicationContext()).studentDao().update(studentModelList.get(0));
                        } else {
                            return -1;
                        }
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                if (integer > 0) {
                                    Log.d("awei", "修改成功");
                                } else {
                                    Log.d("awei", "修改失败");
                                }
                            }
                        });
                break;
            //查
            case R.id.buttonQuery:
                //room框架是同步操作 因此只能运行在子线程中 使用rxJava来进行进程调度
                BaseDataBaseUtil.instance(getApplicationContext()).studentDao().getAllStudentsByRxjava()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<StudentModel>>() {
                            @Override
                            public void accept(List<StudentModel> studentModels) throws Exception {
                                Log.d("awei", "aa");
                                Log.d("awei", "aa");
                                ToastMessage.toastSuccess("查询成功...");
                                adapter.fillList(studentModels);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        });
                break;
            default:
                break;
        }
    }
}
