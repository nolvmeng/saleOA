package com.haishan.saleoa;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //声明ViewPager
    private ViewPager ViewPager;
    //适配器
    private FragmentPagerAdapter Adapter;
    //装载Fragment的集合
    private List<Fragment> mFragments;

    //三个Tab对应的布局
    private LinearLayout TabGoods;
    private LinearLayout TabOrders;
    private LinearLayout TabCustomers;


    //三个Tab对应的ImageButton
    private ImageButton ImgGoods;
    private ImageButton ImgOrders;
    private ImageButton ImgCustomers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        initViews();//初始化控件
        initEvents();//初始化事件
        initDatas();//初始化数据
        selectTab(0);//默认点击
    }


    //初始化控件
    private void initViews() {
        ViewPager = (ViewPager) findViewById(R.id.viewpager);

        TabGoods = (LinearLayout) findViewById(R.id.main_goods);
        TabOrders = (LinearLayout) findViewById(R.id.main_orders);
        TabCustomers = (LinearLayout) findViewById(R.id.main_customers);


        ImgGoods = (ImageButton) findViewById(R.id.main_goods_img);
        ImgOrders = (ImageButton) findViewById(R.id.main_orders_img);
        ImgCustomers = (ImageButton) findViewById(R.id.main_customers_img);


    }


    private void initDatas() {
        mFragments = new ArrayList<>();
        //将四个Fragment加入集合中
        mFragments.add(new GoodsFragment());
        mFragments.add(new OrdersFragment());
        mFragments.add(new CustomersFragment());


        //初始化适配器
        Adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {//从集合中获取对应位置的Fragment
                return mFragments.get(position);
            }

            @Override
            public int getCount() {//获取集合中Fragment的总数
                return mFragments.size();
            }

        };
        //不要忘记设置ViewPager的适配器
        ViewPager.setAdapter(Adapter);

        //设置ViewPager的切换监听
        ViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            //页面滚动事件
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //页面选中事件
            @Override
            public void onPageSelected(int position) {
                //设置position对应的集合中的Fragment

                ViewPager.setCurrentItem(position);
                resetImgs();
                selectTab(position);

            }

            @Override
            //页面滚动状态改变事件
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //先将四个ImageButton置为灰色
            resetImgs();


            //根据点击的Tab切换不同的页面及设置对应的ImageButton为绿色
            switch (v.getId()) {
                case R.id.main_goods:
                    selectTab(0);
                    break;
                case R.id.main_orders:
                    selectTab(1);
                    break;
                case R.id.main_customers:
                    selectTab(2);
                    break;

            }
        }
    };

    private void initEvents() {
        //设置四个Tab的点击事件
        TabGoods.setOnClickListener(onClickListener);
        TabOrders.setOnClickListener(onClickListener);
        TabCustomers.setOnClickListener(onClickListener);


    }

    private void selectTab(int i) {
        //根据点击的Tab设置对应的ImageButton为绿色
        switch (i) {
            case 0:
                ImgGoods.setImageResource(R.drawable.huopin_press);
                break;
            case 1:
                ImgOrders.setImageResource(R.drawable.dingdan_press);
                break;
            case 2:
                ImgCustomers.setImageResource(R.drawable.kehu_press);
                break;
        }
        //设置当前点击的Tab所对应的页面
        ViewPager.setCurrentItem(i);
    }

    //将四个ImageButton设置为灰色
    private void resetImgs() {
        ImgGoods.setImageResource(R.drawable.huopin);
        ImgOrders.setImageResource(R.drawable.dingdan);
        ImgCustomers.setImageResource(R.drawable.kehu);

    }
}
