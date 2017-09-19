package com.example.xu.myapplication.moduleMy.fragment.viewInterface;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.bigkoo.pickerview.TimePickerView;
import com.example.xu.myapplication.base.BaseViewInterface;

/**
 * Created by 逝 on 2017/09/18.
 */

public interface IMyPersonal extends BaseViewInterface {
    /**
     * 获取Context实例
     * @return context
     */
    Context getCon();

    /**
     * 获取Activity实例
     * @return activity
     */
    Activity getAct();
}
