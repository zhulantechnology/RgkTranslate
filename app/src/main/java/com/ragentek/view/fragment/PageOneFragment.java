package com.ragentek.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.ragentek.activity.CountrySelectedActivity;
import com.ragentek.activity.R;
import com.ragentek.view.fragment.base.BaseFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jun.wang on 2018/9/13.
 */

public class PageOneFragment extends BaseFragment implements View.OnClickListener {

    private List<Map<String, Object>> dataList;
    private TextView timeView;
    private TextView dateView;

    private updateBg listerner;
    private Context mContext;
    private TextView currentCounty;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pageone_layout, container, false);
        mContext = getActivity();
        currentCounty = view.findViewById(R.id.country_text_view);
        currentCounty.setText(getCurrentCountryName());
        currentCounty.setOnClickListener(this);
     
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                mContext, initSpnnerData(), R.layout.spinner_item_layout,
                new String[] { "img", "text" }, new int[] {
                R.id.image_view, R.id.text_view });

        timeView = view.findViewById(R.id.time_view);
        dateView = view.findViewById(R.id.date_view);

        getDate();
        new TimeThread().start();

        return view;
    }

    public void setListerner(updateBg listerner) {
        this.listerner = listerner;
    }

    public List<Map<String, Object>> initSpnnerData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0;i < countryNameArray.size();i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", countryIconArray.getResourceId(i, 0));
            map.put("text", countryNameArray.get(i));
            list.add(map);
        }
        return list;
    }

    private void getDate() {
        long time = System.currentTimeMillis();
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        String data = format.format(date);
        String dateStr = data.substring(0, 10);
        String timeStr = data.substring(11);

        dateView.setText(dateStr);
        timeView.setText(timeStr);
    }
    private static final int REQUEST_COUNTRY_CODE = 0x01;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.country_text_view:
                Intent intent = new Intent();
                intent.setClass(getActivity(), CountrySelectedActivity.class);
                startActivityForResult(intent, REQUEST_COUNTRY_CODE);
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_COUNTRY_CODE:
                currentCounty.setText(getCurrentCountryName());
                break;
        }
    }

    class TimeThread extends Thread {
        @Override
        public void run() {
            do {
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = 1;
                    mHandler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    long time = System.currentTimeMillis();
                    Date date = new Date(time);
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                    String data = format.format(date);
                    timeView.setText(data);
                    break;
                default:
                    break;

            }
        }
    };

    public interface updateBg {
        public void changeWallpaper(int index);
    }
}
