/*
 * Copyright (c) 2016 by xiaoxue. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lixue.aibei.autolayoutlib.attr;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/2/22.
 */
public class MarginAttr extends AutoAttr {
    public MarginAttr(int pxVal, int baseWidth, int baseHeight) {
        super(pxVal, baseWidth, baseHeight);
    }

    @Override
    protected int attrVal() {
        return Attrs.MARGIN;
    }

    @Override
    protected boolean defaultBaseWidth() {
        return false;
    }

    @Override
    protected void execute(View view, int val) {
        ViewGroup.MarginLayoutParams ml = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        ml.leftMargin = ml.rightMargin = ml.topMargin = ml.bottomMargin = val;
    }

    @Override
    public void apply(View view) {
        if (!(view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)){
            return;
        }
        if (useDefault()){
            ViewGroup.MarginLayoutParams mp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            mp.leftMargin = mp.rightMargin = getPercentWidthSize();
            mp.topMargin = mp.bottomMargin = getPercentHeightSize();
            return;
        }
        super.apply(view);
    }
}
