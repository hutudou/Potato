package com.example.administrator.potato.activity;

import android.content.Context;
import android.content.ContextWrapper;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.example.administrator.potato.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UseVideoViewActivity extends BaseActivity {

    @Bind(R.id.videoView)
    VideoView videoView;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        public void run() {
            if (!videoView.isPlaying()) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
            handler.postDelayed(runnable, 500);//每0.5秒监听一次是否在播放视频
        }
    };


    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_video_view);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    //解决videoView内存泄漏问题
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(new ContextWrapper(newBase) {
            @Override
            public Object getSystemService(String name) {
                if (Context.AUDIO_SERVICE.equals(name))
                    return getApplicationContext().getSystemService(name);
                return super.getSystemService(name);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //退出时移除runnable 否则会一直占用资源
        handler.removeCallbacks(runnable);
        videoView.stopPlayback();
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, "使用videoview", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        videoView.setVideoURI(Uri.parse("http://hls.open.ys7.com/openlive/0133a3401e294b86b596233b374e08c0.m3u8"));
        videoView.setMediaController(new MediaController(this));
        videoView.start();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                @Override
                public boolean onInfo(MediaPlayer mediaPlayer, int i, int i1) {
                    if (i == MediaPlayer.MEDIA_INFO_BUFFERING_START) {
                        progressBar.setVisibility(View.VISIBLE);
                    } else if (i == MediaPlayer.MEDIA_INFO_BUFFERING_END) {
                        //此接口每次回调完START就回调END,若不加上判断就会出现缓冲图标一闪一闪的卡顿现象
                        if (mediaPlayer.isPlaying()) {
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                    return true;
                }
            });
        }
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                progressBar.setVisibility(View.GONE);//缓冲完成就隐藏
            }
        });
    }

    @Override
    protected void initData() {

    }
}
