//package com.zncm.easycycd.modules;
//
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.text.method.LinkMovementMethod;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import com.actionbarsherlock.app.ActionBar;
//import com.actionbarsherlock.view.Menu;
//import com.actionbarsherlock.view.MenuItem;
//import com.umeng.analytics.MobclickAgent;
//import com.zncm.easycycd.R;
//import com.zncm.easycycd.db.CollectZiDataAdapter;
//import com.zncm.easycycd.db.ZiDataAdapter;
//import com.zncm.easycycd.pojo.CollectZi;
//import com.zncm.easycycd.pojo.Zi;
//
//public class ZiDetails extends BaseHomeActivity {
//    //    Dialog pb_dialog;
//    ZiDataAdapter dda = new ZiDataAdapter(ZiDetails.this);
//    String word = "";
//    TextView tv01, tv02, tv03, tv04, tv05, tv06 = null;
//    Zi zi;
//    boolean is_collected = false;
//    SPUtils spUtils = null;
//
//    private ActionBar actionBar;
//    private ProgressBar pbLoading;
//    private CollectZiDataAdapter collectAdapter;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.zi_details);
//        word = getIntent().getExtras().getString("word");
//
//
//        actionBar = getSupportActionBar();
//
//
//        collectAdapter = new CollectZiDataAdapter(this);
//
//        spUtils = new SPUtils(this);
//
//
////        String[] like_items = null;
////        if (spUtils.getVery_like().length() > 0) {
////            like_items = spUtils.getVery_like().substring(1).split("\\|");
////            for (int i = 0; i < like_items.length; i++) {
////                if (word.equals(like_items[i])) {
////                    is_collected = true;
////                    break;
////                }
////            }
////        }
////        if (is_collected) {
////            t_iv02.setImageResource(R.drawable.contact_star);
////        } else {
////            t_iv02.setImageResource(R.drawable.star_empty);
////        }
////
////        t_iv02.setOnClickListener(new OnClickListener() {
////            @Override
////            public void onClick(View v) {
//
////            }
////        });
//        tv01 = (TextView) findViewById(R.id.zi_details_tv01);
//        tv02 = (TextView) findViewById(R.id.zi_details_tv02);
//        tv03 = (TextView) findViewById(R.id.zi_details_tv03);
//        tv04 = (TextView) findViewById(R.id.zi_details_tv04);
//        tv05 = (TextView) findViewById(R.id.zi_details_tv05);
//        tv06 = (TextView) findViewById(R.id.zi_details_tv06);
//
//        pbLoading = (ProgressBar) findViewById(R.id.pbLoading);
////        ShowDialog();
//        pbLoading.setVisibility(View.VISIBLE);
//        GetZiData getZiData = new GetZiData();
//        getZiData.execute("");
//
//    }
//
//    class GetZiData extends AsyncTask<String, Integer, Integer> {
//        protected Integer doInBackground(String... params) {
//            int status = 0;
//            zi = dda.QueryZi(word);
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
//            pbLoading.setVisibility(View.GONE);
////            pb_dialog.dismiss();
//
//
//            tv01.setText(zi.getZi());
//            actionBar.setTitle(zi.getZi());
//            tv02.setText("拼音:" + zi.getPy2());
//            tv03.setText("笔画:" + zi.getBh());
//            tv04.setText("部首:" + zi.getBs());
//            tv05.setText("网络释义");
//            tv05.setMovementMethod(LinkMovementMethod.getInstance());
//            tv06.setText(zi.getJs());
//
//            tv05.setOnClickListener(new OnClickListener() {
//
//                @Override
//                public void onClick(View arg0) {
//
//                    if (CommonUtils.isNetworkAvailable(ZiDetails.this)) {
//
//                        Intent intent = new Intent(ZiDetails.this,
//                                BikeWebView.class);
//                        intent.putExtra("word", zi.getZi());
//                        startActivity(intent);
//                    } else {
//                        CommonUtils.showText(ZiDetails.this, "请检查网络连接.");
//                    }
//
//                }
//            });
//
//        }
//    }
//
////    private void ShowDialog() {
////        LayoutInflater mInflater = LayoutInflater.from(ZiDetails.this);
////        final View view = mInflater.inflate(R.layout.loading, null);
////        pb_dialog = new AlertDialog.Builder(ZiDetails.this).setView(view)
////                .create();
////        pb_dialog.show();
////    }
//
//    public void onResume() {
//        super.onResume();
//        MobclickAgent.onResume(this);
//    }
//
//    public void onPause() {
//        super.onPause();
//        MobclickAgent.onPause(this);
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        is_collected = collectAdapter.bZiCollected(word);
//
//        if (is_collected) {
//            menu.add("collecte").setIcon(R.drawable.star_full).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
//        } else {
//            menu.add("collecte").setIcon(R.drawable.star_empty).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
//        }
//
//
//        return true;
//    }
//
//
//    public boolean onOptionsItemSelected(MenuItem item) {
//        super.onOptionsItemSelected(item);
//
//
//        if (item.getTitle().equals("collecte")) {
//            if (is_collected) {
//                collectAdapter.deleteCollectZiById(zi.getId());
////                spUtils.setVery_like(spUtils.getVery_like().replace(
////                        "|" + word, ""));
////                t_iv02.setImageResource(R.drawable.star_empty);
//                item.setIcon(R.drawable.star_empty);
//                is_collected = false;
//            } else {
//                CollectZi collectZi = new CollectZi(zi.getId(), zi.getZi());
//                collectAdapter.insertCollectZi(collectZi);
////                spUtils.setVery_like(spUtils.getVery_like() + "|" + word);
////                t_iv02.setImageResource(R.drawable.contact_star);
//                item.setIcon(R.drawable.star_full);
//                is_collected = true;
//            }
//        }
//
//        return false;
//    }
//
//}