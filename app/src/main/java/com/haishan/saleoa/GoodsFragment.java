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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.haishan.saleoa.GoodsSubFragment.AddGoodsFragment;
import com.haishan.saleoa.GoodsSubFragment.MainGoodsFragment;
import com.haishan.saleoa.GoodsSubFragment.OthersGoodsFragment;
import com.haishan.saleoa.GoodsSubFragment.SubFragment1;
import com.haishan.saleoa.GoodsSubFragment.SubFragment2;
import com.haishan.saleoa.GoodsSubFragment.SubFragment3;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoodsFragment extends Fragment {
    private SubFragment1 subFragment1;
    private SubFragment2 subFragment2;
    private SubFragment3 subFragment3;
    private MainGoodsFragment mainGoodsFragment;
    private OthersGoodsFragment othersGoodsFragment;
    private AddGoodsFragment addGoodsFragment;
    /**
     * PagerSlidingTabStrip的实例
     */
    private PagerSlidingTabStrip tabs;

    /**
     * 获取当前屏幕的密度
     */
    private DisplayMetrics dm;

    public GoodsFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_goods,null);
        initView(view);
        return view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Auto-generated method stub
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_login, menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. T he action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView(View view) {

        dm = getResources().getDisplayMetrics();
        ViewPager pager = (ViewPager) view.findViewById(R.id.goods_pager);
        tabs = (PagerSlidingTabStrip) view.findViewById(R.id.goods_top);
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
                TypedValue.COMPLEX_UNIT_DIP,2, dm));// 4
        // 设置Tab标题文字的大小
        tabs.setTextSize((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 15, dm)); // 16
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

        private final String[] titles = { "全部商品", "肉类", "蔬菜类" ,"主食","其它","添加商品"};

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

                    if (null == subFragment1) {
                        subFragment1 = new SubFragment1();
                    }

                    return subFragment1;

                case 1:

                    if (null == subFragment2) {
                        subFragment2 = new SubFragment2();
                    }

                    return subFragment2;
                case 2:

                    if (null == subFragment3) {
                        subFragment3 = new SubFragment3();
                    }
                    return subFragment3;
                case 3:
                    if(null == mainGoodsFragment){
                        mainGoodsFragment = new MainGoodsFragment();
                    }
                    return mainGoodsFragment;
                case 4:
                    if(null == othersGoodsFragment){
                        othersGoodsFragment = new OthersGoodsFragment();
                    }
                    return othersGoodsFragment;
                case 5:
                    if(null == addGoodsFragment){
                        addGoodsFragment = new AddGoodsFragment();
                    }
                    return addGoodsFragment;
                default:
                    return null;
            }
        }

    }
}
