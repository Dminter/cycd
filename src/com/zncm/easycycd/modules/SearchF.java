package com.zncm.easycycd.modules;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SearchViewCompat;
import android.support.v4.widget.SearchViewCompat.OnQueryTextListenerCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.zncm.component.pullrefresh.PullToRefreshBase;
import com.zncm.easycycd.R;
import com.zncm.easycycd.data.base.CyData;
import com.zncm.easycycd.global.GlobalConstants;
import com.zncm.easycycd.modules.adapter.CyAdapter;
import com.zncm.utils.L;
import com.zncm.utils.StrUtil;
import com.zncm.utils.exception.CheckedExceptionHandler;

import java.util.ArrayList;
import java.util.List;

public class SearchF extends BaseFragment {
    private Activity mActivity;
    public List<CyData> datas = null;
    private int curPosition;
    private RuntimeExceptionDao<CyData, Integer> dao = null;
    private boolean bSearch = false;
    private CyAdapter mAdapter;
    private boolean bZiCount = false;
    private int ziCount;
    private boolean bSolitaire = false;
    private String solitaireEnd;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem item = menu.add("search");
        item.setIcon(android.R.drawable.ic_menu_search);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        SubMenu sub = menu.addSubMenu("字数");
        sub.setIcon(R.drawable.word_width);
        sub.add(0, 6, 0, "任意个字");
        sub.add(0, 1, 0, "三个字");
        sub.add(0, 2, 0, "四个字");
        sub.add(0, 3, 0, "五个字");
        sub.add(0, 4, 0, "六个字");
        sub.add(0, 5, 0, "六个字以上");
        sub.getItem().setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        menu.add("solitaire").setIcon(R.drawable.solitaire_grey).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);


        SherlockFragmentActivity activity = (SherlockFragmentActivity) getActivity();
        View searchView = SearchViewCompat.newSearchView(activity.getSupportActionBar().getThemedContext());
        if (searchView != null) {
            SearchViewCompat.setOnQueryTextListener(searchView, new OnQueryTextListenerCompat() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    if (StrUtil.notEmptyOrNull(newText)) {
                        bSearch = true;
                        try {
                            QueryBuilder<CyData, Integer> builder = dao.queryBuilder();
                            builder.where().like("name", "%" + newText.toString().trim() + "%");
                            List<CyData> datas = dao.query(builder.prepare());
                            SearchF.this.datas = datas;
                            mAdapter.setItems(SearchF.this.datas);
                            loadComplete();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        bSearch = false;
                        getData(true);
                    }

                    return true;
                }
            });
            item.setActionView(searchView);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getTitle().equals("solitaire")) {

            if (bSolitaire) {
                bSolitaire = false;
                item.setIcon(R.drawable.solitaire_grey);
            } else {
                bSolitaire = true;
                item.setIcon(R.drawable.solitaire);
                L.toastShort("成语接龙,长按查看详情~");
            }

        }


//
        switch (item.getItemId()) {

            case 1:
                ziCountDo(3);
                break;
            case 2:
                ziCountDo(4);
                break;
            case 3:
                ziCountDo(5);
                break;
            case 4:
                ziCountDo(6);
                break;
            case 5:
                ziCountDo(7);
                break;
            case 6:
                ziCountDo(0);
                break;
        }
        return false;
    }


    private void ziCountDo(int count) {
        bZiCount = true;
        ziCount = count;
        getData(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        this.mActivity = (Activity) getActivity();
        datas = new ArrayList<CyData>();
        dao = getHelper().getCyDataDao();
        mAdapter = new CyAdapter(mActivity) {
            @Override
            public void setData(int position, NoteViewHolder holder) {
                CyData data = (CyData) datas.get(position);
                holder.tvName.setText(data.getName());
            }
        };
        mAdapter.setItems(datas);
        lvBase.setAdapter(mAdapter);
        plListView.setOnItemClickListener(new OnItemClickListener() {

            @SuppressWarnings({"rawtypes", "unchecked"})
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                curPosition = position - lvBase.getHeaderViewsCount();

//                intent.putExtra(GlobalConstants.KEY_ID, datas.get(curPosition).getId());
//                startActivity(intent);

                if (bSolitaire) {
                    String cyData = datas.get(curPosition).getName();
                    solitaireEnd = cyData.substring(cyData.length() - 1);
                    getData(true);
                } else {
                    Intent intent = new Intent(mActivity, CyPagerActivity.class);
                    intent.putExtra("current", curPosition);
                    ArrayList list = new ArrayList();
                    list.add(datas);
                    intent.putParcelableArrayListExtra(GlobalConstants.KEY_LIST_DATA, list);
                    startActivity(intent);

                }


            }
        });

        lvBase.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                curPosition = position - lvBase.getHeaderViewsCount();

                Intent intent = new Intent(mActivity, CyPagerActivity.class);
                intent.putExtra("current", curPosition);
                ArrayList list = new ArrayList();
                list.add(datas);
                intent.putParcelableArrayListExtra(GlobalConstants.KEY_LIST_DATA, list);
                startActivity(intent);

                return false;
            }
        });

        initListView();
        getData(true);

        return view;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @SuppressWarnings("deprecation")
    public void initListView() {
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
                    dao = getHelper().getCyDataDao();
                }
                QueryBuilder<CyData, Integer> builder = dao.queryBuilder();
                if (bZiCount) {
                    switch (ziCount) {
                        case 0:
                            break;
                        case 3:
                            builder.where().like("name", "___");
                            break;
                        case 4:
                            builder.where().like("name", "____");
                            break;
                        case 5:
                            builder.where().like("name", "_____");
                            break;
                        case 6:
                            builder.where().like("name", "______");
                            break;
                        default:
                            builder.where().like("name", "%______%");
                            break;
                    }
                }
                if (bSolitaire) {
                    builder.where().like("name", solitaireEnd + "%");
                }
                builder.orderBy("id", true);
                if (params[0]) {
                    builder.limit(GlobalConstants.DB_PAGE_SIZE);
                } else {
                    builder.limit(GlobalConstants.DB_PAGE_SIZE).offset((long) datas.size());
                }
                List<CyData> list = dao.query(builder.prepare());
                if (params[0]) {
                    datas = new ArrayList<CyData>();
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
