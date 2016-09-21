package com.zncm.easycycd.modules;

import android.content.Intent;
import android.os.Bundle;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.umeng.analytics.MobclickAgent;
import com.zncm.easycycd.R;
import com.zncm.easycycd.data.base.CollectData;
import com.zncm.easycycd.data.base.CyData;
import com.zncm.easycycd.global.GlobalConstants;
import com.zncm.utils.StrUtil;
import com.zncm.utils.ViewUtils;
import com.zncm.utils.exception.CheckedExceptionHandler;

import java.util.ArrayList;
import java.util.List;

public class Details extends BaseHomeActivity {
    private int id;
    private CyData data;
    private CollectData collectData;
    private RuntimeExceptionDao<CyData, Integer> dao = null;
    private RuntimeExceptionDao<CollectData, Integer> collectDao = null;
    private boolean bCollected = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_cy_details);
        initData();
    }

    private void initData() {
        getSupportActionBar().setTitle("我的收藏");

        id = getIntent().getExtras().getInt(GlobalConstants.KEY_ID);
        try {
            if (dao == null) {
                dao = getHelper().getCyDataDao();
            }
            if (collectDao == null) {
                collectDao = getHelper().getCollectDataDao();
            }
            data = dao.queryForId(id);
            if (data != null) {
                List<CollectData> list = new ArrayList<CollectData>();
                list = collectDao.queryForEq("cy_id", id);
                if (StrUtil.listNotNull(list)) {
                    collectData = list.get(0);
                    bCollected = true;
                } else {
                    bCollected = false;
                }

                ViewUtils.setTextView(this, R.id.tvName, data.getName());
                ViewUtils.setTextView(this, R.id.tvPy, data.getPy());
                ViewUtils.setTextView(this, R.id.tvJs, data.getJs());
                ViewUtils.setTextView(this, R.id.tvCc, data.getCc());
            }
        } catch (Exception e) {
            CheckedExceptionHandler.handleException(e);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add("net").setIcon(R.drawable.net).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        if (bCollected) {
            menu.add("collecte").setIcon(R.drawable.star_full).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        } else {
            menu.add("collecte").setIcon(R.drawable.star_empty).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }
        menu.add("share").setIcon(R.drawable.abs__ic_menu_share_holo_dark).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getTitle().equals("net")) {
            if (data != null && StrUtil.notEmptyOrNull(data.getName())) {
                Intent intent = new Intent(this,
                        BikeWebView.class);
                intent.putExtra("word", data.getName());
                startActivity(intent);
            }
        }

        if (item.getTitle().equals("collecte")) {
            if (bCollected) {
                if (collectData != null) {
                    collectDao.deleteById(collectData.getId());
                }
                item.setIcon(R.drawable.star_empty);
                bCollected = false;
            } else {
                if (data != null) {
                    collectData = new CollectData(data.getId(), data.getName());
                    collectDao.create(collectData);
                }
                item.setIcon(R.drawable.star_full);
                bCollected = true;
            }
        }


        if (item.getTitle().equals("share")) {
            if (data != null && StrUtil.notEmptyOrNull(data.getName())) {
                StrUtil.SendTo(Details.this, data.getName() + "：" + data.getJs());
            }
        }

        return false;
    }


    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }


}