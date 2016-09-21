package com.zncm.easycycd.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.actionbarsherlock.app.SherlockDialogFragment;
import com.zncm.easycycd.R;

public class XUtil {

    public abstract static class TwoAlertDialogFragment extends SherlockDialogFragment {
        public int icon;
        public String title;
        public String positive;
        public String negative;

        public TwoAlertDialogFragment(String title) {
            this.icon = R.drawable.info;
            this.title = title;
            this.positive = "确定";
            this.negative = "取消";
        }

        public TwoAlertDialogFragment(int icon, String title) {
            this.icon = icon;
            this.title = title;
            this.positive = "确定";
            this.negative = "取消";
        }

        public TwoAlertDialogFragment(int icon, String title, String positive, String negative) {
            this.icon = icon;
            this.title = title;
            this.positive = positive;
            this.negative = negative;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            return new AlertDialog.Builder(getActivity()).setIcon(icon).setTitle(title)
                    .setPositiveButton(positive, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            doPositiveClick();
                        }
                    }).setNegativeButton(negative, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            doNegativeClick();
                        }
                    }).create();
        }

        public abstract void doPositiveClick();

        public abstract void doNegativeClick();

    }


}
