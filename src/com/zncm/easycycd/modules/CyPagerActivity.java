package com.zncm.easycycd.modules;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.umeng.analytics.MobclickAgent;
import com.zncm.component.ormlite.DatabaseHelper;
import com.zncm.component.view.HackyViewPager;
import com.zncm.easycycd.R;
import com.zncm.easycycd.data.base.CollectData;
import com.zncm.easycycd.data.base.CyData;
import com.zncm.easycycd.global.GlobalConstants;
import com.zncm.easycycd.global.SharedApplication;
import com.zncm.utils.L;
import com.zncm.utils.StrUtil;
import com.zncm.utils.exception.CheckedExceptionHandler;

import java.util.ArrayList;
import java.util.List;

public class CyPagerActivity extends SherlockFragmentActivity {

    private ViewPager mViewPager;
    @SuppressWarnings("rawtypes")
    private ArrayList list;
    private int current;
    private ArrayList<CyData> datas = null;
    private CollectData collectData;
    private RuntimeExceptionDao<CyData, Integer> dao = null;
    private RuntimeExceptionDao<CollectData, Integer> collectDao = null;
    private ActionBar actionBar;
    private DatabaseHelper databaseHelper = null;

    public DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(SharedApplication.getInstance().ctx, DatabaseHelper.class);
        }
        return databaseHelper;
    }


    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_cy_scan);
        mViewPager = (HackyViewPager) findViewById(R.id.vpCy);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("成语浏览");

        current = getIntent().getExtras().getInt("current");
        list = getIntent().getExtras().getParcelableArrayList(GlobalConstants.KEY_LIST_DATA);
        datas = (ArrayList<CyData>) list.get(0);
        mViewPager.setAdapter(new SamplePagerAdapter(datas));
        mViewPager.setCurrentItem(current);
    }

    class SamplePagerAdapter extends PagerAdapter {
        private ArrayList<CyData> datas;

        public SamplePagerAdapter(ArrayList<CyData> datas) {
            this.datas = datas;
        }

        @Override
        public int getCount() {
            if (datas == null) {
                return 0;
            } else {
                return datas.size();
            }

        }

        @Override
        public View instantiateItem(ViewGroup container, final int position) {
            final Context ctx = container.getContext();
            final CyDetailsView view = new CyDetailsView(ctx);
            final CyData data = datas.get(position);

            view.getTvName().setText(data.getName());
            view.getTvPy().setText("[拼音] " + data.getPy());
            view.getTvJs().setText("[解释] " + data.getJs());
            view.getTvCc().setText("[出处] " + data.getCc());
            view.getTvPage().setText((position + 1) + "/" + datas.size());
            container.addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }


    // umeng

    @Override
    protected void onResume() {

        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {

        super.onPause();
        MobclickAgent.onPause(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("net").setIcon(R.drawable.net).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add("collecte").setIcon(R.drawable.star_empty).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add("share").setIcon(R.drawable.abs__ic_menu_share_holo_dark).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getTitle().equals("net")) {
            if (datas != null && StrUtil.notEmptyOrNull(datas.get(mViewPager.getCurrentItem()).getName())) {
                Intent intent = new Intent(this,
                        BikeWebView.class);
                intent.putExtra("word", datas.get(mViewPager.getCurrentItem()).getName());
                startActivity(intent);
            }
        }

        if (item.getTitle().equals("collecte")) {
            try {
                if (collectDao == null) {
                    collectDao = getHelper().getCollectDataDao();
                }
                if (datas.get(mViewPager.getCurrentItem()) != null) {
                    List<CollectData> list = new ArrayList<CollectData>();
                    list = collectDao.queryForEq("cy_id", datas.get(mViewPager.getCurrentItem()).getId());
                    if (StrUtil.listNotNull(list)) {
                        collectData = list.get(0);
                        item.setIcon(R.drawable.star_full);
                        L.i("收藏过了");
                    } else {
                        collectData = new CollectData(datas.get(mViewPager.getCurrentItem()).getId(), datas.get(mViewPager.getCurrentItem()).getName());
                        collectDao.create(collectData);
                        item.setIcon(R.drawable.star_full);
                    }

                }
            } catch (Exception e) {
                CheckedExceptionHandler.handleException(e);
            }
        }


        if (item.getTitle().equals("share")) {
            if (datas != null && StrUtil.notEmptyOrNull(datas.get(mViewPager.getCurrentItem()).getName())) {
                StrUtil.SendTo(CyPagerActivity.this, datas.get(mViewPager.getCurrentItem()).getName() + "：" + datas.get(mViewPager.getCurrentItem()).getJs());
            }
        }


        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return false;
    }


}
