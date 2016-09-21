package com.zncm.easycycd.modules;

import android.content.Intent;
import android.os.Bundle;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.zncm.easycycd.R;
import com.zncm.easycycd.data.base.CyData;
import com.zncm.utils.L;
import com.zncm.utils.exception.CheckedExceptionHandler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main extends BaseHomeActivity {
    private RuntimeExceptionDao<CyData, Integer> dao = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        L.i("--------------");
        try {
            dao = getHelper().getCyDataDao();
            if (dao.countOf() == 0) {
                initData();
            }
//            initData();
        } catch (Exception e) {
            CheckedExceptionHandler.handleException(e);
        }

        Intent intent = new Intent(Main.this, MainTabsPager.class);
        startActivity(intent);
        finish();
    }


    private void initData() {
        L.i("-------initData-------");
        String str = readFile();
        ArrayList<String> list = new ArrayList<String>();
        String[] temp = str.split("\n");
        for (int i = 0; i < temp.length; i++) {
//            list.add(temp[i]);
            String[] fields = temp[i].split(",");
            CyData data = new CyData();
            data.setName(fields[0]);
            data.setPy(fields[1]);
            data.setJs(fields[2]);
            data.setCc(fields[3]);
            L.i("data>>>>" + data.toString());
            dao.create(data);
        }
//        for (int i = 0; i < list.size(); i++) {
//
//            String[] fields = list.get(i).split(",");
//            CyData data = new CyData();
//            data.setName(fields[0]);
//            data.setPy(fields[1]);
//            data.setJs(fields[2]);
//            data.setCc(fields[3]);
//            L.i("data>>>>" + data.toString());
//            dao.create(data);
//
//
//        }
    }

    public String readFile() {
        L.i("-------readFile-------");
        StringBuilder sb = new StringBuilder();
        try {
            InputStream inputStream = getResources().openRawResource(
                    R.raw.cycd);
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(isr);
            String lineTXT = null;
            while ((lineTXT = reader.readLine()) != null) {
                sb.append(lineTXT);
                sb.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return sb.toString();
    }

}