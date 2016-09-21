//package com.zncm.easycycd.modules;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.v4.widget.SearchViewCompat;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.BaseAdapter;
//import android.widget.GridView;
//import android.widget.ListView;
//import android.widget.ProgressBar;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.actionbarsherlock.app.SherlockFragment;
//import com.actionbarsherlock.app.SherlockFragmentActivity;
//import com.actionbarsherlock.view.Menu;
//import com.actionbarsherlock.view.MenuInflater;
//import com.actionbarsherlock.view.MenuItem;
//import com.umeng.analytics.MobclickAgent;
//import com.zncm.easycycd.R;
//import com.zncm.easycycd.data.base.CyData;
//
//import java.util.ArrayList;
//
//public class SearchFragment extends SherlockFragment {
//    private Activity mActivity;
//    private View view;
//    private ArrayList<CyData> cyList = null;
//    private String search_key = "";
//    private ProgressBar pb01 = null;
//    private ListView gv01 = null;
//    private GridViewAdapter gridViewAdapter = null;
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        mActivity = getActivity();
//        view = inflater.inflate(R.layout.search, null);
//        gv01 = (GridView) view.findViewById(R.id.search_gv01);
//        pb01 = (ProgressBar) view.findViewById(R.id.search_pb01);
//        tv01 = (TextView) view.findViewById(R.id.search_tv01);
//        tv01.setVisibility(View.GONE);
//        pb01.setVisibility(View.GONE);
//        search_key = "查字";
//        GetRandomZi randomZi = new GetRandomZi();
//        randomZi.execute("");
//        return view;
//    }
//
//
//    private class GridViewAdapter extends BaseAdapter {
//        Context context;// 上下文
//        ArrayList<Zi> cyList;
//
//        public GridViewAdapter(Context context, ArrayList<Zi> cyList) {
//            this.context = context;
//            this.cyList = cyList;
//        }
//
//        @Override
//        public int getCount() {
//
//            return cyList.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//
//            return cyList.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            LayoutInflater mInflater = LayoutInflater.from(context);
//            convertView = mInflater.inflate(R.layout.item, null);
//            TextView tv01 = (TextView) convertView.findViewById(R.id.item_tv01);
//            TextView tv02 = (TextView) convertView.findViewById(R.id.item_tv02);
//            TextView tv03 = (TextView) convertView.findViewById(R.id.item_tv03);
//            RelativeLayout rl01 = (RelativeLayout) convertView
//                    .findViewById(R.id.item_rl01);
//            tv01.setText(cyList.get(position).getPy());
//            tv02.setText(cyList.get(position).getZi());
//            tv03.setText(cyList.get(position).getBs());
//            rl01.setBackgroundResource(R.drawable.bg_texture);
//            return convertView;
//        }
//    }
//
//    class GetSearchDreamInfo extends AsyncTask<String, Integer, Integer> {
//
//
//        protected Integer doInBackground(String... params) {
//            int status = 0;
//            cyList = zda.QueryZis("zi = '" + search_key + "'or py = '" + search_key + "'or bh = '" + search_key + "'", null);
//            return status;
//
//        }
//
//        @Override
//        protected void onPreExecute() {
//
//            super.onPreExecute();
//        }
//
//        protected void onPostExecute(Integer integer) {
//            super.onPostExecute(integer);
//            try {
//
//                pb01.setVisibility(View.GONE);
//                tv01.setVisibility(View.VISIBLE);
//
//                if (cyList != null) {
//                    if (cyList.size() > 0) {
//                        tv01.setText("找到" + cyList.size() + "条记录.");
//                    } else {
//                        tv01.setText("什么也没有耶.");
//                    }
//                } else {
//                    tv01.setText("什么也没有耶.");
//                }
//
//                gridViewAdapter = new GridViewAdapter(mActivity, cyList);
//                gv01.setAdapter(gridViewAdapter);
//
//                gv01.setOnItemClickListener(new OnItemClickListener() {
//
//                    @Override
//                    public void onItemClick(AdapterView<?> arg0, View arg1,
//                                            int position, long arg3) {
//
//                        Intent intent = new Intent(mActivity, ZiDetails.class);
//                        intent.putExtra("word", cyList.get(position).getZi());
//                        startActivity(intent);
//
//                    }
//                });
//
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//
//        }
//    }
//
//    class GetRandomZi extends AsyncTask<String, Integer, Integer> {
//
//
//        protected Integer doInBackground(String... params) {
//            int status = 0;
//            ArrayList<String> temp = CommonUtils.TenRandom(20890);
//            cyList = new ArrayList<Zi>();
//            for (int i = 0; i < temp.size(); i++) {
//                Zi zi = new Zi();
//                zi = zda.QueryZiById(temp.get(i));
//                cyList.add(zi);
//            }
//            return status;
//
//        }
//
//        @Override
//        protected void onPreExecute() {
//
//            super.onPreExecute();
//        }
//
//        protected void onPostExecute(Integer integer) {
//            super.onPostExecute(integer);
//            try {
//                tv01.setVisibility(View.GONE);
//                pb01.setVisibility(View.GONE);
//                gridViewAdapter = new GridViewAdapter(mActivity, cyList);
//                gv01.setAdapter(gridViewAdapter);
//                gv01.setOnItemClickListener(new OnItemClickListener() {
//
//                    @Override
//                    public void onItemClick(AdapterView<?> arg0, View arg1,
//                                            int position, long arg3) {
//
//                        Intent intent = new Intent(mActivity, ZiDetails.class);
//                        intent.putExtra("word", cyList.get(position).getZi());
//                        startActivity(intent);
//
//                    }
//                });
//
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//
//        }
//    }
//
//
//    public void onResume() {
//        super.onResume();
//        MobclickAgent.onResume(mActivity);
//    }
//
//    public void onPause() {
//        super.onPause();
//        MobclickAgent.onPause(mActivity);
//    }
//
//
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        setHasOptionsMenu(true);
//    }
//
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        MenuItem item = menu.add("search");
//        item.setIcon(android.R.drawable.ic_menu_search);
//        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
//        menu.add("refresh").setIcon(R.drawable.refresh).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
//        SherlockFragmentActivity activity = (SherlockFragmentActivity) getActivity();
//        View searchView = SearchViewCompat.newSearchView(activity.getSupportActionBar().getThemedContext());
//        if (searchView != null) {
//            SearchViewCompat.setOnQueryTextListener(searchView, new SearchViewCompat.OnQueryTextListenerCompat() {
//                @Override
//                public boolean onQueryTextChange(String newText) {
//
//                    if (!CommonUtils.isEmptyOrNull(newText)) {
//                        search_key = newText;
//                        GetSearchDreamInfo searchDreamInfo = new GetSearchDreamInfo();
//                        searchDreamInfo.execute("");
//                        pb01.setVisibility(View.VISIBLE);
//                    } else {
//                        GetRandomZi randomZi = new GetRandomZi();
//                        randomZi.execute("");
//                    }
//                    return true;
//                }
//            });
//            item.setActionView(searchView);
//        }
//    }
//
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getTitle().equals("refresh")) {
//            GetRandomZi randomZi = new GetRandomZi();
//            randomZi.execute("");
//        }
//        return false;
//    }
//
//}