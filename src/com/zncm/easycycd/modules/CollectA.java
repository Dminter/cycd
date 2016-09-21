package com.zncm.easycycd.modules;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.zncm.component.pullrefresh.PullToRefreshBase;
import com.zncm.easycycd.R;
import com.zncm.easycycd.data.base.CollectData;
import com.zncm.easycycd.global.GlobalConstants;
import com.zncm.easycycd.modules.adapter.CyAdapter;
import com.zncm.easycycd.utils.XUtil;
import com.zncm.utils.L;
import com.zncm.utils.StrUtil;
import com.zncm.utils.exception.CheckedExceptionHandler;

import java.util.ArrayList;
import java.util.List;

public class CollectA extends BaseLvActivity {
    private Activity ctx;
    public List<CollectData> datas = null;
    private int curPosition;
    private RuntimeExceptionDao<CollectData, Integer> dao = null;
    private boolean bSearch = false;
    private CyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        actionBar.setTitle("我的收藏");
        ctx = this;
        datas = new ArrayList<CollectData>();
        dao = getHelper().getCollectDataDao();
        mAdapter = new CyAdapter(ctx) {
            @Override
            public void setData(int position, NoteViewHolder holder) {
                CollectData data = (CollectData) datas.get(position);
                holder.tvName.setText(data.getCy());
            }
        };
        mAdapter.setItems(datas);
        lvBase.setAdapter(mAdapter);
        plListView.setOnItemClickListener(new OnItemClickListener() {

            @SuppressWarnings({"rawtypes", "unchecked"})
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                curPosition = position - lvBase.getHeaderViewsCount();
                Intent intent = new Intent(ctx, Details.class);
                intent.putExtra(GlobalConstants.KEY_ID, datas.get(curPosition).getCy_id());
                startActivity(intent);
            }
        });
        lvBase.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                curPosition = position - lvBase.getHeaderViewsCount();

                final CollectData collectData = datas.get(curPosition);

                DialogFragment newFragment = new XUtil.TwoAlertDialogFragment(R.drawable.icon_del, "移出收藏") {

                    @Override
                    public void doPositiveClick() {
                        if (collectData != null) {
                            dao.deleteById(collectData.getId());
                            datas.remove(curPosition);
                            mAdapter.setItems(datas);
                            loadComplete();
                            L.toastShort("已移出");
                        }
                    }


                    @Override
                    public void doNegativeClick() {

                    }
                };
                newFragment.show(getSupportFragmentManager(), "dialog");
                return false;
            }
        });
        initListView();
        getData(true);
    }

    @SuppressWarnings("deprecation")
    public void initListView() {
//        plListView.setEmptyView();
        plListView.setPullToRefreshEnabled(false);
        plListView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {

            @Override
            public void onLastItemVisible() {
                if (!bSearch) {
                    getData(false);
                } else {
                    loadComplete();
                }
            }
        });
    }

    private void getData(boolean bFirst) {
        GetData getData = new GetData();
        getData.execute(bFirst);
    }

    class GetData extends AsyncTask<Boolean, Integer, Integer> {

        protected Integer doInBackground(Boolean... params) {
            try {
                if (dao == null) {
                    dao = getHelper().getCollectDataDao();
                }
                QueryBuilder<CollectData, Integer> builder = dao.queryBuilder();
                builder.orderBy("id", true);
                if (params[0]) {
                    builder.limit(GlobalConstants.DB_PAGE_SIZE);
                } else {
                    builder.limit(GlobalConstants.DB_PAGE_SIZE).offset((long) datas.size());
                }
                List<CollectData> list = dao.query(builder.prepare());
                if (params[0]) {
                    datas = new ArrayList<CollectData>();
                }
                if (StrUtil.listNotNull(list)) {
                    datas.addAll(list);
                }
            } catch (Exception e) {
                CheckedExceptionHandler.handleException(e);
            }
            return 0;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected void onPostExecute(Integer ret) {
            super.onPostExecute(ret);
            loadComplete();
            mAdapter.setItems(datas);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        getData(true);
    }

}
