package com.example.xu.myapplication.moduleType.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseFragment;
import com.example.xu.myapplication.util.Logger;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuContentFragment extends BaseFragment {
    private static MenuContentFragment instance;

    public static MenuContentFragment newInstance(String menu) {
        Bundle args = new Bundle();
        args.putString(ARG_MENU, menu);
        if (instance == null) {
            instance = new MenuContentFragment();
            instance.setArguments(args);
        }
        return instance;
    }

    private static final String ARG_MENU = "arg_menu";

    @BindView(R.id.tv_menu_content)
    TextView tvContent;

    private String menuStr;

    @Override
    public int getLayout() {
        return R.layout.fragment_menu_content;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void initData() {
        Bundle args = getArguments();
        if (args != null)
            menuStr = args.getString(ARG_MENU);
        Logger.e("menuStr",menuStr);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        tvContent.setText(menuStr);
    }

    @Override
    public void setToolbar() {

    }

    @Override
    public boolean onBackPressedSupport() {
        return super.onBackPressedSupport();
    }
}