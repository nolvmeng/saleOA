package com.haishan.saleoa;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haishan.saleoa.CustomersSubFragment.SubFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class CustomersFragment extends Fragment {
    private SubFragment subFragment;

    /**
     * PagerSlidingTabStrip的实例
     */
    private PagerSlidingTabStrip tabs;

    /**
     * 获取当前屏幕的密度
     */
    private DisplayMetrics dm;

    public CustomersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_customers,null);
        initView(view);
        return view;
    }
    private void initView(View view) {

        dm = getResources().getDisplayMetrics();
        ViewPager pager = (ViewPager) view.findViewById(R.id.customers_pager);
        tabs = (PagerSlidingTabStrip) view.findViewById(R.id.customers_top);
        pager.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
        tabs.setViewPager(pager);
        setTabsValue();

    }

    /**
     * 对PagerSlidingTabStrip的各项属性进行赋值。
     */
    private void setTabsValue() {
        // 设置Tab是自动填充满屏幕的
        tabs.setShouldExpand(true);
        // 设置Tab的分割线是透明的
        tabs.setDividerColor(Color.TRANSPARENT);
        // tabs.setDividerColor(Color.BLACK);
        // 设置Tab底部线的高度
        tabs.setUnderlineHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 1, dm));
        // 设置Tab Indicator的高度
        tabs.setIndicatorHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 4, dm));// 4
        // 设置Tab标题文字的大小
        tabs.setTextSize((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 12, dm)); // 16
        // 设置Tab Indicator的颜色
        tabs.setIndicatorColor(Color.parseColor("#45c01a"));// #45c01a
        // 取消点击Tab时的背景色
        tabs.setTabBackground(0);
    }

    public class MyPagerAdapter extends FragmentStatePagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            // TODO Auto-generated constructor stub
        }

        private final String[] titles = { "全部客户" };

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:

                    if (null == subFragment) {
                        subFragment = new SubFragment();
                    }

                    return subFragment;

                default:
                    return null;
            }
        }

    }
}
