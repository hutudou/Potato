package com.example.administrator.potato.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.administrator.potato.R;
import com.example.administrator.potato.utils.ToastMessage;


import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author potato
 */
public class PopupWindowActivity extends BaseActivity {
    @Bind(R.id.buttonOpen)
    Button buttonOpen;
    /**
     * 弹窗消失
     */
    private static final String DISMISS = "dismiss";
    /**
     * 弹窗显示
     */
    private static final String SHOW = "show";
    /**
     * 获取本地相册
     */
    private static final int IMAGE = 1;
    /**
     * 相机
     */
    private static final int CAMERA = 2;
    @Bind(R.id.image)
    ImageView image;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, "使用popupWindow", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*
         */
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE && data != null) {
            //取得图片的uri 这里是android自定义的uri
            Uri selectedImage = data.getData();
            if (selectedImage == null) {
                return;
            }
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            //根据上一步得到的uri来查找对应的图片信息
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            if (c != null) {
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePathColumns[0]);
                String imagePath = c.getString(columnIndex);
                //根据图片的绝对路径来显示图片
                Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                image.setImageBitmap(bitmap);
                c.close();
            } else {
                ToastMessage.toastError("获取图片路径异常，请稍后再试...", false);
            }
        } else {
            ToastMessage.toastError("图片获取出错，请重试...", true);
        }
    }

    /**
     * 打开弹窗中
     */
    @OnClick(R.id.buttonOpen)
    public void onViewClicked() {
        //获取popupwindow布局时
        final View popupView = getLayoutInflater().inflate(R.layout.popupview_get_photo, null);
        //获取布局中的控件
        TextView textAlbum = popupView.findViewById(R.id.textAlbum);
        TextView textCamera = popupView.findViewById(R.id.textCamera);
        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //为控件设置点击事件
        textAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, IMAGE);
                //关闭弹窗解决情况下
                popupWindow.dismiss();
                ToastMessage.toastSuccess("选择了本地相册...", true);
            }
        });
        //使用相机拍照 掉用系统的摄像机
        textCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastMessage.toastSuccess("选择了相机拍照...", true);
            }
        });
        //是否可以获取事件 设为false则事件会被父view拦截
        popupWindow.setFocusable(true);
        //设置是否可以在popubview外部进行点击
        popupWindow.setOutsideTouchable(true);
        //设置动画
        popupWindow.setAnimationStyle(R.style.popup_window_anim);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                change(DISMISS);
            }
        });
        change(SHOW);
        //更新popupWindow，
        popupWindow.update();
        //设置popupWindow的位置 相对于屏幕
        popupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 0);
        //此方法同样是设置popupWindow的位置 相对于组件
        //popupWindow.showAsDropDown(buttonOpen,0,0);
    }

    /**
     * 弹窗在显示和消失时改变背景屏幕的透明度
     */
    private void change(String type) {
        Window window = getWindow();
        final WindowManager.LayoutParams params = window.getAttributes();
        if (type.equals(DISMISS)) {
            params.alpha = 1.0f;
        } else if (type.equals(SHOW)) {
            params.alpha = 0.5f;
        }
        window.setAttributes(params);
    }
}
