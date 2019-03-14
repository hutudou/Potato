package com.example.administrator.potato.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.potato.R;
import com.example.administrator.potato.utils.NormalUtils;
import com.example.administrator.potato.utils.ToastMessage;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class RegisterActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.editPhoneNumber)
    EditText editPhoneNumber;
    @Bind(R.id.editCode)
    EditText editCode;
    @Bind(R.id.buttonCode)
    Button buttonCode;
    //存放正确的phone
    private String phone;
    private EventHandler eventHandler = new EventHandler() {
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
                            ToastMessage.toastSuccess("验证成功...", true);
                            // TODO 处理验证码验证通过的结果
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
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, "注册", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        editPhoneNumber.addTextChangedListener(phoneWatcher);
        // 在尝试读取通信录时以弹窗提示用户（可选功能）
        SMSSDK.setAskPermisionOnReadContact(true);


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
                // 请求验证码，其中country表示国家代码，如“86”；phone表示手机号码，如“13800138000”
                SMSSDK.getVerificationCode("86", phone);
                break;
            case R.id.buttonNext:
                // 提交验证码，其中的code表示验证码，如“1357”
                SMSSDK.submitVerificationCode("86", phone, editCode.getText().toString());
                break;
        }
    }
}
