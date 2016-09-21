//package com.zncm.easycycd.modules;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.BaseAdapter;
//import android.widget.GridView;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import com.actionbarsherlock.app.SherlockFragment;
//import com.actionbarsherlock.view.Menu;
//import com.actionbarsherlock.view.MenuInflater;
//import com.actionbarsherlock.view.MenuItem;
//import com.umeng.analytics.MobclickAgent;
//import com.zncm.easycycd.db.CollectZiDataAdapter;
//import com.zncm.easycycd.pojo.CollectZi;
//
//import java.util.ArrayList;
//import com.zncm.easycycd.R;
//public class CollectFragment extends SherlockFragment {
//    private Activity mActivity;
//    private View view;
//    private CollectZiDataAdapter collectAdapter = new CollectZiDataAdapter(mActivity);
//    private ArrayList<CollectZi> ziList = null;
//    private ProgressBar pb01 = null;
//    private GridView gv01 = null;
//    private GridViewAdapter gridViewAdapter = null;
//    private TextView tv01 = null;
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
//        GetSearchDreamInfo info = new GetSearchDreamInfo();
//        info.execute("");
//        return view;
//    }
//
//
//    private class GridViewAdapter extends BaseAdapter {
//        Context context;// 上下文
//        ArrayList<CollectZi> ziList;
//
//        public GridViewAdapter(Context context, ArrayList<CollectZi> ziList) {
//            this.context = context;
//            this.ziList = ziList;
//        }
//
//        @Override
//        public int getCount() {
//
//            return ziList.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//
//            return ziList.get(position);
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
//            convertView = mInflater.inflate(R.layout.collect_item, null);
//            TextView tvZi = (TextView) convertView.findViewById(R.id.tvZi);
//            tvZi.setText(ziList.get(position).getZi());
//            return convertView;
//        }
//    }
//
//
//    class GetSearchDreamInfo extends AsyncTask<String, Integer, Integer> {
//
//
//        protected Integer doInBackground(String... params) {
//            int status = 0;
//            ziList = collectAdapter.queryCollectZis(null, null);
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
//                if (ziList != null) {
//                    if (ziList.size() > 0) {
//                        tv01.setText("已经收藏了" + ziList.size() + "个字.");
//                    } else {
//                        tv01.setText("什么也没有耶.");
//                    }
//                } else {
//                    tv01.setText("什么也没有耶.");
//                }
//
//                gridViewAdapter = new GridViewAdapter(mActivity, ziList);
//                gv01.setAdapter(gridViewAdapter);
//
//                gv01.setOnItemClickListener(new OnItemClickListener() {
//
//                    @Override
//                    public void onItemClick(AdapterView<?> arg0, View arg1,
//                                            int position, long arg3) {
//
//                        Intent intent = new Intent(mActivity, ZiDetails.class);
//                        intent.putExtra("word", ziList.get(position).getZi());
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
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        setHasOptionsMenu(true);
//    }
//
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        menu.add("refresh").setIcon(R.drawable.refresh).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
//    }
//
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getTitle().equals("refresh")) {
//            GetSearchDreamInfo info = new GetSearchDreamInfo();
//            info.execute("");
//        }
//        return false;
//    }
//
//}