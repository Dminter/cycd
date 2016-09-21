//package com.zncm.easycycd.modules;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentStatePagerAdapter;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v4.view.GravityCompat;
//import android.support.v4.view.ViewPager;
//import android.support.v4.widget.DrawerLayout;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//
//import com.actionbarsherlock.app.ActionBar;
//import com.actionbarsherlock.app.ActionBar.Tab;
//import com.actionbarsherlock.app.SherlockFragmentActivity;
//import com.actionbarsherlock.view.MenuItem;
//import com.sherlock.navigationdrawer.compat.SherlockActionBarDrawerToggle;
//import com.zncm.easycycd.R;
//
//import java.util.ArrayList;
//
////主界面tab
//public class MainPager extends SherlockFragmentActivity {
//
//    private ViewPager mViewPager;
//    private MyTabsAdapter mMyTabsAdapter;
//    private ActionBar actionBar;
//    private String titles[] = new String[]{"查字", "部首", "音序", "收藏"};
//
//    private SherlockActionBarDrawerToggle mDrawerToggle;
//    private DrawerLayout mDrawerLayout;
//    private ListView listView;
//    private ActionBarHelper mActionBar;
//    private static final String[] left_titles = new String[]{"关于我们", "作者其他软件", "意见反馈", "退出"};
//    private boolean bOpen = false;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        actionBar = getSupportActionBar();
//        setContentView(R.layout.ac_tabs_pager);
//        mViewPager = (ViewPager) this.findViewById(R.id.pager);
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//        SearchF searchFragment = new SearchF();
////        BSGridFragment bsGridFragment = new BSGridFragment();
////        ZimuGridFragment zimuGridFragment = new ZimuGridFragment();
////        CollectFragment collectFragment = new CollectFragment();
//        mMyTabsAdapter = new MyTabsAdapter(this, mViewPager);
//        mMyTabsAdapter.addTab(actionBar.newTab().setText("查字"), SearchF.class, null, searchFragment);
////        mMyTabsAdapter.addTab(actionBar.newTab().setText("部首"), BSGridFragment.class, null, bsGridFragment);
////        mMyTabsAdapter.addTab(actionBar.newTab().setText("音序"), ZimuGridFragment.class, null, zimuGridFragment);
////        mMyTabsAdapter.addTab(actionBar.newTab().setText("收藏"), CollectFragment.class, null, collectFragment);
//        mViewPager.setOffscreenPageLimit(mMyTabsAdapter.getCount() - 1);
//        mViewPager.setCurrentItem(0);
//        actionBar.setTitle(titles[0]);
//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        listView = (ListView) findViewById(R.id.left_drawer);
//        mDrawerLayout.setDrawerListener(new DemoDrawerListener());
//        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
//        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, left_titles));
//        listView.setOnItemClickListener(new DrawerItemClickListener());
//        listView.setCacheColorHint(0);
//        listView.setScrollingCacheEnabled(false);
//        listView.setScrollContainer(false);
//        listView.setFastScrollEnabled(true);
//        listView.setSmoothScrollbarEnabled(true);
//        mActionBar = createActionBarHelper();
//        mActionBar.init();
//        mDrawerToggle = new SherlockActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer_light,
//                R.string.drawer_open, R.string.drawer_close);
//        mDrawerToggle.syncState();
//    }
//
//    public class MyTabsAdapter extends FragmentStatePagerAdapter implements ActionBar.TabListener,
//            ViewPager.OnPageChangeListener {
//
//        private final ActionBar mActionBar;
//        private final ViewPager mViewPager;
//        private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();
//        private final ArrayList<Fragment> mFrag = new ArrayList<Fragment>();
//
//        final class TabInfo {
//            @SuppressWarnings("unused")
//            private final Class<?> clss;
//            @SuppressWarnings("unused")
//            private final Bundle args;
//
//            TabInfo(Class<?> _class, Bundle _args) {
//                clss = _class;
//                args = _args;
//            }
//        }
//
//        public MyTabsAdapter(MainPager activity, ViewPager pager) {
//            super(activity.getSupportFragmentManager());
//            mActionBar = activity.getSupportActionBar();
//            mViewPager = pager;
//            mViewPager.setAdapter(this);
//            mViewPager.setOnPageChangeListener(this);
//        }
//
//        public void addTab(Tab tab, Class<?> clss, Bundle args, Fragment frag) {
//            TabInfo info = new TabInfo(clss, args);
//            tab.setTag(info);
//            tab.setTabListener(this);
//            mTabs.add(info);
//            mFrag.add(frag);
//            mActionBar.addTab(tab);
//            notifyDataSetChanged();
//        }
//
//        @Override
//        public int getCount() {
//            return mTabs.size();
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return mFrag.get(position);
//        }
//
//        @Override
//        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            actionBar.setTitle(titles[position]);
//        }
//
//        @Override
//        public void onPageSelected(int position) {
//            mActionBar.setSelectedNavigationItem(position);
//            /*
//             * MenuInflater inflater = mContext.getSupportMenuInflater();
//			 * mContext.menu.clear(); inflater.inflate(R.menu.MainActivity,
//			 * mContext.menu);
//			 */
//        }
//
//        @Override
//        public void onPageScrollStateChanged(int state) {
//        }
//
//        @Override
//        public void onTabSelected(Tab tab, FragmentTransaction ft) {
//            Object tag = tab.getTag();
//            for (int i = 0; i < mTabs.size(); i++) {
//                if (mTabs.get(i) == tag) {
//                    mViewPager.setCurrentItem(i);
//                }
//            }
//        }
//
//        @Override
//        public void onTabUnselected(Tab tab, FragmentTransaction ft) {
//        }
//
//        @Override
//        public void onTabReselected(Tab tab, FragmentTransaction ft) {
//        }
//    }
//
//    private class DrawerItemClickListener implements ListView.OnItemClickListener {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            Intent intent = null;
//            switch (position) {
//                case 0:
//                    intent = new Intent(MainPager.this, About.class);
//                    startActivity(intent);
//                    break;
//                case 1:
//                    intent = new Intent(MainPager.this, Recommend.class);
//                    startActivity(intent);
//                    break;
//                case 2:
//                    UMFeedbackService.openUmengFeedbackSDK(MainPager.this);
//                    break;
//                case 3:
//                    finish();
//                    break;
//                default:
//                    break;
//            }
//            bOpen = false;
//            mDrawerLayout.closeDrawer(listView);
//        }
//    }
//
//    private class DemoDrawerListener implements DrawerLayout.DrawerListener {
//        @Override
//        public void onDrawerOpened(View drawerView) {
//            bOpen = true;
//            mDrawerToggle.onDrawerOpened(drawerView);
//            mActionBar.onDrawerOpened();
//        }
//
//        @Override
//        public void onDrawerClosed(View drawerView) {
//            bOpen = false;
//            mDrawerToggle.onDrawerClosed(drawerView);
//            mActionBar.onDrawerClosed();
//        }
//
//        @Override
//        public void onDrawerSlide(View drawerView, float slideOffset) {
//            mDrawerToggle.onDrawerSlide(drawerView, slideOffset);
//        }
//
//        @Override
//        public void onDrawerStateChanged(int newState) {
//            mDrawerToggle.onDrawerStateChanged(newState);
//        }
//    }
//
//    private ActionBarHelper createActionBarHelper() {
//        return new ActionBarHelper();
//    }
//
//    private class ActionBarHelper {
//        private final ActionBar mActionBar;
//        private CharSequence mDrawerTitle;
//        private CharSequence mTitle;
//
//        private ActionBarHelper() {
//            mActionBar = getSupportActionBar();
//        }
//
//        public void init() {
//            mActionBar.setDisplayHomeAsUpEnabled(true);
//            mActionBar.setHomeButtonEnabled(true);
//            mTitle = mDrawerTitle = getTitle();
//        }
//
//        public void onDrawerClosed() {
//            mActionBar.setTitle(mTitle);
//        }
//
//        public void onDrawerOpened() {
//            mActionBar.setTitle(mDrawerTitle);
//        }
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        if (mDrawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//
////    @Override
////    public boolean onKeyDown(int keyCode, KeyEvent event) {
////        if (keyCode == KeyEvent.KEYCODE_MENU) {
////
////            if (bOpen) {
////                mDrawerToggle.onDrawerClosed(mDrawerLayout);
////                mActionBar.onDrawerClosed();
////            } else {
////                mDrawerToggle.onDrawerOpened(mDrawerLayout);
////                mActionBar.onDrawerOpened();
////            }
////
////            mDrawerToggle.syncState();
////
////        }
////
////        return true;
////    }
//
//
//}
