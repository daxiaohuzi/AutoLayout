/*
 * Copyright (c) 2016 by xiaoxue. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lixue.aibei.autolayoutlib.attr;

import android.view.View;

import com.lixue.aibei.autolayoutlib.utils.AutoUtils;
import com.lixue.aibei.autolayoutlib.utils.L;

/**
 * Created by Administrator on 2016/2/19.
 */
public abstract class AutoAttr {
    public static final int BASE_WIDTH = 1;
    public static final int BASE_HEIGHT = 2;
    public static final int BASE_DEFAULT = 3;

    protected int pxVal;
    protected int baseWidth;
    protected int baseHeight;

    public AutoAttr(int pxVal, int baseWidth, int baseHeight) {
        this.pxVal = pxVal;
        this.baseWidth = baseWidth;
        this.baseHeight = baseHeight;
    }

    public void apply(View view) {
        boolean log = L.debug;
        if (log) {
            L.e("pxval = " + pxVal + "," + this.getClass().getSimpleName());
        }
        int val;
        //使用默认尺寸
        if (useDefault()) {
            val = defaultBaseWidth() ? getPercentWidthSize() : getPercentHeightSize();
            if (log) {
                L.e(" useDefault val= " + val);
            }
        } else if (baseWidth()) {
            val = getPercentWidthSize();
            if (log) {
                L.e(" baseWidth val= " + val);
            }
        } else {
            val = getPercentHeightSize();
            if (log) {
                L.e(" baseHeight val= " + val);
            }
        }
        if (val > 0){
            val = Math.max(val, 1);//for very thin divider
        }
        execute(view, val);
    }

    protected int getPercentWidthSize() {
        return AutoUtils.getPercentWidthSizeBigger(pxVal);
    }

    protected int getPercentHeightSize() {
        return AutoUtils.getPercentHeightSizeBigger(pxVal);
    }


    protected boolean baseWidth() {
        return contains(baseWidth, attrVal());
    }

    protected boolean useDefault() {
        return !contains(baseHeight, attrVal()) && !contains(baseWidth, attrVal());
    }

    protected boolean contains(int baseVal, int flag) {
        /**0&0=0;   0&1=0;    1&0=0;     1&1=1;不为0则true,为0则false**/
        return (baseVal & flag) != 0;
    }

    protected abstract int attrVal();

    protected abstract boolean defaultBaseWidth();

    protected abstract void execute(View view, int val);

    @Override
    public String toString() {
        return "AutoAttr{" +
                "pxVal=" + pxVal +
                ", baseWidth=" + baseWidth() +
                ", defaultBaseWidth=" + defaultBaseWidth() +
                '}';
    }

}
