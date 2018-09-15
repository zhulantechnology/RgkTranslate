package com.ragentek.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ragentek.activity.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jun.wang on 2018/9/13.
 */

public class PageOneFragment extends Fragment {
    private GridView firstGridView;
    private List<Map<String, Object>> dataList;
    private SimpleAdapter adapter;
    private TextView timeView;
    private TextView dateView;
    private Spinner countrySpinner;

    private updateBg listerner;



    public void setListerner(updateBg listerner) {
        this.listerner = listerner;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pageone_layout, container, false);
        firstGridView = (GridView) view.findViewById(R.id.first_grid_view);
        timeView = view.findViewById(R.id.time_view);
        dateView = view.findViewById(R.id.date_view);

        countrySpinner = view.findViewById(R.id.country_spinner_view);
        // 声明一个ArrayAdapter用于存放简单数据
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_item, getSpinnerData());
        countrySpinner.setAdapter(spinnerAdapter);
        countrySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // 在选中之后触发
                Toast.makeText(getActivity(),
                        parent.getItemAtPosition(position).toString(),
                        Toast.LENGTH_SHORT).show();
                listerner.changeWallpaper(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // 这个一直没有触发，我也不知道什么时候被触发。
                //在官方的文档上说明，为back的时候触发，但是无效，可能需要特定的场景
            }
        });

        //初始化数据
        initData();
        getDate();

        String[] from={"img","text"};
        int[] to={R.id.img,R.id.text};
        adapter=new SimpleAdapter(getActivity(), dataList, R.layout.gridview_item, from, to);
        firstGridView.setAdapter(adapter);

        new TimeThread().start();

        return view;
    }
    private List<String> getSpinnerData() {
        // 数据源
        List<String> dataList = new ArrayList<String>();
        dataList.add("北京");
        dataList.add("上海");
        dataList.add("南京");
        dataList.add("宜昌");
        return dataList;
    }

    private void getDate() {
        long time = System.currentTimeMillis();
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        String data = format.format(date);
        String dateStr = data.substring(0,10);
        String timeStr = data.substring(11);
        
        dateView.setText(dateStr);
        timeView.setText(timeStr);
    }

    void initData() {
        //图标
        int icno[] = { R.drawable.fontmanager, R.drawable.messages };
        //图标下的文字
        String name[]={
                getContext().getResources().getString(R.string.voice_translate),
                getContext().getResources().getString(R.string.walkie_talkie)
                };
        dataList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i <icno.length; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("img", icno[i]);
            map.put("text",name[i]);
            dataList.add(map);
        }
    }

    class TimeThread extends Thread {
        @Override
        public void run() {
            do {
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = 1;  //消息(一个整型值)
                    mHandler.sendMessage(msg);// 每隔1秒发送一个msg给mHandler
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
                   // SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 EEEE");
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                    String data = format.format(date);
                    timeView.setText(data); //更新时间
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
