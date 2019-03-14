package com.example.administrator.potato.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.administrator.potato.R;
import com.example.administrator.potato.utils.PictureSelectorUtil;
import com.example.administrator.potato.utils.ToastMessage;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.adapter.PictureImageGridAdapter;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PictureSelectorActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recycler)
    RecyclerView recycler;
    private PictureImageGridAdapter adapter;
    private List<LocalMedia> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_selector);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, "PictureSelector的使用以及封装", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        list = new ArrayList<>();
        adapter = new PictureImageGridAdapter(this, PictureSelectorUtil.getImageConfig());
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
        adapter.setOnPhotoSelectChangedListener(new PictureImageGridAdapter.OnPhotoSelectChangedListener() {
            @Override
            public void onTakePhoto() {

            }

            @Override
            public void onChange(List<LocalMedia> list) {

            }

            @Override
            public void onPictureClick(LocalMedia localMedia, int i) {
                ToastMessage.toastSuccess("点击图片一", true);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == 0) {
                list = PictureSelector.obtainMultipleResult(data);
                adapter.bindSelectImages(list);
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.buttonAdd)
    public void onViewClicked() {
        PictureSelector.create(PictureSelectorActivity.this)
                .openGallery(PictureMimeType.ofImage())
                .forResult(0);
    }
}
