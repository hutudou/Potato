package com.example.administrator.potato.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.potato.R;
import com.example.administrator.potato.activity.AddSecretActivity;
import com.example.administrator.potato.bmobbeen.Secret;
import com.example.administrator.potato.holder.BaseRecyclerViewHolder;
import com.example.administrator.potato.recyclerView.adapter.CommonRecyclerViewAdapter;
import com.example.administrator.potato.utils.ToastMessage;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 作者 Administrator
 * 时间 2019/1/24
 */

public class SecretFragment extends BaseFragment {

    //常量
    private static final String ARG_PARAM1 = "param1";
    @Bind(R.id.recycler)
    RecyclerView recycler;
    private String mParam1;
    private View view;
    private CommonRecyclerViewAdapter<Secret> adapter;

    public static SecretFragment newInstance(String param1) {
        SecretFragment fragment = new SecretFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        request();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_secret, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView() {
        adapter = new CommonRecyclerViewAdapter<Secret>(getActivity(), R.layout.recycler_secret_item) {
            @Override
            public void convert(BaseRecyclerViewHolder holder, final Secret item, final int position) {
                //昵称
                TextView textView = holder.getView(R.id.textNickName);
                textView.setText(item.getNickName());
                //内容
                textView = holder.getView(R.id.textContent);
                textView.setText(item.getSecretContent());
                //点赞信息
                textView = holder.getView(R.id.textSecretInfo);
                textView.setText(String.format("赞 %s 评论 %s", item.getSpotGoodNumber(), item.getCommentNumber()));
                //点赞
                final ImageView imageView = holder.getView(R.id.imageViewSpotGood);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Secret secret = new Secret();
                        secret.setSpotGoodNumber(item.getSpotGoodNumber() + 1);
                        secret.update(adapter.getItem(position).getObjectId(), new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null) {
                                    adapter.getItem(position).setSpotGoodNumber(adapter.getItem(position).getSpotGoodNumber() + 1);
                                    ToastMessage.toastSuccess("点赞成功...", true);
                                } else {
                                    ToastMessage.toastError("点赞失败..." + e.getMessage(), true);
                                }
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
                });
                //评论
                ImageView imageViewComment = holder.getView(R.id.imageViewComment);
                imageViewComment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ToastMessage.toastWarn("评论功能暂无开放，敬请期待...", false);
                    }
                });
            }
        };
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        request();
    }

    private void request() {
        BmobQuery<Secret> bmobQuery = new BmobQuery<>();
        bmobQuery.findObjects(new FindListener<Secret>() {
            @Override
            public void done(List<Secret> list, BmobException e) {
                if (e == null) {
                    adapter.fillList(list);
                } else {
                    ToastMessage.toastError("查询数据失败：" + e.getMessage(), true);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.textAddSecret)
    public void onViewClicked() {
        gotoActivity(AddSecretActivity.class);
    }
}
