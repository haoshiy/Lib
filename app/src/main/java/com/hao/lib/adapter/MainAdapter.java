package com.hao.lib.adapter;

import android.content.Context;

import com.hao.lib.bean.News;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * @author Yang Shihao
 * @date 2018/3/16
 */

public class MainAdapter extends CommonAdapter<News> {

    public MainAdapter(Context context, int layoutId, List<News> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, News news, int position) {

    }
}
