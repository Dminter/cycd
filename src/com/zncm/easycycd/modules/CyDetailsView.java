package com.zncm.easycycd.modules;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zncm.easycycd.R;

public class CyDetailsView extends RelativeLayout {
    private LayoutInflater mInflater;
    private LinearLayout llView;
    private TextView tvPage;
    private TextView tvName;
    private TextView tvPy;
    private TextView tvJs;
    private TextView tvCc;

    public CyDetailsView(Context context) {
        this(context, null);
    }

    public CyDetailsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        llView = (LinearLayout) mInflater.inflate(R.layout.view_cy_details, null);
        llView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        addView(llView);
        tvPage = (TextView) llView.findViewById(R.id.tvPage);
        tvName = (TextView) llView.findViewById(R.id.tvName);
        tvPy = (TextView) llView.findViewById(R.id.tvPy);
        tvJs = (TextView) llView.findViewById(R.id.tvJs);
        tvCc = (TextView) llView.findViewById(R.id.tvCc);
    }

    public TextView getTvPy() {
        return tvPy;
    }

    public TextView getTvName() {
        return tvName;
    }


    public TextView getTvPage() {
        return tvPage;
    }

    public TextView getTvJs() {
        return tvJs;
    }

    public TextView getTvCc() {
        return tvCc;
    }
}
