package com.example.administrator.potato.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.potato.R;
import com.example.administrator.potato.bean.MusicBean;
import com.example.administrator.potato.holder.BaseRecyclerViewHolder;
import com.example.administrator.potato.recycler.adapter.CommonRecyclerViewAdapter;
import com.example.administrator.potato.utils.MusicUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author potato
 * @date 2020/1/16
 */
public class QueryLocalMusicActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recycler)
    RecyclerView recycler;
    private CommonRecyclerViewAdapter<MusicBean> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_local_music);
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
        tvTitle.setText("扫描本地音乐");
        List<MusicBean> list = MusicUtil.getmusic(mContext);
        adapter = new CommonRecyclerViewAdapter<MusicBean>(mContext, R.layout.recycler_music_item) {
            @Override
            public void convert(BaseRecyclerViewHolder holder, MusicBean item, int position) {
                TextView textView = holder.getView(R.id.textMusicName);
                textView.setText(item.getName());

                textView = holder.getView(R.id.textMusicAuthor);
                textView.setText(item.getSinger());

                textView = holder.getView(R.id.textMusicTotalTime);
                textView.setText(MusicUtil.formatTime(item.getDuration()));

                textView = holder.getView(R.id.textMusicAlbum);
                textView.setText(item.getAlbum());

                ImageView imageView=holder.getView(R.id.imageMusicPhoto);
                imageView.setImageBitmap(MusicUtil.getArtwork(mContext,item.getId(),item.getAlbumId(),true,true));
            }
        };
        adapter.fillList(list);
        recycler.setLayoutManager(new LinearLayoutManager(mContext));
        recycler.setAdapter(adapter);
    }

    @Override
    protected void initData() {

    }
}
