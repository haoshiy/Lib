package com.hao.lib.ui.fragment;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hao.lib.App;
import com.hao.lib.Constant;
import com.hao.lib.R;
import com.hao.lib.adapter.NewsAdapter;
import com.hao.lib.base.ui.BaseListFragment;
import com.hao.lib.di.component.fragment.DaggerNewsComponent;
import com.hao.lib.di.module.fragment.FragmentCommonModule;
import com.hao.lib.di.module.fragment.NewsModule;
import com.hao.lib.contract.fragment.NewsContract;
import com.hao.lib.ui.activity.DetailsActivity;

public class NewsFragment extends BaseListFragment<NewsContract.Presenter>
        implements NewsContract.View {

    public static NewsFragment newInstance(String type) {
        NewsFragment fragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.EXTRA_STRING_1, type);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void initInject() {
        DaggerNewsComponent.builder()
                .appComponent(App.getAppComponent())
                .fragmentCommonModule(new FragmentCommonModule(this))
                .newsModule(new NewsModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        setDefaultItemDecoration();
    }

    @Override
    public BaseQuickAdapter createAdapter() {
        return new NewsAdapter(R.layout.item_news, mPresenter.getDataList());
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.EXTRA_BEAN_1, mPresenter.getDataList().get(position));
        Bundle options = ActivityOptions.makeSceneTransitionAnimation(mActivity, view, getString(R.string.transition_name)).toBundle();
        Intent intent = new Intent(mActivity, DetailsActivity.class);
        intent.putExtras(bundle);
        startActivity(intent, options);
    }
}