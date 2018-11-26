package com.example.administrator.potato.activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.potato.AppConstant;
import com.example.administrator.potato.R;
import com.example.administrator.potato.recyclerView.adapter.CommonRecyclerViewAdapter;
import com.example.administrator.potato.been.DocBean;
import com.example.administrator.potato.holder.BaseRecyclerViewHolder;
import com.example.administrator.potato.recyclerView.click.ItemClickListener;
import com.example.administrator.potato.utils.DateUtils;
import com.example.administrator.potato.utils.FileUtils;
import com.example.administrator.potato.utils.ToastMessage;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import okhttp3.internal.Util;

public class ChooseDocActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private List<DocBean> docBeansList;
    private CommonRecyclerViewAdapter<DocBean> adapter;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_doc);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //活动销毁时关闭连接
        closeCursor();
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, "查找本地doc", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        docBeansList = new ArrayList<>();
        adapter = new CommonRecyclerViewAdapter<DocBean>(this, R.layout.item_recycler_view_choose_doc) {
            @Override
            public void convert(BaseRecyclerViewHolder holder, DocBean item, int position) {
                //设置文件参数
                TextView textView = holder.getView(R.id.textFileId);
                textView.setText(item.getId());

                textView = holder.getView(R.id.textFileName);
                textView.setText(item.getName());

                textView = holder.getView(R.id.textFilePath);
                textView.setText(item.getPath());

                textView = holder.getView(R.id.textFileSize);
                textView.setText(String.format("%sKb", String.valueOf(item.getSize())));

                textView = holder.getView(R.id.textModifyDate);
                textView.setText(item.getModifyDate());
            }
        };
        //添加点击事件
        recyclerView.addOnItemTouchListener(new ItemClickListener(recyclerView, new ItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = FileUtils.getWordFileIntent(adapter.getItem(position).getPath(), mContext);
                try {
                    startActivity(intent);
                } catch (Exception e) {
                    Log.d(AppConstant.logName, "" + e.toString());
                    showSnackBar(toolbar, "没有找到可以打开该文件的程序哦...", true, null, null);
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
                ToastMessage.toastWarn("长按" + position, true);
            }
        }));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //给recyclerView设置动画
        ScaleInAnimationAdapter animationAdapter = new ScaleInAnimationAdapter(adapter);
        animationAdapter.setDuration(500);
        recyclerView.setAdapter(animationAdapter);
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.buttonChooseWord)
    public void onViewClicked() {
        closeCursor();
        queryFiles("doc");
    }

    private void closeCursor() {
        if (cursor != null) {
            cursor.close();
        }
    }

    //根据指定格式查找手机文件
    private void queryFiles(String fileType) {
        String[] projection = new String[]{
                MediaStore.Files.FileColumns._ID,
                MediaStore.Files.FileColumns.DATA,
                MediaStore.Files.FileColumns.SIZE,
                MediaStore.Files.FileColumns.DATE_MODIFIED
        };
        cursor = getContentResolver().query(
                Uri.parse("content://media/external/file"),
                projection,
                MediaStore.Files.FileColumns.DATA + " like ?",
                new String[]{"%." + fileType},
                null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {

                int indexId = cursor
                        .getColumnIndex(MediaStore.Files.FileColumns._ID);
                int indexData = cursor
                        .getColumnIndex(MediaStore.Files.FileColumns.DATA);
                int indexSize = cursor
                        .getColumnIndex(MediaStore.Files.FileColumns.SIZE);
                int indexModifyDate = cursor
                        .getColumnIndex(MediaStore.Files.FileColumns.DATE_MODIFIED);
                do {
                    DocBean docBean = new DocBean();
                    String id = cursor.getString(indexId);
                    String path = cursor.getString(indexData);
                    int size = cursor.getInt(indexSize);
                    String modifyDate = cursor.getString(indexModifyDate);
                    docBean.setId(id);
                    docBean.setPath(path);
                    docBean.setSize(size / 1024);
                    docBean.setModifyDate(DateUtils.formatTimeStamp("yyyy/MM/dd HH:mm:ss", Long.parseLong(modifyDate)));
                    //查找path中最后一个出现'/'的地方 分割后即得文件名
                    int dot = path.lastIndexOf("/");
                    String name = path.substring(dot + 1);
                    docBean.setName(name);
                    Log.e(AppConstant.logName, name);
                    docBeansList.add(docBean);
                } while (cursor.moveToNext());
            }
        }
        if (docBeansList.size() > 0) {
            adapter.fillList(docBeansList);
        } else {
            showSnackBar(toolbar, "没有查到指定数据", false, null, null);
        }
        cursor.close();
    }
}
