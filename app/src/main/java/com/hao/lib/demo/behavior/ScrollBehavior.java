package com.hao.lib.demo.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * @author Yang Shihao
 * @date 2018/6/14
 */
public class ScrollBehavior extends CoordinatorLayout.Behavior<SmartRefreshLayout> {

    private static final String TAG = "ScrollBehavior";

    private ScrollerUtil mScrollerUtil;

    public ScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScrollerUtil = new ScrollerUtil(context);
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, SmartRefreshLayout child, int layoutDirection) {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        if (params != null && params.height == CoordinatorLayout.LayoutParams.MATCH_PARENT) {
            child.layout(0, 0, parent.getWidth(), parent.getHeight());
            child.setTranslationY(getHeaderHeight());
            return true;
        }
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull SmartRefreshLayout child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull SmartRefreshLayout child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        if (dy < 0) {
            return;
        }
        float transY = child.getTranslationY() - dy;
        if (transY > 0) {
            child.setTranslationY(transY);
            consumed[1] = dy;
        }
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull SmartRefreshLayout child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
        if (dyUnconsumed > 0) {
            return;
        }
        float transY = child.getTranslationY() - dyUnconsumed;
        if (transY > 0 && transY < getHeaderHeight()) {
            child.setTranslationY(transY);
        }
    }

    public int getHeaderHeight() {
        return 500;
    }
}
