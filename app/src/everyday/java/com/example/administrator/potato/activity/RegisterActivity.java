package com.example.administrator.potato.activity;

import android.app.ActivityOptions;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.potato.AppConstant;
import com.example.administrator.potato.R;
import com.example.administrator.potato.bmobbeen.Person;
import com.example.administrator.potato.utils.MD5Utils;
import com.example.administrator.potato.utils.NormalUtils;
import com.example.administrator.potato.utils.SharedPreferencesUtil;
import com.example.administrator.potato.utils.ToastMessage;
import com.mob.MobSDK;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RegisterActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.editPhoneNumber)
    EditText editPhoneNumber;
    @Bind(R.id.editCode)
    EditText editCode;
    @Bind(R.id.buttonCode)
    Button buttonCode;
    private static final int RESTTIME = 60;
    //监听验证码是否可点击
    private Disposable mDisposable;
    //存放正确的phone
    private String phone;
    EventHandler eventHandler = new EventHandler() {
        public void afterEvent(int event, int result, Object data) {
            // afterEvent会在子线程被调用，因此如果后续有UI相关操作，需要将数据发送到UI线程
            Message msg = new Message();
            msg.arg1 = event;
            msg.arg2 = result;
            msg.obj = data;
            new Handler(Looper.getMainLooper(), new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    int event = msg.arg1;
                    int result = msg.arg2;
                    Object data = msg.obj;
                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            // TODO 处理成功得到验证码的结果
                            // 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达
                            ToastMessage.toastSuccess("验证码发送成功,请注意查收...", true);
                        } else {
                            // TODO 处理错误的结果
                            ((Throwable) data).printStackTrace();
                            ToastMessage.toastError("验证码发送失败,请重试...", true);
                        }
                    } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            // TODO 处理验证码验证通过的结果
                            ToastMessage.toastSuccess("验证通过...", true);
                        } else {
                            // TODO 处理错误的结果
                            ((Throwable) data).printStackTrace();
                            ToastMessage.toastError("验证失败,请重试...", true);
                        }
                    }
                    // TODO 其他接口的返回结果也类似，根据event判断当前数据属于哪个接口
                    return false;
                }
            }).sendMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
        if (mDisposable != null) {//活动销毁后取消心跳任务
            mDisposable.dispose();
        }
    }

    @Override
    protected void initView() {
        // 在尝试读取通信录时以弹窗提示用户（可选功能）
        SMSSDK.setAskPermisionOnReadContact(false);
        MobSDK.init(RegisterActivity.this, "2568b8e7ec06f", "2f412debdcfc0436c5324def5517661f");
        initToolBar(toolbar, "注册", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        editPhoneNumber.addTextChangedListener(phoneWatcher);


        // 注册一个事件回调，用于处理SMSSDK接口请求的结果
        SMSSDK.registerEventHandler(eventHandler);

    }

    @Override
    protected void initData() {

    }

    //监听手机号的输入
    private TextWatcher phoneWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //动态验证手机号是否合法  合法时可以获取验证码
            if (NormalUtils.isMobile(charSequence.toString())) {
                buttonCode.setVisibility(View.VISIBLE);
                phone = charSequence.toString();
            } else {
                buttonCode.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


    @OnClick({R.id.buttonCode, R.id.buttonNext})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.buttonCode:
                Observable.create(new ObservableOnSubscribe<Boolean>() {//先验证用户是否注册 将结果以true/false方式传递下去
                    @Override
                    public void subscribe(final ObservableEmitter<Boolean> emitter) throws Exception {
                        //根据手机号查找数据库中是否存在该用户
                        BmobQuery<Person> queryPerson = new BmobQuery<>();
                        queryPerson.doSQLQuery("select * from Person where account='" + editPhoneNumber.getText().toString() + "'", new SQLQueryListener<Person>() {
                            @Override
                            public void done(BmobQueryResult<Person> bmobQueryResult, BmobException e) {
                                if (e == null) {
                                    emitter.onNext(bmobQueryResult.getResults().size() != 0);
                                } else {
                                    ToastMessage.toastError("与服务器连接失败,原因是:" + e.getLocalizedMessage(), false);
                                }
                            }
                        });
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {
                                if (aBoolean) {
                                    ToastMessage.toastWarn("当前用户已注册,请勿重复注册...", false);
                                } else {
                                    // 请求验证码，其中country表示国家代码，如“86”；phone表示手机号码，如“13800138000”
                                    SMSSDK.getVerificationCode("86", phone);
                                    buttonCode.setEnabled(false);
                                    mDisposable = Flowable.interval(1, TimeUnit.SECONDS)
                                            .subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(new Consumer<Long>() {
                                                @Override
                                                public void accept(Long aLong) throws Exception {
                                                    if (RESTTIME - 1 - aLong == 0) {//六十秒计时到了之后需要让button可点击
                                                        buttonCode.setEnabled(true);
                                                        buttonCode.setText("获取验证码");
                                                        mDisposable.dispose();
                                                    } else {
                                                        buttonCode.setText(String.format("%d秒", RESTTIME - 1 - aLong));
                                                    }
                                                }
                                            });
                                }
                            }
                        }).dispose();
                break;
            case R.id.buttonNext:
                if (TextUtils.isEmpty(phone)) {
                    ToastMessage.toastWarn("请输入正确的手机号...", true);
                    return;
                }
                if (TextUtils.isEmpty(editCode.getText().toString())) {
                    ToastMessage.toastWarn("请输入验证码...", true);
                    return;
                }
                // 提交验证码，其中的code表示验证码，如“1357”
                SMSSDK.submitVerificationCode("86", phone, editCode.getText().toString());
                Bundle bundle = new Bundle();
                bundle.putString("account", phone);
                gotoActivity(SetPasswordActivity.class, bundle, true);
                break;
        }
    }
}
