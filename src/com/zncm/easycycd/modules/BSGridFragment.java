//package com.zncm.easycycd.modules;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.BaseAdapter;
//import android.widget.GridView;
//import android.widget.TextView;
//
//import com.actionbarsherlock.app.SherlockFragment;
//import com.zncm.easycycd.R;
//
//public class BSGridFragment extends SherlockFragment {
//    String[] bu_shou_items_text = null;
//    GridView gridView = null;
//    GridAdapter gridAdapter = null;
//    private View view;
//    private Activity mActivity;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        mActivity = getActivity();
//        view = inflater.inflate(R.layout.grid, null);
//        bu_shou_items_text = getResources().getStringArray(
//                R.array.bu_shou_items);
//        gridView = (GridView) view.findViewById(R.id.grid_gv01);
//        gridAdapter = new GridAdapter(mActivity);
//        gridView.setAdapter(gridAdapter);
//        gridView.setOnItemClickListener(new OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1,
//                                    int position, long arg3) {
//                Intent intent = new Intent(mActivity, Details.class);
//                intent.putExtra("type", "bs");
//                intent.putExtra("key", bu_shou_items_text[position]);
//                startActivity(intent);
//            }
//        });
//        return view;
//    }
//
//    class GridAdapter extends BaseAdapter {
//        Context context = null;
//
//        public GridAdapter(Context context) {
//            this.context = context;
//        }
//
//        @Override
//        public int getCount() {
//
//            return bu_shou_items_text.length;
//        }
//
//        @Override
//        public Object getItem(int position) {
//
//            return bu_shou_items_text[position];
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
//            TextView textView = new TextView(context);
//            textView.setText(bu_shou_items_text[position]);
//            textView.setTextColor(Color.BLACK);
//            textView.setTextSize(20);
//            textView.setGravity(Gravity.CENTER);
//            textView.setBackgroundResource(R.drawable.bg_texture);
//            return textView;
//        }
//
//    }
//
//
//}