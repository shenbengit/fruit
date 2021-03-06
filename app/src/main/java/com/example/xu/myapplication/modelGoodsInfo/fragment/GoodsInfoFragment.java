package com.example.xu.myapplication.modelGoodsInfo.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.xu.myapplication.GlideApp;
import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseFragment;
import com.example.xu.myapplication.modelGoodsInfo.presenter.GoodsInfoPresenter;
import com.example.xu.myapplication.modelGoodsInfo.viewInterface.IGoodsInfo;
import com.example.xu.myapplication.moduleActivity.main.customer.BottomBar;
import com.example.xu.myapplication.model.Fruit;
import com.example.xu.myapplication.util.Logger;
import com.example.xu.myapplication.util.ToastUtils;


import butterknife.BindView;
import butterknife.OnClick;

public class GoodsInfoFragment extends BaseFragment<GoodsInfoPresenter> implements IGoodsInfo {

    public static GoodsInfoFragment newInstance(Fruit.FruitDetail fruitDetail, double oldPrice, int count) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_MENUS, fruitDetail);
        args.putDouble(ARG_PRICE, oldPrice);
        args.putInt(ARG_COUNT, count);
        GoodsInfoFragment instance = new GoodsInfoFragment();
        instance.setArguments(args);
        return instance;
    }

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    //物品图片
    @BindView(R.id.img_goods_img)
    ImageView img_goods;
    //物品名称
    @BindView(R.id.tv_goods_title)
    TextView tv_goodsTitle;
    //现价
    @BindView(R.id.tv_new_price)
    TextView tv_newPrice;
    //原价
    @BindView(R.id.tv_old_price)
    TextView tv_oldPrice;
    //当前已选择物品
    @BindView(R.id.tv_current_goods)
    TextView tv_currentGoods;
    //添加商品个数
    @BindView(R.id.img_goods_add)
    ImageView img_goodsSelect;

    @OnClick(R.id.img_goods_add)
    public void addGoods() {
        count++;
        tv_currentGoods.setText(fruitDetail.getGoodsName() + "," + count + "件");
        Logger.e("addGoods", "count\t" + count);
    }

    @OnClick(R.id.img_goods_minus)
    public void minusGoodsCount() {
        if (count == 1)
            return;
        count--;
        tv_currentGoods.setText(fruitDetail.getGoodsName() + "," + count + "件");
    }

    //用户点评数
    @BindView(R.id.tv_comment_count)
    TextView tv_commentCount;
    //好评度
    @BindView(R.id.tv_good_comment)
    TextView tv_goodsComment;
    //商品介绍
    @BindView(R.id.tv_goods_introduction)
    TextView tv_goodsIntroduction;
    //营养
    @BindView(R.id.tv_goods_nutrition)
    TextView tv_goodsNutrition;
    //功效
    @BindView(R.id.tv_goods_effect)
    TextView tv_goodsEffect;
    //收藏
    @BindView(R.id.btn_collection)
    Button btn_collection;

    @OnClick(R.id.btn_collection)
    public void postToCollection() {
        presenter.postToCollection(_mActivity, fruitDetail.getId());
    }

    //加入购物车
    @BindView(R.id.btn_post)
    Button btn_post;

    @OnClick(R.id.btn_post)
    public void postToShopping() {
        presenter.postToShopping(_mActivity, fruitDetail.getId(), count);
    }

    BottomBar bottomBar;

    private static final String ARG_MENUS = "arg_goods";
    private static final String ARG_PRICE = "arg_price";
    private static final String ARG_COUNT = "arg_count";
    private Fruit.FruitDetail fruitDetail;
    private double oldPrice = 0;
    private int count = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            fruitDetail = args.getParcelable(ARG_MENUS);
            oldPrice = args.getDouble(ARG_PRICE);
            count = args.getInt(ARG_COUNT);
        }
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_goods_info;
    }

    @Override
    public void setPresenter() {
        presenter = new GoodsInfoPresenter(this);
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        bottomBar = (BottomBar) _mActivity.findViewById(R.id.bottomBar);

        bottomBar.setVisibility(View.GONE);

        toolbar.setNavigationIcon(R.mipmap.toolbar_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
        toolbar.setTitle("商品详情");

        GlideApp.with(_mActivity).asBitmap().load(fruitDetail.getGoodsImage()).diskCacheStrategy(DiskCacheStrategy.ALL).circleCrop().into(img_goods);
        tv_goodsTitle.setText(fruitDetail.getGoodsName());
        tv_currentGoods.setText(fruitDetail.getGoodsName() + "," + count + "件");

        tv_goodsIntroduction.setText(fruitDetail.getGoodsIntroduction());
        tv_goodsNutrition.setText(fruitDetail.getNutritionInfo());
        tv_goodsEffect.setText(fruitDetail.getEffect());
        tv_newPrice.setText(fruitDetail.getGoodsPrice() + "");
        if (oldPrice == 0) {
            tv_oldPrice.setText((Double.parseDouble(fruitDetail.getGoodsPrice()) + 10.00) + "");
        } else {
            tv_oldPrice.setText(oldPrice + "");

        }
    }

    @Override
    public void setToolbar() {

    }

    @Override
    public void onSuccessToast(String msg) {
        ToastUtils.showToast(_mActivity, msg);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bottomBar.setVisibility(View.VISIBLE);
    }
}
